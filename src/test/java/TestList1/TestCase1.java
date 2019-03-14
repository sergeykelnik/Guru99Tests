package TestList1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
//Add listener for pdf report generation
@Listeners(JyperionListener.class)
//@Listeners(CustomReporting.class)
public class TestCase1 {

    @Test(priority = 1)
    public void SortMobilesByName() {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.xpath("//h2[contains(text(),'This is demo site for')]")).shouldBe(Condition.visible);
        assertEquals(title(), "Home page");
        $(By.linkText("MOBILE")).click();
        sleep(1000);
        assertEquals(title(), "Mobile");
        $(By.tagName("select")).selectOption("Name");
        Assert.assertEquals($(By.xpath("//li[1]//a[@class=\"product-image\"]")).getAttribute("title"), "IPhone");
        Assert.assertEquals($(By.xpath("//li[2]//a[@class=\"product-image\"]")).getAttribute("title"),
                "Samsung Galaxy");
        Assert.assertEquals($(By.xpath("//li[3]//a[@class=\"product-image\"]")).getAttribute("title"), "Xperia");
    }

}
