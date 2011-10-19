package spectacular.framework.webdriver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

class WebDriverHelper {

    WebDriver driver;

    WebDriverHelper(WebDriver d) {
        this.driver = d;
    }


    def waitForElement(By selector) {
        return(waitForElement(selector, 30));
    }

    def waitForElement(By selector, int maximumSecondsToWait) {

        WebElement element = (new WebDriverWait(this.driver, maximumSecondsToWait)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(selector);
            }
        });

        return(element);
    }

    def methodMissing(String methodName, args) {
        return(this.driver.invokeMethod(methodName, args));
    }



}
