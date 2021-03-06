package testrunner;

import org.testng.annotations.Test;
import pageobjects.FirstMobilePage;
import pageobjects.HomePage;
import pageobjects.MobileDetailsPage;
import utils.TestBase;

public class AmazonTestClass4 extends TestBase {

    HomePage homePage;
    MobileDetailsPage mobileDetailsPage;

    String searchItem = "mi mobile";
    //as count and page count is dynamic, setting expected display contains the below string
    String expectedSearchMobileResults = "results for 'mi mobile'";
    String expectedFirstProductPageTitle = "Samsung Galaxy M02 (Red,3GB RAM, 32GB Storage) | Dual Rear Camera | 6.5\" HD+ Display | Introductory Offer- Rs.200 Off: Amazon.in: Electronics";
    String expectedProductPrice = "₹ 7,299.00";
    String expectedDeliveryLocationLinkText = "Deliver to Kancheepuram 631501";
    String pincodeToUpdate = "631501";
    String expectedPageTitleAfterSponsoredLinkClick = "Samsung Galaxy M51 (Celestial Black, 6GB RAM, 128GB Storage): Amazon.in: Electronics";

    @Test
    public void addToCartValidation() {
        homePage = new HomePage(driver);
        mobileDetailsPage = new MobileDetailsPage(driver);
        FirstMobilePage firstMobilePage = new FirstMobilePage(driver);
        homePage.navigateToSearchItems(searchItem);
        mobileDetailsPage.isExpectedTotalResultsDisplayed(expectedSearchMobileResults);
        mobileDetailsPage.selectAverageCustomerReview("4stars");
        firstMobilePage.isPageTitleChangedToSelectedProduct(expectedFirstProductPageTitle);
        firstMobilePage.validateProductPrice(expectedProductPrice);
        firstMobilePage.validateDeliveryLocation(pincodeToUpdate,expectedDeliveryLocationLinkText);
        firstMobilePage.clickOnSponsoredLink();
        firstMobilePage.validateSponsoredLinkNewTab(expectedPageTitleAfterSponsoredLinkClick);
        mobileDetailsPage.addProductToCart();
        mobileDetailsPage.closeAddProductToCartWindow();
        firstMobilePage.scrollDownToTechnicalDetails();
      }
}
