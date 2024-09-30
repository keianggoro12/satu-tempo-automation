package org.example.pageObject;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

public class SatuTempoArticlePage {
    public static WebDriver webDriver;

    public SatuTempoArticlePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    // ARTICLE PAGE
    @FindBy(xpath = "//h1[contains(@class, 'text-[26px]')]")
    private WebElement articleTitle;
    public WebElement getArticleTitle() {
        return articleTitle;
    }

    @FindBy(xpath = "//p[contains(@class, 'font-roboserif')]")
    private WebElement articleSubtitle;
    public WebElement getArticleSubtitle() {
        return articleSubtitle;
    }

    @FindBy(xpath = "//p[contains(@class, 'text-neutral-900 text-sm')]")
    private WebElement articlePostDate;
    public WebElement getArticlePostDate() {
        return articlePostDate;
    }

    @FindBy(xpath = "//div[@id='feature_image']//img")
    private WebElement articleImage;
    public WebElement getArticleImage() {
        return articleImage;
    }

    @FindBy(xpath = "//div[@id='content-wrapper']//p")
    private List<WebElement> articleContent;
    public List<WebElement> getArticleContent() {
        return articleContent;
    }

}
