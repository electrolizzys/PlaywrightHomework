package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.HomePage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class HomeSteps {
    Page page;
    HomePage homePage;

    public HomeSteps(Page page) {
        this.page = page;
        homePage = new HomePage(page);
    }

    @Step("Opening the home page catalog")
    public HomeSteps openHome() {
        page.navigate(BASE_URL);
        homePage.productCards.first().waitFor();
        return this;
    }

    @Step("Validating that the product catalog is displayed")
    public HomeSteps validateCatalogDisplayed() {
        homePage.productCards.first().waitFor();
        assertThat(homePage.productCards.first()).isVisible();
        assertThat(homePage.sortDropdown).isAttached();
        assertThat(homePage.searchInput).isAttached();
        assertThat(homePage.filtersPanel).isAttached();
        assertThat(homePage.paginationNext).isAttached();
        return this;
    }

    @Step("Opening filters if they are collapsed")
    public HomeSteps openFiltersIfCollapsed() {
        if (homePage.filtersToggle.isVisible()) {
            homePage.filtersToggle.click();
            homePage.searchInput.waitFor();
        }
        return this;
    }

    @Step("Searching the catalog for {term}")
    public HomeSteps searchFor(String term) {
        openFiltersIfCollapsed();
        homePage.searchInput.fill(term);
        homePage.searchSubmit.click();
        homePage.filterCompleted.or(homePage.searchResultCount).first().waitFor();
        homePage.productNames.first().waitFor();
        assertThat(homePage.productNames.first()).containsText(term);
        return this;
    }

    @Step("Validating search results contain {term}")
    public HomeSteps validateSearchResultsContain(String term) {
        assertThat(homePage.productNames.first()).containsText(term);
        assertThat(homePage.productNames.first()).isVisible();
        return this;
    }

    @Step("Selecting category {categoryName}")
    public HomeSteps selectCategory(String categoryName) {
        openFiltersIfCollapsed();
        homePage.categoryCheckbox(categoryName).check();
        waitForFilterToSettle();
        return this;
    }

    @Step("Selecting brand {brandName}")
    public HomeSteps selectBrand(String brandName) {
        openFiltersIfCollapsed();
        homePage.brandCheckbox(brandName).check();
        waitForFilterToSettle();
        return this;
    }

    @Step("Validating that filtered products are visible")
    public HomeSteps validateFilteredProductsVisible() {
        homePage.productCards.first().waitFor();
        assertThat(homePage.productCards.first()).isVisible();
        return this;
    }

    @Step("Opening product {productName}")
    public HomeSteps openProduct(String productName) {
        homePage.productCardByName(productName).click();
        return this;
    }

    @Step("Opening the first product in the catalog")
    public HomeSteps openFirstProduct() {
        homePage.productCards.first().waitFor();
        homePage.productCards.first().click();
        return this;
    }

    @Step("Choosing a random product from the catalog")
    public String chooseRandomProduct() {
        homePage.productCards.first().waitFor();
        int count = homePage.productCards.count();
        int index = java.util.concurrent.ThreadLocalRandom.current().nextInt(count);
        String name = homePage.productNames.nth(index).innerText().trim();
        homePage.productCards.nth(index).click();
        return name;
    }

    @Step("Unselecting category {categoryName}")
    public HomeSteps unselectCategory(String categoryName) {
        openFiltersIfCollapsed();
        homePage.categoryCheckbox(categoryName).uncheck();
        waitForFilterToSettle();
        return this;
    }

    @Step("Counting filtered products")
    public int countFilteredProducts() {
        homePage.filterCompleted.waitFor();
        homePage.productCards.first().waitFor();
        return homePage.productCards.count();
    }

    @Step("Opening the Hand Tools category")
    public HomeSteps goToHandToolsCategory() {
        new NavigationSteps(page).openMenuIfCollapsed();
        homePage.categoriesNav.click();
        homePage.handToolsLink.waitFor();
        homePage.handToolsLink.click();
        homePage.productCards.first().waitFor();
        return this;
    }

    private void waitForFilterToSettle() {
        try {
            homePage.filterStarted.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(3000));
            homePage.filterCompleted.waitFor();
        } catch (RuntimeException ignored) {
            homePage.productCards.first().waitFor();
        }
    }
}
