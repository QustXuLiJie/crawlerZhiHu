package Crawler.Crawler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Unicom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = initWebDriver();
		try
		{
			login(driver);
			crawler(driver);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			driver.close();
			driver.quit();
			driver = null;
		}
		
	}
	
	public static WebDriver initWebDriver()
	{
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
		return driver;
	}
	
	public static void login(WebDriver driver) throws Exception
	{
//		1.登陆
		String targetUrl = "http://uac.10010.com/portal/hallLogin";
		driver.get(targetUrl);
		
		WebElement errorEle = CrawlerUtils.findElementByDriver(driver, ".mpLogin .error", CrawlerUtils.CSS);
		System.out.println("错误提示框====="+errorEle.getText());
		
//		2.获取userName、passWord、confirmbtn
		WebElement userEle = CrawlerUtils.findElementByDriver(driver, "#userName", CrawlerUtils.CSS);
		//userEle.sendKeys("18562672684");
//		userEle.sendKeys("13120523886");
		userEle.sendKeys("18656305135");
		WebElement passEle = CrawlerUtils.findElementByDriver(driver, "#userPwd", CrawlerUtils.CSS);
//		passEle.sendKeys("337744");
//		passEle.sendKeys("742839");
		passEle.sendKeys("151199");
		WebElement btn = CrawlerUtils.findElementByDriver(driver, "#login1", CrawlerUtils.CSS);
		btn.click();
		CrawlerUtils.sleep(5000);
		
//		3.错误提示
		errorEle = CrawlerUtils.findElementByDriver(driver, ".mpLogin .error", CrawlerUtils.CSS);
		System.out.println("错误提示框====="+errorEle.getText());
	}
	
	public static void crawler(WebDriver driver) throws Exception
	{
//		1.查询基本信息
		String basicInfoUrl = "http://iservice.10010.com/e4/query/basic/personal_xx_iframe.html?menuCode=000800010001";
		driver.get(basicInfoUrl);
		
		/*WebElement iframeEle = CrawlerUtils.findElementByDriver(driver, "#mainContent iframe", CrawlerUtils.CSS);
		driver.switchTo().frame(iframeEle);*/
		
		Set<Cookie> cookies = driver.manage().getCookies();
		StringBuffer result=new StringBuffer("");
		for(Cookie cookie:cookies)
		{
			result.append(cookie.getName()+"="+cookie.getValue()+";");
		}
		String cookie =  result.substring(0, result.length()-1);
		
		try
		{
			crawlerBillinfo(driver, cookie);
//			System.out.println("**********抓取基本信息 开始**********");
//			crawlerBasicInfo(driver, cookie);
//			System.out.println("**********抓取基本信息 结束**********");
			
//			System.out.println("**********抓取通话信息 开始**********");
//			crawlerCallInfo(driver, cookie);
//			System.out.println("**********抓取抓取通话信息信息 结束**********");
			
//			System.out.println("**********抓取上网信息 开始**********");
//			crawlerNetInfo(driver, cookie);
//			System.out.println("**********抓取上网信息 结束**********");
			
//			System.out.println("**********抓取短信信息 开始**********");
//			crawlerSmsInfo(driver, cookie);
//			System.out.println("**********抓取短信信息 结束**********");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查询基本信息
	 * @param driver
	 */
	public static void crawlerBasicInfo(WebDriver driver, String cookie) throws Exception
	{
		System.out.println(doPost("http://iservice.10010.com/e3/static/check/checklogin/?_="+new Date().getTime(), 
				cookie, ""));
	}
	
	/**
	 * 查询童话记录
	 * @param driver
	 */
	public static void crawlerCallInfo(WebDriver driver, String cookie) throws Exception
	{
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        /* 设置为当前时间 */
        cal.setTime(nowdate);
        System.out.println("当前时间是：" + sdf.format(nowdate));
        int monthCount = 0;
		for(int i=0; i<8; i++)
		{
			/* 当前日期月份-1 */
			if(i == 0)
			{
				cal.add(Calendar.MONTH, 0);
			}
			else
			{
				cal.add(Calendar.MONTH, -1);
			}
	        // 得到前一个月的第一天
	        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	        String startMonth = sdf.format(cal.getTime());
	        System.out.println("startMonth::::::::::" + startMonth);
	        // 得到前一个月的最后一天
	        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	        String endMonth = sdf.format(cal.getTime());
	        System.out.println("endMonth::::::::::" + endMonth);
			
			System.out.println(doPost("http://iservice.10010.com/e3/static/query/callDetail?_=1481900611934&accessURL=http%3A%2F%2Fiservice.10010.com%2Fe4%2Fquery%2Fbill%2Fcall_dan-iframe.html%3FmenuCode&menuid=000100030001",
					cookie, 
					"beginDate="+startMonth+"&endDate="+endMonth));
		}
	}
	
	/**
	 * 查询上网信息
	 * @param driver
	 */
	public static void crawlerNetInfo(WebDriver driver, String cookie) throws Exception
	{
		
		String url = "http://iservice.10010.com/e3/static/query/callFlow?_="+new Date().getTime()
				+ "&accessURL=http://iservice.10010.com/e4/query/basic/call_flow_iframe1.html?menuCode=000100030004"
				+ "&menuid=000100030004";
		int pageSize = 100;
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        /* 设置为当前时间 */
        cal.setTime(nowdate);
        System.out.println("当前时间是：" + sdf.format(nowdate));
		for(int i=0; i<8; i++)
		{
			if(i == 0)
			{
				cal.add(Calendar.MONTH, 0);
			}
			else
			{
				cal.add(Calendar.MONTH, -1);
			}
	        // 得到前一个月的第一天
	        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	        String startMonth = sdf.format(cal.getTime());
	        System.out.println("startMonth::::::::::" + startMonth);
	        // 得到前一个月的最后一天
	        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	        String endMonth = sdf.format(cal.getTime());
	        System.out.println("endMonth::::::::::" + endMonth);
			
	        
	        
	        /*try
	        {
	        	
	        	driver = initWebDriver();
	        	login(driver);
	        	String basicInfoUrl = "http://iservice.10010.com/e4/query/basic/personal_xx_iframe.html?menuCode=000800010001";
	    		driver.get(basicInfoUrl);
	    		
	    		WebElement iframeEle = CrawlerUtils.findElementByDriver(driver, "#mainContent iframe", CrawlerUtils.CSS);
	    		driver.switchTo().frame(iframeEle);
	    		
	    		Set<Cookie> cookies = driver.manage().getCookies();
	    		StringBuffer result=new StringBuffer("");
	    		for(Cookie cookietemp:cookies)
	    		{
	    			result.append(cookietemp.getName()+"="+cookietemp.getValue()+";");
	    		}
	    		cookie =  result.substring(0, result.length()-1);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        finally
	        {
	        	driver.close();
	        	driver.quit();
	        	driver = null;
	        }*/
	        
	        String firstPage = doPost(url,
					cookie, 
					"beginDate="+startMonth+"&endDate="+endMonth+"&pageNo=1&pageSize="+pageSize);
	        System.out.println("firstPage::::::::::"+firstPage);
	        JSONObject json = JSON.parseObject(firstPage);
	        int totalPage = json.getIntValue("totalpage");
	        System.out.println("总页数::::::::::"+totalPage);
	        int pageNo=2;
	        while(pageNo <= totalPage)
	        {
	        	System.out.println(doPost(url,
					cookie, 
					"beginDate="+startMonth+"&endDate="+endMonth+"&pageNo="+pageNo+"&pageSize="+pageSize));
	        	pageNo++;
	        }
		}
	}
	
	/**
	 * 查询短信信息
	 * @param driver
	 */
	public static void crawlerSmsInfo(WebDriver driver, String cookie) throws Exception
	{
		String url = "http://iservice.10010.com/e3/static/query/sms?_="+new Date().getTime()
				+ "&accessURL=http://iservice.10010.com/e4/query/calls/call_sms-iframe.html?menuCode=000100030002"
				+ "&menuid=000100030002";
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        /* 设置为当前时间 */
        cal.setTime(nowdate);
        System.out.println("当前时间是：" + sdf.format(nowdate));
		for(int i=0; i<8; i++)
		{
			/* 当前日期月份-1 */
			String startMonth = null;
			String endMonth = null;
			if(i == 0)
			{
				cal.add(Calendar.MONTH, 0);
				endMonth = sdf.format(cal.getTime());
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				startMonth = sdf.format(cal.getTime());
			}
			else
			{
				cal.add(Calendar.MONTH, -1);
				// 得到前一个月的第一天
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				startMonth = sdf.format(cal.getTime());
				// 得到前一个月的最后一天
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				endMonth = sdf.format(cal.getTime());
			}
			startMonth = startMonth.replace("-", "");
			endMonth = endMonth.replace("-", "");
			System.out.println("startMonth::::::::::" + startMonth);
	        System.out.println("endMonth::::::::::" + endMonth);
			
			System.out.println(doPost(url,
					cookie, 
					"begindate="+startMonth+"&enddate="+endMonth));
		}
	}
	
	public static void crawlerBillinfo(WebDriver driver, String cookie) throws Exception
	{
		String targetUrl = "http://iservice.10010.com/e3/static/query/queryHistoryBill?_="+new Date().getTime();
		System.out.println(doPost(targetUrl, cookie, 
				"querytype='0001'&querycode='0001'&billdate=201611&flag=1"));
		
		
		
	}
	
	public static String doPost(String postUrl, String cookie, String param) throws Exception
	{
		URL url = new URL(postUrl);
		// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
		// (标识一个url所引用的远程对象连接)
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
		// 设置连接输出流为true,默认false (post请求是以流的方式隐式的传递参数)
		connection.setDoOutput(true);
		// 设置连接输入流为true
		connection.setDoInput(true);
		// 设置请求方式为post
		connection.setRequestMethod("POST");
		// post请求缓存设为false
		connection.setUseCaches(false);
		// 设置该HttpURLConnection实例是否自动执行重定向
		connection.setInstanceFollowRedirects(true);
		// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
		// application/x-javascript text/xml->xml数据
		// application/x-javascript->json对象
		// application/x-www-form-urlencoded->表单数据
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//		connection.setRequestProperty("Cookie", "mallcity=11|110; iservice=PBV3YTjBMpn7RlRlXD1LMn9r2Tg8vzDQnZF4JQQ5LRTGhpwGnTlg!1963288011!NONE; route=ed50ca68bf40ebda0ed2f36ba779410e; SHOP_PROV_CITY=; _dc_gtm_UA-27681312-1=1; e3=1BK7YT2JTS51Y6J0J0TB1XLG7M9l7H40dGCPQTgBhHzNyXhv5LGY!-1234215335; piw=%7B%22login_name%22%3A%22185****2684%22%2C%22nickName%22%3A%22%E8%AE%B8%E7%AB%8B%E6%9D%B0%22%2C%22rme%22%3A%7B%22ac%22%3A%22%22%2C%22at%22%3A%22%22%2C%22pt%22%3A%2201%22%2C%22u%22%3A%2218562672684%22%7D%2C%22verifyState%22%3A%22%22%7D; JUT=SMyxuKtDxD8PDQcDu+gtpS0Utup3gqKWETHkQ1sV+fjCP1kNDSsmUN6+vTmIIKZCNvWBQWBX/kqn7K7+WE/L4/a+q3hJI83jVo3dgk5BjNhZO5VRowWAPwqzXLtYed390Ys9FkwdD8PslrO8XfopZQeAUIj9I3XRscLQp8jJQ/l5tvCrBCUOeDqZyWgzz4sshz51fdKgaGKij1t6NpJ6rCLHinsKc2q+uc1hPhQkfJA/sI6gFgTTZYlhXbmjFT2vq088qWaWS/Zu4N2OuT6oszUfkGVGCRIWMS0MiYQkg8MeEWhiHWHqSmmFXZ6fBKxJV+uEacvR+/my16vT7HPksE0HvhNQTUI0WlMJAtwIpapn62Y+0EyvLhNBjNZBhfIBtxpvbjlJv6MqeO/dVYeDfPWdK5YvHePrGCjjv74IlL/CTXCTfeb5ebblrym8108MRplxFs5d3y0E82+8N8KtGJkCEot+7KHSdpW1g12frYUqzFMsO1NoaCS1YvXSsOE6vCTJMe3NvQ2idoO7Ft3NvXWr9BBJnCo3A4DD/pbSbFA=AKcuZIQBmx03Q4dABBVUQg==; _uop_id=af1c3ad794ddedc9c807693000e3616b; _n3fa_cid=429d32318ac34084ba66eb97b4a4319b; _n3fa_ext=ft=1481899723; _n3fa_lvt_a9e72dfe4a54a20c3d6e671b3bad01d9=1481899723; _n3fa_lpvt_a9e72dfe4a54a20c3d6e671b3bad01d9=1481899723; Hm_lvt_9208c8c641bfb0560ce7884c36938d9d=1481891986,1481892697,1481892722,1481892843; Hm_lpvt_9208c8c641bfb0560ce7884c36938d9d=1481899735; _ga=GA1.3.283735564.1481892033; WT_FPC=id=2636818929a033dca941481891985503:lv=1481899822932:ss=1481899582695");
		connection.setRequestProperty("Cookie", cookie);
		// 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
		connection.connect();
		// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
		// "storeId=" + URLEncoder.encode("32", "utf-8");
		// //URLEncoder.encode()方法 为字符串进行编码

		// 将参数输出到连接
		// dataout.writeBytes(paramHeader+"="+URLEncoder.encode(paramValue,
		// "utf-8"));
		dataout.writeBytes(param);
		// 输出完成后刷新并关闭流
		dataout.flush();
		dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
		// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
		System.out.println("responseCode====="+connection.getResponseCode());
		BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuilder sb = new StringBuilder(); // 用来存储响应数据

		String line = "";
		// 循环读取流,若不到结尾处
		while ((line = bf.readLine()) != null) {
			sb.append(line);
		}
		bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
		connection.disconnect(); // 销毁连接
		String retStr = sb.toString();
		return retStr;
	}

}
