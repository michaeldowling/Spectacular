package spectacular.framework.webdriver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedCondition
import org.apache.commons.logging.LogFactory
import org.apache.commons.logging.Log


class WebDriverCategory {

    static Log LOGGER = LogFactory.getLog(WebDriverCategory.class);

    static WebElement findAndWaitForElement(WebDriver driver, By selector, long maximumSecondsToWait) {

        WebElement element = null;
        try {

            waitForElement(driver, selector, maximumSecondsToWait);
            driver.findElement(selector);

        } catch(Exception e) {
            LOGGER.error("Unable to find element.", e);
        }

        return(element);

    }

    static void waitForElement(WebDriver driver, By selector, long maximumSecondsToWait) {

        WebElement element = (new WebDriverWait(driver, maximumSecondsToWait)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(selector);
            }
        });


    }


    static boolean isTextPresent(WebElement element, String textToMatch) {

        int idx = element.getText().indexOf(textToMatch);
        if(idx >= 0) {
            return true;
        }

        return false;

    }


}
