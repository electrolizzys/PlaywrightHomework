package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CartPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartSteps {
    Page page;
    CartPage cartPage;

    public CartSteps(Page page) {
        this.page = page;
        cartPage = new CartPage(page);
    }

    public CartSteps validateCartItem(String productName, int quantity, double unitPrice) {
        cartPage.productTitle.waitFor();
        assertThat(cartPage.productTitle).containsText(productName);
        assertThat(cartPage.productQuantity).hasValue(String.valueOf(quantity));
        assertThat(cartPage.linePrice).containsText(formatPrice(unitPrice * quantity));
        assertThat(cartPage.cartTotal).containsText(formatPrice(unitPrice * quantity));
        return this;
    }

    public CartSteps updateQuantity(int quantity) {
        cartPage.productQuantity.fill(String.valueOf(quantity));
        cartPage.productQuantity.blur();
        cartPage.quantityUpdatedToast.waitFor();
        return this;
    }

    public CartSteps validateTotal(double unitPrice, int quantity) {
        assertThat(cartPage.cartTotal).containsText(formatPrice(unitPrice * quantity));
        return this;
    }

    public CartSteps proceedToCheckout() {
        cartPage.proceedToCheckout.click();
        return this;
    }

    private String formatPrice(double value) {
        return String.format("%.2f", value);
    }
}
