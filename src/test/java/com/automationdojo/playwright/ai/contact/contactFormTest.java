package com.automationdojo.playwright.ai.contact;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.assertions.PlaywrightAssertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.automationdojo.playwright.ai.pages.ContactFormPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;

@UsePlaywright
public class contactFormTest {

    ContactFormPage contactFormPage;

    @BeforeEach
    public void setUp(Page page) {
        contactFormPage = new ContactFormPage(page);
        //Go to contact page
        page.navigate("https://practicesoftwaretesting.com/contact");
    }
    
    @Test
    public void fillTheContactForm(Page page) throws URISyntaxException{

        //Enter the text fields
        contactFormPage.setFirstName("Test Name");
        contactFormPage.setLastName("Test Last Name");
        contactFormPage.setEmail("testfakeemail@test.com");
        contactFormPage.setMessage("Hello this is a message");

        // Now the dropdowns
        contactFormPage.setSubject("Webmaster");

        //Attachment field
        Path fileToUpload = Paths.get(ClassLoader.getSystemResource("data/hello.txt").toURI());
        contactFormPage.setAttachment(fileToUpload);
        contactFormPage.submitForm();
    }

    @ParameterizedTest
    @ValueSource(strings = {"First name", "Last name", "Email", "Message"})
    public void mandatoryFields(String fieldName, Page page){
        //Submit button without data
        contactFormPage.submitForm();

        //Check the error messages
        var errorMessage = contactFormPage.getErrorMessage(fieldName);
        PlaywrightAssertions.assertThat(errorMessage).isVisible();
    }
}
