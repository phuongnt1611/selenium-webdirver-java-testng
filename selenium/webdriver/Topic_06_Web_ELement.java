package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_ELement {
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
		// Tương tác được với Element thì cần phải tìm được element đó
		// Thông qua locator của nó
		
		// By: Id/ class/ name/ xpath/css/ tagname/linktext/ partialLinkText
		WebElement element = driver.findElement(By.className(""));
		
		// Xóa dữ liệu đi trước khi nhập text
		element.clear();
		
		// dùng cho các textbox/ textarea/ dropdown (Editable)
		// Nhập liệu
		element.sendKeys("");
		
		// Click vào các button/ Link/ checkbox/ radio/ image...
		element.click();
		
		// WebElement support tìm element liên tục=> Nhưng ko nên dùng
		driver.findElement(By.cssSelector(""));
		driver.findElement(By.cssSelector("")).findElement(By.cssSelector("")).findElement(By.cssSelector(""));
		
		// Lấy ra giá trị cua thuộc tính
		String searchAttribute = element.getAttribute("placeholder");
		String emailTextBox = element.getAttribute("value");
		
		// Lấy ra giá trị của thuộc tính css: Font/ Size/ Color/ Location/ Position/...
		String css = element.getCssValue("");
		
		// Vị trí của element so với web bên ngoài
		Point point= element.getLocation();
		point.x= 23;
		point.y= 32;
		
		
		// Kích thước của element bên trong
		Dimension di = element.getSize();
		di.getHeight();
		di.getWidth();
		
		// location + size
		element.getRect();
		
		// Chụp lại result test khi failed
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		// Cần lấy ra tên thẻ HTML của element đó => Truyền vào cho 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("")).getTagName();
		
		String emailTextboxTagename = driver.findElement(By.cssSelector("")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagename + "[@id='email']"));
		
		
		// Lấy text từ error message/ success message/ Label/ header
		element.getText();
		
		// Khi nào dùng getText(), khi nào dùng getAttribute
		// Khi nó nằm bên trong thẻ thì dùng getAttribute()
		// Khi nó nằm bên ngoài thẻ thì dùng getText()
		
		// Dùng để verify xem 1 element hiển thị hay không: cho tất cả các element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		//Dùng để verify 1 element có thao tác được hay ko: cho tất cả các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Dùng để verify xem 1 element được chọn hay chưa
		// Phạm vi: checkbox/ radio
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		//các element nằm trong thẻ form đều có thể submit
		//Tương ứng với hành động Enter của end user
		element.submit();
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