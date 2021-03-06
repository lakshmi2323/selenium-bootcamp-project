package testrunner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import utils.SetupTestDriver;
import utils.TestBase;

public class AmazonTestClass1 extends TestBase {

    //used this class for parallel testing with selenium grid
    public WebDriver driver;
    String expectedURL = "https://www.amazon.in/";

    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser", "url", "node"})
    public void setUp(String os, String browser, String url, String node) {
        SetupTestDriver setupTestDriver = new SetupTestDriver(os, browser, url, node);
        driver = setupTestDriver.getDriver();
    }

    @Test
    public void urlValidation(){
        //check if url is redirected to https://www.amazon.in
        String actualRedirectURL = getRedirectedURL();
        Assert.assertEquals(actualRedirectURL,expectedURL);
    }

    @Test
    public void pageTitleValidation() {
        HomePage homePage = new HomePage(driver);
        //validate page title
        Assert.assertEquals(getPageTitle(), homePage.pageTitle);
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

}
