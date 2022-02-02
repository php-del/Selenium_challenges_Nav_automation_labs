package Naveen_Selenium_challenge;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class naveen_selenium_challenge_part_3 {


	public static void main(String args[]) {
		System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.xpath("//*[@class='input_error form_input' and @id='user-name']")).sendKeys("standard_user");

		driver.findElement(By.xpath("//*[@class='input_error form_input' and @id='password']")).sendKeys("secret_sauce");

		driver.findElement(By.cssSelector("#login-button")).click();

		//Collected all web element prices
		List<WebElement> price=driver.findElements(By.xpath("//*[@class='inventory_item_price']"));

		//Removed dollar sign from prices

		String stringconvertedpriceayofpriceinstring[] = new String[price.size()];
		for(int i=0;i<price.size();i++) {
			stringconvertedpriceayofpriceinstring[i]=price.get(i).getText().replace('$',' ').trim();
		}

		//Parsed String value to float

		float stringconvertedprice[]=new float[stringconvertedpriceayofpriceinstring.length];

		for(int i=0;i<stringconvertedpriceayofpriceinstring.length;i++) {
			stringconvertedprice[i]=Float.parseFloat(stringconvertedpriceayofpriceinstring[i]);
		}

		//Sorted the floating values in descending order
		float temp;

		for(int i=0;i<stringconvertedprice.length;i++) {
			for(int j=i+1;j<stringconvertedprice.length;j++) {
				if(stringconvertedprice[i]<stringconvertedprice[j]) {
					temp=stringconvertedprice[i];
					stringconvertedprice[i]=stringconvertedprice[j];
					stringconvertedprice[j]=temp;
				}
			}
		}

		//Printed the sort on the console
		for(int i=0;i<stringconvertedprice.length;i++) {
			System.out.print(stringconvertedprice[i]+"  ");
		}

		//Located the button Add to cart with respect to the maximum price value and clicked on the button
		WebElement dd=driver.findElement(By.xpath("//div[@class='pricebar']//*[text()='Add to cart' and preceding-sibling::div[text()="+stringconvertedprice[0]+"]]"));


		dd.click();

		//Clicked on shopping cart
		driver.findElement(By.className("shopping_cart_link")).click();


		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
