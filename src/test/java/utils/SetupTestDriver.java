package utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetupTestDriver {
    private WebDriver driver;
    private String browser;
    private String baseUrl;
    private String os;
    private String node;

    public SetupTestDriver(String os, String browser, String baseUrl, String node) {
        this.browser = browser;
        this.os = os;
        this.baseUrl = baseUrl;
        this.node = node;

        Platform platform = Platform.fromString(os.toUpperCase());
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("platform", platform);
            try {
                this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), chromeOptions);
            } catch (MalformedURLException e) {
                System.out.println("Error in creating chrome remote instance \n");
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("platform", platform);
            try {
                this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), firefoxOptions);
            } catch (MalformedURLException e) {
                System.out.println("Error in creating firefox remote instance \n");
                e.printStackTrace();
            }
        }
        else {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.setCapability("platform", platform);
            try {
                this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), ieOptions);
            } catch (MalformedURLException e) {
                System.out.println("Error in creating IE remote instance \n");
                e.printStackTrace();
            }
        }

        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.driver.get(baseUrl);
    }

    public String getOs() {
        return this.os;
    }

    public String getBrowser() {
        return this.browser;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getNode() {
        return this.node;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
