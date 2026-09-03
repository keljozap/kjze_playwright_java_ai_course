package com.automationdojo.playwright.ai.pages;

import java.nio.file.Path;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ContactFormPage {

    private final Page page;
    private Locator first_name;
    private Locator last_name;
    private Locator email;
    private Locator message;
    private Locator subject;
    private Locator attachment;
    private Locator submitButton;
    private Locator errorMessage;
    
    public ContactFormPage(Page page){
        this.page = page;
        this.first_name = page.locator("#first_name");
        this.last_name = page.locator("#last_name");
        this.email = page.locator("#email");
        this.message = page.locator("#message");
        this.subject = page.locator("#subject");
        this.attachment = page.locator("#attachment");
        this.submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"));
    }

    public void setFirstName(String firstName) {
        first_name.fill(firstName);
    }

    public void setLastName(String string) {
        last_name.fill(string);
    }

    public void setEmail(String string) {
        email.fill(string);
    }

    public void setMessage(String string) {
        message.fill(string);
    }

    public void setSubject(String string) {
        subject.selectOption(string);
    }

    public void setAttachment(Path fileToUpload) {
        attachment.setInputFiles(fileToUpload);
    }   

    public void submitForm() {
        submitButton.click();
    }

    public Locator getErrorMessage(String fieldName) {
        return page.getByRole(AriaRole.ALERT).getByText(fieldName + " is required");
    }

}
