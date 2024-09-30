package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SatuTempoLoginPage {

    public static WebDriver webDriver;
    private RandomDataGenerator randomDataGenerator;

//   public WebDriverWait wait;

    public SatuTempoLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
        randomDataGenerator = new RandomDataGenerator(); // Initialize it in the constructor
        this.webDriver = driver; // Assign the driver to the class variable
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    // LOGIN PAGE
    @FindBy(xpath = "//input[@id='inputNewEmailModal']")
    private WebElement fieldEmail;

    public void inputFieldEmail(String email) {
        fieldEmail.sendKeys(email);
    }

    public void inputFieldEmailRandom() {
        String randomEmail = randomDataGenerator.generateRandomEmail(); // Generate random email
        fieldEmail.sendKeys(randomEmail); // Input the random email
    }


    @FindBy(xpath = "//button[@id='newBtnSubmitModal']")
    private WebElement lanjutkanButton;
    public WebElement getLanjutkanButton() {
        return lanjutkanButton;
    }
    public void setLanjutkanButton() {
        lanjutkanButton.click();

    }
    public boolean verifyLoginPage() {
        return lanjutkanButton.isDisplayed();
    }

    @FindBy(xpath = "//input[@id='inputBirthdayModal']")
    private WebElement tanggallahirPicker;
    public WebElement getBirthdayDatePicker() {
        return tanggallahirPicker;
    }
    public void inputBirthdayDate(String birthdaydate){
        tanggallahirPicker.sendKeys(birthdaydate);
    }
    public boolean verifyBirthdayDatePicker(){
        return tanggallahirPicker.isDisplayed();
    }

    @FindBy(xpath = "//input[@id='inputNewPasswordModal']")
    private WebElement fieldPassword;
    public void clickFieldPassword(){fieldPassword.click();}
    @FindBy(xpath = "//input[@id='inputNewPasswordConfirmationModal']")
    private WebElement fieldConfirmPassword;

    public void inputFieldPassword(String password) {
        fieldPassword.sendKeys(password); // Input password ke field password
    }

    public void inputFieldConfirmPassword(String password) {
        fieldConfirmPassword.sendKeys(password); // Input password ke field confirm password
    }

    public void inputFieldPasswordRandom() {
        String randomPassword = randomDataGenerator.generateRandomPassword(); // Generate random password
        inputFieldPassword(randomPassword); // Input random password ke field password
        inputFieldConfirmPassword(randomPassword); // Input random password ke field confirm password
    }

    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    private WebElement reCHAPTCHA;

    @FindBy(xpath = "//button[@id='newBtnLoginModal']")
    private WebElement masukButton;

    public void setReCHAPTCHA() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    
        try {
            // Tunggu hingga elemen reCAPTCHA terlihat
            wait.until(ExpectedConditions.visibilityOf(reCHAPTCHA));
    
            // Klik reCAPTCHA
            reCHAPTCHA.click();
            System.out.println("Silakan selesaikan reCAPTCHA secara manual.");
    
            // Tunggu hingga checkbox yang tercentang muncul dengan batas waktu yang lebih cepat
            wait.withTimeout(Duration.ofMinutes(2)); // Batas waktu maksimum 2 menit
            boolean checkmarkFound = false;
            long startTime = System.currentTimeMillis();
            
            while (!checkmarkFound && (System.currentTimeMillis() - startTime) < Duration.ofMinutes(2).toMillis()) {
                try {
                    WebElement checkmarkElement = webDriver.findElement(By.xpath("//div[@class='recaptcha-checkbox-checkmark']"));
                    if (checkmarkElement.isDisplayed()) {
                        checkmarkFound = true; // Checkbox sudah tercentang
                        System.out.println("reCAPTCHA telah berhasil diselesaikan.");
                    }
                } catch (NoSuchElementException e) {
                    Thread.sleep(1000); // Tunggu 1 detik sebelum mencoba lagi (lebih cepat dari sebelumnya)
                }
            }
            
            if (!checkmarkFound) {
                System.out.println("Timeout: reCAPTCHA tidak diselesaikan dalam waktu yang ditentukan.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setMasukButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(masukButton));
        masukButton.click();
    }


    @FindBy (xpath = "//input[@class='form-check-input checkbox1']")
    private WebElement tickAgreement;
    public void checkAgreement(){
        tickAgreement.click();
    }

    @FindBy(xpath = "//button[@id='newBtnRegisterModal']")
    private WebElement buttonRegister;
    public void clickRegister(){
        buttonRegister.click();
    }

    @FindBy(css = ".email-warning")
    private WebElement emailInvalid;
    public boolean verifyEmailInvalid() {
        return emailInvalid.isDisplayed();
    }
    public WebElement getEmailInvalid(){
        return emailInvalid;
    }





}