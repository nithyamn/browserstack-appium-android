package com.browserstack;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
      AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
      searchElement.click();
      AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
      insertTextElement.sendKeys("BrowserStack");
      Thread.sleep(5000);

      //driver.findElementByAccessibilityId("\torg.wikipedia.alpha:id/search_container")
      List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
      Assert.assertTrue(allProductsName.size() > 0);
        
      JavascriptExecutor jse = (JavascriptExecutor)driver;
        if(allProductsName.size() < 0) {
            //TestStatus.mark(session, "passed", username, accesskey);
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Validated\"}}");

        }
        else{
            //TestStatus.mark(session,"failed", username, accesskey);
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Not Validated\"}}");

        }
      
    }
}
/*
curl -u "nithyamani3:P4JKysg5WuchQxBfKQu1" \
  -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
  -F "file=@/Users/nithyamani/Desktop/LocalSample.apk"
*/
