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

    public ProductSteps validateDetailView() {
        assertThat(productPage.productName).isVisible();
        assertThat(productPage.unitPrice).isVisible();
        assertThat(productPage.productDescription).isVisible();
        assertThat(productPage.co2Rating).isVisible();
        assertThat(productPage.quantityInput).isVisible();
        return this;
    }

    public ProductSteps increaseQuantityTo(int quantity) {
        productPage.quantityInput.waitFor();
        while (Integer.parseInt(productPage.quantityInput.inputValue()) < quantity) {
            productPage.increaseQuantity.click();
        }
        assertThat(productPage.quantityInput).hasValue(String.valueOf(quantity));
        return this;
    }

    public ProductSteps addToCart() {
        productPage.addToCartBtn.click();
        productPage.addedToCartToast.or(productPage.cartQuantity).first().waitFor();
        return this;
    }

    public ProductSteps addToFavourites() {
        productPage.addToFavouritesBtn.click();
        productPage.addedToFavouritesToast.waitFor();
        return this;
    }

    public ProductSteps validateCartBadgeCount(String expectedCount) {
        assertThat(productPage.cartQuantity).isAttached();
        assertThat(productPage.cartQuantity).hasText(expectedCount);
        return this;
    }

    public String productName() {
        productPage.productName.waitFor();
        return productPage.productName.innerText().trim();
    }

    public double unitPrice() {
        String raw = productPage.unitPrice.innerText().replace("$", "").trim();
        return Double.parseDouble(raw);
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
