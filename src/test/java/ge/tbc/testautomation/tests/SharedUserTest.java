package ge.tbc.testautomation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.FIRST_CATEGORY;
import static ge.tbc.testautomation.data.Constants.SECOND_CATEGORY;
import static ge.tbc.testautomation.data.Constants.THOR_HAMMER;
import static ge.tbc.testautomation.data.Constants.THOR_HAMMER_BRAND_TAG;
import static ge.tbc.testautomation.data.Constants.THOR_HAMMER_CATEGORY_TAG;

public class SharedUserTest extends BaseTest {
    private String favouriteProductName;

    @Override
    protected boolean isHeadless() {
        return false;
    }

    @Override
    protected boolean isolateTests() {
        return false;
    }

    @Test(priority = 1)
    public void favouritesTest() {
        favouriteProductName = homeSteps.openHome().chooseRandomProduct();
        productSteps.addToFavourites();

        loginSteps.logOut().openLoginPage()
                .fillLoginCredentials(email, password).logIn();

        favoritesSteps.openFavourites()
                .validateFavouriteIsVisible(favouriteProductName);
    }

    @Test(priority = 2)
    public void filterTest() {
        homeSteps.openHome().selectCategory(FIRST_CATEGORY);
        int firstCategoryCount = homeSteps.countFilteredProducts();
        homeSteps.unselectCategory(FIRST_CATEGORY).selectCategory(SECOND_CATEGORY);
        int secondCategoryCount = homeSteps.countFilteredProducts();
        homeSteps.selectCategory(FIRST_CATEGORY);
        int combinedCount = homeSteps.countFilteredProducts();

        Assert.assertEquals(combinedCount, firstCategoryCount + secondCategoryCount);
    }

    @Test(priority = 3)
    public void removeFavouriteTest() {
        favoritesSteps.openFavourites().removeFavourite().validateNoFavourites();

        loginSteps.logOut().openLoginPage().fillLoginCredentials(email, password)
                .logIn();
        favoritesSteps.openFavourites().validateNoFavourites()
                .validateFavouriteIsNotVisible(favouriteProductName);
    }

    @Test(priority = 4)
    public void TagsTest() {
        homeSteps.goToHandToolsCategory().selectCategory(FIRST_CATEGORY)
                .openProduct(THOR_HAMMER);

        productSteps.validateProductName(THOR_HAMMER)
                .validateTags(THOR_HAMMER_CATEGORY_TAG, THOR_HAMMER_BRAND_TAG);
    }
}
