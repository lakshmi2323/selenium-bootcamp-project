package testrunner;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.TestBase;

public class AmazonTestClass2 extends TestBase {

    HomePage homePage;
    String expectedWishListPageTitle = "Wish List";
    String expectedAmazonPayPageTitle = "Amazon Pay";
    String expectedNewReleasesPageTitle = "Amazon.in Hot New Releases: The bestselling new and future releases on Amazon";

     /* Test1:
    Use Navigation to navigate to Create a Wish List (Accounts and Lists ➔
            Create a Wish List) and verify whether the navigation was successful */
    @Test
    public void createWishListNavigationValidation() {
        homePage = new HomePage(driver);

        homePage.navigateToCreateWishList();
        Assert.assertEquals(getPageTitle(), expectedWishListPageTitle);
    }

     /* Test2: Use Navigation to navigate to Amazon Pay module and validate the page */
     @Test
     public void amazonPayNavigationValidation() {
         homePage = new HomePage(driver);
         PrepaidRechargesPage prepaidRechargesPage = new PrepaidRechargesPage(driver);
         homePage.navigateToAmazonPayMobileRecharge();
         prepaidRechargesPage.navigateToAmazonPay();
         Assert.assertEquals(getPageTitle(), expectedAmazonPayPageTitle);
     }

     /* Test3: Use Navigation to navigate New Releases module and validate the page */
     @Test
     public void newReleasesNavigationValidation(){
         homePage = new HomePage(driver);
         homePage.navigateToNewReleases();
         Assert.assertEquals(getPageTitle(), expectedNewReleasesPageTitle);
     }
}
