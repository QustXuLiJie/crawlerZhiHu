package image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import Crawler.Crawler.CrawlerUtils;

public class Chinamoblie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","d:\\development\\chromedriver.exe");
		Capabilities caps = new DesiredCapabilities();
		((DesiredCapabilities) caps).setJavascriptEnabled(true);
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		
		
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "resourceTimeout",
				100000);
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "loadImages",
				false);
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
	            "--web-security=false",
	            "--ignore-ssl-errors=true",
	            "--disk-cache=true"/*,
	            "--handlesAlerts=true"*/
	        });
		
//		PhantomJSDriver driver = new PhantomJSDriver(caps);
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://sd.ac.10086.cn/login/");
		driver.switchTo().frame("submitFrame");
		WebElement user = CrawlerUtils.findElementByDriver(driver, "mobileNum", CrawlerUtils.ID);
		user.sendKeys("15865265075");
		WebElement loginMode = CrawlerUtils.findElementByDriver(driver, "logonMode", CrawlerUtils.ID);
		loginMode.click();
		WebElement pass = CrawlerUtils.findElementByDriver(driver, "servicePWD", CrawlerUtils.ID);
		pass.sendKeys("985721");
		WebElement btn = CrawlerUtils.findElementByDriver(driver, "button", CrawlerUtils.ID);
		btn.click();
		driver.switchTo().defaultContent();
		CrawlerUtils.sleep(2000);
		System.out.println("****************************************"+driver.getCurrentUrl());
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementsByClassName('thirdNavNew')[4].style.display='block';");
		
		WebElement url = CrawlerUtils.findElementByDriver(driver, "#indexForm > div.myMob_leftSide > div.myNav > div:nth-child(2) > ul > li.son.last > div.thirdNavNew > div.content > table > tbody > tr:nth-child(3) > td:nth-child(2) > a:nth-child(1)", CrawlerUtils.CSS);
		System.out.println("====================="+url.getAttribute("onclick"));
		url.click();
		CrawlerUtils.sleep(2000);
		
		WebElement refesh = CrawlerUtils.findElementByDriver(driver, "refreshRandomCode", CrawlerUtils.ID);
		int i = 0;
		while(i <= 1000000)
		{
			refesh.click();
			WebElement imgEle = CrawlerUtils.findElementByDriver(driver, "randomCodeImage", CrawlerUtils.ID);
			String imgUrl = imgEle.getAttribute("src");
//			System.out.println(imgUrl);
			Set<Cookie> cookies = driver.manage().getCookies();
			StringBuffer result=new StringBuffer("");
			for(Cookie cookie:cookies)
			{
				result.append(cookie.getName()+"="+cookie.getValue()+";");
			}
			String cookie =  result.substring(0, result.length()-1);
			System.out.println("*********************"+Chinamoblie.putChaptaToLocal(imgUrl, cookie, i+""));
			i++;
		}
		
	}
	
	public static String putChaptaToLocal(String imgUrl,String result, String taskHashcode) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		File resourceFilePath = new File("");
        if (!resourceFilePath.exists())
        {
            resourceFilePath.mkdirs();
        }
		String imageUrl = "d:/imgetemp"+"/"+taskHashcode+".jpg";
		try {
			url = new URL(imgUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.setRequestProperty("Cookie", result);
//			System.out.println("cookie***"+result);
//			httpUrl.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
//			httpUrl.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
//			httpUrl.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			httpUrl.setRequestProperty("Connection", "Keep-Alive");
			httpUrl.setRequestProperty("Host","www.sd.10086.cn");
			httpUrl.setRequestProperty("Referer", "http://www.sd.10086.cn/eMobile/checkSmsPass.action?menuid=customerinfo");
//			httpUrl.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(imageUrl);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
			return imageUrl;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "-1";
		}
		finally
		{
			try 
			{
				if(fos != null)
				{
					fos.close();
				}
				if(bis != null)
				{
					bis.close();
				}
				if(httpUrl != null)
				{
					httpUrl.disconnect();
				}
			}
			catch (Exception e) 
			{
			}
		}
	}

}
