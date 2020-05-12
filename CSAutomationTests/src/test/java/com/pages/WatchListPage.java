package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class WatchListPage extends BasePage {
	
	public WatchListPage() {
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public List<String> validateWatchListItems() {
		List<String> actualItems = new ArrayList<>();
		for(int i = 1; i <= 7; i++) {
			String text = driver.findElement(By.xpath("(//img[contains(@class,'cmc-static-icon cmc-static-icon-')])["+i+"]/following::a[1]")).getText();
			actualItems.add(text);
			
			
		}
		return actualItems;
		
	}
}
