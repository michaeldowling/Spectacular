import static org.junit.Assert.*
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import spectacular.framework.webdriver.WebDriverCategory

step.Action("Open browser", {context ->

    assertNotNull(context.webDriver);


});

step.Action("Go to \"(.*?)\"", {context, url ->

    context.message "Going to:  ${url}";
    context.webDriver.get(url);
    if (!context.webDriver.getTitle().equals("Yahoo! Finance - Business Finance, Stock Market, Quotes, News")) {
        throw new Exception("Title was not as expected:  " + context.webDriver.getTitle());
    }

});

step.Action("Verify user can see \"(.*?)\"", {context, textPresent ->

    assertTrue(context.webDriver.waitForElement(By.tagName("body")).getText().indexOf(textPresent) > -1);


});


step.Action("Enter \"(.*?)\" in quote form", {context, stockSymbol ->

    WebElement quoteFormElement = context.webDriver.waitForElement(By.id("txtQuotes"));
    quoteFormElement.sendKeys(stockSymbol);


});

step.Action("Click \"(.*?)\"", {context, buttonValue ->

    WebElement getQuotesButtonElement = context.webDriver.waitForElement(By.xpath("//input[@value='${buttonValue}']"));
    getQuotesButtonElement.click();


});


step.Action("Verify company name as \"(.*?)\"", {context, companyName ->

    WebElement companyNameElement = context.webDriver.waitForElement(By.xpath("//div[@class='yfi_quote_summary']/div[@class='hd']/div[@class='title']/h2"));
    assertEquals(companyName, companyNameElement.getText());

});

step.Action("Verify stock symbol as \"(.*?)\"", {context, stockSymbolVerify ->

    WebElement stockSymbolVerifyElement = context.webDriver.waitForElement(By.xpath("//div[@class='yfi_quote_summary']/div[@class='hd']/div[@class='title']/span"));
    assertEquals("(${stockSymbolVerify} )".toString(), stockSymbolVerifyElement.getText());


});