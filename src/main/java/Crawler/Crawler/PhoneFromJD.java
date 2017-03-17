package Crawler.Crawler;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PhoneFromJD {
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\development\\chromedriver.exe");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("takesScreenshot", false);

		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:/development/phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
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
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		PhantomJSDriver driver = new PhantomJSDriver(caps);
//		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=fjus23wi.0ibe3g");
		CrawlerUtils.sleep(2000);
		
		WebElement more = CrawlerUtils.findElementByDriver(driver, "#J_selector > div.J_selectorLine.s-brand > div > div.sl-ext > a.sl-e-more.J_extMore", CrawlerUtils.CSS);
		more.click();
		CrawlerUtils.sleep(1000);
		
		List<WebElement> phonebrandList = CrawlerUtils.findElementsByDriver(driver, "li[id*='brand']>a", CrawlerUtils.CSS);
		List<String> urlList = new ArrayList<>();
		for(WebElement ele : phonebrandList)
		{
			urlList.add(ele.getAttribute("href"));
		}
		System.out.println(urlList.size());
		
		
		
		/*WebElement total = CrawlerUtils.findElementByDriver(driver, ".p-skip>em>b", CrawlerUtils.CSS);
		System.out.println("总页码========="+total.getText());*/
		JSONArray jsonArr = new JSONArray();
		for(String url : urlList)
		{
			System.out.println(url);
			driver.get(url);
			WebElement ele = CrawlerUtils.findElementByDriver(driver, ".crumb-select-item>em", CrawlerUtils.CSS);
			System.out.println("品牌=========="+ele.getText());
			int i=1;
			String tempUrl = driver.getCurrentUrl();
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			List<WebElement> itemList = null;
			while(true)
			{
				itemList = CrawlerUtils.findElementsByDriver(driver, ".gl-item", CrawlerUtils.CSS);
				int itemSize = itemList.size();
				while(true)
				{
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					CrawlerUtils.sleep(3000);
					
					itemList = CrawlerUtils.findElementsByDriver(driver, ".gl-item", CrawlerUtils.CSS);
					if(itemList.size() == itemSize)
					{
						break;
					}
					else
					{
						itemSize = itemList.size();
					}
				}
				System.out.println("itemtotal/page====="+itemSize);
				
				for(WebElement itemEle : itemList)
				{
					WebElement UnitEle = CrawlerUtils.findElementByElement(itemEle, ".p-price>strong>em", CrawlerUtils.CSS);
					WebElement priceEle = CrawlerUtils.findElementByElement(itemEle, ".p-price>strong>i", CrawlerUtils.CSS);
					WebElement itemName = CrawlerUtils.findElementByElement(itemEle, ".p-name>a>em", CrawlerUtils.CSS);
					if(itemName != null && UnitEle != null && priceEle != null)
					{
						JSONObject jo = new JSONObject();
						jo.put("型号", itemName.getText());
						jo.put("价格", UnitEle.getText()+priceEle.getText());
						jsonArr.add(jo);
						System.out.println(itemName.getText()+",价格++++++++++"+UnitEle.getText()+priceEle.getText());
					}
				}
				
				WebElement page = CrawlerUtils.findElementByDriver(driver, ".pn-next", CrawlerUtils.CSS);
				if(page == null)
				{
					break;
				}
				page.click();
				CrawlerUtils.sleep(2000);
				if(tempUrl.equalsIgnoreCase(driver.getCurrentUrl()))
				{
					break;
				}
				else
				{
					i++;
					tempUrl = driver.getCurrentUrl();
				}
			}
			System.out.println("total======"+i);
		}
		System.out.println(jsonArr.toJSONString());
		driver.close();
		driver.quit();
	}
}
