import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Submit_Form {
    WebDriver driver;
    @BeforeAll
    public  void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void submit() throws InterruptedException {
      Random random = new Random();
      Actions action=new Actions(driver);

      // Generate a random integer with 2 digit
      int randomTwoDigitInt = 10 + random.nextInt(90);
      String mail="limon@gmail.com";
      String randomTwoDigitString = String.valueOf(randomTwoDigitInt);

      String new_mail="limon"+randomTwoDigitString+"@gmail.com";
      driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
      //fill up the first name
      driver.findElement(By.id("first_name")).sendKeys("Limon");
      //fill up the last name
      driver.findElement(By.id("last_name")).sendKeys("Alam");
      //fill up with unique mail address
      driver.findElement(By.id("user_email")).sendKeys(new_mail);
      //check the gender button
      driver.findElement(By.id("radio_1665627729_Male")).click();
      //fill up the password field
      driver.findElement(By.id("user_pass")).sendKeys("limon27121..");

      //select date of brith and fill up

      WebElement dateInput = driver.findElement(By.xpath("//input[@data-id='date_box_1665628538']"));
      String date = "2024-01-01";
      JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
      jsExecutor.executeScript("arguments[0].value = arguments[1];", dateInput, date);
      jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", dateInput);

      Utlis.scroll(driver,300);

      //fill up the phone_number field
      WebElement element=driver.findElement(By.id("phone_1665627880"));
      action.sendKeys(element,"1234567892");

      //fill emergency_contact
//      WebElement element1=driver.findElement(By.id("phone_1665627865"));
//      action.sendKeys(element1,"(123) 456-7890");

      //fill the nationality
       driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

//       select the country from the dropdown
       WebElement country=driver.findElement(By.id("country_1665629257"));
       Select select=new Select(country);
       select.selectByValue("BD");
       Utlis.scroll(driver,500);

      // check the privacy field
      driver.findElement(By.id("privacy_policy_1665633140")).click();

      //submit the form
      driver.findElement(By.xpath("//button[@type='submit']")).click();

      Thread.sleep(2000);

      //check message after submit the form

//
//        String successMsgExpected = "User successfully registered.";
//        String successMsg = driver.findElement(By.className("user-registration-message")).getText();
//        System.out.println(successMsg);
//        Assertions.assertEquals(successMsgExpected,successMsg);



//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
//        WebElement ul = driver.findElement(By.xpath("//ul[text() = 'User successfully registered.']"));
//        wait.until(
//                ExpectedConditions.visibilityOf(ul));
//        String successful_msg = ul.getText();
//        Assertions.assertTrue(successful_msg.contains("User successfully registered."));

        Utlis.scroll(driver,20);
        String successMsgExpected = "User successfully registered.";
        String successMsg = driver.findElement(By.className("user-registration-message")).getText();
        Assertions.assertEquals(successMsgExpected,successMsg);


    }


//    @AfterAll
//    public void close(){
//        driver.quit();
//    }
}
