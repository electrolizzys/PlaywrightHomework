package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.UserData;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.FORGEFLEX_BRAND;
import static ge.tbc.testautomation.data.Constants.HAMMER_CATEGORY;
import static ge.tbc.testautomation.data.Constants.HAND_TOOLS_CATEGORY;
import static ge.tbc.testautomation.data.Constants.SEARCH_TERM;
import static ge.tbc.testautomation.data.Constants.GUEST_PRODUCT;

@Feature("Completion of a purchase by a guest user")
public class GuestPurchaseCompletionTest extends ScenarioBaseTest {
    private String productName;
    private double unitPrice;
    private UserData guest;

    @Test(priority = 1, description = "Display of the product catalog on the homepage")
    public void displayOfProductCatalog() {
        homeSteps.openHome().validateCatalogDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "displayOfProductCatalog", description = "Filtering of the catalog by search term")
    public void filteringOfCatalogBySearchTerm() {
        homeSteps.searchFor(SEARCH_TERM).validateSearchResultsContain(SEARCH_TERM);
    }

    @Test(priority = 3, dependsOnMethods = "filteringOfCatalogBySearchTerm", description = "Combined filtering by category and brand")
    public void combinedFilteringByCategoryAndBrand() {
        homeSteps.selectCategory(HAND_TOOLS_CATEGORY)
                .selectCategory(HAMMER_CATEGORY)
                .selectBrand(FORGEFLEX_BRAND)
                .validateFilteredProductsVisible();
    }

    @Test(priority = 4, dependsOnMethods = "combinedFilteringByCategoryAndBrand", description = "Opening of the product detail view")
    public void openingOfProductDetailView() {
        homeSteps.openProduct(GUEST_PRODUCT);
        productSteps.validateDetailView();
        productName = productSteps.productName();
        unitPrice = productSteps.unitPrice();
    }

    @Test(priority = 5, dependsOnMethods = "openingOfProductDetailView", description = "Update of product quantity")
    public void updateOfProductQuantity() {
        productSteps.increaseQuantityTo(2);
    }

    @Test(priority = 6, dependsOnMethods = "updateOfProductQuantity", description = "Adding of the product to the cart")
    public void addingOfProductToCart() {
        productSteps.addToCart().validateCartBadgeCount("2");
    }

    @Test(priority = 7, dependsOnMethods = "addingOfProductToCart", description = "Review of cart contents and pricing")
    public void reviewOfCartContentsAndPricing() {
        navigationSteps.openCart();
        cartSteps.validateCartItem(productName, 2, unitPrice);
    }

    @Test(priority = 8, dependsOnMethods = "reviewOfCartContentsAndPricing", description = "Recalculation of cart total after quantity change")
    public void recalculationOfCartTotalAfterQuantityChange() {
        cartSteps.updateQuantity(3).validateTotal(unitPrice, 3);
    }

    @Test(priority = 9, dependsOnMethods = "recalculationOfCartTotalAfterQuantityChange", description = "Transition from cart to checkout authentication")
    public void transitionFromCartToCheckoutAuthentication() {
        cartSteps.proceedToCheckout();
        checkoutSteps.validateSignInStep();
    }

    @Test(priority = 10, dependsOnMethods = "transitionFromCartToCheckoutAuthentication", description = "Continuation of checkout as guest")
    public void continuationOfCheckoutAsGuest() {
        guest = UserData.randomUser();
        checkoutSteps.continueAsGuest(guest);
    }

    @Test(priority = 11, dependsOnMethods = "continuationOfCheckoutAsGuest", description = "Provision of a valid billing address")
    public void provisionOfValidBillingAddress() {
        checkoutSteps.fillBillingAddress(guest);
    }

    @Test(priority = 12, dependsOnMethods = "provisionOfValidBillingAddress", description = "Completion of payment and order confirmation")
    public void completionOfPaymentAndOrderConfirmation() {
        checkoutSteps.completePayment();
    }
}
