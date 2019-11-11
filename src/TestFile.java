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

public class TestFile {
	private WebDriver driver;
	private String url = "http://pod01.cs.virginia.edu:8080/computeGPA/calculategpa.jsp";  // subject under test (sut)

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/chromedriver");       // specify path to the driver
		driver = new ChromeDriver();    // open a web browser

//      System.setProperty("webdriver.chrome.driver", "/path-to-your-driver/chromedriver");   // specify path to the driver
//      driver = new ChromeDriver();   // open a web browser

		driver.get(url);                 // open the given url
	}

	@AfterEach
	public void teardown() {
		driver.quit();                   // close the browser
	}

	@Test
	public void test_openURL() {
		// check if we are on the right page
		assertEquals(driver.getTitle(), "Measurement Conversion");
	}
}
