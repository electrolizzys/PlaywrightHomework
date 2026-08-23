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
        pageTitle = page.getByTestId("page-title");
        favouriteProductNames = page.getByTestId("product-name");
        deleteBtn = page.getByTestId("delete");
        noFavouritesMessage = page.getByText(Constants.NO_FAVOURITES_MESSAGE);
    }

    public Locator favouriteByName(String productName) {
        return favouriteProductNames.filter(new Locator.FilterOptions().setHasText(productName));
    }
}
