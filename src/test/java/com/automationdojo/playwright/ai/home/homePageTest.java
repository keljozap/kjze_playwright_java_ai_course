package com.automationdojo.playwright.ai.home;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;

import org.assertj.core.api.Assertions;


@UsePlaywright
public class homePageTest {

    @DisplayName("Search for pliers")
    @Test
    public void searchAnItem(Page page){
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("#search-query").fill("Pliers");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();

        List<String> productNames = page.getByTestId("product-name").allTextContents();
        Assertions.assertThat(productNames).allMatch(name -> name.contains("Pliers"));
    }

}
