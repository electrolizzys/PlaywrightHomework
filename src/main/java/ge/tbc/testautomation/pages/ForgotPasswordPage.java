package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;

public class ForgotPasswordPage extends CommonPage {
    public Locator pageTitle;
    public Locator emailInput;
    public Locator submitBtn;
    public Locator confirmation;

    public ForgotPasswordPage(Page page) {
        super(page);
        pageTitle = page.getByRole(com.microsoft.playwright.options.AriaRole.HEADING, new Page.GetByRoleOptions().setName("Forgot Password"));
        emailInput = page.getByTestId("email");
        submitBtn = page.getByTestId("forgot-password-submit");
        confirmation = page.locator(".alert-success").or(page.getByText(Constants.FORGOT_PASSWORD_CONFIRMATION));
    }
}
