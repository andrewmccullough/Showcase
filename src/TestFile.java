import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// to handle alert popup window
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Alert;

class TestFile {
	private WebDriver driver;
	private String url = "http://www.cs.virginia.edu/~up3f/swtesting/sut/convert.php";  // subject under test (sut)

	@BeforeEach
	void setUp() {
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver();

		driver.get(url);
	}

	@AfterEach
	void teardown() {
		driver.quit();
	}

	@Test
	void test_openURL() {
		// check if we are on the right page
		assertEquals(driver.getTitle(), "Measurement Conversion");
	}
}
