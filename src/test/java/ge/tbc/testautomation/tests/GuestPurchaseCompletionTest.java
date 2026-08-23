package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.UserData;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.FORGEFLEX_BRAND;
import static ge.tbc.testautomation.data.Constants.HAMMER_CATEGORY;
import static ge.tbc.testautomation.data.Constants.HAND_TOOLS_CATEGORY;
import static ge.tbc.testautomation.data.Constants.SEARCH_TERM;
import static ge.tbc.testautomation.data.Constants.GUEST_PRODUCT;

public class GuestPurchaseCompletionTest extends ScenarioBaseTest {
    private String productName;
    private double unitPrice;
    private UserData guest;

    @Test(priority = 1)
    public void displayOfProductCatalog() {
        homeSteps.openHome().validateCatalogDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "displayOfProductCatalog")
    public void filteringOfCatalogBySearchTerm() {
        homeSteps.searchFor(SEARCH_TERM).validateSearchResultsContain(SEARCH_TERM);
    }

    @Test(priority = 3, dependsOnMethods = "filteringOfCatalogBySearchTerm")
    public void combinedFilteringByCategoryAndBrand() {
        homeSteps.selectCategory(HAND_TOOLS_CATEGORY)
                .selectCategory(HAMMER_CATEGORY)
                .selectBrand(FORGEFLEX_BRAND)
                .validateFilteredProductsVisible();
    }

    @Test(priority = 4, dependsOnMethods = "combinedFilteringByCategoryAndBrand")
    public void openingOfProductDetailView() {
        homeSteps.openProduct(GUEST_PRODUCT);
        productSteps.validateDetailView();
        productName = productSteps.productName();
        unitPrice = productSteps.unitPrice();
    }

    @Test(priority = 5, dependsOnMethods = "openingOfProductDetailView")
    public void updateOfProductQuantity() {
        productSteps.increaseQuantityTo(2);
    }

    @Test(priority = 6, dependsOnMethods = "updateOfProductQuantity")
    public void addingOfProductToCart() {
        productSteps.addToCart().validateCartBadgeCount("2");
    }

    @Test(priority = 7, dependsOnMethods = "addingOfProductToCart")
    public void reviewOfCartContentsAndPricing() {
        navigationSteps.openCart();
        cartSteps.validateCartItem(productName, 2, unitPrice);
    }

    @Test(priority = 8, dependsOnMethods = "reviewOfCartContentsAndPricing")
    public void recalculationOfCartTotalAfterQuantityChange() {
        cartSteps.updateQuantity(3).validateTotal(unitPrice, 3);
    }

    @Test(priority = 9, dependsOnMethods = "recalculationOfCartTotalAfterQuantityChange")
    public void transitionFromCartToCheckoutAuthentication() {
        cartSteps.proceedToCheckout();
        checkoutSteps.validateSignInStep();
    }

    @Test(priority = 10, dependsOnMethods = "transitionFromCartToCheckoutAuthentication")
    public void continuationOfCheckoutAsGuest() {
        guest = UserData.randomUser();
        checkoutSteps.continueAsGuest(guest);
    }

    @Test(priority = 11, dependsOnMethods = "continuationOfCheckoutAsGuest")
    public void provisionOfValidBillingAddress() {
        checkoutSteps.fillBillingAddress(guest);
    }

    @Test(priority = 12, dependsOnMethods = "provisionOfValidBillingAddress")
    public void completionOfPaymentAndOrderConfirmation() {
        checkoutSteps.completePayment();
    }
}
