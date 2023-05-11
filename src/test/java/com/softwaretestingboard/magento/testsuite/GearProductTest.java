package com.softwaretestingboard.magento.testsuite;

import com.softwaretestingboard.magento.customlisteners.CustomListeners;
import com.softwaretestingboard.magento.pages.GearBagPage;
import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.ShoppingCartPage;
import com.softwaretestingboard.magento.testbase.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(CustomListeners.class)
public class GearProductTest extends BaseTest {
    HomePage homePage;
    GearBagPage gearBagPage;
    ShoppingCartPage shoppingCartPage;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setBrowser() {
        gearBagPage = new GearBagPage();
        homePage = new HomePage();
        shoppingCartPage = new ShoppingCartPage();
        softAssert = new SoftAssert();
    }


    @Test(groups = {"smoke", "regression"})
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        homePage.hoverMouseOnGearMenu();
        homePage.clickOnBagMenu();
        gearBagPage.clickOnNightDuffleBag();
        softAssert(gearBagPage.verifyTheBagText(), "Overnight Duffle", "The bag name is incorrect");
        gearBagPage.changeTheQuantityOfTheBag("3");
        gearBagPage.clickOnAddToCartButton();
        softAssert(gearBagPage.bagAddedToCartSuccessfullyMessage(), "You added Overnight Duffle to your shopping cart.","Bag is added successfully");
        gearBagPage.clickOnShoppingCartLink();
        softAssert(shoppingCartPage.verifyTheProductsName(), "Overnight Duffle", "The Bag Name is incorrect");
        softAssert(shoppingCartPage.getQuantityOfProduct(), "3", "Wrong quantity");
        softAssert(shoppingCartPage.getPriceOfTheProduct(), "$135.00", "Incorrect price");
        shoppingCartPage.changeTheQuantityOfTheBag("5");
        shoppingCartPage.updateTheShoppingCart();
        softAssert(shoppingCartPage.UpdatedProductPrice(), "$225.00", "Wrong price");
        softAssert.assertAll();
    }
}
