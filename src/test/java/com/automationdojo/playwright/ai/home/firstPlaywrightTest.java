package com.automationdojo.playwright.ai.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;

@UsePlaywright
public class firstPlaywrightTest {

    @Test
    void shouldShouldThePageTitle(Page page){
        page.navigate("https://practicesoftwaretesting.com");
        //Get the title of the page
        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    void shouldSearchByKeyword(Page page){
        page.navigate("https://practicesoftwaretesting.com");
        //locate the search button
        page.getByPlaceholder("Search").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        //count the number of elements in the page
        int numResults = page.locator(".card").count();

        //Assert the number of elements
        Assertions.assertTrue(numResults > 0);
    }

}
