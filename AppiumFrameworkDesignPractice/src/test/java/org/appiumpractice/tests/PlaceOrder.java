package org.appiumpractice.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.appiumpractice.pageobjects.CartPage;
import org.appiumpractice.pageobjects.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlaceOrder extends BaseTest {
	
	Double sum = 0.0;
	Double totalPrice = 0.0;

	@Test(dataProvider = "getData",groups ={"Regression"})
	public void placeNewOrder(HashMap<String, String> testData) throws InterruptedException {
		 String productsToBeAddedString = testData.get("productsToBeAdded");
		 String[] productsToBeAdded = productsToBeAddedString.split(",");
		
		formpage.waitUntilPresenceOfElement(driver);
		formpage.waitUntilattributeContainsValue(driver,testData.get("formPageTitle"));
		formpage.getCountryDropdown();
		formpage.selectCountry(testData.get("countryName"));
		formpage.EnterUserName(testData.get("userName"));
		formpage.setGender(testData.get("gender"));
		ProductsListPage productsListPage = formpage.submitForm();

		productsListPage.waitUntilPresenceOfElement(driver);
		productsListPage.waitUntilattributeContainsValue(driver,testData.get("productsPageTitle"));
		productsListPage.addProductsToCart(productsToBeAdded);
		CartPage cartPage = productsListPage.cart();

		cartPage.waitUntilPresenceOfElement(driver);
		cartPage.waitUntilElementIsVisible(driver);
		cartPage.waitUntilattributeContainsValue(driver,testData.get("cartPageTitle"));
		sum = cartPage.getSumOfProducts();
		totalPrice = cartPage.getDisplayedTotalPrice();
		Assert.assertEquals(sum, totalPrice);
		cartPage.acceptTermsAndConditions();
		cartPage.submitOrder();
	}

	@BeforeMethod(alwaysRun=true)
	public void preSetup() {
		formpage.setActivity();
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//org//appiumpractice//testData//ecommerce.json");
		return new Object[][] { { data.get(0)}, {data.get(1) } };
	}
	
	

}
