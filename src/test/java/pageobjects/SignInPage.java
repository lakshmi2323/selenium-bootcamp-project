package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    WebDriver driver;
    Navigation navigation;
    String loginError = "We cannot find an account with that email address";
    public String pageTitle = "Amazon Sign In";

    public SignInPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        navigation = new Navigation(driver);
    }

    @FindBy(how=How.ID,using="ap_email")
    WebElement txtBox_EmailId;
    @FindBy(how=How.ID,using="ap_password")
    WebElement txtBox_password;
    @FindBy(how=How.CSS,using=".a-button-inner > #continue")
    WebElement btn_Continue;
    @FindBy(how=How.ID,using="signInSubmit")
    WebElement btn_SignIn;
    @FindBy(how=How.CSS,using="#auth-error-message-box > div > div > ul > li")
    WebElement err_SignIn;

    public boolean isLoginSuccessful(String emailId, String password){
        boolean result = true;
        navigation.enterStringInWebElement(txtBox_EmailId, emailId);
        navigation.clickWebElement(btn_Continue);
        try {
            navigation.enterStringInWebElement(txtBox_password, password);
            navigation.clickWebElement(btn_Continue);
            navigation.clickWebElement(btn_SignIn);
        } catch(NoSuchElementException e){
            //when invalid email is entered, will not see password text box page and error will be thrown.
            //verifying error here
            result = false;
        }
        return result;
    }

    public boolean verifyLoginErrorMessage(){
        return getAlertMessage().contains(loginError);
    }

    public String getAlertMessage(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".a-alert-heading")));
        return err_SignIn.getText();
    }
}
