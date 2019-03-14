package TestList1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class Config {


    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        // Check if parameter passed from TestNG.xml is 'firefox'
        if (browser.equalsIgnoreCase("firefox")) {
            // create firefox instance
            Configuration.browser = System.getProperty("browser", WebDriverRunner.FIREFOX);

        }
        // Check if parameter passed as 'chrome'
        else if (browser.equalsIgnoreCase("chrome")) {
            // create chrome instance
            Configuration.browser = System.getProperty("browser", WebDriverRunner.CHROME);
            Configuration.startMaximized = true;
        }
        // Check if parameter passed as 'ie'
        else if (browser.equalsIgnoreCase("ie")) {
            // create ie instance
            Configuration.browser = System.getProperty("browser", WebDriverRunner.IE);
        }
        // Check if parameter passed as 'edge'
        else if (browser.equalsIgnoreCase("edge")) {
            // create edge instance
            Configuration.browser = System.getProperty("browser", WebDriverRunner.EDGE);
        } else {
            // If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
    }

    public static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            // Set from address
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //Set subject
            message.setSubject(subject);
            message.setText(body);
            BodyPart objMessageBodyPart = new MimeBodyPart();
            objMessageBodyPart.setText("Please Find The Attached Report File!");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(objMessageBodyPart);
            objMessageBodyPart = new MimeBodyPart();
//Set path to the pdf report file
            String filename = "D:\\IDEA_workspace\\HaldexUITests\\Test.pdf";
            //Create data source to attach the file in mail
            DataSource source = new FileDataSource(filename);
            objMessageBodyPart.setDataHandler(new DataHandler(source));
            objMessageBodyPart.setFileName(filename);
            multipart.addBodyPart(objMessageBodyPart);
            message.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    // After complete execution send pdf report by email
   // @AfterSuite
    /*public void SendMail() {
        sendPDFReportByGMail("gigamonsterrr@gmail.com", "fymgbakfzxfsmqsy", "gigamonsterrr@gmail.com", "CSV Report", "");
    }*/
}