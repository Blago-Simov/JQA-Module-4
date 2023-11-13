package Custom;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {


    public void customWait(WebDriver driver, Duration timeout, @NotNull String condition, By locator) {
        switch(condition) {
            case "presenceOfElement":
                new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
            case "elementToBeClickable":
                new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
        }

    }
}
