package util;

import com.file.FileApplicationTests;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
@Lazy
public class AllDrivers {
// Please change the gecko driver path

        public static WebDriver driver ;
@Autowired
    public void setDrivers(String browser){ //
        if (driver == null) {
            if (browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }
    }

    public void takeScreenShot() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        File MyFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(MyFile,new File("src/test/java/drivers/Screenshots" + timeStamp + ".png"));
    }
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
}


