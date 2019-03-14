package TestList1;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
//Add listener for pdf report generation
@Listeners(JyperionListener.class)
//@Listeners(CustomReporting.class)
public class TestCase6 {

    @Test(priority = 9)
    public void EnterProductCoupon() {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.xpath("//a[contains(text(),'Mobile')]")).click();
        $(By.tagName("select")).selectOption("Name");
        $(By.xpath("//ul[@class='products-grid products-grid--max-4-col first last odd']//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
        $(By.xpath("//*[@id=\"coupon_code\"]")).setValue("GURU50");
        $(By.xpath("//span[contains(text(),'Apply')]")).click();
        $(By.xpath("//li[@class='success-msg']")).shouldHave(text("applied"));
    }
}