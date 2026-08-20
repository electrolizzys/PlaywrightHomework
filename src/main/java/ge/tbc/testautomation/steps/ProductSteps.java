package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ProductPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductSteps {
    Page page;
    ProductPage productPage;

    public ProductSteps(Page page) {
        this.page = page;
        productPage = new ProductPage(page);
    }

    public ProductSteps addToFavourites() {
        productPage.addToFavouritesBtn.click();
        productPage.successToast.waitFor();
        return this;
    }

    public ProductSteps validateTags(String expectedCategory, String expectedBrand) {
        assertThat(productPage.categoryTag).hasText(expectedCategory);
        assertThat(productPage.brandTag).hasText(expectedBrand);
        return this;
    }

    public ProductSteps validateProductName(String expectedName) {
        assertThat(productPage.productName).hasText(expectedName);
        return this;
    }
}
