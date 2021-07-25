package StepDefs;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps {
	
	WebDriver driver = BaseClass.driver;

    @Given("^User has already launched the application$")
    public void user_has_already_launched_the_application() throws Throwable {
    	 driver.get("https://www.saucedemo.com/");	
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        
    }

    @When("^User enters valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_valid_something_and_something(String username, String password) throws Throwable {
    	driver.findElement(By.id("user-name")).sendKeys(username);
    	driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("^User clicks on Login button$")
    public void user_clicks_on_login_button() throws Throwable {
       driver.findElement(By.id("login-button")).click();
    }
    
    @Then("^User should be succesfully logged in$")
    public void user_should_be_succesfully_logged_in() throws Throwable {
    	
   Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
       
    }

    @Then("^User should get the error message \"([^\"]*)\"$")
    public void user_should_get_the_error_message_something(String ErrorMessage) throws Throwable {
    	String message =  driver.findElement(By.xpath("//*[@data-test ='error']")).getText();
        Assert.assertEquals(ErrorMessage,message);
        }


}
