package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.FavoritesPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FavoritesSteps {
    Page page;
    FavoritesPage favoritesPage;

    public FavoritesSteps(Page page) {
        this.page = page;
        favoritesPage = new FavoritesPage(page);
    }

    @Step("Validating favourite {productName} is visible")
    public FavoritesSteps validateFavouriteIsVisible(String productName) {
        assertThat(favoritesPage.pageTitle).isVisible();
        assertThat(favoritesPage.favouriteProductNames).hasText(productName);
        return this;
    }

    @Step("Opening the favourites page")
    public FavoritesSteps openFavourites() {
        new NavigationSteps(page).openFavourites();
        assertThat(favoritesPage.pageTitle).isVisible();
        return this;
    }

    @Step("Removing a favourite product")
    public FavoritesSteps removeFavourite() {
        favoritesPage.deleteBtn.click();
        return this;
    }

    @Step("Validating that there are no favourites")
    public FavoritesSteps validateNoFavourites() {
        assertThat(favoritesPage.noFavouritesMessage).isVisible();
        assertThat(favoritesPage.favouriteProductNames).hasCount(0);
        return this;
    }

    @Step("Validating favourite {productName} is not visible")
    public FavoritesSteps validateFavouriteIsNotVisible(String productName) {
        assertThat(favoritesPage.favouriteByName(productName)).hasCount(0);
        return this;
    }
}
