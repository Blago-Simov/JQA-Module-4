package Pages;

import Custom.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class HomePage {

    final String userName = "maria";
    final String password = "thoushallnotpass";
    private final String homePageUrl = "https://robotsparebinindustries.com";


    private By userNameTextField = By.xpath("//input[@id='username']");

    private By passwordTextField = By.xpath("//input[@id='password']");

    private By loginButton = By.xpath("//button[contains(text(),'Log in')]");
    private By homePageHeaderText = By.tagName("h4");

    Waits waits = new Waits();

    public String getHomePageUrl() {
        return homePageUrl;
    }


    public String getHomePageHeaderText(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", homePageHeaderText);
        return driver.findElement(homePageHeaderText).getText();
    }

    public void fillTheLoginForm(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(2), "presenceOfElement", userNameTextField);
        driver.findElement(userNameTextField).sendKeys(userName);
        waits.customWait(driver, Duration.ofSeconds(2), "presenceOfElement", passwordTextField);
        driver.findElement(passwordTextField).sendKeys(password);
        waits.customWait(driver, Duration.ofSeconds(2), "elementToBeClickable", loginButton);
        driver.findElement(loginButton).click();
    }
}
