package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstMobilePage extends MobileDetailsPage {

    WebDriver driver;
    String technicalDetailsHeading = "Technical Details";

    public FirstMobilePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how= How.CSS,using="#prodDetails > div > div:nth-child(1) > div:nth-child(1) > div > div.a-row > div > h1")
    WebElement itemTechnicalDetails;

    //verify first product page title
    public boolean isPageTitleChangedToSelectedProduct(String expectedProductTitle) {
        selectFirstProductInSearchResults();
        try{
            WebElement element = driver.findElement(By.linkText("Redmi 9A (Nature Green, 2GB Ram, 32GB Storage) | 2GHz Octa-core Helio G25 Processor"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (NoSuchElementException e){
            System.out.println("Error in clicking on the first product link in the search results. \n");
            e.printStackTrace();
        }
        return driver.getTitle().equals(expectedProductTitle);
    }

    //validate given product price
    public boolean validateProductPrice(String expectedProductPrice) {
        navigation.changeTabFocusBasedOnGivenIndex(1);
        return getProductPrice().contains(expectedProductPrice);
    }

    //validate the delivery location
    public boolean validateDeliveryLocation(String pincode, String expectedDeliveryLocationLinkText) {
        selectDeliveryLocation();
        changeDeliveryLocation(pincode);
        return link_DeliveryLocation.getText().contains(expectedDeliveryLocationLinkText);
    }

    public void clickOnSponsoredLink() {
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            //scroll down and switch to the frame and click on the frame to open new tab
            js.executeScript("arguments[0].scrollIntoView(true);", new WebDriverWait(driver, 20).
                    until(ExpectedConditions.
                            visibilityOfElementLocated(By.cssSelector(".a-link-normal"))));
            driver.switchTo().frame(1);
            WebElement body = driver.findElement(By.tagName("body"));
            js.executeScript("arguments[0].scrollIntoView(true);", body);
            navigation.clickWebElement(body);
            driver.switchTo().defaultContent();
        } catch (TimeoutException e){
            System.out.println("Error in locating the sponsored link element. \n");
            e.printStackTrace();
        }
        catch (NoSuchFrameException e){
            System.out.println("Error in switching into the frame. \n");
            e.printStackTrace();
        }
    }

    public void validateSponsoredLinkNewTab(String expectedPageTitle) {
        driver.getTitle().contains(expectedPageTitle);
    }

    public void scrollDownToTechnicalDetails(){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);",
                    new WebDriverWait(driver, 3)
                    .until(ExpectedConditions
                          .visibilityOfElementLocated(By.cssSelector("#prodDetails"))));
            itemTechnicalDetails.getText().contains(technicalDetailsHeading);
        } catch (TimeoutException e){
            System.out.println("Error in locating the technical details of the selected product. \n");
            e.printStackTrace();
        }
    }

}
