package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends CommonPage {
    public Locator productCards;
    public Locator filterStarted;
    public Locator filterCompleted;
    public Locator categoryCheckboxes;
    private final Page page;

    public HomePage(Page page) {
        super(page);
        productCards = page.locator("a.card[data-test^='product-']");
        filterStarted = page.locator("[data-test='filter_started']");
        filterCompleted = page.locator("[data-test='filter_completed']");
        categoryCheckboxes = page.locator("#filters").getByRole(AriaRole.CHECKBOX);
        this.page = page;
    }



    public Locator categoryCheckbox(String categoryName) {
        return page.locator("#filters")
                .getByRole(AriaRole.CHECKBOX, new Locator.GetByRoleOptions().setName(categoryName).setExact(true));
    }

    public Locator productCardByName(String productName) {
        return page.locator("a.card")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }
}
