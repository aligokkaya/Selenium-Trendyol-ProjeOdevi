import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestOne {


    WebDriver driver;


    @Test
   public void Test1() throws InterruptedException {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C:\\selenium2\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com/");
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"popupContainer\"]/div/div[2]/a/span[2]")).click();
        driver.findElement(By.xpath("//span[@id='not-logged-in-container']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("ali.gkky196@gmail.com");
        driver.findElement(By.name("password")).sendKeys("ali@19671570");

        driver.findElement(By.id("loginSubmit")).click();
        Thread.sleep(2000);

       /* driver.findElement(By.xpath("//*[@id=\"emailPreference\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[2]/div[2]/div[6]/div")).click();
       */
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement e = driver.findElement(By.xpath("//*[@id=\"auto-complete-app\"]/div/div/input"));
        e.sendKeys("bilgisayar" + Keys.ENTER);
        Thread.sleep(2000);

        String a = driver.findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div[2]/div[1]/div[1]/div")).getText();
        String[] countryLines = a.split(" ");
        //System.out.println(countryLines[3]);
        int ifade1 = Integer.valueOf(countryLines[3]);
        //"bilgisayar" araması için 6704 sonuç listeleniyor       yazısını ayrıştırıp ürün adet sayısını alıyorum Örn= 6704

        Random r = new Random();
        // int random =r.nextInt(ifade1);     // bilgisayarımın düşük özelliklerinden dolayı sayfa içerisindeki tüm ürün sayısından değilde kendi verdiğim sayı aralığında bir random değerle herhangi bir ürüne gidiyor
        int random = r.nextInt(24);

        String fiyat = driver.findElement(By.className("prdct-cntnr-wrppr")).findElements(By.className("prc-box-sllng")).get(random).getText();
        Thread.sleep(3000);
        System.out.println("ürünlistesindeki fiyat:" + fiyat);
        driver.findElement(By.className("prdct-cntnr-wrppr")).findElements(By.className("p-card-wrppr")).get(random).click();
        Thread.sleep(3000);
        driver.findElement(By.className("add-to-bs-tx")).click();
        Thread.sleep(3000);
        String sepetAdet = driver.findElement(By.xpath("//*[@id=\"basketItemCount\"]")).getText();
        System.out.println("Sepet adedi" + sepetAdet);
        int sepet = Integer.parseInt(sepetAdet);
        Thread.sleep(1000);

        if (sepet == 1) {

            Thread.sleep(1000);
            WebElement elem = driver.findElement(By.xpath("//*[@id=\"product-detail-app\"]/div/div[3]/div[2]/div/div/div/div[7]/a"));
            elem.click();
            Thread.sleep(1000);

            String sepetFiyat = driver.findElement(By.xpath("//*[@id=\"basketPreviewcontent\"]/div[1]/ul/li[1]/a/div[2]/p[2]/span")).getText();
            System.out.println("Sepetteki Fiyat" + sepetFiyat);
            Thread.sleep(1000);
            if (sepetFiyat != fiyat) {
                System.out.println("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatı eşittir.. ");
            } else {
                System.out.println("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatı eşit değildir.. ");

            }
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"product-detail-app\"]/div/div[2]/div[2]/div[2]/div[5]/button[1]")).click();
        } else {

            String sepetFiyat = driver.findElement(By.xpath("//*[@id=\"basketPreviewcontent\"]/div[1]/ul/li[1]/a/div[2]/p[2]/span")).getText();
            System.out.println("Sepetteki Fiyat" + sepetFiyat);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"myBasketListItem\"]")).click();
            Thread.sleep(1000);

            if (sepetFiyat != fiyat) {
                System.out.println("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatı eşittir.. ");
            } else {
                System.out.println("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatı eşit değildir.. ");

            }


            Thread.sleep(5000);
      //silme işlemi geröekleştirilecek..
            for(int k =sepet; k>=1; k--)     // sepet=sepetteki Ürün Adeti
            {
                driver.findElement(By.className("i-trash")).click();
                Thread.sleep(2000);
                driver.findElement(By.className("left")).findElements(By.tagName("Button")).get(1).click();
                Thread.sleep(1000);
                if(k==1)
                {
                    System.out.println("Sepette ürün bululunmamakta");
                }
            }




        }
    }

    //*[@id="ngdialog1"]/div[2]/form/div/div[2]/div/div[1]/button[2]
    //*[@id="ngdialog2"]/div[2]/form/div/div[2]/div/div[1]/button[2]
    @Test
    public void Test2()
    {
        //System.out.println("Test 2");
    }

    @AfterMethod
    public void afterMethod(){

       // System.out.println("After Method her testten sonra bir defa çalışır.");

    }
    @AfterSuite
    public void afterSuite() {

        //System.out.println("After Suite tüm testlerden önce bir defa çalışır.");
    }


}