package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    Navigation navigation;
    public String pageTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        navigation = new Navigation(driver);
    }

    @FindBy(how=How.CSS,using="#nav-link-accountList")
    public WebElement menuItem_AccountsAndLists;
    @FindBy(how=How.CSS,using="#nav-al-wishlist > a:nth-child(2) > span")
    public WebElement navAccountsLists_CreateWishlist;
    @FindBy(how=How.CSS,using="#nav-item-signout > span")
    public WebElement navAccountsLists_SignOut;

    @FindBy(how=How.LINK_TEXT,using="New Releases")
    public WebElement menuItem_NewReleases;

    @FindBy(how=How.CSS,using=".a-icon-next-rounded")
    public WebElement icon_Next;
    @FindBy(how=How.XPATH,using="//img[@alt='Do more with Amazon']")
    public WebElement icon_AmazonPayMobileRecharge;

    @FindBy(how=How.CSS,using="#nav-signin-tooltip .nav-action-inner")
    public WebElement btn_SignIn;

    @FindBy(how=How.ID,using="twotabsearchtextbox")
    WebElement txtBox_SearchItems;

    //method to navigate from accounts and lists to create wishlist on home page
    public void navigateToCreateWishList(){
        try{
            navigation.navigateToWebElement(menuItem_AccountsAndLists);
            navigation.navigateToWebElement(navAccountsLists_CreateWishlist);
            navigation.clickWebElement(navAccountsLists_CreateWishlist);
        } catch (NoSuchElementException e){
            System.out.println("Error in navigating/locating the create wish list. \n");
            e.printStackTrace();
        }
    }

    //method to navigate new releases on home page
    public void navigateToNewReleases(){
        try {
            navigation.navigateToWebElement(menuItem_NewReleases);
            navigation.clickWebElement(menuItem_NewReleases);
        } catch (NoSuchElementException e){
            System.out.println("Error in navigating/locating the new releases menu item. \n");
            e.printStackTrace();
        }
    }

    //method to navigate amazon pay mobile recharge to go to amazon pay on home page
    public void navigateToAmazonPayMobileRecharge(){
        navigation.navigateToWebElement(icon_Next);
        navigation.clickWebElement(icon_Next);
        navigation.navigateToWebElement(icon_Next);
        navigation.clickWebElement(icon_Next);
        try{
            navigation.navigateToWebElement(icon_AmazonPayMobileRecharge);
            navigation.clickWebElement(icon_AmazonPayMobileRecharge);
        } catch (NoSuchElementException e){
            System.out.println("Error in locating the amazon pay option. \n");
            e.printStackTrace();
        }
    }

    //method to navigate new releases on home page
    public void navigateToSignIn() {
        try{
            navigation.navigateToWebElement(btn_SignIn);
            navigation.clickWebElement(btn_SignIn);
        } catch (NoSuchElementException e){
            System.out.println("Error in navigating/locating the sign in button. \n");
            e.printStackTrace();
        }
    }

    //method to navigate new releases on home page
    public void navigateToSignOut() {
        try{
            navigation.navigateToWebElement(menuItem_AccountsAndLists);
            navigation.navigateToWebElement(navAccountsLists_SignOut);
            navigation.clickWebElement(navAccountsLists_SignOut);
        } catch (NoSuchElementException e){
            System.out.println("Error in navigating/locating the sign out. \n");
            e.printStackTrace();
        }
    }

    //method to navigate new releases on home page
    public void navigateToSearchItems(String searchItem) {
        try{
            navigation.navigateToWebElement(txtBox_SearchItems);
            navigation.clickWebElement(txtBox_SearchItems);
            navigation.enterStringInWebElement(txtBox_SearchItems, searchItem);
            txtBox_SearchItems.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e){
            System.out.println("Error in navigating/locating the search text box in home page. \n");
            e.printStackTrace();
        }
    }
}
