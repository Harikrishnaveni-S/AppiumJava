package org.appiumpractice.pageobjects;

import java.util.List;

import org.appiumpractice.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productsPriceInCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPriceDisplayed;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement confirmCheckBox;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditions;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptTermsAndConditions;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;


	public Double getSumOfProducts() {
		int cartcnt = productsPriceInCart.size();
		double sum = 0;
		for (int i = 0; i < cartcnt; i++) {

			String price = productsPriceInCart.get(i).getText().substring(1);
			Double priceVal = Double.parseDouble(price);
			sum = sum + priceVal;
		}
		return sum;

	}

	public Double getDisplayedTotalPrice() {
		String total = totalPriceDisplayed.getText().substring(2);
		Double totalPrice = Double.parseDouble(total);
		return totalPrice;
	}
	
	public void acceptTermsAndConditions()
	{
		confirmCheckBox.click();
		longPressAction(termsAndConditions);
		acceptTermsAndConditions.click();
	}
	
	public void submitOrder()
	{
		proceedButton.click();

	}

}
