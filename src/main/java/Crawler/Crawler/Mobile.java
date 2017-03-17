package Crawler.Crawler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.alibaba.fastjson.JSONObject;


public class Mobile {
	public static void main(String[] args){
		
		
		/*Calendar ca = Calendar.getInstance();
		System.out.println("year=========="+(ca.get(Calendar.YEAR)));
		System.out.println("month========="+(ca.get(Calendar.MONTH) + 1));
		
		ca.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String last = format.format(ca.getTime());
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
		System.out.println("===============last:"+last);*/
		
		
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
		
		PhantomJSDriver driver = new PhantomJSDriver(caps);
//		WebDriver driver = new ChromeDriver();
		/*Object obj = driver.executePhantomJS("alertmsg='aa'; var page=this;  page.onAlert = function(msg) {console.log('ALERT: ' + msg);alertmsg=msg;"
				+"alertmsg=msg;console.log('**********'+alertmsg);return true;};");
		driver.get("http://www.dreamdu.com/javascript/exe_window.alert/");
		obj = driver.executePhantomJS("var page = this; return alertmsg;");
		/*obj = driver.executePhantomJS(""
				+ "var h = document.createElement('input');"
				+ "h.setAttribute('id', 'ghostId_');h.setAttribute('value', 'aa');document.body.appendChild(h);"
				+ "console.log(document.getElementById('ghostId_').value);console.log('**********'+aa);};", str, str);*/
		try
		{
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
			
//			获取用户名
//			WebElement usermessage = CrawlerUtils.findElementByDriver(driver, ".message", CrawlerUtils.CSS);
//			System.out.println("real_name======================="+CrawlerUtils.findElementByElement(usermessage, "div", CrawlerUtils.CSS).getText());
//			获取电话
//			System.out.println("real_phone======================"+CrawlerUtils.findElementByElement(usermessage, "span", CrawlerUtils.CSS).getText());
//			获取身份证
//			driver.get("http://www.sd.10086.cn/eMobile/qryCustInfo.action?menuid=customerinfo");
			Actions action = new Actions(driver);  
			WebElement aa = CrawlerUtils.findElementByDriver(driver, "li.son.last", CrawlerUtils.CSS);
			System.out.println(aa.getText());
			CrawlerUtils.sleep(1000);
			action.moveToElement(aa).clickAndHold();
			CrawlerUtils.sleep(1000);
			CrawlerUtils.sleep(2000);
			
			JavascriptExecutor js = ((JavascriptExecutor) driver);

//			获取用户基本信息
			js.executeScript("document.getElementsByClassName('thirdNavNew')[4].style.display='block';");
			
			WebElement url = CrawlerUtils.findElementByDriver(driver, "#indexForm > div.myMob_leftSide > div.myNav > div:nth-child(2) > ul > li.son.last > div.thirdNavNew > div.content > table > tbody > tr:nth-child(3) > td:nth-child(2) > a:nth-child(1)", CrawlerUtils.CSS);
			System.out.println("====================="+url.getAttribute("onclick"));
			url.click();
			CrawlerUtils.sleep(2000);

			CrawlerUtils.findElementByDriver(driver, "refreshRandomCode", CrawlerUtils.ID).click();
			WebElement imgEle = CrawlerUtils.findElementByDriver(driver, "randomCodeImage", CrawlerUtils.ID);
			String imgUrl = imgEle.getAttribute("src");
			System.out.println(imgUrl);
			Set<Cookie> cookies = driver.manage().getCookies();
			StringBuffer result=new StringBuffer("");
			for(Cookie cookie:cookies)
			{
				result.append(cookie.getName()+"="+cookie.getValue()+";");
			}
			String cookie =  result.substring(0, result.length()-1);
			System.out.println("*********************"+CrawlerUtils.putChaptaToLocal(imgUrl, cookie, "checkImg"));
			WebElement send = CrawlerUtils.findElementByDriver(driver, "sendBtnCount", CrawlerUtils.ID);
			send.click();
			CrawlerUtils.sleep(2000);
			btn = CrawlerUtils.findElementByDriver(driver, "dialogCancelDialogConfirm", CrawlerUtils.ID);
			btn.click();
			CrawlerUtils.sleep(2000);
			/*Alert alt = driver.switchTo().alert();
	        alt.accept();*/
	        System.out.println(driver.getCurrentUrl());
			
			Scanner s = new Scanner(System.in); 
			String msg = s.nextLine(); 
            s = new Scanner(System.in); 
            String img = s.nextLine(); 
            
            WebElement randsms = CrawlerUtils.findElementByDriver(driver, "randomSms", CrawlerUtils.ID);
            WebElement confirmCode = CrawlerUtils.findElementByDriver(driver, "confirmCode", CrawlerUtils.ID);
            btn = CrawlerUtils.findElementByDriver(driver, "#checkSmsPass_commit > div.myMob_rightSide > div.myMobile_content > div.personInfo_tableBorder > input", CrawlerUtils.CSS);
            
            randsms.sendKeys(msg);
            confirmCode.sendKeys(img);
            btn.click();
			
			CrawlerUtils.sleep(2000);
			System.out.println("*************"+driver.getCurrentUrl());
			System.out.println("idCard========================="+CrawlerUtils.findElementByDriver(driver, "//*[@id='tableAjaxID']/tbody/tr[2]/td[4]", CrawlerUtils.XPATH).getText());
//			获取注册时间
			System.out.println("reg_time========================="+CrawlerUtils.findElementByDriver(driver, "//*[@id='tableAjaxID']/tbody/tr[3]/td[4]", CrawlerUtils.XPATH).getText());
			

			js.executeScript("document.getElementsByClassName('thirdNavNew')[2].style.display='block';");
			url = CrawlerUtils.findElementByDriver(driver, "//*[@id='qryCustInfo']/div[1]/div[2]/div[2]/ul/li[3]/div[3]/div[2]/table/tbody/tr[2]/td[2]/a[1]", CrawlerUtils.XPATH);
			System.out.println("====================="+url.getAttribute("onclick"));
			url.click();
			CrawlerUtils.sleep(2000);
			
			String infoUrl = driver.getCurrentUrl();
			
//			获取通话记录
			WebElement info = CrawlerUtils.findElementByDriver(driver, "#qryNewBill > tbody > tr:nth-child(2) > td:nth-child(2) > input[type='radio']", CrawlerUtils.CSS);
			info.click();
			
			/*js.executeScript("var start=document.getElementById('startDate');start.removeAttribute('readonly');"
					+ "start.setAttribute('value','2016-07-01 00:00:00');");
			
			js.executeScript("var end=document.getElementById('endDate');end.removeAttribute('readonly');"
					+ "end.setAttribute('value','2016-12-01 00:00:00');");*/
			
			
			Calendar cal = Calendar.getInstance();
			
			
			Set<String> allWindowsId = null;
			List<WebElement> eles = null;
			List<String> headList = null;
			boolean isHead = true;
			String infowindowHandle = driver.getWindowHandle();
			
			int count=0;
			List<Map<String,String>> callList = new ArrayList<>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			while(count<5)
			{
				if(count != 0)
				{
					cal.add(Calendar.MONTH,(0-1));
					cal.set(Calendar.DAY_OF_MONTH, 1);
					String firstDay = format.format(cal.getTime());
					cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  ;
					String lastDay = format.format(cal.getTime());				
					
					js.executeScript("var start=document.getElementById('startDate');start.removeAttribute('readonly');"
							+ "start.setAttribute('value','"+firstDay+" 00:00:00');");
					
					js.executeScript("var end=document.getElementById('endDate');end.removeAttribute('readonly');"
							+ "end.setAttribute('value','"+lastDay+" 23:59:59');");
				}
				btn = CrawlerUtils.findElementByDriver(driver, "qryBtnId", CrawlerUtils.ID);
				System.out.println(btn.getAttribute("onclick"));
				btn.click();
				CrawlerUtils.sleep(2000);
				
				System.out.println("infowindowHandle====================="+infowindowHandle);
				
				allWindowsId = driver.getWindowHandles();
				System.out.println(allWindowsId);
				for (String windowId : allWindowsId) {
					System.out.println("windowId====================="+windowId);
					System.out.println("infowindowHandle=================="+infowindowHandle);
					if(windowId.equalsIgnoreCase(infowindowHandle))
					{
						continue;
					}
					
			        System.out.println(driver.switchTo().window(windowId).getCurrentUrl());
			    }
				CrawlerUtils.sleep(2000);
				eles = CrawlerUtils.findElementsByElement(CrawlerUtils.findElementByDriver(driver, ".box_out06", CrawlerUtils.CSS), "tr", CrawlerUtils.CSS);
				headList = new ArrayList<>();
				for(WebElement ele : eles)
				{
					List<WebElement> temps = CrawlerUtils.findElementsByElement(ele, "td", CrawlerUtils.CSS);
					if(temps.size() != 9)
					{
						continue;
					}
					if(isHead)
					{
//						表头
						for(WebElement temp : temps)
						{
							headList.add(temp.getText());
						}
						isHead = false;
					}
					else
					{
//						非表头
						Map<String,String> calMap = new HashMap<>();
						for(int i=0, size=headList.size(); i<size; i++)
						{
							calMap.put(headList.get(i), temps.get(i).getText());
						}
						callList.add(calMap);
					}
				}
				driver.close();
				driver.switchTo().window(infowindowHandle);
				count++;
				
			}
			
			System.out.println("通话记录***********************"+callList.toString());
			System.out.println("***************通话记录"+callList.size());
			
			
//			driver.switchTo().defaultContent();
//			
			driver.navigate().refresh();
			CrawlerUtils.sleep(2000);
			
			
//			短彩信#qryNewBill > tbody > tr:nth-child(2) > td:nth-child(3) > input[type="radio"]
//			try document.getElementById().value='3';
			info = CrawlerUtils.findElementByDriver(driver, "#qryNewBill > tbody > tr:nth-child(2) > td:nth-child(3) > input[type='radio']", CrawlerUtils.CSS);
			info.click();
			btn = CrawlerUtils.findElementByDriver(driver, "qryBtnId", CrawlerUtils.ID);
			System.out.println(btn.getAttribute("onclick"));
			btn.click();
			CrawlerUtils.sleep(2000);
			allWindowsId = driver.getWindowHandles();
			System.out.println(allWindowsId);
			for (String windowId : allWindowsId) {
				if(windowId.equalsIgnoreCase(infowindowHandle))
				{
					continue;
				}
		        System.out.println(driver.switchTo().window(windowId).getCurrentUrl());;
		    }
			CrawlerUtils.sleep(2000);
			eles = CrawlerUtils.findElementsByElement(CrawlerUtils.findElementByDriver(driver, ".box_out06", CrawlerUtils.CSS), "tr", CrawlerUtils.CSS);
			List<Map<String,String>> smsList = new ArrayList<>();
			headList = new ArrayList<>();
			isHead = true;
			for(WebElement ele : eles)
			{
				List<WebElement> temps = CrawlerUtils.findElementsByElement(ele, "td", CrawlerUtils.CSS);
				if(temps.size() != 8)
				{
					continue;
				}
				if(isHead)
				{
//					表头
					for(WebElement temp : temps)
					{
						headList.add(temp.getText());
					}
					isHead = false;
				}
				else
				{
//					非表头
					Map<String,String> calMap = new HashMap<>();
					for(int i=0, size=headList.size(); i<size; i++)
					{
						calMap.put(headList.get(i), temps.get(i).getText());
					}
					smsList.add(calMap);
				}
			}
			System.out.println("短信记录***********************"+smsList.toString());
			System.out.println("***************短信记录"+smsList.size());
			
			driver.close();
			driver.switchTo().window(infowindowHandle);
//			driver.switchTo().defaultContent();
//			driver.get(infoUrl);
			CrawlerUtils.sleep(2000);
			
//			上网#qryNewBill > tbody > tr:nth-child(3) > td:nth-child(1) > input[type="radio"]
//			try document.getElementById().value='4';
			info = CrawlerUtils.findElementByDriver(driver, "#qryNewBill > tbody > tr:nth-child(3) > td:nth-child(1) > input[type='radio']", CrawlerUtils.CSS);
			info.click();
			btn = CrawlerUtils.findElementByDriver(driver, "qryBtnId", CrawlerUtils.ID);
			System.out.println(btn.getAttribute("onclick"));
			btn.click();
			CrawlerUtils.sleep(2000);
			allWindowsId = driver.getWindowHandles();
			System.out.println(allWindowsId);
			for (String windowId : allWindowsId) {
				if(windowId.equalsIgnoreCase(infowindowHandle))
				{
					continue;
				}
		        System.out.println(driver.switchTo().window(windowId).getCurrentUrl());;
		    }
			CrawlerUtils.sleep(2000);
			eles = CrawlerUtils.findElementsByElement(CrawlerUtils.findElementByDriver(driver, ".box_out06", CrawlerUtils.CSS), "tr", CrawlerUtils.CSS);
			List<Map<String,String>> netList = new ArrayList<>();
			headList = new ArrayList<>();
			isHead = true;
			for(WebElement ele : eles)
			{
				List<WebElement> temps = CrawlerUtils.findElementsByElement(ele, "td", CrawlerUtils.CSS);
				if(temps.size() != 9)
				{
					continue;
				}
				if(isHead)
				{
//					表头
					for(WebElement temp : temps)
					{
						headList.add(temp.getText());
					}
					isHead = false;
				}
				else
				{
//					非表头
					Map<String,String> calMap = new HashMap<>();
					for(int i=0, size=headList.size(); i<size; i++)
					{
						calMap.put(headList.get(i), temps.get(i).getText());
					}
					netList.add(calMap);
				}
			}
			System.out.println("上网记录***********************"+netList.toString());
			System.out.println("***************上网记录"+netList.size());
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("通话记录", callList);
			jsonObj.put("短信记录", smsList);
			jsonObj.put("上网记录", netList);
			/*System.out.println(jsonObj.toJSONString());
			btn = CrawlerUtils.findElementByDriver(driver, "qryBtnId", CrawlerUtils.ID);
			System.out.println(btn.getAttribute("onclick"));
			btn.click();
			CrawlerUtils.sleep(2000);
<<<<<<< HEAD
//			Set<String> allWindowsId = driver.getWindowHandles();
			System.out.println(allWindowsId);
//			String infoUrl = driver.getCurrentUrl();
=======
			allWindowsId = driver.getWindowHandles();
			System.out.println(allWindowsId);
			infoUrl = driver.getCurrentUrl();
>>>>>>> 0d8260be96c64d8b30e16e48c953a338d61bfe0f
			for (String windowId : allWindowsId) {
		        System.out.println(driver.switchTo().window(windowId).getCurrentUrl());;
		    }
			driver.get(infoUrl);
			System.out.println("***********************"+driver.getCurrentUrl());
			
			info = CrawlerUtils.findElementByDriver(driver, "#qryNewBill > tbody > tr:nth-child(2) > td:nth-child(2) > input[type='radio']", CrawlerUtils.CSS);
			info.click();
			btn = CrawlerUtils.findElementByDriver(driver, "qryBtnId", CrawlerUtils.ID);
			System.out.println(btn.getAttribute("onclick"));
			btn.click();
			CrawlerUtils.sleep(2000);
			allWindowsId = driver.getWindowHandles();
			System.out.println(allWindowsId);
			infoUrl = driver.getCurrentUrl();
			for (String windowId : allWindowsId) {
		        System.out.println(driver.switchTo().window(windowId).getCurrentUrl());;
		    }*/
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
}
