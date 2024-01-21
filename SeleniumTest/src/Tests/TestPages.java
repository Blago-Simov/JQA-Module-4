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

    final String userName = "maria";
    final String password = "thoushallnotpass";

    @BeforeTest
    public void loadHomePage() {
        homePage = new HomePage();
        elementsPage = new ElementsPage();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void testTheHoePage() {
        driver.get(homePage.getHomePageUrl(driver));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = homePage.getHomePageUrl(driver) + "/";
        Assert.assertEquals("[ERROR] Different than the expected URL!", expectedUrl, currentUrl);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
    }


    @Test(priority = 2)
    public void successfulLoginAndLogout() {
        driver.get(homePage.getHomePageUrl(driver));
        homePage.fillTheLoginForm(driver, userName, password);
        Assert.assertTrue(elementsPage.isUsernameDisplayed(driver));
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));
    }

    @Test(priority = 3)
    public void testDeleteButtonFunctionality() {
        driver.get(homePage.getHomePageUrl(driver));
        homePage.fillTheLoginForm(driver, userName, password);
        elementsPage.fillSalesForm(driver, "Victor", "Ivanov", "10000", 15000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue(elementsPage.isPerformanceButtonDisplayed(driver));
        elementsPage.clickOnDeleteButton(driver);
        Assert.assertTrue(elementsPage.isPerformanceTableEmpty());
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }

    @Test(priority = 4)
    public void testSalesFillingForm() {
        driver.get(homePage.getHomePageUrl(driver));
        homePage.fillTheLoginForm(driver, userName, password);
        elementsPage.fillSalesForm(driver, "Angel", "Angelov", "5000", 7000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue("[ERROR] Different than the expected Name!"
                , elementsPage.getSummaryTableElement(driver, "Angel"));
        Assert.assertEquals(elementsPage.getActiveSalesPeopleElement(driver), "Active sales people:" + "\n" + "1");
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));


    }

    @Test(priority = 5)
    public void testCalculationSalesForm() {
        driver.get(homePage.getHomePageUrl(driver));
        homePage.fillTheLoginForm(driver, userName, password);
        elementsPage.fillSalesForm(driver, "Petar", "Petrov", "75000", 8008);
        elementsPage.clickOnSubmit(driver);
        Assert.assertEquals(elementsPage.getActiveSalesPeopleElement(driver), "Active sales people:" + "\n" + "1");
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver, "75000")));
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver, "8008")));
        Assert.assertTrue((elementsPage.getCalculationTableElement(driver, "$-66,992")));
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }

    @Test(priority = 6)
    public void testPerformanceButtonFunctionality() {
        driver.get(homePage.getHomePageUrl(driver));
        homePage.fillTheLoginForm(driver, userName, password);
        elementsPage.fillSalesForm(driver, "Anton", "Angelov", "10000", 15000);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue(elementsPage.isPerformanceButtonDisplayed(driver));
        elementsPage.clickOnPerformanceButton(driver);
        Assert.assertEquals("A positive result. Well done!", elementsPage.getPerformanceMessage(driver));
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }


    @Test(priority = 7)
    public void testSummaryTable() {
        driver.get(homePage.getHomePageUrl(driver));
        homePage.fillTheLoginForm(driver, userName, password);
        elementsPage.fillSalesForm(driver, "Plamen", "Petrov", "5000", 8000);
        elementsPage.clickOnSubmit(driver);
        elementsPage.fillSalesForm(driver, "Victor", "Markov", "75000", 4003);
        elementsPage.clickOnSubmit(driver);
        elementsPage.fillSalesForm(driver, "Svilen", "Svilenov", "25000", 36009);
        elementsPage.clickOnSubmit(driver);
        Assert.assertTrue("[ERROR] Different than the expected Name!",
                elementsPage.getSummaryTableElement(driver, "Plamen Petrov"));
        Assert.assertTrue("[ERROR] Different than the expected Name!",
                elementsPage.getSummaryTableElement(driver, "Victor Markov"));
        Assert.assertTrue("[ERROR] Different than the expected Name!",
                elementsPage.getSummaryTableElement(driver, "Svilen Svilenov"));
        Assert.assertTrue("[ERROR] Different than the expected Target!",
                elementsPage.getSummaryTableElement(driver, "$5,000"));
        Assert.assertTrue("[ERROR] Different than the expected Target!",
                elementsPage.getSummaryTableElement(driver, "$75,000"));
        Assert.assertTrue("[ERROR] Different than the expected Target!",
                elementsPage.getSummaryTableElement(driver, "$25,000"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver, "$8,000"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver, "$4,003"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver, "$36,009"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getSummaryTableElement(driver, "$-70,997"));
        Assert.assertTrue("[ERROR] Different than the expected Result!"
                , elementsPage.getCalculationTableElement(driver, "$-56,988"));
        elementsPage.clickOnDeleteButton(driver);
        elementsPage.clickOnLogout(driver);
        Assert.assertTrue(homePage.getHomePageHeaderText(driver).contains("Hey! Wait a minute... What is this site?"));

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
