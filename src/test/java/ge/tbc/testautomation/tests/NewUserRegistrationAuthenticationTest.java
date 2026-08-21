package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.UserData;
import org.testng.annotations.Test;

public class NewUserRegistrationAuthenticationTest extends ScenarioBaseTest {
    private UserData user;
    private String favouriteProductName;

    @Test(priority = 1)
    public void accessOfAuthenticationPage() {
        navigationSteps.openSignIn();
        loginSteps.validateLoginPageIsDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "accessOfAuthenticationPage")
    public void accessOfRegistrationForm() {
        loginSteps.openRegistrationForm();
        registerSteps.validateRegistrationFormIsDisplayed();
    }

    @Test(priority = 3, dependsOnMethods = "accessOfRegistrationForm")
    public void submissionOfRegistrationWithInvalidInput() {
        registerSteps.submitInvalidData().validateInvalidRegistrationErrors();
    }

    @Test(priority = 4, dependsOnMethods = "submissionOfRegistrationWithInvalidInput")
    public void completionOfRegistrationFormWithValidData() {
        user = UserData.randomUser();
        registerSteps.fillRegistrationForm(user);
    }

    @Test(priority = 5, dependsOnMethods = "completionOfRegistrationFormWithValidData")
    public void submissionOfValidRegistration() {
        registerSteps.submit().validateRedirectToLogin();
    }

    @Test(priority = 6, dependsOnMethods = "submissionOfValidRegistration")
    public void authenticationWithCreatedCredentials() {
        loginSteps.fillLoginCredentials(user.email, user.password).logIn();
    }

    @Test(priority = 7, dependsOnMethods = "authenticationWithCreatedCredentials")
    public void addingOfProductToFavourites() {
        homeSteps.openHome().openFirstProduct();
        favouriteProductName = productSteps.productName();
        productSteps.addToFavourites();
        navigationSteps.openFavourites();
        favoritesSteps.validateFavouriteIsVisible(favouriteProductName);
    }

    @Test(priority = 8, dependsOnMethods = "addingOfProductToFavourites")
    public void terminationOfAuthenticatedSession() {
        loginSteps.logOut().validateSignedOut();
    }

    @Test(priority = 9, dependsOnMethods = "terminationOfAuthenticatedSession")
    public void accessOfPasswordRecovery() {
        navigationSteps.openSignIn();
        loginSteps.openForgotPassword();
        forgotPasswordSteps.validatePageIsDisplayed();
    }

    @Test(priority = 10, dependsOnMethods = "accessOfPasswordRecovery")
    public void requestOfPasswordRecovery() {
        forgotPasswordSteps.requestReset(user.email).validateConfirmation();
    }
}
