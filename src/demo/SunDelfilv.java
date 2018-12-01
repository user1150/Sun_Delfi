package demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class SunDelfilv {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\CR\\mithr\\chrome\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://delfi.lv");
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/a[2]")).click();
		//cookies
		
		driver.findElement(By.xpath("//*[@id=\"header-burger-button\"]/i")).click();
		driver.findElement(By.xpath("//*[@id=\"site-navigation\"]/nav/div[1]/nav[3]/div[2]/a[2]/span")).click();
		//go to Tasty
		
		List<WebElement> RECP = driver.findElements(By.xpath("/html/body/div[5]/main/div[1]/div[6]/div/section/div[1]/a/div[2]"));
		for (WebElement elem : RECP) {
		    String recepte = elem.getText();
		    System.out.println("Selected Recipe: "+ recepte);
		//Get recipe name from "Pirms gada"
		    
		driver.findElement(By.xpath("/html/body/div[5]/main/div[1]/div[6]/div/section/div[1]/a/div[1]/div[2]")).click();
		String RecTitle = driver.getTitle();
		//select recipe
		
		List<WebElement> alllinks = driver.findElements(By.className("ing-title"));
        for(WebElement cl:alllinks)
        {
            System.out.println(cl.getText());
            if(cl.getText()!="")
            {
                Actions action = new Actions(driver);
                action.keyDown(Keys.CONTROL).moveToElement(cl).click().perform();
                action.keyUp(Keys.CONTROL).perform();
            }
         }
		//open all ingredient links in new tabs
        
        String parentWindow = driver.getWindowHandle();
        ArrayList<String>tabs0  = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs0.get(1));
        //switch tab

        while (true)
        {try{
        {if(!parentWindow.equals(driver.getWindowHandle()))
            if(driver.findElement(By.xpath("/html/body/div[5]/main/div[1]/section")).getText().contains(recepte))
        	{
        		System.out.println("Name is Present in: " + driver.getCurrentUrl());
        		driver.close();
                ArrayList<String>tabs1  = new ArrayList<String>(driver.getWindowHandles());
                		if(tabs1.size() >= 2)
                		{driver.switchTo().window(tabs1.get(1));}
                		else 
                		{driver.switchTo().window(tabs1.get(0));}
        		}
        		else
        		{
        			if(driver.findElement(By.className("hl3-paging-next")) != null);
        				{
        				driver.findElement(By.className("hl3-paging-next")).click();
        			}}
        			else
        			{
        			    driver.switchTo().window(parentWindow);
        				System.out.println("Done!");
        				break;
        				}}}
        		catch(Exception e)
        			{
        			if(driver.getTitle().equals(RecTitle))
        				{System.out.println("Redirected to recepie");
        				driver.close();
        				ArrayList<String>tabs2  = new ArrayList<String>(driver.getWindowHandles());
        					if(tabs2.size() >= 2)
        						{driver.switchTo().window(tabs2.get(1));}
        					else 
        						{driver.switchTo().window(tabs2.get(0));}
        			}
        			else
        				{System.out.println("Section not present in: " + driver.getCurrentUrl());
        				driver.close();
        				ArrayList<String>tabs3  = new ArrayList<String>(driver.getWindowHandles());
        					if(tabs3.size() >= 2)
        						{driver.switchTo().window(tabs3.get(1));}
        					else 
        						{driver.switchTo().window(tabs3.get(0));}}}
        //search trough tabs for recipe title
        }}}}