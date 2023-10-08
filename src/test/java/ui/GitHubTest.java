package ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;


public class GitHubTest<HomePage> extends BaseTest {
    @org.testng.annotations.Test
    public void verifyThatLogioOnTheLoginPageIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.goToLoginPage().getLogo().isDisplayed());
    }
    @Test
    public void verifyLoginIsseccessful() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.goToLoginPage().loginSuccessful("nadia.suworowa@hotmail.com",
                        "Cologne123!!!")
                .getLogoOnTheMainPage().isDisplayed());
    }
    @org.testng.annotations.Test
    public void verifyFailedLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().negativeLogin("uhdfurbj", "sedjvodkfb")
                .validateErrorMessage("Incorrect username or password.");
    }
    @org.testng.annotations.Test
    @Tag("regression")
    public void verifyRepositoriesList() {
        HomePage homePage = new HomePage(driver);
        List<String> actualRepList = homePage.goToLoginPage().loginSuccessful("nadia.suworowa@hotmail.com", "Cologne123!!!")
                .goToProfileForm().goToRepositoriesPage().getRepositories();
        List expectedRepList = new ArrayList<>();
        expectedRepList.add("HW_Test_9");
        expectedRepList.add("testFrameWork");
        expectedRepList.add("HW6");
        Assertions.assertEquals(expectedRepList, actualRepList, "Actual repositories List doesn't equal " +
                "to expected repositiries List");
        Actions actions = new Actions(driver);
        actions.doubleClick();
    }
    @DataProvider(name = "dataProvider1")
    public Object[][] createData() {
        return new Object[][]{
                {"Alex", 20},
                {"Kate", 18}
        };
    }
    @org.testng.annotations.Test(dataProvider = "dataProvider1")
    public void verifyData(String name, Integer age) {
        System.out.println(name + " " + age);
    }
    @DataProvider(name = "invalidCredentialProvider")
    public Object[][] invalidCredentialProvider() {
        return new Object[][]{
                {"ajciladsjnvl.hsd", "ajciladsjnvl.hsd"},
                {"ajciladsjnvl.hsd", "ajciladsjnvl.hsd"},
                {"ajciladsjnvl.hsd", "ajciladsjnvl.hsd"}
        };
    }
    @org.testng.annotations.Test(dataProvider = "credentialProviver")
    public void verifyNegativeLoginParametrized(String login, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().negativeLogin(login, password)
                .validateErrorMessage("Incorrect username or password.");
    }
    @org.testng.annotations.Test
    public void someChecks() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
        softAssert.assertEquals(2, 3);
        softAssert.assertTrue(false);
        softAssert.assertAll();
    }
    @DataProvider(name = "searchQueries")
    public static Object[][] searchQueries() {
        return new Object[][]{
                {"java"},
                {"python"},
                {"javascript"}
        };
    }
    @org.testng.annotations.Test(dataProvider = "searchQueries")
    public void testGitHubSearch(String query) {
        driver.get("https://github.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }
}