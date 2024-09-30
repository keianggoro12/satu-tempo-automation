package step_definitions;

import java.time.Duration;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObject.RandomDataGenerator;
import org.example.pageObject.SatuTempoArticlePage;
import org.example.pageObject.SatuTempoHomePage;
import org.example.pageObject.SatuTempoLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Assert;

public class SatuTempoHomePageSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private RandomDataGenerator randomDataGenerator;
    private SatuTempoLoginPage satuTempoLoginPage;
    private SatuTempoHomePage satuTempoHomePage;
    private SatuTempoArticlePage satuTempoArticlePage;

    public SatuTempoHomePageSteps() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        

        // Inisialisasi page objects dengan driver yang benar
        this.satuTempoLoginPage = new SatuTempoLoginPage(driver);
        this.satuTempoHomePage = new SatuTempoHomePage(driver);
        this.satuTempoArticlePage = new SatuTempoArticlePage(driver);

        // Inisialisasi random data generator
        this.randomDataGenerator = new RandomDataGenerator();
    }

    SatuTempoLoginSteps loginSteps = new SatuTempoLoginSteps();

    @When("User click on main logo")
    public void userClickOnMainLogo() {
        satuTempoHomePage.clickMainLogo();
        Assert.assertTrue(satuTempoHomePage.verifyMainLogo());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Then("Verify header tab links")
    public void verifyHeaderTabLinks() {
        satuTempoHomePage.verifyHeaderLinksExist();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Then("Verify langganan button")
    public void verifyLanggananNavigation() {
        satuTempoHomePage.verifyLanggananButtonNavigation();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.navigate().back();
        loginSteps.verifyHomePage();   
    }

    @Then("Verify Masuk button")
    public void verifyMasukButton() {
        satuTempoHomePage.verifyLoginButtonNavigation();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Then("User go to Akun sidebar")
    public void userGoToAkunSidebar() {
        satuTempoHomePage.clickLoggedInUser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Then("User click on logout button")
    public void userClickOnLogoutButton() {
        satuTempoHomePage.clickLogoutButton();
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(satuTempoHomePage.getUnLoggedInUserElement()));

        if (!satuTempoHomePage.verifyUnloggedInHomepage()) {
            throw new AssertionError("Pengguna tidak berhasil masuk ke halaman.");
        } else {
            System.out.println("Pengguna berhasil login dan halaman terverifikasi.");
        }
    }

    @When("User click on menu")
    public void userClickOnMenu() {
        satuTempoHomePage.clickMenuButton();

        wait.until(ExpectedConditions.visibilityOf(satuTempoHomePage.getMenuContainer()));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", satuTempoHomePage.getMenuContainer());

        Assert.assertTrue(satuTempoHomePage.isMenuContainerDisplayed());
    }

    @When("User click x button menu")
    public void userClickXButtonMenu() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement xButton = satuTempoHomePage.getXButton();
        js.executeScript("arguments[0].click();", xButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(satuTempoHomePage.getMenuContainer()));

        boolean isMenuContainerPresent = !driver
                .findElements(By.xpath("//div[contains(@id, 'radix-vue-dialog-content-') and @role='dialog']"))
                .isEmpty();
        Assert.assertFalse("Menu container should be removed from the DOM", isMenuContainerPresent);
    }

    @When("User clicked random article")
    public void userClickedRandomArticle() {
        satuTempoHomePage.clickRandomArticle();
        wait.until(ExpectedConditions.visibilityOf(satuTempoArticlePage.getArticleTitle()));

    }

    @When("User verify Title and Sub-title and post date Article")
    public void userVerifyTitleSubitlePostDateArticle() {
        boolean isTitleDisplayed = satuTempoArticlePage.getArticleTitle().isDisplayed();
        Assert.assertTrue("Title should be displayed", isTitleDisplayed);

        boolean isSubtitleDisplayed = satuTempoArticlePage.getArticleSubtitle().isDisplayed();
        Assert.assertTrue("Subtitle should be displayed", isSubtitleDisplayed);

        boolean isPostDateDisplayed = satuTempoArticlePage.getArticlePostDate().isDisplayed();
        Assert.assertTrue("Post date should be displayed", isPostDateDisplayed);
    }

    @Then("User verifies the article content and image")
    public void userVerifiesArticleContentAndImage() {
        WebElement articleImage = satuTempoArticlePage.getArticleImage();
        wait.until(ExpectedConditions.visibilityOf(articleImage));
        Assert.assertTrue("Article image should be displayed", articleImage.isDisplayed());

        List<WebElement> articleContent = satuTempoArticlePage.getArticleContent();
        wait.until(ExpectedConditions.visibilityOfAllElements(articleContent));
        Assert.assertFalse("Article content should not be empty", articleContent.isEmpty());

        for (WebElement paragraph : articleContent) {
            Assert.assertTrue("Each paragraph of article content should be displayed", paragraph.isDisplayed());
        }
    }

}
