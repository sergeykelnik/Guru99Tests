package TestList1;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
//Add listener for pdf report generation
@Listeners(JyperionListener.class)
//@Listeners(CustomReporting.class)
public class TestCase4 {

    @Test(priority = 4)
    public void CompareTwoProducts() {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.linkText("MOBILE")).click();
        $(By.tagName("select")).selectOption("Position");

        // Workaround for IE
        if ($(By.linkText("Clear All")).exists()) {
            $(By.linkText("Clear All")).click();
            confirm();
        }
        // Workaround for IE

        $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a"))
                .click();
        $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a"))
                .click();
        $(By.xpath("//button[@title='Compare']")).click();
        switchTo().window("Products Comparison List - Magento Commerce");
        Assert.assertEquals($(By.xpath("//h2/a[@title='Sony Xperia']")).getText(), "SONY XPERIA");
        Assert.assertEquals($(By.xpath("//h2/a[@title='Samsung Galaxy']")).getText(), "SAMSUNG GALAXY");
        $(By.xpath("//button[@title='Close Window']")).click();
        switchTo().window("Mobile");
    }

}