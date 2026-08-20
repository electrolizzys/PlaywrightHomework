package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;

public class ProductPage extends CommonPage {
    public Locator productName;
    public Locator addToFavouritesBtn;
    public Locator categoryTag;
    public Locator brandTag;
    public Locator successToast;

    public ProductPage(Page page) {
        super(page);
        productName = page.locator("[data-test='product-name']");
        addToFavouritesBtn = page.locator("[data-test='add-to-favorites']");
        categoryTag = page.locator("[aria-label='category']");
        brandTag = page.locator("[aria-label='brand']");
        successToast = page.getByText(Constants.PRODUCT_ADDED_TO_FAVOURITES);
    }
}
