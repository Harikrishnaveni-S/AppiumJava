package org.appiumpractice.tests;

import org.appiumpractice.pageobjects.ProductsListPage;
import org.testng.annotations.Test;


public class PlaceOrder2 extends BaseTest {

	Double sum = 0.0;
	Double totalPrice = 0.0;
	
	@Test(groups="S")
	public void placeNewOrder() throws InterruptedException {

//		formpage.waitUntilPresenceOfElement(driver);
//		formpage.waitUntilattributeContainsValue(driver, "General Store");
		Thread.sleep(3000);
		formpage.getCountryDropdown();
		formpage.selectCountry("Belgium");
		formpage.EnterUserName("hk");
		formpage.setGender("Femal");
		ProductsListPage productsListPage = formpage.submitForm();
		productsListPage.waitUntilattributeContainsValue(driver, "ooooppppplkjhgfd");

	}

}
