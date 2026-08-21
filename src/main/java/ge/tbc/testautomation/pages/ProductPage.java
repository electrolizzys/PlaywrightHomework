package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;

public class ProductPage extends CommonPage {
    public Locator productName;
    public Locator unitPrice;
    public Locator productDescription;
    public Locator co2Rating;
    public Locator quantityInput;
    public Locator increaseQuantity;
    public Locator decreaseQuantity;
    public Locator addToCartBtn;
    public Locator addToFavouritesBtn;
    public Locator categoryTag;
    public Locator brandTag;
    public Locator addedToFavouritesToast;
    public Locator addedToCartToast;

    public ProductPage(Page page) {
        super(page);
        productName = page.getByTestId("product-name");
        unitPrice = page.getByTestId("unit-price");
        productDescription = page.getByTestId("product-description");
        co2Rating = page.getByTestId("co2-rating-badge");
        quantityInput = page.getByTestId("quantity");
        increaseQuantity = page.getByTestId("increase-quantity");
        decreaseQuantity = page.getByTestId("decrease-quantity");
        addToCartBtn = page.getByTestId("add-to-cart");
        addToFavouritesBtn = page.getByTestId("add-to-favorites");
        categoryTag = page.locator("[aria-label='category']");
        brandTag = page.locator("[aria-label='brand']");
        addedToFavouritesToast = page.getByText(Constants.PRODUCT_ADDED_TO_FAVOURITES);
        addedToCartToast = page.getByText(Constants.PRODUCT_ADDED_TO_CART);
    }
}
