package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Qunarquery {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.qunar.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testQunarquery() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("js_intersearchtype_roundtrip")).click();
    driver.findElement(By.xpath("(//input[@name='fromCity'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='fromCity'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@name='fromCity'])[2]")).sendKeys("上海(SHA)");
    driver.findElement(By.xpath("(//input[@name='toCity'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='toCity'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@name='toCity'])[2]")).sendKeys("新加坡");
    driver.findElement(By.cssSelector("#fromDate")).click();
    driver.findElement(By.cssSelector("#fromDate")).clear();
    driver.findElement(By.cssSelector("#fromDate")).sendKeys("2015-08-05");
    driver.findElement(By.cssSelector("#toDate")).click();
    driver.findElement(By.cssSelector("#toDate")).clear();
    driver.findElement(By.cssSelector("#toDate")).sendKeys("2015-08-12");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("#filter_showAllprice_yes"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("#filter_showAllprice_yes")).click();
    String price = driver.findElement(By.cssSelector("label.label-box")).getText();
    System.out.println("the lowest price is " + price);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
