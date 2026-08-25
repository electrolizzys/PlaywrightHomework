package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.UserData;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Feature("Registration, authentication and password recovery of a new user")
public class NewUserRegistrationAuthenticationTest extends ScenarioBaseTest {
    private UserData user;
    private String favouriteProductName;

    @Test(priority = 1, description = "Access of the authentication page")
    public void accessOfAuthenticationPage() {
        navigationSteps.openSignIn();
        loginSteps.validateLoginPageIsDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "accessOfAuthenticationPage", description = "Access of the registration form")
    public void accessOfRegistrationForm() {
        loginSteps.openRegistrationForm();
        registerSteps.validateRegistrationFormIsDisplayed();
    }

    @Test(priority = 3, dependsOnMethods = "accessOfRegistrationForm", description = "Submission of registration with invalid input")
    public void submissionOfRegistrationWithInvalidInput() {
        registerSteps.submitInvalidData().validateInvalidRegistrationErrors();
    }

    @Test(priority = 4, dependsOnMethods = "submissionOfRegistrationWithInvalidInput", description = "Completion of the registration form with valid data")
    public void completionOfRegistrationFormWithValidData() {
        user = UserData.randomUser();
        registerSteps.fillRegistrationForm(user);
    }

    @Test(priority = 5, dependsOnMethods = "completionOfRegistrationFormWithValidData", description = "Submission of valid registration")
    public void submissionOfValidRegistration() {
        registerSteps.submit().validateRedirectToLogin();
    }

    @Test(priority = 6, dependsOnMethods = "submissionOfValidRegistration", description = "Authentication with the created credentials")
    public void authenticationWithCreatedCredentials() {
        loginSteps.fillLoginCredentials(user.email, user.password).logIn();
    }

    @Test(priority = 7, dependsOnMethods = "authenticationWithCreatedCredentials", description = "Adding of a product to favourites")
    public void addingOfProductToFavourites() {
        homeSteps.openHome().openFirstProduct();
        favouriteProductName = productSteps.productName();
        productSteps.addToFavourites();
        navigationSteps.openFavourites();
        favoritesSteps.validateFavouriteIsVisible(favouriteProductName);
    }

    @Test(priority = 8, dependsOnMethods = "addingOfProductToFavourites", description = "Termination of the authenticated session")
    public void terminationOfAuthenticatedSession() {
        loginSteps.logOut().validateSignedOut();
    }

    @Test(priority = 9, dependsOnMethods = "terminationOfAuthenticatedSession", description = "Access of password recovery")
    public void accessOfPasswordRecovery() {
        navigationSteps.openSignIn();
        loginSteps.openForgotPassword();
        forgotPasswordSteps.validatePageIsDisplayed();
    }

    @Test(priority = 10, dependsOnMethods = "accessOfPasswordRecovery", description = "Request of password recovery")
    public void requestOfPasswordRecovery() {
        forgotPasswordSteps.requestReset(user.email).validateConfirmation();
    }
}
