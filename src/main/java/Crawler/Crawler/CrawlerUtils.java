package Crawler.Crawler;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


public class CrawlerUtils {
	public static AtomicBoolean switchFlag = new AtomicBoolean(true);
	public final static String CSS = "css";
	public final static String XPATH = "xpath";
	public final static String ID = "id";
	
//	private static String IMGABSOLUTEPATH = "D:/image";
	
	public static WebElement findElementByElement(WebElement element, String selectString, String type) {
		WebElement result = null;
		try {
			if (type.equals(CSS)) {
				result = element.findElement(By.cssSelector(selectString));
			} else if (type.equals(XPATH)) {
				result = element.findElement(By.xpath(selectString));
			}
			return result;
		} catch (NoSuchElementException e) {
			return result;
		}

	}
    
    public static List<WebElement> findElementsByDriver(WebDriver driver, String selectString, String type) {
		List<WebElement> elements = new ArrayList<>();
		try {
			if (type.equals(CSS)) {
				elements = driver.findElements(By.cssSelector(selectString));
			} else if (type.equals(XPATH)) {
				elements = driver.findElements(By.xpath(selectString));
			}
			return elements;
		} catch (NoSuchElementException e) {
			return elements;
		}
	}
    
    public static WebElement findElementByDriver(WebDriver driver, String selectString, String type) {
		WebElement result = null;
		try {
			if (type.equals(CSS)) {
				result = driver.findElement(By.cssSelector(selectString));
			} else if (type.equals(XPATH)) {
				result = driver.findElement(By.xpath(selectString));
			} else if(type.equals(ID)) {
				result = driver.findElement(By.id(selectString));
			}
			return result;
		} catch (NoSuchElementException e) {
			return result;
		}
	}
    public static List<WebElement> findElementsByElement(WebElement element, String selectString, String type) {
		List<WebElement> elements = new ArrayList<>();
		try {
			if (type.equals(CSS)) {
				elements = element.findElements(By.cssSelector(selectString));
			} else if (type.equals(XPATH)) {
				elements = element.findElements(By.xpath(selectString));
			}
			return elements;
		} catch (NoSuchElementException e) {
			return elements;
		}
	}
    public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
    

    /**
     * 将url指向的图片保存到本地，并返回本地地址
     * @param imgUrl
     * @param result 
     * @return 成功返回本地图片地址，失败返回-1
     */
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
		String imageUrl = "d:/image"+"/"+taskHashcode+".jpg";
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
    
    public static String getWebelementAttribute(WebElement webElement, String attributeName)
    {
    	try
    	{
    		return (webElement == null) ? "" : webElement.getAttribute(attributeName);
    	}
    	catch(Exception e)
    	{
    		return "";
    	}
    }
    
    public static WebDriver getDriver(){
    	Capabilities caps = new DesiredCapabilities();
		((DesiredCapabilities) caps).setJavascriptEnabled(true);
		// ((DesiredCapabilities)
		// caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		// "D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
//		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//				"/usr/soft/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
		

		WebDriver driver = new PhantomJSDriver(caps);
//		 driver= new ChromeDriver();
		return driver;
    }
}
