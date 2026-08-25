package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ProductPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductSteps {
    Page page;
    ProductPage productPage;

    public ProductSteps(Page page) {
        this.page = page;
        productPage = new ProductPage(page);
    }

    @Step("Validating the product detail view")
    public ProductSteps validateDetailView() {
        assertThat(productPage.productName).isVisible();
        assertThat(productPage.unitPrice).isVisible();
        assertThat(productPage.productDescription).isVisible();
        assertThat(productPage.co2Rating).isVisible();
        assertThat(productPage.quantityInput).isVisible();
        return this;
    }

    @Step("Increasing product quantity to {quantity}")
    public ProductSteps increaseQuantityTo(int quantity) {
        productPage.quantityInput.waitFor();
        while (Integer.parseInt(productPage.quantityInput.inputValue()) < quantity) {
            productPage.increaseQuantity.click();
        }
        assertThat(productPage.quantityInput).hasValue(String.valueOf(quantity));
        return this;
    }

    @Step("Adding the product to the cart")
    public ProductSteps addToCart() {
        productPage.addToCartBtn.click();
        productPage.addedToCartToast.or(productPage.cartQuantity).first().waitFor();
        return this;
    }

    @Step("Adding the product to favourites")
    public ProductSteps addToFavourites() {
        productPage.addToFavouritesBtn.click();
        productPage.addedToFavouritesToast.waitFor();
        return this;
    }

    @Step("Validating cart badge count is {expectedCount}")
    public ProductSteps validateCartBadgeCount(String expectedCount) {
        assertThat(productPage.cartQuantity).isAttached();
        assertThat(productPage.cartQuantity).hasText(expectedCount);
        return this;
    }

    @Step("Reading the product name")
    public String productName() {
        productPage.productName.waitFor();
        return productPage.productName.innerText().trim();
    }

    @Step("Reading the unit price")
    public double unitPrice() {
        String raw = productPage.unitPrice.innerText().replace("$", "").trim();
        return Double.parseDouble(raw);
    }

    @Step("Validating product tags")
    public ProductSteps validateTags(String expectedCategory, String expectedBrand) {
        assertThat(productPage.categoryTag).hasText(expectedCategory);
        assertThat(productPage.brandTag).hasText(expectedBrand);
        return this;
    }

    @Step("Validating product name is {expectedName}")
    public ProductSteps validateProductName(String expectedName) {
        assertThat(productPage.productName).hasText(expectedName);
        return this;
    }
}
