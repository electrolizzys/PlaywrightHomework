package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;

public class CartPage extends CommonPage {
    public Locator productTitle;
    public Locator productQuantity;
    public Locator linePrice;
    public Locator cartTotal;
    public Locator proceedToCheckout;
    public Locator quantityUpdatedToast;

    public CartPage(Page page) {
        super(page);
        productTitle = page.getByTestId("product-title");
        productQuantity = page.getByTestId("product-quantity");
        linePrice = page.getByTestId("line-price");
        cartTotal = page.getByTestId("cart-total");
        proceedToCheckout = page.getByTestId("proceed-1");
        quantityUpdatedToast = page.getByText(Constants.PRODUCT_QUANTITY_UPDATED);
    }
}
