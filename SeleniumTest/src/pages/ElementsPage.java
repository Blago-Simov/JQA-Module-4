package Pages;

import Custom.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class ElementsPage {
    Waits waits = new Waits();
    private Integer activeSalesPeopleCount = 0;


    private By buttonLogout = By.xpath("//button[@id='logout']");

    private By firstNameField = By.xpath("//input[@id='firstname']");
    private By lastNameField = By.xpath("//input[@id='lastname']");
    private By salesTargetDropDown = By.xpath("//select[@id='salestarget']");
    private By salesResultField = By.xpath("//input[@id='salesresult']");
    private By submitButton = By.xpath("//button[contains(text(),'Submit')]");
    private By activeSalesPeople = By.xpath("//div[@class = 'alert alert-dark sales-summary']/div[1]");
    private By expectedSales = By.xpath("//div[@class = 'alert alert-dark sales-summary']/div[2]");
    private By actualSales = By.xpath("//div[@class = 'alert alert-dark sales-summary']/div[3]");
    private By differenceResult = By.xpath("//div[@class = 'alert alert-dark sales-summary']/div[4]");

    private By tableSummaryName = By.xpath("//tbody/tr/td[1]");

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
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", activeSalesPeople);
        return driver.findElement(activeSalesPeople).getText();
    }

    public String getExpectedSalesResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", expectedSales);
        return driver.findElement(expectedSales).getText();
    }

    public String getActualSalesResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", actualSales);
        return driver.findElement(actualSales).getText();
    }

    public String getDifferenceResult(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", differenceResult);
        return driver.findElement(differenceResult).getText();
    }

    public String getTableSummaryName(WebDriver driver) {
        waits.customWait(driver, Duration.ofSeconds(3), "presenceOfElement", tableSummaryName);
        return driver.findElement(tableSummaryName).getText();
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
