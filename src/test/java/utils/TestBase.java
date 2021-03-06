package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        launchAppURL();
        driver = getDriver();
    }
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    //method to get page title
    public String getPageTitle(){
        return driver.getTitle();
    }

    //method to get redirected URL
    public String getRedirectedURL(){ return  driver.getCurrentUrl();}

    //method to kick off the browser defined in Property file
    public static void startBrowser()
    {
        FileUtilLibrary obj = new FileUtilLibrary();
        //read the browser name from property file
        String strBrowser = obj.readProperty("browser");
        String strDriverPath = obj.readProperty("driverPath");
        if(strBrowser.equals("Chrome"))
        {
            //pass the driver path
            File driverFile = new File(strDriverPath +"/chromedriver.exe");
            try {
                System.setProperty("webdriver.chrome.driver", driverFile.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Error in getting chrome driver canonical path \n");
                e.printStackTrace();
            }
            ChromeOptions opts = new ChromeOptions();
            opts.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(opts);
        }
        if(strBrowser.equals("Firefox"))
        {
            File driverFile = new File(strDriverPath +"/geckodriver.exe");
            try {
                System.setProperty("webdriver.firefox.driver", driverFile.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Error in getting firefox driver canonical path \n");
                e.printStackTrace();
            }
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    //Method to launch the application
    public static void launchAppURL() {
        FileUtilLibrary obj = new FileUtilLibrary();
        startBrowser();
        driver.get(obj.readProperty("url"));
    }
}
