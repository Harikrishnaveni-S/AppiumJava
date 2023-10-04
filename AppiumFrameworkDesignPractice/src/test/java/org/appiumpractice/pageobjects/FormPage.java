package org.appiumpractice.pageobjects;


import org.appiumpractice.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countrySelectDropdown;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement userNameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	public void getCountryDropdown()
	{
		countrySelectDropdown.click();

	}
	public void selectCountry(String countryName) {
		scrollAndSelectOption(countryName);

	}
	
	public void EnterUserName(String userName)
	{
		userNameField.sendKeys(userName);
		driver.hideKeyboard();

	}
	
	public void setGender(String genderName)
	{
		if(genderName.equals("Female"))
		{
			femaleOption.click();
		}
		else {
			maleOption.click();
		}
	}
	
	public ProductsListPage submitForm()
	{
		shopButton.click();
		return new ProductsListPage(driver);
	}
	
	public void setActivity()
	{
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
//		/adb shell dumpsys window | find "mCurrentFocus"

	}
	
	
	
	
	

}
