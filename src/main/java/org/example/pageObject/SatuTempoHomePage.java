package org.example.pageObject;

import java.util.List;
import java.util.Random;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class SatuTempoHomePage {
    public static WebDriver webDriver;

    public SatuTempoHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    // HOMEGAPE
    @FindBy(xpath = "//button[contains(text(), 'Masuk')]")
    private WebElement loginButton;
    public boolean verifyUnloggedInHomepage() {
        return loginButton.isDisplayed();
    }
    public WebElement getUnLoggedInUserElement() {
        try {
            if (loginButton.isDisplayed()) {
                return loginButton;
            } else {
                System.out.println("user masih login");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Elemen loggedInUser tidak ditemukan: " + e.getMessage());
            return null;
        }
    }
    public void setLoginButton() {
        loginButton.click();
    }

    public void verifyLoginButtonNavigation() {
        String currentUrl = webDriver.getCurrentUrl();
        setLoginButton();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("subscribe.tempo.co/sso/auth/initial")); // Menunggu hingga URL
        String newUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://subscribe.tempo.co/sso/auth/initial?ref=https://satu.tempo.co/";
        if (!newUrl.equals(expectedUrl)) {
            throw new AssertionError("Tautan tidak menuju ke URL yang diharapkan. URL saat ini: " + newUrl);
        } else {
            System.out.println("Tautan menuju ke URL yang benar: " + newUrl);
        }
    }

    @FindBy(xpath = "//span[contains(@class, 'inline-flex') and @type='button' and @aria-haspopup='dialog']")
    private WebElement loggedInUser;
    public boolean verifyLoggedInHomepage() {
        return loggedInUser.isDisplayed();
    }
    public void clickLoggedInUser() {
        loggedInUser.click();
    }
    public WebElement getLoggedInUserElement() {
        try {
            if (loggedInUser.isDisplayed()) {
                return loggedInUser;
            } else {
                System.out.println("Elemen loggedInUser tidak ditampilkan.");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Elemen loggedInUser tidak ditemukan: " + e.getMessage());
            return null;
        }
    }

    @FindBy(xpath = "//img[@src='/_ipx/w_272&f_webp/img/logo-tempo.svg']")
    private WebElement mainLogo;
    public boolean verifyMainLogo() {
        return mainLogo.isDisplayed();
    }
    public void clickMainLogo() {
        mainLogo.click();
    }

    @FindBy(xpath = "//nav[@aria-label='Main']//a")
    private List<WebElement> headerTabLinks;
    public void verifyHeaderLinksExist() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(headerTabLinks));
        String baseUrl = "https://satu.tempo.co";
        String[] expectedLinks = { "/fokus", "/mingguan", "/plus" };
        for (String expectedLink : expectedLinks) {
            String fullLink = baseUrl + expectedLink;
            boolean linkFound = false;
            for (WebElement link : headerTabLinks) {
                String hrefValue = link.getAttribute("href");
                System.out.println("Ditemukan link: " + hrefValue); // Debugging output
                if (hrefValue != null && hrefValue.equals(fullLink)) {
                    linkFound = true;
                    break; // Keluar dari loop jika tautan ditemukan
                }
            }

            // Jika tautan tidak ditemukan, lempar error
            if (!linkFound) {
                throw new AssertionError("Tautan tidak ditemukan: '" + fullLink + "'");
            }
        }
    }

    @FindBy(xpath = "(//button[contains(@class, 'bg-primary-main') and text()=' Langganan '])[1]")
    private WebElement langgananButton;
    public void verifyLanggananButtonNavigation() {
        String currentUrl = webDriver.getCurrentUrl();
        langgananButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("subscribe.tempo.co"));

        String newUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://subscribe.tempo.co/subscriptions/subscriptions/new?ref=https://satu.tempo.co/";
        if (!newUrl.equals(expectedUrl)) {
            throw new AssertionError("Tautan tidak menuju ke URL yang diharapkan. URL saat ini: " + newUrl);
        } else {
            System.out.println("Tautan menuju ke URL yang benar: " + newUrl);
        }
    }

    // MENU SIDEBAR

    @FindBy(xpath = "//button[contains(@class, 'items-center') and contains(., 'Menu')]")
    private WebElement menuButton;

    @FindBy(xpath = "//div[contains(@id, 'radix-vue-dialog-content-') and @role='dialog']")
    private WebElement menuContainer;

    public void clickMenuButton() {
        menuButton.click();
    }

    public boolean isMenuContainerDisplayed() {
        return menuContainer.isDisplayed();
    }

    public WebElement getMenuContainer() {
        return menuContainer;
    }

    @FindBy(xpath = "//button[contains(@class, 'absolute right-4 top-4 rounded-sm')]")
    private WebElement xButton;

    public void clickXButton() {
        xButton.click();
    }

    public WebElement getXButton() {
        return xButton;
    }



    // AKUN SIDEBAR

    @FindBy(xpath = "//a[span[text()='Keluar']]")
    private WebElement logoutButton;

    public void clickLogoutButton() {
        logoutButton.click();

    }

    // ARTICLE 
    @FindBy(xpath = "//div[@class='w-full lg:w-[736px]']")
    private WebElement articleContainer;

    // Method to click a random article
    public void clickRandomArticle() {
        // Locate all <a> elements within the article container
        List<WebElement> articles = articleContainer.findElements(By.tagName("a"));

        // Generate a random index to click
        Random random = new Random();
        int randomIndex = random.nextInt(articles.size());

        // Click the random article
        WebElement randomArticle = articles.get(randomIndex);
        randomArticle.click();
    }

}
