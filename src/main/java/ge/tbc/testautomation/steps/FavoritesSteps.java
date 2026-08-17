package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.FavoritesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FavoritesSteps {
    Page page;
    FavoritesPage favoritesPage;

    public FavoritesSteps(Page page) {
        this.page = page;
        favoritesPage = new FavoritesPage(page);
    }

    public FavoritesSteps openFavourites() {
        favoritesPage.userMenu.click();
        favoritesPage.myFavouritesLink.click();
        assertThat(favoritesPage.pageTitle).isVisible();
        return this;
    }

    public FavoritesSteps validateFavouriteIsVisible(String productName) {
        assertThat(favoritesPage.favouriteProductNames).hasText(productName);
        return this;
    }

    public FavoritesSteps removeFavourite() {
        favoritesPage.deleteBtn.click();
        return this;
    }

    public FavoritesSteps validateNoFavourites() {
        assertThat(favoritesPage.noFavouritesMessage).isVisible();
        assertThat(favoritesPage.favouriteProductNames).hasCount(0);
        return this;
    }

    public FavoritesSteps validateFavouriteIsNotVisible(String productName) {
        assertThat(page.getByText(productName, new Page.GetByTextOptions().setExact(true))).hasCount(0);
        return this;
    }
}
