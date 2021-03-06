package testrunner;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.SignInPage;
import utils.TestBase;

public class AmazonTestClass3 extends TestBase {
    HomePage homePage;
    SignInPage signInPage;

    /* ReadMe: As amazon website password cannot be shared here and not able to create dummy account
       used the real account and tested this. but getting captcha page as app recognizes execution
       controlled by software.*/
//    @Test
//    @Parameters({"validEmail","password"})
    public void validLoginTest(String validEmail, String mailPassword){
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);

        homePage.navigateToSignIn();
        Assert.assertTrue(signInPage.isLoginSuccessful(validEmail,mailPassword));
        Assert.assertEquals(getPageTitle(), homePage.pageTitle);
        homePage.navigateToSignOut();
        Assert.assertEquals(getPageTitle(), signInPage.pageTitle);
    }

    @Test
    @Parameters({"invalidEmail"})
    public void invalidLoginTest(String invalidEmail){
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);

        homePage.navigateToSignIn();
        Assert.assertFalse(signInPage.isLoginSuccessful(invalidEmail,null));
        signInPage.verifyLoginErrorMessage();
    }

}
