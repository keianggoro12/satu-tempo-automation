package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Cookie;
import java.util.List;
import java.util.ArrayList;

public class Hooks {
    public static WebDriver driver;
    public static List<Cookie> savedCookies = new ArrayList<>(); // Menyimpan cookies

    @Before
    public void openBrowser() {
        // Setup WebDriver dengan ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        
        // Tambahkan argumen untuk mengizinkan remote origins
        co.addArguments("--remote-allow-origins=*");

        // Tambahkan argumen User-Agent
        co.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");

        // Inisialisasi WebDriver dengan opsi
        driver = new ChromeDriver(co);

        // Akses URL aplikasi
        String appUrl = "https://satu.tempo.co/";
        driver.get(appUrl); // Buka link HTML
        driver.manage().window().maximize(); // Maksimalkan browser
    }

    @After(order = 0)
    public void closeBrowser() {
        saveCookies(); // Simpan cookies sebelum browser ditutup
        driver.quit(); // Tutup browser
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }

    public static void saveCookies() {
        savedCookies.clear(); // Hapus cookies yang sudah ada
        savedCookies.addAll(driver.manage().getCookies()); // Simpan cookies sesi saat ini
    }

    // Metode untuk memuat cookies
    public static void loadCookies() {
        for (Cookie cookie : savedCookies) {
            driver.manage().addCookie(cookie); // Tambahkan semua cookies yang disimpan
        }
    }
}