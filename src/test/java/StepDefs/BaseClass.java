package StepDefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseClass {
	public static WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webDriver.chrome.driver", "driver");
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {		
		driver.quit();
	}
	

}
