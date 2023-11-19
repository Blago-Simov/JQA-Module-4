package Pages;

import Custom.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ElementsPage {
    Waits waits = new Waits();
    private Integer activeSalesPeopleCount = 0;


    private By buttonLogout = By.xpath("//button[@id='logout']");

    private By firstNameField = By.xpath("//input[@id='firstname']");
    private By lastNameField = By.xpath("//input[@id='lastname']");
    private By salesTargetDropDown = By.xpath("//select[@id='salestarget']");
    private By salesResultField = By.xpath("//input[@id='salesresult']");
    private By submitButton = By.xpath("//button[contains(text(),'Submit')]");
    private By salesCalculationSummary = By.xpath("//div[@class = 'alert alert-dark sales-summary']/div");
    private By tableSummary = By.xpath("//tbody/tr/td");
    private List<WebElement> performanceTable = new ArrayList<>();
    private List<WebElement> calculationTable = new ArrayList<>();

    private By performanceButton = By.xpath("//div/button[1]");
    private By deleteAllSalesEntriesButton = By.xpath("//div/button[2]");
    private By performanceTextField = By.xpath("//td/span[@class ='performance']");

    public void clickOnLogout(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "elementToBeClickable", buttonLogout);
        driver.findElement(buttonLogout).click();
    }

    public void clickOnPerformanceButton(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "elementToBeClickable", performanceButton);
        driver.findElement(performanceButton).click();

    }

    public void clickOnDeleteButton(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "elementToBeClickable", deleteAllSalesEntriesButton);
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
            lastName, String salesTarget, String salesResult) {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", firstNameField);
        driver.findElement(firstNameField).sendKeys(firstName);
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", lastNameField);
        driver.findElement(lastNameField).sendKeys(lastName);
        waits.customWait(driver, Duration.ofSeconds(5), "elementToBeClickable", salesTargetDropDown);
        WebElement targetSale = driver.findElement(salesTargetDropDown);
        targetSale.click();
        targetSale.sendKeys(salesTarget);
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", salesResultField);
        driver.findElement(salesResultField).sendKeys(salesResult);

    }

    public boolean isLogOutButtonDisplayed(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "presenceOfElement", buttonLogout);
        return driver.findElement(buttonLogout).isDisplayed();
    }

    public void clickOnSubmit(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(5), "elementToBeClickable", submitButton);
        driver.findElement(submitButton).click();
        activeSalesPeopleCount++;

    }


    public Integer getActiveSalesPeopleCount() {
        return activeSalesPeopleCount;
    }

    public String getActivePeopleSalesResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", salesCalculationSummary);
        calculationTable = driver.findElements(salesCalculationSummary);
        return calculationTable.get(0).getText();
    }

    public String getExpectedSalesResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", salesCalculationSummary);
        return calculationTable.get(1).getText();
    }

    public String getActualSalesResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", salesCalculationSummary);
        return calculationTable.get(2).getText();
    }

    public String getDifferenceResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", salesCalculationSummary);
        return calculationTable.get(3).getText();
    }

    public String getTableSummaryName(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", tableSummary);
        performanceTable = driver.findElements(tableSummary);
        return performanceTable.get(0).getText();
    }

    public String getTableSummaryTarget(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", tableSummary);
        performanceTable = driver.findElements(tableSummary);
        return performanceTable.get(1).getText();
    }

    public String getTableSummaryResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", tableSummary);
        performanceTable = driver.findElements(tableSummary);
        return performanceTable.get(2).getText();
    }

    public String getTableSummaryDifference(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", tableSummary);
        performanceTable = driver.findElements(tableSummary);
        return performanceTable.get(3).getText();
    }

    public String getFirstName(WebDriver driver) {
        return driver.findElement(firstNameField).getText();
    }

    public String getLastName(WebDriver driver) {
        return driver.findElement(lastNameField).getText();
    }

    public String getSalesResult(WebDriver driver) {
        return driver.findElement(salesResultField).getText();
    }


}
