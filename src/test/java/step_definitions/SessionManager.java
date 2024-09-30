package step_definitions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import java.util.HashSet;
import java.util.Set;

public class SessionManager {
    private static Set<Cookie> savedCookies = new HashSet<>(); // Shared cookies across tests
    private WebDriver driver;

    public SessionManager(WebDriver driver) {
        this.driver = driver;
    }

    public void saveCookies() {
        savedCookies = driver.manage().getCookies(); // Save cookies after login
    }

    public void loadCookies() {
        if (savedCookies != null && !savedCookies.isEmpty()) {
            for (Cookie cookie : savedCookies) {
                driver.manage().addCookie(cookie); // Load saved cookies
            }
            driver.navigate().refresh(); // Refresh to apply cookies
        }
    }

    public boolean hasSavedCookies() {
        return savedCookies != null && !savedCookies.isEmpty();
    }
}