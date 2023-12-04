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
        driver.get(homePage.getHomePageUrl());
        Thread.sleep(1000);
    }

    @Test(priority = 0)
    public void testTheHomePageUrl() {
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = homePage.getHomePageUrl() + "/";
        Assert.assertEquals("[ERROR] Different than the expected URL!", expectedUrl, currentUrl);
    }

    @Test(priority = 1)
    public void checkHomePageHeaderText() {
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
    }

    @Test(priority = 2)
    public void successfulLogin()  {
        homePage.fillTheLoginForm(driver);
        Assert.assertTrue(elementsPage.isLogOutButtonDisplayed(driver));
        elementsPage.clickOnLogout(driver);

    }

    @Test(priority = 3)
    public void successfulLogOut()  {
        homePage.fillTheLoginForm(driver);
        Assert.assertTrue(elementsPage.isLogOutButtonDisplayed(driver));
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }

    @Test(priority = 4)
    public void testSalesFillingForm()  {
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Angel", "Angelov", "5000", 7000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue("[ERROR] Different than the expected Name!"
                , elementsPage.getSummaryTableElement(driver,"Angel"));
        Assert.assertTrue(elementsPage.getSalesPeopleCount() > 0);
        Assert.assertEquals(elementsPage.getActiveSalesPeopleElement(driver),"Active sales people:" +"\n"+
               elementsPage.getSalesPeopleCount().toString());

    }

    @Test(priority = 5)
    public void testCalculationSalesForm() throws InterruptedException {
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Petar", "Petrov", "75000", 8008);
        elementsPage.clickOnSubmit(driver);
        Assert.assertEquals(elementsPage.getActiveSalesPeopleElement(driver),
                "Active sales people:"+"\n"+elementsPage.getSalesPeopleCount().toString());
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver,"75000")));
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver,"8008")));
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver,"$-66,992")));

    }

    @Test(priority = 6)
    public void testPerformanceButtonFunctionality() throws InterruptedException {
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Anton", "Angelov", "10000", 15000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue(elementsPage.isPerformanceButtonDisplayed(driver));
        elementsPage.clickOnPerformanceButton(driver);
        String expectedMessage = "A positive result. Well done!";
        Assert.assertEquals(expectedMessage, elementsPage.getPerformanceMessage(driver));

    }

    @Test(priority = 7)
    public void testDeleteButtonFunctionality(){
        homePage.fillTheLoginForm(driver);
        elementsPage.fillSalesForm(driver, "Victor", "Ivanov", "10000", 15000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue(elementsPage.isPerformanceButtonDisplayed(driver));
        elementsPage.clickOnDeleteButton(driver);
        Assert.assertTrue(elementsPage.isPerformanceTableEmpty());

    }

    @Test(priority = 8)
    public void testSummaryTable(){
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
        elementsPage.clickOnDeleteButton(driver);

    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
