package org.appiumpractice.pageobjects;

import java.util.List;

import org.appiumpractice.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsListPage extends AndroidActions{
	
	AndroidDriver driver;
	public ProductsListPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> products;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCartButtons;

	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;

	public void addProductsToCart(String[] productsToBeAdded)
	{
		for(int c=0;c<productsToBeAdded.length;c++)
		{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+productsToBeAdded[c]+"\"));"));
		 int productCount =  products.size();
		 
		 for(int cnt =0 ;cnt<productCount;cnt++)
		 {
			 String productName = products.get(cnt).getText();
			 if(productName.equals(productsToBeAdded[c]))
			 {
				 addToCartButtons.get(cnt).click();
			 }
		 }
		}
	}
	
	public CartPage cart()
	{
		cartButton.click();
		return new CartPage(driver);
	}
}
