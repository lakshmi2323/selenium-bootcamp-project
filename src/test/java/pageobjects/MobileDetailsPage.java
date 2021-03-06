package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileDetailsPage {
    WebDriver driver;
    Navigation navigation;

    String defaultDeliveryLocationLinkText = "Select delivery location";
    String expectedAlertAfterAddToCart = "Added to Cart";

    public MobileDetailsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        navigation = new Navigation(driver);
    }

    @FindBy(how=How.CSS,using="#search > span > div > span > h1 > div > div.sg-col-14-of-20.sg-col.s-breadcrumb.sg-col-10-of-16.sg-col-6-of-12 > div > div > span:nth-child(1)")
    WebElement label_SearchResults;

    @FindBy(how=How.CSS,using="#p_72\\/1318476031 > span > a > section")
    WebElement label_4StarsAndUpCustomerReview;
    @FindBy(how=How.CSS,using="#p_72\\/1318477031 > span > a > section")
    WebElement label_3StarsAndUpCustomerReview;
    @FindBy(how=How.CSS,using="#p_72\\/1318478031 > span > a > section")
    WebElement label_2StarsAndUpCustomerReview;
    @FindBy(how=How.CSS,using="#p_72\\/1318479031 > span > a > section")
    WebElement label_1StarAndUpCustomerReview;

    @FindBy(how=How.CSS,using="#search > div.s-desktop-width-max.s-desktop-content.sg-row > div.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div:nth-child(2) > div > span > div")
    WebElement firstProductInSearchResults;

    @FindBy(how=How.CSS,using="#priceblock_ourprice")
    WebElement label_ProductPrice;

    @FindBy(how=How.CSS,using="#glowContextualIngressPt_feature_div")
    WebElement link_DeliveryLocation;
    @FindBy(how=How.CSS,using="#GLUXZipUpdateInput")
    WebElement txtBox_ZipUpdate;
    @FindBy(how=How.CSS,using="#GLUXZipUpdate > span > input")
    WebElement btn_ApplyZip;

    @FindBy(how=How.CSS,using="#add-to-cart-button")
    WebElement btn_AddToCart;
    @FindBy(how=How.CSS,using="#attachDisplayAddBaseAlert > div > h4")
    WebElement alertMsg_AddToCart;
    @FindBy(how=How.CSS,using="#attach-close_sideSheet-link")
    WebElement linkImg_CloseAddToCart;


    //validate the total results displayed
    public boolean isExpectedTotalResultsDisplayed(String expectedSearchMobileResults){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#search > span > div > span > h1 > div > div.sg-col-14-of-20.sg-col.s-breadcrumb.sg-col-10-of-16.sg-col-6-of-12 > div > div")));
        return label_SearchResults.getText().contains(expectedSearchMobileResults);
    }
    //select the Avg Customer Review selection
    public void selectAverageCustomerReview(String selectedAverageReview){
        WebElement rating;
        if(selectedAverageReview == "4stars")
            rating = label_4StarsAndUpCustomerReview;
        else if(selectedAverageReview == "3stars")
            rating = label_3StarsAndUpCustomerReview;
        else if(selectedAverageReview == "2stars")
            rating = label_2StarsAndUpCustomerReview;
        else
            rating = label_1StarAndUpCustomerReview;
        navigation.navigateToWebElement(rating);
        navigation.clickWebElement(rating);
    }
    //Select the first product in the search results
    public void selectFirstProductInSearchResults(){
        navigation.navigateToWebElement(firstProductInSearchResults);
    }

    //get the product price
    String getProductPrice(){
       navigation.navigateToWebElement(label_ProductPrice);
       return label_ProductPrice.getText();
    }

    public void selectDeliveryLocation() {
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            //scroll down till the delivery location is visible
            js.executeScript("arguments[0].scrollIntoView(true);", new WebDriverWait(driver, 2).
                    until(ExpectedConditions.
                            visibilityOfElementLocated(By.cssSelector(".a-declarative:nth-child(2) #contextualIngressPtLabel_deliveryShortLine"))));
            navigation.navigateToWebElement(link_DeliveryLocation);
            link_DeliveryLocation.getText().contains(defaultDeliveryLocationLinkText);
            js.executeScript("arguments[0].click();", new WebDriverWait(driver, 2).
                    until(ExpectedConditions.
                            elementToBeClickable(By.cssSelector(".a-declarative:nth-child(2) #contextualIngressPtLabel_deliveryShortLine"))));

        } catch (TimeoutException e){
            System.out.println("Error in navigating/locating the delivery location link. \n");
            e.printStackTrace();
        }
    }
    //change the delivery location
    public void changeDeliveryLocation(String pincode) {
        try {
            navigation.enterStringInWebElement(txtBox_ZipUpdate, pincode);
            navigation.clickWebElement(btn_ApplyZip);
            navigation.changeTabFocusBasedOnGivenIndex(1);
        } catch (NoSuchWindowException e){
            System.out.println("Error in navigating back to the selected product's after applying " +
                    "delivery location change. \n");
            e.printStackTrace();
        }
    }
    //add to cart
    public void addProductToCart() {
        try {
            navigation.navigateToWebElement(btn_AddToCart);
            navigation.clickWebElement(btn_AddToCart);
            alertMsg_AddToCart.getText().contains(expectedAlertAfterAddToCart);
        } catch (NoSuchElementException e){
            System.out.println("Error in clicking on add to cart button. \n");
            e.printStackTrace();
        }
    }
    //close the “Add to Cart” window
    public void closeAddProductToCartWindow() {
        try{
            navigation.navigateToWebElement(linkImg_CloseAddToCart);
            navigation.clickWebElement(linkImg_CloseAddToCart);
        } catch (NoSuchElementException e){
            System.out.println("Error in closing add to cart window. \n");
            e.printStackTrace();
        }
    }

}
