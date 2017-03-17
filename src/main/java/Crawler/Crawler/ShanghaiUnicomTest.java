package Crawler.Crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

public class ShanghaiUnicomTest {
	public static void main(String[] args)
	{
		//基本信息配置
		System.setProperty("webdriver.chrome.driver", "D:\\Mysoft\\chromedriver.exe");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("takesScreenshot", false);

		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:/Mysoft/phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "resourceTimeout",
				10000);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "loadImages",
				true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
	            "--web-security=false",
	            "--webdriver-loglevel=NONE",
	            "--ignore-ssl-errors=true",
	            "--load-images=false",
	            "--disk-cache=true"
	          
        });
		
		WebDriver driver = new ChromeDriver();
		String ShangHaiUnicomUrl = "http://iservice.10010.com/e4/query/bill/call_dan-iframe.html?menuCode=000100030001"; //上海联通URL
		driver.get(ShangHaiUnicomUrl);//转到上海联通登录页面
		CrawlerUtils.sleep(2000);  //暂停2s

		String account = "13120523886";
		String password = "742839";
		
		//定位到login_iframe标签下！！！
		
		driver.switchTo().frame(CrawlerUtils.findElementByDriver(driver, "iframe[src*='http://uac.10010.com/portal/hallLogin']", CrawlerUtils.CSS)); 
		WebElement user = CrawlerUtils.findElementByDriver(driver, "#userName", CrawlerUtils.CSS);
		user.sendKeys(account);  //输入账户名
		WebElement pass = CrawlerUtils.findElementByDriver(driver, "userPwd", CrawlerUtils.ID);
		pass.sendKeys(password); //输入密码
		WebElement  login = CrawlerUtils.findElementByDriver(driver, "#login1", CrawlerUtils.CSS);
		login.click();  //点击登录
	
		WebElement elev = CrawlerUtils.findElementByDriver(driver, "#searchTime > ul > li:nth-child(2)", CrawlerUtils.CSS);
		elev.click();
		
		WebElement nextPage = CrawlerUtils.findElementByDriver(driver, "#callDetailContent > div.score_page > div:nth-child(4)", CrawlerUtils.CSS);
		nextPage.click();
		
	}

}
