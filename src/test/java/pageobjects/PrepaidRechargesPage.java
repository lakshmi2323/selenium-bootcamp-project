package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PrepaidRechargesPage {

    WebDriver driver;
    Navigation navigation;
    public PrepaidRechargesPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        navigation = new Navigation(driver);
    }

    //@FindBy(how=How.CSS,using=".a-link-normal > img")
    @FindBy(how=How.XPATH,using="//img[@alt='Amazon Pay']")
    public WebElement image_AmazonPay;

    //method to navigate from prepaid recharges to amazon pay
    public void navigateToAmazonPay(){
        navigation.navigateToWebElement(image_AmazonPay);
        navigation.clickWebElement(image_AmazonPay);
    }
}
