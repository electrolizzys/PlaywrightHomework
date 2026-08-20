package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.HomePage;

import java.util.concurrent.ThreadLocalRandom;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class HomeSteps {
    Page page;
    HomePage homePage;

    public HomeSteps(Page page) {
        this.page = page;
        homePage = new HomePage(page);
    }

    public HomeSteps openHome() {
        page.navigate(BASE_URL);
        homePage.productCards.first().waitFor();
        homePage.categoryCheckboxes.first().waitFor();
        return this;
    }

    public String chooseRandomProduct() {
        homePage.productCards.first().waitFor();
        int count = homePage.productCards.count();
        int index = ThreadLocalRandom.current().nextInt(count);
        Locator product = homePage.productCards.nth(index);
        String name = product.locator("[data-test='product-name']").innerText().trim();
        product.click();
        return name;
    }

    public HomeSteps selectCategory(String categoryName) {
        homePage.categoryCheckbox(categoryName).check();
        homePage.filterStarted.waitFor();
        homePage.filterCompleted.waitFor();
        return this;
    }

    public HomeSteps unselectCategory(String categoryName) {
        homePage.categoryCheckbox(categoryName).uncheck();
        homePage.filterStarted.waitFor();
        homePage.filterCompleted.waitFor();
        return this;
    }

    public int countFilteredProducts() {
        homePage.filterCompleted.waitFor();
        homePage.productCards.first().waitFor();
        return homePage.productCards.count();
    }

    public HomeSteps openProduct(String productName) {
        homePage.productCardByName(productName).click();
        return this;
    }

    public HomeSteps goToHandToolsCategory() {
        homePage.categoriesNav.click();
        homePage.handToolsLink.waitFor();
        homePage.handToolsLink.click();
        homePage.productCards.first().waitFor();
        homePage.categoryCheckboxes.first().waitFor();
        return this;
    }
}
