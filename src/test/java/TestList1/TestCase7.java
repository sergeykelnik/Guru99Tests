package TestList1;

import com.codeborne.selenide.Selenide;
import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

//Add listener for pdf report generation
//@Listeners(JyperionListener.class)
//@Listeners(CustomReporting.class)
public class TestCase7 {

    @Test(priority = 10)
    public void GetOrderExport() {
        System.setProperty("selenide.browser", "Chrome");
        Selenide.clearBrowserCookies();
        open("http://live.guru99.com/index.php/backendlogin/");
        $("#username").setValue("user01");
        $("#login").setValue("guru99com").pressEnter();
        $(By.xpath("//a[@title='close']")).waitUntil(visible, 10000);
        $(By.xpath("//a[@title='close']")).click();
        $(By.xpath("//span[contains(text(),'Sales')]")).hover();
        $(By.xpath("//span[contains(text(),'Orders')]")).waitUntil(visible, 10000);
        $(By.xpath("//span[contains(text(),'Orders')]")).click();
        $(By.xpath("//span[contains(text(),'Export')]")).click();
        sleep(5000);
    }

    @Test(priority = 11)
    public void ParseCSV() {
        String csvFile = "C:\\Users\\Sergey Kelnik\\Downloads\\orders.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("id = " + line[0] + ", time = " + line[1] + ", name = " + line[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
