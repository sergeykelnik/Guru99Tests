package TestList1;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
//Add listener for pdf report generation
@Listeners(JyperionListener.class)
//@Listeners(CustomReporting.class)
public class TestCase5 {
   /* @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
        System.setProperty("webdriver.edge.driver", "C:\\MicrosoftWebDriver.exe");
        Configuration.browser = "chrome";
        //Configuration.headless = true;
        Configuration.baseUrl = "http://localhost:8080";
        Configuration.startMaximized = true;
}*/
    // Random email generator
    private Random random = new Random();
    private int number = random.nextInt(100000);
    private String randoms = String.format("%03d", number);
    private String email = "mail" + randoms + "@mail.ee";
    // Random email generator
    private String order1 = null;
    private String order3 = null;
    private float PriceAfterNumber = 0;
    private float PriceBeforeNumber = 0;

    @Test(priority = 5)
    void RegisterAndAddWishList() {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
        $(By.linkText("My Account")).click();
        $(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a")).click();
        $("#firstname").setValue("Sergey");
        $("#lastname").setValue("Kelnik");
        $("#email_address").setValue(email);
        $("#password").setValue("12345678");
        $("#confirmation").setValue("12345678");
        $(By.xpath("//*[@id=\"form-validate\"]/div[1]/ul/li[4]/label")).click();
        $(By.xpath("//button[@title='Register']")).click();
        Assert.assertEquals(
                $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText(),
                "Thank you for registering with Main Website Store.");
        $(By.linkText("TV")).click();
        $(By.tagName("select")).selectOption("Position");
        $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[2]/div/div[3]/ul/li[1]/a"))
                .click();
        Assert.assertEquals($(By.xpath("//h3/a[@title='Samsung LCD']")).getText(), "SAMSUNG LCD");
        $(By.name("save_and_share")).click();
        $(By.name("emails")).setValue("jezixapoze@4senditnow.com");
        $(By.name("message")).setValue("Hi!");
        $(By.xpath("//button[@title='Share Wishlist']")).click();
        Assert.assertEquals(
                $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText(),
                "Your Wishlist has been shared.");
    }

    @Test(priority = 6)
    void LoginAndBuyWishList() {
        Selenide.clearBrowserCookies();
        $(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
        $(By.linkText("My Account")).click();
        $("#email").setValue(email);
        System.out.println(email);
        $("#pass").setValue("12345678");
        $("#send2").click();
        $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[1]/div/div[2]/ul/li[8]/a")).click();
        $(By.xpath("//button[@title='Add to Cart']")).click();
        $(By.xpath("//*[@id=\"country\"]")).selectOptionByValue("BY");
        $(By.xpath("//*[@id=\"region\"]")).setValue("Grodno");
        $(By.xpath("//*[@id=\"postcode\"]")).setValue("230030");
        String PriceBefore = $(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span")).getText()
                .substring(1);
        PriceBeforeNumber = Float.parseFloat(PriceBefore);
        $(By.xpath("//button[@title='Estimate']")).click();
        $(By.xpath("//*[@id=\"s_method_flatrate_flatrate\"]")).click();
        $(By.xpath("//button[@title='Update Total']")).click();
        String PriceAfter = $(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span")).getText()
                .substring(1);
        PriceAfterNumber = Float.parseFloat(PriceAfter);
        Assert.assertEquals(PriceBeforeNumber + 5, PriceAfterNumber);
        $(By.xpath("//button[@title='Proceed to Checkout']")).click();
        $(By.xpath("//*[@id=\"billing:firstname\"]")).setValue("Sergey");
        $(By.xpath("//*[@id=\"billing:lastname\"]")).setValue("Kelnik");
        $(By.xpath("//*[@id=\"billing:street1\"]")).setValue("Ulitsa");
        $(By.xpath("//*[@id=\"billing:city\"]")).setValue("Minsk");
        $(By.xpath("//*[@id=\"billing:postcode\"]")).setValue("230030");
        $(By.xpath("//*[@id=\"billing:country_id\"]")).selectOptionByValue("BY");
        $(By.xpath("//*[@id=\"billing:telephone\"]")).setValue("12345678");
        $(byTitle("Continue")).click();
        $(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).waitUntil(visible, 3000).click();
        $(byValue("checkmo")).waitUntil(visible, 3000).click();
        $(By.xpath("//*[@id=\"payment-buttons-container\"]/button")).click();
        $(byTitle("Place Order")).click();
        order1 = $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
        System.out.println(order1);

    }

    @Test(priority = 7)
    void CheckOrderAndGetPdf() {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
        $(By.linkText("My Account")).click();
        $("#email").setValue(email);
        $("#pass").setValue("12345678");
        $("#send2").click();
        $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[1]/div/div[2]/ul/li[4]/a")).click();
        $(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr/td[6]/span/a[1]")).click();
        String order2 = $(By.tagName("h1")).getText();
        order2 = (order2.substring(7, 16));
        Assert.assertEquals(order2, order1);
        $(By.linkText("Print Order")).shouldBe(visible);

    }

    @Test(priority = 8)
    void ReorderProduct() throws ParseException {
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/");
        $(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
        $(By.linkText("My Account")).click();
        $("#email").setValue(email);
        $("#pass").setValue("12345678");
        $("#send2").click();
        $(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr[1]/td[6]/span/a[2]")).click();
        $(byTitle("Qty")).setValue("10");
        $(byTitle("Update")).click();
        Assert.assertEquals(NumberFormat.getNumberInstance(Locale.UK)
                .parse($(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span"))
                        .getText()
                        .substring(1))
                .floatValue(), PriceBeforeNumber * 10);
        $(By.xpath("//button[@title='Proceed to Checkout']")).click();
        $(byTitle("Continue")).click();
        $(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
        $(byValue("checkmo")).waitUntil(visible, 3000).click();
        $(By.xpath("//*[@id=\"payment-buttons-container\"]/button")).click();
        $(byTitle("Place Order")).click();
        order3 = $(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
        System.out.println(order3);
    }
}
