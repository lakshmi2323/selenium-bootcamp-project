package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Navigation {
    WebDriver driver;
    public Navigation(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToWebElement(WebElement webElement){
        //create an object for the Actions class and pass the driver argument
        Actions builder = new Actions(driver);
        Action mouseOverPage = builder
                .moveToElement(webElement)
                .build();
        mouseOverPage.perform();
    }

    public void clickWebElement(WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void enterStringInWebElement(WebElement webElement, String text){
        webElement.sendKeys(text);
    }

    //method to get parent window handle
    public void changeTabFocusBasedOnGivenIndex(int currentPageWinHandleIndex) {
        //getting all the window handles
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        System.out.println(windowHandles.size());
        System.out.println(windowHandles.toString());
        //change focus to new browser tab based on the given index
        driver.switchTo().window(windowHandles.get(currentPageWinHandleIndex));
    }

}
