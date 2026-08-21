package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends CommonPage {
    public Locator pageTitle;
    public Locator emailInput;
    public Locator passwordInput;
    public Locator loginBtn;
    public Locator registerLink;
    public Locator loginError;
    public Locator forgotPasswordLink;

    public LoginPage(Page page) {
        super(page);
        pageTitle = page.getByRole(com.microsoft.playwright.options.AriaRole.HEADING, new Page.GetByRoleOptions().setName("Login"));
        emailInput = page.getByTestId("email");
        passwordInput = page.getByTestId("password");
        loginBtn = page.getByTestId("login-submit");
        registerLink = page.getByTestId("register-link");
        loginError = page.getByTestId("login-error");
        forgotPasswordLink = page.getByTestId("forgot-password-link");
    }
}
