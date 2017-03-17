package Crawler.Crawler;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;

public class JDCrawler {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED);
		driver.get("https://passport.jd.com/uc/login?ltype=logout");
		System.out.println(driver.getCurrentUrl());
		WebElement loginname = driver.findElement(By.id("loginname"));
		loginname.sendKeys("jingwen_1985");
		WebElement loginpwd = driver.findElement(By.id("nloginpwd"));
		loginpwd.sendKeys("XJw239386839");
		WebElement searchButton = driver.findElement(By.id("loginsubmit"));
		searchButton.click();
		System.out.println(driver.getCurrentUrl());
		/*WebClient webClient = new WebClient(); 
		webClient.getOptions().setJavaScriptEnabled(true);
        //1.获取某个待测页面  
        HtmlPage page = webClient.getPage("https://passport.jd.com/uc/login?ltype=logout");  
        //2.获取页面上的表单  
        DomElement log = page.getElementById("loginname");
        
        DomElement pass = page.getElementById("nloginpwd");
        pass.setNodeValue("XJw239386839");
        DomElement btn = page.getElementById("loginsubmit");
        page = btn.click();
        CrawlerUtils.sleep(2000);
        System.out.println("***************"+page.getUrl().toString());
        webClient.close();*/
	}
	
}
