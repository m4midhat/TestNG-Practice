package Base;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestBase {

    protected SoftAssert softAssert = new SoftAssert();
    protected static WebDriver driver;
    protected static String baseUrl = "http://automationpractice.com/index.php";
    protected double combinedPrice=0.0;

    @Step ("initiateDriver")
    @BeforeSuite(dependsOnMethods = "initiateDriver")
    public void openURL(){
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Long.valueOf(60), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.valueOf(60), TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @BeforeSuite
    public void initiateDriver(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeTest
    public void beforeTest(){
        log.info("in before test");
    }

    @BeforeClass
    public void beforeClass(){
        log.info("in before class");
    }

    @BeforeMethod
    public void beforeMethod(){
        log.info("in before method");
    }

    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }

    @AfterTest
    public void report(){
        softAssert.assertAll();
    }
}
