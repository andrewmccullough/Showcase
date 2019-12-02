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
	private String url = "http://pod01.cs.virginia.edu:8080/computeGPA/calculategpa.jsp";  // subject under test (sut)

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
		assertEquals("Grade Calculator", driver.getTitle());
	}

	@Test
	void test_2_or_less_Cs() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertFalse(element.getAttribute("innerHTML").contains("Only 2 grades of C are allowed for graduation"));
	}

	@Test
	void test_3_or_more_Cs() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Only 2 grades of C are allowed for graduation"));
	}

	@Test
	void test_no_Fs() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertFalse(element.getAttribute("innerHTML").contains("Fs cannot be used to graduate, so are not included in the total number of hours accumulated. Fs are also not included in your GPA calculation."));
	}

	@Test
	void test_F() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(8)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		System.out.println(element.getAttribute("innerHTML"));
		assertTrue(element.getAttribute("innerHTML").contains("Fs cannot be used to graduate, so are not included in the total number of hours accumulated."));
	}
}
