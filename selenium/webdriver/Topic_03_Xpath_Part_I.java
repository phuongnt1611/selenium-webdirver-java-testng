package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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

	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// //button[@class='btn_pink_sm fs16']

		// co 3 cach verify
		// Assert.assertTrure ->
		// Assert.assertFalse -> Kiểm tra 1 đk trả về là sai
		// Assert.assertEquals -> Kiểm tra thực tế với mong đợi là như nhau

		// Data Type: WebElement = String
		// Data Type: String= String
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_With_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("1231323@12121@1212");
		driver.findElement(By.id("txtCEmail")).sendKeys("1231323@12121@1212");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_Register_With_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("john@wick.net");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Register_With_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Register_With_Incorrect_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678912");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Register_With_Invalid_PhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action 1
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("john@wick.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("098788");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0987881212121212");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify 2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Action 3
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("98788121212");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify 3
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}