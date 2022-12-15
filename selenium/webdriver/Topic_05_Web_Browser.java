package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	// Tương tác với Browser thì sẽ thoogn qua biến WebDriver driver;
	// Tương tác với Element thì sẽ thông qua biến WebElement element;
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		// >=2: Nó sẽ đóng tab/ Window mà nó đang đứng
		// = 1 Nó sẽ đóng Browser
		driver.close();

		// Ko quan tâm bao nhiêu tab/window => đóng hết
		driver.quit();

		// Có thể lưu nó vào 1 biến để sử dụng cho các step sau => dúng lại nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("input[@id='email']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("adad");

		// Nếu dùng lại code nhiều lần sẽ làm cho code ko clean, code chạy chậm
		// WebElement: Chứa 1 element
		// List<WebElement>: Chứa nhiều elements
		List<WebElement> checkBoxes= driver.findElements(By.xpath(osName));
		
		// Mở ra 1 trang nào đó
		driver.get("https://www.facebook.com/");
		
		//trả về URL của 1 page hiện tại
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL,"https://www.facebook.com/");
		
		// Trả về Source code HTML của page hiện tại
		// Verify tương đối
		Assert.assertTrue(driver.getPageSource().contains(""));
		
		// Trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		// Webdriver API - Windows/ Tab
		// Lấy ra được ID của window/tab mà driver đang đứng(active)
		String loginWindowID = driver.getWindowHandle();
		
		// Lấy ra ID của các Window/Tab
		Set<String> allIDs = driver.getWindowHandles();
		
		// Cookie và Cache
		Options opt = driver.manage();
		
		// Login thành công -> Lưu lại
		opt.getCookies();
		// Test case khác -> Set cookie vào để ko cần phải login nữa
		
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		// Khoảng thời gian chờ cho element xuất hiện
		time.implicitlyWait(5, TimeUnit.SECONDS); // 5s= 5000ms= 5000000 µSEC
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		// Chờ 1 page load xong trong vòng x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		// Khoảng thời gian chwof script được thực thi trong vòng x giây
		time.setScriptTimeout(0, null);
		
		Window win = opt.window();
		win.fullscreen();
		win.maximize();
		
		// Test GUI: Font/ Size/ Color/ Position/ Location....
		win.getPosition();
		win.getSize();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		nav.to("");
		
		TargetLocator tar= driver.switchTo();
		tar.alert();
		
		tar.frame("");
		
		tar.window("");	

	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}