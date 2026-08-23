package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import ge.tbc.testautomation.data.Constants;

public class ContactPage extends CommonPage {
    public Locator pageTitle;
    public Locator firstNameInput;
    public Locator lastNameInput;
    public Locator emailInput;
    public Locator subjectSelect;
    public Locator messageInput;
    public Locator attachmentInput;
    public Locator submitBtn;
    public Locator firstNameError;
    public Locator lastNameError;
    public Locator emailError;
    public Locator messageError;
    public Locator attachmentError;
    public Locator successMessage;
    public Locator contactForm;

    public ContactPage(Page page) {
        super(page);
        pageTitle = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact"));
        firstNameInput = page.getByTestId("first-name");
        lastNameInput = page.getByTestId("last-name");
        emailInput = page.getByTestId("email");
        subjectSelect = page.getByTestId("subject");
        messageInput = page.getByTestId("message");
        attachmentInput = page.getByTestId("attachment");
        submitBtn = page.getByTestId("contact-submit");
        firstNameError = page.getByTestId("first-name-error");
        lastNameError = page.getByTestId("last-name-error");
        emailError = page.getByTestId("email-error");
        messageError = page.getByTestId("message-error");
        attachmentError = page.getByTestId("attachment-error");
        successMessage = page.getByText(Constants.CONTACT_SUCCESS);
        contactForm = page.locator("form");
    }
}
