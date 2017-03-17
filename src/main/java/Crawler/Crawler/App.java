package Crawler.Crawler;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Capabilities caps = new DesiredCapabilities();
		((DesiredCapabilities) caps).setJavascriptEnabled(true);
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		WebDriver driver = new PhantomJSDriver(caps);
    	Cache<String, WebDriver> cache = CacheBuilder.newBuilder().expireAfterAccess(20, TimeUnit.SECONDS).build(); 
    	cache.put("test1", driver);
    	CrawlerUtils.sleep(20000);
    	try
    	{
    		driver = cache.get("test1", new Callable<WebDriver>() {
    			@Override
    			public WebDriver call(){
    				// TODO Auto-generated method stub
    				return null;
    			}
    		});
    	}
    	catch(Exception e)
    	{
    		driver = null;
    	}
    	System.out.println(driver);
    }
}
