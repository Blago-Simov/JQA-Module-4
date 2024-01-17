package Pages;

import Custom.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ElementsPage {
    Waits waits = new Waits();


    private By buttonLogout = By.id("logout");

    private By firstNameField = By.name("firstname");
    private By lastNameField = By.id("lastname");
    private By salesTargetDropDown = By.id("salestarget");
    private By salesResultField = By.id("salesresult");
    private By submitButton = By.cssSelector("#sales-form > button");
    private By salesCalculationSummary = By.xpath("//div[@class = 'alert alert-dark sales-summary']/div");
    private By tableSummary = By.xpath("//tbody/tr/td");
    private List<WebElement> performanceTable = new ArrayList<>();
    private List<WebElement> calculationTable = new ArrayList<>();

    private By performanceButton = By.xpath("//div/button[1]");
    private By deleteAllSalesEntriesButton = By.xpath("//div/button[2]");
    private By performanceTextField = By.xpath("//td/span[@class ='performance']");

    private By userNameTextField = By.className("username");

    public void clickOnLogout(WebDriver driver) throws InterruptedException {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", buttonLogout);
        driver.findElement(buttonLogout).click();
        Thread.sleep(3000);
    }

    public void clickOnPerformanceButton(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "elementToBeClickable", performanceButton);
        driver.findElement(performanceButton).click();

    }

    public void clickOnDeleteButton(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", deleteAllSalesEntriesButton);
        driver.findElement(deleteAllSalesEntriesButton).click();
    }

    public String getPerformanceMessage(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", performanceTextField);
        return driver.findElement(performanceTextField).getText();
    }

    public boolean isPerformanceButtonDisplayed(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", performanceButton);
        return driver.findElement(performanceButton).isEnabled();
    }


    public void fillSalesForm(WebDriver driver, String firstName, String
            lastName, String salesTarget, double salesResult) {
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", firstNameField);
        driver.findElement(firstNameField).sendKeys(firstName);
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", lastNameField);
        driver.findElement(lastNameField).sendKeys(lastName);
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", salesTargetDropDown);
        Select targetObj = new Select(driver.findElement(salesTargetDropDown));
        targetObj.selectByValue(salesTarget);
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", salesResultField);
        String salesField = "" + salesResult;
        driver.findElement(salesResultField).sendKeys(salesField);

    }

    public boolean isUsernameDisplayed(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", userNameTextField);
        return driver.findElement(userNameTextField).isDisplayed();
    }

    public void clickOnSubmit(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", submitButton);
        driver.findElement(submitButton).click();


    }


    public Boolean getCalculationTableElement(WebDriver driver, String param) {
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", salesCalculationSummary);
        calculationTable = driver.findElements(salesCalculationSummary);
        if (calculationTable != null) {
            for (WebElement e : calculationTable) {
                e.getText().contains(param);
            }
        }
        return true;
    }

    public String getActiveSalesPeopleElement(WebDriver driver) {
        calculationTable = driver.findElements(salesCalculationSummary);
        return calculationTable.get(0).getText();
    }

    public Boolean getSummaryTableElement(WebDriver driver, String value) {
        waits.customWait(driver, Duration.ofSeconds(10), "presenceOfElement", tableSummary);
        performanceTable = driver.findElements(tableSummary);
        if (performanceTable != null) {
            for (WebElement e : performanceTable) {
                e.getText().contains(value);
            }
        }
        return true;
    }


    public boolean isPerformanceTableEmpty() {
        return performanceTable.isEmpty();
    }


}
