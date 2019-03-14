package TestList1;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
//Add listener for pdf report generation
@Listeners(JyperionListener.class)
//@Listeners(CustomReporting.class)
public class TestCase2 {

    @Test(priority = 2)
    public void VerifyPricesPdpPlp() {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.xpath("//a[contains(text(),'Mobile')]")).click();
        String Price = $(By.id("product-price-1")).getText();
        $("#product-collection-image-1").click();
        System.out.println(Price);
        Assert.assertEquals(Price, $(By.id("product-price-1")).getText());
    }

}
