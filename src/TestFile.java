import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class TestFile {
	private WebDriver driver;

	@BeforeEach
	void setUp() {
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver();

		String url = "http://pod01.cs.virginia.edu:8080/computeGPA/calculategpa.jsp";
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
	void test_GPA_from_A_plus() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_GPA_from_A() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_GPA_from_A_minus() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.7"));
	}

	@Test
	void test_GPA_from_B_plus() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.33"));
	}

	@Test
	void test_GPA_from_B() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(5)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.0"));
	}

	@Test
	void test_GPA_from_B_minus() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 2.67"));
	}

	@Test
	void test_GPA_from_C() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 2.0"));
	}

	@Test
	void test_GPA_from_F() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(8)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 0.0"));
	}

	@Test
	void test_0_Cs() {
		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertFalse(element.getAttribute("innerHTML").contains("Only 2 grades of C are allowed for graduation"));
	}

	@Test
	void test_1_C() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertFalse(element.getAttribute("innerHTML").contains("Only 2 grades of C are allowed for graduation"));
	}

	@Test
	void test_2_Cs() {
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

	@Test
	void test_blank_credits() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		System.out.println(element.getAttribute("innerHTML"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 0.0"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 30 more hours to graduate."));

		// entering no credits at all should be treated as 0 credits
	}

	@Test
	void test_0_credits() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("0");
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		System.out.println(element.getAttribute("innerHTML"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 0.0"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 30 more hours to graduate."));
	}

	@Test
	void test_1_through_29_above_3() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("1");
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		System.out.println(element.getAttribute("innerHTML"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.33")); // current GPA higher than remaining credits must be
		assertTrue(element.getAttribute("innerHTML").contains("You need 29 more hours to graduate."));
	}

	@Test
	void test_1_through_29_below_3() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		WebElement credits = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("4");
		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("at least a B+ average")); // Remaining credits must be higher than current GPA.
		assertTrue(element.getAttribute("innerHTML").contains("You need 17 more hours to graduate."));
	}

	@Test
	void test_above_30_above_3() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		WebElement credits = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("4");
		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(3) > input:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.")); // Don't need to know what the rest is, just need to know that it's >= 3.0
		assertTrue(element.getAttribute("innerHTML").contains("which is enough to graduate with an MS degree."));
		assertFalse(element.getAttribute("innerHTML").contains("However, you do not have the minimum GPA of 3.0."));
	}

	@Test
	void test_above_30_below_3() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		WebElement credits = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("4");
		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > input:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(3) > input:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(3) > input:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(3) > input:nth-child(5)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(3) > input:nth-child(5)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 2.")); //Dont need to know what the rest is, just need to know that it's < 3.0
		assertTrue(element.getAttribute("innerHTML").contains("which is enough to graduate with an MS degree."));
		assertTrue(element.getAttribute("innerHTML").contains("However, you do not have the minimum GPA of 3.0."));
	}

	@Test
	void test_above_30_above_3_more_Cs() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		WebElement credits = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("4");
		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(3) > input:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(3) > input:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(3) > input:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(12) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.")); //Dont need to know what the rest is, just need to know that it's < 3.0
		assertTrue(element.getAttribute("innerHTML").contains("You have 31 hours, which is enough to graduate with an MS degree."));
		assertTrue(element.getAttribute("innerHTML").contains("Only 2 grades of C are allowed for graduation and 3 were submitted, so the additional Cs are not included in the total number of hours accumulated."));
	}

	@Test
	void test_above_30_above_3_one_F() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		WebElement credits = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("4");
		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(3) > input:nth-child(8)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(3) > input:nth-child(2)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(12) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 3.")); //Dont need to know what the rest is, just need to know that it's >= 3.0
		assertTrue(element.getAttribute("innerHTML").contains("Fs cannot be used to graduate, so are not included in the total number of hours accumulated"));
		assertTrue(element.getAttribute("innerHTML").contains("You have 31 hours, which is enough to graduate with an MS degree."));
	}

	@Test
	void test_empty_string() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(1) > input[type=text]"));
		assertEquals("input", input.getTagName());
		input.sendKeys("");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 27 more hours to graduate."));
	}

	@Test
	void test_a_string() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(1) > input[type=text]"));
		assertEquals("input", input.getTagName());
		input.sendKeys("Calculus III");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 27 more hours to graduate."));
	}

	@Test
	void test_strings_and_empty_strings() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(1) > input[type=text]"));
		assertEquals("input", input.getTagName());
		input.sendKeys("Calculus III");

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(1) > input[type=text]"));
		assertEquals("input", input.getTagName());
		input.sendKeys("");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 24 more hours to graduate."));
	}

	@Test
	void test_0_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("0");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 30 more hours to graduate."));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 0.0"));
	}

	@Test
	void test_1_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("1");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 29 more hours to graduate."));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_2_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("2");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 28 more hours to graduate."));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_3_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("3");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 27 more hours to graduate."));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_4_to_29_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("14");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You need 16 more hours to graduate."));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_30_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("30");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("You have 30 hours")); // distinct from our test of whether a single 30-credit class is sufficient to graduate; simply testing whether high numbers of credit hours in a single course are handled properly
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_negative_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("-5");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());

		assertThrows(Exception.class, submit::click);
	}

	@Test
	void test_invalid_character_credit_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input[type=text]"));
		assertEquals("input", credits.getTagName());
		credits.clear();
		credits.sendKeys("-5");

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());

		assertThrows(Exception.class, submit::click);
	}

	@Test
	void test_30_credits_10_courses() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement another = driver.findElement(By.cssSelector("#theForm > a"));
		assertEquals("Add another course", another.getAttribute("innerHTML"));
		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(3) > td:nth-child(3) > input[type=radio]:nth-child(5)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table > tbody > tr:nth-child(4) > td:nth-child(3) > input[type=radio]:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > input:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(3) > input:nth-child(6)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(3) > input:nth-child(3)"));
		assertEquals("input", input.getTagName());
		input.click();

		another.click();

		input = driver.findElement(By.cssSelector("#theForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(3) > input:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("which is enough to graduate with an MS degree"));
	}

	@Test
	void test_30_credits_1_course() {
		WebElement credits = driver.findElement(By.cssSelector("#firstRow > td:nth-child(2) > input:nth-child(1)"));
		credits.clear();
		credits.sendKeys("30");
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(4)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		System.out.println(element.getAttribute("innerHTML"));
		assertFalse(element.getAttribute("innerHTML").contains("which is enough to graduate with an MS degree")); // should be false, as 10 courses are required
	}

	@Test
	void test_back_button() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		driver.navigate().back();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_forward_button() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(7)"));
		assertEquals("input", input.getTagName());
		input.click();

		submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		driver.navigate().back();
		driver.navigate().forward();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 2.0"));
	}

	@Test
	void test_refresh_button_no_resubmit() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		driver.navigate().refresh();
		driver.switchTo().alert().dismiss();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}

	@Test
	void test_refresh_button_do_resubmit() {
		WebElement input = driver.findElement(By.cssSelector("#firstRow > td:nth-child(3) > input[type=radio]:nth-child(1)"));
		assertEquals("input", input.getTagName());
		input.click();

		WebElement submit = driver.findElement(By.name("submitCourses"));
		assertEquals("button", submit.getTagName());
		submit.click();

		driver.navigate().refresh();
		driver.switchTo().alert().accept();

		WebElement element = driver.findElement(By.cssSelector("body"));
		assertTrue(element.getAttribute("innerHTML").contains("Your current GPA is: 4.0"));
	}
}
