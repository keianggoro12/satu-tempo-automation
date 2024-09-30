package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObject.RandomDataGenerator;
import org.example.pageObject.SatuTempoHomePage;
import org.example.pageObject.SatuTempoLoginPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Cookie;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class SatuTempoLoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private RandomDataGenerator randomDataGenerator;
    private Set<Cookie> savedCookies = new HashSet<>();
    private SatuTempoLoginPage satuTempoLoginPage;
    private SatuTempoHomePage satuTempoHomePage;
    

    public SatuTempoLoginSteps() {
        // Inisialisasi driver dari Hooks
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Optional: Buat wait untuk driver

        // Inisialisasi page objects dengan driver yang benar
        this.satuTempoLoginPage = new SatuTempoLoginPage(driver);
        this.satuTempoHomePage = new SatuTempoHomePage(driver);

        // Inisialisasi random data generator
        this.randomDataGenerator = new RandomDataGenerator();
    }

    @Given("User already on homepage")
    public void verifyHomePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    
        // Tunggu hingga elemen loggedInUser muncul di halaman tujuan
        wait.until(ExpectedConditions.visibilityOf(satuTempoHomePage.getUnLoggedInUserElement()));
        
        // Verifikasi bahwa pengguna berhasil masuk
        if (!satuTempoHomePage.verifyUnloggedInHomepage()) {
            throw new AssertionError("Pengguna tidak berhasil masuk ke halaman.");
        } else {
            System.out.println("Pengguna berhasil login dan halaman terverifikasi.");
        }
    }

    @When("User click to login page")
    public void clickButtonSignin() {satuTempoHomePage.setLoginButton();
    }

    @Then("User already on login page")
    public void userAlreadyOnLoginPage() {
        Assert.assertTrue(satuTempoLoginPage.verifyLoginPage());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @And("User input registered {string} as email and continue")
    public void userInputAsEmail(String email) {
        satuTempoLoginPage.inputFieldEmail(email);
        satuTempoLoginPage.setLanjutkanButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @And("User input {string} as password")
    public void userInputAsPassword(String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        satuTempoLoginPage.inputFieldPassword(password);
    }

    @And("User verified captcha and login")
public void userVerifiedCaptchaAndLogin() {
    // Tunggu hingga reCAPTCHA selesai
    satuTempoLoginPage.setReCHAPTCHA();

    // Klik tombol "Masuk"
    satuTempoLoginPage.setMasukButton();

    // Tunggu hingga elemen yang menunjukkan pengguna berhasil login muncul
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    
    // Tunggu hingga elemen loggedInUser muncul di halaman tujuan
    wait.until(ExpectedConditions.visibilityOf(satuTempoHomePage.getLoggedInUserElement()));
    
    // Verifikasi bahwa pengguna berhasil masuk
    if (!satuTempoHomePage.verifyLoggedInHomepage()) {
        throw new AssertionError("Pengguna tidak berhasil masuk ke halaman.");
    } else {
        System.out.println("Pengguna berhasil login dan halaman terverifikasi.");
    }

    // Setelah login berhasil, simpan cookies
    Hooks.saveCookies(); // Pastikan memanggil metode statis
}

@And("User already on logged in homepage")
public void userAlreadyOnLoggedInHomepage() {
    // Jika cookies sudah ada, maka langsung load cookies
    if (Hooks.savedCookies != null && !Hooks.savedCookies.isEmpty()) {
        Hooks.loadCookies(); // Memanggil loadCookies() dari kelas Hooks
        driver.navigate().refresh(); // Refresh untuk mengaplikasikan cookies
    } else {
        Assert.fail("No cookies found, user needs to login first.");
    }

    // Set implicit wait time untuk step-step berikutnya
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    
    // Verifikasi bahwa user sudah ada di homepage yang sudah login
    Assert.assertTrue(satuTempoHomePage.verifyLoggedInHomepage());
}
    

    @When("User input unregistered email and continue")
    public void userInputUnregisteredEmailAndContinue() {
        String unregisteredEmail = "keirinne12@gmail.com";
        satuTempoLoginPage.inputFieldEmail(unregisteredEmail);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // satuTempoLoginPage.inputFieldEmailRandom();

        // Wait until the Lanjutkan button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(satuTempoLoginPage.getLanjutkanButton()));

        satuTempoLoginPage.setLanjutkanButton(); // Now click the button

        // Wait until the birthday date picker is displayed and verified
        wait.until(ExpectedConditions.visibilityOf(satuTempoLoginPage.getBirthdayDatePicker()));

        // Now verify the birthday date picker and input the date
        Assert.assertTrue(satuTempoLoginPage.verifyBirthdayDatePicker());
    }

    @And("User input birthday date")
    public void userInputBirthdayDate() {
        String birthdayDate = "13062000"; // Hardcoded birthday date
        satuTempoLoginPage.inputBirthdayDate(birthdayDate);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        satuTempoLoginPage.clickFieldPassword();
    }

    @And("User input password and confirm password")
    public void userInputPasswordAndConfirmPassword() {
        satuTempoLoginPage.clickFieldPassword();

        String randomPassword = randomDataGenerator.generateRandomPassword();
        satuTempoLoginPage.inputFieldPassword(randomPassword);
        satuTempoLoginPage.inputFieldConfirmPassword(randomPassword);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @And("User checklist agreement and register")
    public void userChecklistAgreementAndRegister() {
        satuTempoLoginPage.checkAgreement();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }

    @When("User input invalid email and continue")
    public void userInputInvalidEmailAndContinue() {
        satuTempoLoginPage.inputFieldEmailRandom();

        // Wait until the Lanjutkan button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(satuTempoLoginPage.getLanjutkanButton()));

        satuTempoLoginPage.setLanjutkanButton(); // Now click the button

        // Wait until the birthday date picker is displayed and verified
        wait.until(ExpectedConditions.visibilityOf(satuTempoLoginPage.getEmailInvalid()));

        // Now verify the birthday date picker and input the date
        Assert.assertTrue(satuTempoLoginPage.verifyEmailInvalid());
    }

    @Then("User verify message email is invalid")
    public void userVerifyMessageEmailIsInvalid() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // Wait until the birthday date picker is displayed and verified
        wait.until(ExpectedConditions.visibilityOf(satuTempoLoginPage.getEmailInvalid()));

    }
}

