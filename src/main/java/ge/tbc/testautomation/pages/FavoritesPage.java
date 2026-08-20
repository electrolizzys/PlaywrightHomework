package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;

public class FavoritesPage extends CommonPage {
    public Locator pageTitle;
    public Locator favouriteProductNames;
    public Locator deleteBtn;
    public Locator noFavouritesMessage;

    public FavoritesPage(Page page) {
        super(page);
        pageTitle = page.locator("[data-test='page-title']");
        favouriteProductNames = page.locator("[data-test='product-name']");
        deleteBtn = page.locator("[data-test='delete']");
        noFavouritesMessage = page.getByText(Constants.NO_FAVOURITES_MESSAGE);
    }
}
