package StepDefs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {
	
	WebDriver driver = BaseClass.driver;
	WebDriverWait wait = new WebDriverWait(driver,20);
	
	 @Given("^User has launched the SwagLags Application$")
	    public void launchedSwaglagsApplication() throws Throwable {
		 driver.get("https://www.saucedemo.com/");	
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		         
	    }

	  @When("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
	    public void EntersUserNamePassword(String username, String password) throws Throwable {
	    	driver.findElement(By.id("user-name")).sendKeys(username);
	    	driver.findElement(By.id("password")).sendKeys(password);
	    }
	  
	  @And("^click on Login button$")
	    public void click_on_login_button() throws Throwable {
		  driver.findElement(By.id("login-button")).click();
	    }
	  
	    @Given("^User should be in the home page$")
	    public void checkHomepage() throws Throwable {
	   
	    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Products']"))));
	      	
	    }

	    @When("^User click on Add to Cart button of the item \"([^\"]*)\"$")
	    public void clickOnAddToCartOfSingleItem(String item) throws Throwable {
	    		
	    	System.out.println("The product name is " + item);
	    	driver.findElement(By.xpath("//div[text()='"+item +"']//following::button[1]")).click();
	    	Thread.sleep(1000);
	    	
	    	//verify the change in text shown on the button after adding to cart
	    	Assert.assertEquals("REMOVE", driver.findElement(By.xpath("//div[text()='"+item +"']//following::button[1]")).getText());
	    	   
	    }

	    @Then("^User should be able to see the added product \"([^\"]*)\" in checkout page$")
	    public void viewProductInCheckoutpage(String item) throws Throwable {
	    
	    //Verify the count of items shown in cart (as seen from inventory page)	
	    String count = driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText();
	    	Assert.assertEquals("1", count);  
	    	
	   //Open checkout page	
	   driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	   
	   // Wait until the display of Your Cart Title 
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class='title' and text()='Your Cart']")))); 
	  
	  //Verify the added product is displayed in the checkout page 
	  Assert.assertEquals(item, driver.findElement(By.xpath("//div[@class = 'inventory_item_name']")).getText());
	    	    
	    }
	       	    
	    @When("^User clicks on Add to Cart button of multiple products$")
	    public void uaddMultipleProducts(DataTable table) throws Throwable {
	     	
	    	for(int i = 0;i<3;i++) {
	    	driver.findElement(By.xpath("//div[text()='"+ table.cell(i, 0) +"']//following::button[1]")).click();
	    	}
	       
	    }
	    
	    @Then("^All the added product should be in the checkout page$")
	    public void productsInCheckoutPage(DataTable table) throws Throwable {
	    	
	    //Verify the count of items in the cart	
		  Assert.assertEquals("3", driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText()); 
	    	
	      driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	   	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class='title' and text()='Your Cart']"))));
	   	  
	   	  List<WebElement> list  = driver.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
	   	  
	   	//Verify count of items in checkout page 
	   	  Assert.assertTrue(list.size()==3);
	   	  
	   	  //Verify the names of the item in checkout 
	   	  	for (int i = 0;i<3;i++) {
	    	 Assert.assertEquals(table.cell(i, 0),list.get(i).getText() );
	   	  	}
	    }

	   

}
