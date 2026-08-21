package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends CommonPage {
    public Locator productCards;
    public Locator productNames;
    public Locator filterStarted;
    public Locator filterCompleted;
    public Locator categoryCheckboxes;
    public Locator sortDropdown;
    public Locator searchInput;
    public Locator searchSubmit;
    public Locator searchResultCount;
    public Locator filtersToggle;
    public Locator paginationNext;
    public Locator filtersPanel;
    private final Page page;

    public HomePage(Page page) {
        super(page);
        this.page = page;
        productCards = page.locator("a.card[data-test^='product-']");
        productNames = productCards.getByTestId("product-name");
        filterStarted = page.getByTestId("filter_started");
        filterCompleted = page.getByTestId("filter_completed");
        categoryCheckboxes = page.locator("#filters").getByRole(AriaRole.CHECKBOX);
        sortDropdown = page.getByTestId("sort");
        searchInput = page.getByTestId("search-query");
        searchSubmit = page.getByTestId("search-submit");
        searchResultCount = page.getByTestId("search-result-count");
        filtersToggle = page.locator("a.btn[data-test='filters']");
        paginationNext = page.getByTestId("pagination-next");
        filtersPanel = page.locator("#filters");
    }

    public Locator categoryCheckbox(String categoryName) {
        return page.locator("#filters")
                .getByRole(AriaRole.CHECKBOX, new Locator.GetByRoleOptions().setName(categoryName).setExact(true));
    }

    public Locator brandCheckbox(String brandName) {
        return page.locator("#filters")
                .getByRole(AriaRole.CHECKBOX, new Locator.GetByRoleOptions().setName(brandName).setExact(true));
    }

    public Locator productCardByName(String productName) {
        return page.locator("a.card")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }
}
