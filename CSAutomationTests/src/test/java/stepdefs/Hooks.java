package stepdefs;

import com.pages.BasePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Hooks extends BasePage {
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://coinmarketcap.com/");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed() && driver != null) {
            String filePath = takeScrSht();
            File file = new File(filePath);
            try {
                scenario.embed(Files.readAllBytes(file.toPath()), "image/png");
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        if (driver != null) {
            driver.quit();
        }
    }


	public static String takeScrSht() {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(System.getProperty("user.dir")+"/src/test/resources/Screens/Screen_Failed_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".png");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return DestFile.getAbsolutePath();

	}
}
