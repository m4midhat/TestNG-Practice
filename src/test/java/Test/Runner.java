package Test;
import Base.TestBase;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.List;

@Slf4j
public class Runner extends TestBase{

    @Step("Add to Cart")
    @Test
    public void addToCart(){
        String title  =driver.getTitle();
        softAssert.assertEquals(title, "My Store", "Title is not 'My Store' !!!");

        String contactUs = driver.findElement(By.id("contact-link")).getText();
        softAssert.assertEquals(contactUs,"Contact us", "Contact us is missing");

        String loginTag = driver.findElement(By.className("login")).getText();
        //softAssert.assertEquals(loginTag,"Sign in", "'Sign in' is missing");
        softAssert.assertTrue(loginTag.equalsIgnoreCase("Sign in"), "'Sign in' is missing");

        WebElement productBlock = driver.findElement(By.cssSelector("ul#homefeatured"));
        String quantity = "";
        String totalPrice = "";
        String price = "";

        List<WebElement> itemList = productBlock.findElements(By.tagName("li"));
        softAssert.assertTrue(itemList.size()>5,"Item values less than 5");
        for(WebElement listItem:itemList){
            WebElement rightBlock = listItem.findElement(By.className("right-block"));
            price = rightBlock.findElement(By.className("content_price")).getText();
            WebElement productName = rightBlock.findElement(By.className("product-name"));

//        rightBlock.findElement(By.partialLinkText("Add")).click();
                Actions actions = new Actions(driver);
                actions.moveToElement(productName).perform();
                rightBlock.findElement(By.partialLinkText("Add")).click();
                while(driver.findElement(By.id("layer_cart_product_quantity")).getText().equalsIgnoreCase("")){

                }
                quantity = driver.findElement(By.id("layer_cart_product_quantity")).getText();
                totalPrice = driver.findElement(By.id("layer_cart_product_price")).getText().replace("$","").substring(0,5);
                driver.findElement(By.className("cross")).click();

                combinedPrice+= Double.parseDouble(totalPrice);


        }

        DecimalFormat two = new DecimalFormat("0.00");

        System.out.println(price);
        System.out.println(quantity);
        System.out.println(totalPrice);
        softAssert.assertAll();

        log.info("Total price " +two.format(combinedPrice));
    }

    @AfterSuite
    public  void closeBrowser()
    {

        driver.quit();

    }
}
