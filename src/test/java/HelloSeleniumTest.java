import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSeleniumTest {
    String pw = "";
    String eml = "mukaddamkhamraeva@gmail.com";
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void test(){
        //Open website
        driver.get("https://www.etsy.com/");
        //Simple selenium commands
        testOpenItem();
        testSearchItem();
        testLikeItem();
        testOpenLikedItems();
        //Sign in
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        testSignIn();
    }
    public void testOpenItem(){
        String jewelry = "//*[@id=\"content\"]/div/div[1]/div[2]/div/ul/li[4]/a/div[1]/div/img";
        driver.findElement(By.xpath(jewelry)).click();
        testScrollPage();
        driver.navigate().back();
    }

    public void testSearchItem(){
        String search = "//*[@id=\"gnav-search\"]/div/div[1]/button[2]";
        driver.findElement(By.name("search_query")).sendKeys("vermeer");
        driver.findElement(By.xpath(search)).click();
    }

    public void testLikeItem(){
        String item = "//*[@id=\"content\"]/div/div[1]/div/div[4]/div[5]/div[3]/div[10]/div[1]/div/div/ul/li[8]/div/div/a/div[1]/div/div/div/div/div/img";
        String img_like = "//*[@id=\"content\"]/div/div[1]/div/div[4]/div[5]/div[3]/div[10]/div[1]/div/div/ul/li[8]/div/div/div/button";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(item));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.xpath(img_like)).click();
        driver.navigate().back();

    }

    public void testOpenLikedItems(){
        String input = "//*[@id=\"gnav-header-inner\"]/div[4]/nav/ul/li[2]/span";
        driver.findElement(By.xpath(input)).click();
    }

    public void testSignIn(){
        driver.navigate().to("https://www.etsy.com/signin");
        driver.findElement(By.name("email")).sendKeys(eml);
        driver.findElement(By.name("password")).sendKeys(pw);
        driver.findElement(By.name("submit_attempt")).click();
    }

    public void testScrollPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0;i<500;i++) {
            js.executeScript("window.scrollBy(0,2)", "");
        }
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(6000);
        driver.quit();
    }
}
