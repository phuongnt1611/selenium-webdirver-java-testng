package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[@text()='Add Employee']")).click();
		sleepInSecond(3);
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.sendKeys(employeeID);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label/span")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).clear();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
				.sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).clear();
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
				.sendKeys("Password@123");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
				.sendKeys("Password@123");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]"));
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

	}

	public void sleepInSecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TC_02_Verify_Employee() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}