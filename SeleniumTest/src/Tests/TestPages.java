package Tests;

import Pages.ElementsPage;
import Pages.HomePage;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestPages {

    WebDriver driver;

    HomePage homePage;

    ElementsPage elementsPage;


    @BeforeTest
    public void loadHomePage() throws InterruptedException {
        homePage = new HomePage();
        elementsPage = new ElementsPage();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test(priority = 0)
    public void testTheHomePageUrl() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = homePage.getHomePageUrl() + "/";
        Assert.assertEquals("[ERROR] Different than the expected URL!", expectedUrl, currentUrl);
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void checkHomePageHeaderText() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void successfulLogin() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        Assert.assertTrue(elementsPage.isLogOutButtonDisplayed(driver));
        elementsPage.clickOnLogout(driver);
        Thread.sleep(2000);

    }

    @Test(priority = 3)
    public void successfulLogOut() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        Assert.assertTrue(elementsPage.isLogOutButtonDisplayed(driver));
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void testSalesFillingForm() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Angel", "Angelov", "5000", 7000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue("[ERROR] Different than the expected Name!"
                , elementsPage.getSummaryTableElement(driver,"Angel"));
        Assert.assertTrue(elementsPage.getSalesPeopleCount() > 0);
        Assert.assertEquals(elementsPage.getActiveSalesPeopleElement(driver),"Active sales people:" +"\n"+
               elementsPage.getSalesPeopleCount().toString());
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
        Thread.sleep(3000);

    }

    @Test(priority = 5)
    public void testCalculationSalesForm() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Petar", "Petrov", "75000", 8008);
        elementsPage.clickOnSubmit(driver);
        Assert.assertEquals(elementsPage.getActiveSalesPeopleElement(driver),
                "Active sales people:"+"\n"+elementsPage.getSalesPeopleCount().toString());
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver,"75000")));
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver,"8008")));
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver,"$-66,992")));
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void testPerformanceButtonFunctionality() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Anton", "Angelov", "10000", 15000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue(elementsPage.isPerformanceButtonDisplayed(driver));
        elementsPage.clickOnPerformanceButton(driver);
        Assert.assertEquals("A positive result. Well done!", elementsPage.getPerformanceMessage(driver));
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
        Thread.sleep(2000);
    }

    @Test(priority = 7)
    public void testDeleteButtonFunctionality() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Victor", "Ivanov", "10000", 15000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue(elementsPage.isPerformanceButtonDisplayed(driver));
        elementsPage.clickOnDeleteButton(driver);
        Assert.assertTrue(elementsPage.isPerformanceTableEmpty());
        Thread.sleep(2000);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }

    @Test(priority = 8)
    public void testSummaryTable() throws InterruptedException {
        driver.get(homePage.getHomePageUrl());
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Plamen", "Petrov", "5000", 8000);
        elementsPage.clickOnSubmit(driver);
        elementsPage.fillSalesForm(driver, "Victor", "Markov", "75000", 4003);
        elementsPage.clickOnSubmit(driver);
        elementsPage.fillSalesForm(driver, "Svilen", "Svilenov", "25000", 36009);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue("[ERROR] Different than the expected Name!",
                elementsPage.getSummaryTableElement(driver,"Plamen Petrov"));
        Assert.assertTrue("[ERROR] Different than the expected Name!",
                elementsPage.getSummaryTableElement(driver,"Victor Markov"));
        Assert.assertTrue("[ERROR] Different than the expected Name!",
                elementsPage.getSummaryTableElement(driver,"Svilen Svilenov"));
        Assert.assertTrue("[ERROR] Different than the expected Target!",
                elementsPage.getSummaryTableElement(driver,"$5,000"));
        Assert.assertTrue("[ERROR] Different than the expected Target!",
                elementsPage.getSummaryTableElement(driver,"$75,000"));
        Assert.assertTrue("[ERROR] Different than the expected Target!",
                elementsPage.getSummaryTableElement(driver,"$25,000"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver,"$8,000"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver,"$4,003"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver,"$36,009"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver,"$-70,997"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getCalculationTableElement(driver,"$-56,988"));
        elementsPage.clickOnDeleteButton(driver);
        Thread.sleep(2000);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
