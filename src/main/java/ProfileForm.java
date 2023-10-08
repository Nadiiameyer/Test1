import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileForm extends BasePage{
    private By yourRepositoriesLocator = By.xpath("//span[contains(text(), \"Your repositories\")]");
    public ProfileForm(WebDriver driver) {

        super(driver);
    }
public RepositoriesPage goToRepositoriesPage(){
    Assertions.assertTrue(driver.findElement(yourRepositoriesLocator).isDisplayed());
    driver.findElement(yourRepositoriesLocator).click();
    return new RepositoriesPage(driver);
}
}
