package ge.tbc.testautomation.tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.FIRST_CATEGORY;
import static ge.tbc.testautomation.data.Constants.SECOND_CATEGORY;
import static ge.tbc.testautomation.data.Constants.THOR_HAMMER;
import static ge.tbc.testautomation.data.Constants.THOR_HAMMER_BRAND_TAG;
import static ge.tbc.testautomation.data.Constants.THOR_HAMMER_CATEGORY_TAG;

@Feature("Isolated-user headless tests for favourites, filters and product tags")
public class IsolatedUserTest extends BaseTest {

    @Override
    protected boolean isHeadless() {
        return true;
    }

    @Override
    protected boolean isolateTests() {
        return true;
    }

    @Test(description = "Add a product to favourites and confirm it remains after re-login")
    public void favouritesTest() {
        String favouriteProductName = homeSteps.openHome().chooseRandomProduct();
        productSteps.addToFavourites();
        loginSteps.logOut().openLoginPage().fillLoginCredentials(email, password).logIn();
        favoritesSteps.openFavourites().validateFavouriteIsVisible(favouriteProductName);
    }

    @Test(description = "Filter by two categories and confirm the combined product count")
    public void filterTest() {
        homeSteps.openHome().selectCategory(FIRST_CATEGORY);
        int firstCategoryCount = homeSteps.countFilteredProducts();
        homeSteps.unselectCategory(FIRST_CATEGORY).selectCategory(SECOND_CATEGORY);
        int secondCategoryCount = homeSteps.countFilteredProducts();
        homeSteps.selectCategory(FIRST_CATEGORY);
        int combinedCount = homeSteps.countFilteredProducts();
        Assert.assertEquals(combinedCount, firstCategoryCount + secondCategoryCount);
    }

    @Test(description = "Remove a favourite and confirm it stays deleted after re-login")
    public void removeFavouriteTest() {
        String favouriteProductName = homeSteps.openHome().chooseRandomProduct();
        productSteps.addToFavourites();

        favoritesSteps.openFavourites().removeFavourite().validateNoFavourites();

        loginSteps.logOut().openLoginPage().fillLoginCredentials(email, password)
                .logIn();

        favoritesSteps.openFavourites().validateNoFavourites()
                .validateFavouriteIsNotVisible(favouriteProductName);
    }

    @Test(description = "Open Thor Hammer and validate category and brand tags")
    public void TagsTest() {
        homeSteps.goToHandToolsCategory().selectCategory(FIRST_CATEGORY)
                .openProduct(THOR_HAMMER);

        productSteps.validateProductName(THOR_HAMMER)
                .validateTags(THOR_HAMMER_CATEGORY_TAG, THOR_HAMMER_BRAND_TAG);
    }
}
