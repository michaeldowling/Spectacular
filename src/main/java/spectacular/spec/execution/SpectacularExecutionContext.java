package spectacular.spec.execution;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import spectacular.spec.execution.webdriver.WebDriverType;

import java.util.HashMap;
import java.util.Map;

public class SpectacularExecutionContext {

    private static Log LOGGER = LogFactory.getLog(SpectacularExecutionContext.class);

    private Map<String, Object> contextAttributes;
    private static WebDriver webDriver;



    public SpectacularExecutionContext() {
        this.contextAttributes = new HashMap<String, Object>();
    }

    public Object getAttribute(String key) {
        return(this.contextAttributes.get(key));
    }

    public void setAttribute(String key, Object value) {
        this.contextAttributes.put(key, value);
    }

    public WebDriver getWebDriver() {
        return(SpectacularExecutionContext.webDriver);
    }

    public void message(String message) {
        if(LOGGER.isInfoEnabled()) LOGGER.info(message);
    }

    public static void initWebDriver(WebDriverType type) {

        if(webDriver != null) return;
        if(type.equals(WebDriverType.HTMLUNIT)) {
            SpectacularExecutionContext.webDriver = new HtmlUnitDriver();
        } else if(type.equals(WebDriverType.CHROME)) {
            SpectacularExecutionContext.webDriver = new ChromeDriver();
        } else if(type.equals(WebDriverType.FIREFOX)) {
            SpectacularExecutionContext.webDriver = new FirefoxDriver();
        } else {
            if(LOGGER.isInfoEnabled()) LOGGER.info("No valid WebDriver driver was specified.");
        }



    }




}
