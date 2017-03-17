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

public class ShangHaiUnicom {
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
		String ShangHaiUnicomUrl = "https://uac.10010.com/portal/mallLogin.jsp?redirectURL=http://www.10010.com"; //上海联通URL
		driver.get(ShangHaiUnicomUrl);//转到上海联通登录页面
		CrawlerUtils.sleep(2000);  //暂停2s
		//driver.switchTo().frame("submitFrame");
		//String account = "708926849@qq.com";
		//String password = "qlpckf1212";
		String account = "13120523886";
		String password = "742839";
		
		//定位到login_iframe标签下！！！
		
		WebElement pass = CrawlerUtils.findElementByDriver(driver, "#userPwdDiv > dd", CrawlerUtils.CSS);
		pass.sendKeys(password); //输入密码
		WebElement  login = CrawlerUtils.findElementByDriver(driver, "#login1", CrawlerUtils.CSS);
		login.click();  //点击登录
		WebElement  elev = CrawlerUtils.findElementByDriver(driver, "#searchTime > ul > li.on", CrawlerUtils.CSS);
		elev.click();
		/*driver.switchTo().frame(CrawlerUtils.findElementByDriver(driver, ".login_iframe", CrawlerUtils.CSS)); 
		WebElement user = CrawlerUtils.findElementByDriver(driver, "#userName", CrawlerUtils.CSS);
		user.sendKeys(account);  //输入账户名
		WebElement pass = CrawlerUtils.findElementByDriver(driver, "userPwd", CrawlerUtils.ID);
		pass.sendKeys(password); //输入密码
		WebElement  login = CrawlerUtils.findElementByDriver(driver, "#login1", CrawlerUtils.CSS);
		login.click();  //点击登录
*/		
		//进入自助服务
		CrawlerUtils.sleep(3000);  //暂停
		WebElement selfService = CrawlerUtils.findElementByDriver(driver, "body > div.center980.contentBg > div:nth-child(1) > div.navIndex.navMar > div.navIndexC > div.nav > ul > li:nth-child(2) > a", CrawlerUtils.CSS);
		selfService.click();
		
		//进入查询
		CrawlerUtils.sleep(1000);  //暂停
		WebElement inquire = CrawlerUtils.findElementByDriver(driver, "#menu_query > span", CrawlerUtils.CSS);
		inquire.click();
		String type = "上海联通信息";
		//进入我的基本信息**********
		/*basic                     基本信息                                list 
		update_time                 数据获取时间                         String
		cell_phone                 	本机号码      		   String
		idcard              		登记身份证号                         String
		real_name              		登记姓名                      	   String
		reg_time					入网时间                                 String*/

		/*CrawlerUtils.sleep(1000);  //暂停
		//进入我的基本信息
		WebElement myBasicInfo = CrawlerUtils.findElementByDriver(driver, "#mainContent > div.wrapper.minHeight > div:nth-child(1) > div.query_list_r > ul > li:nth-child(5)", CrawlerUtils.CSS);
		myBasicInfo.click();
		//提取我的资料
		List<String> basicInformation = new ArrayList<>();  //存储基本信息
		String title = "1.基本信息:"; 
		basicInformation.add(title);
		CrawlerUtils.sleep(1000);  //暂停
		driver.switchTo().frame(CrawlerUtils.findElementByDriver(driver, "#mainContent > iframe", CrawlerUtils.CSS)); 
		WebElement myMessageName = CrawlerUtils.findElementByDriver(driver, "#userInfocontext > div.add_main > div.data_basic_c.ly_gr_zl > dl:nth-child(1) > dd", CrawlerUtils.CSS);
		String name = myMessageName.getText();
		basicInformation.add(name);
		
		WebElement myMessageSex = CrawlerUtils.findElementByDriver(driver, "#userInfocontext > div.add_main > div.data_basic_c.ly_gr_zl > dl:nth-child(3) > dd", CrawlerUtils.CSS);
		String sex = myMessageSex.getText();
		basicInformation.add(sex);
		
		WebElement myMessageIdcard = CrawlerUtils.findElementByDriver(driver, "#userInfocontext > div.add_main > div.data_basic_c.ly_gr_zl > dl:nth-child(5) > dd", CrawlerUtils.CSS);
		String idcard = myMessageIdcard.getText();
		basicInformation.add(idcard);
		
		WebElement myMessageCellphone = CrawlerUtils.findElementByDriver(driver, "#userInfocontext > div.add
		
		_main > div.data_basic_c.ly_gr_zl > dl:nth-child(9) > dd", CrawlerUtils.CSS);
		String cellphone = myMessageCellphone.getText();
		basicInformation.add(cellphone);
		
		WebElement myMessageAddress = CrawlerUtils.findElementByDriver(driver, "#userInfocontext > div.add_main > div.data_basic_c.ly_gr_zl > dl:nth-child(11) > dd", CrawlerUtils.CSS);
		String address = myMessageAddress.getText();
		basicInformation.add(address);
		
		WebElement myMessageRegtimeName = CrawlerUtils.findElementByDriver(driver, "#numberContext > div.data_basic_c2 > div.data_basic_c2_r > dl:nth-child(1) > dt", CrawlerUtils.CSS);
		WebElement myMessageRegtime = CrawlerUtils.findElementByDriver(driver, "#numberContext > div.data_basic_c2 > div.data_basic_c2_r > dl:nth-child(1) > dd", CrawlerUtils.CSS);
		String regtime = myMessageRegtimeName.getText()+myMessageRegtime.getText();
		basicInformation.add(regtime);
		//String basicInfomation = name+","+sex+","+idcard+","+cellphone+","+address+","+regtime; //将所有基本信息整合在一起
		writeFileText(basicInformation,type,title); //写入到txt文档
*/		
		
		//查询通话记录***********
		/*calls                     通话信息                        list 
		update_time					数据获取时间                 String
		cell_phone                 	本机号码      		String
		place             			通话地点                         String
		other_cell_phone            对方号码                         String
		subtotal					本次通话花费                 float
		start_time                 	通话时间      		String
		init_type             		呼叫类型                         String
		call_type                 	通话类型      		String
		use_time             		通话时长                         float*/
		
		CrawlerUtils.sleep(1000);
		//进入通话详单
		WebElement callDetails = CrawlerUtils.findElementByDriver(driver, "#mainContent > div.wrapper.minHeight > div:nth-child(2) > div.query_list_r > ul > li:nth-child(3)", CrawlerUtils.CSS);
		callDetails.click();
		//获取页面上显示的所有查询时间
		List<WebElement> callTimeList = CrawlerUtils.findElementsByDriver(driver, "#searchTime > ul>li", CrawlerUtils.CSS);
		String title = "2.通话详单: ";
		List<String> tempRecord = new ArrayList<String>();
		List<WebElement> callRecord = null;
		for(WebElement ele:callTimeList)
		{
			ele.click();
			/*WebElement callTimeName = CrawlerUtils.findElementByDriver(driver, "#callDetailAll > dl:nth-child(5) > dt", CrawlerUtils.CSS);
			WebElement callTime = (WebElement) CrawlerUtils.findElementsByDriver(driver, "#callDetailAll > dl:nth-child(5) > dd", CrawlerUtils.CSS);
			String inquireTime = callTimeName.getText()+callTime.getText();
			System.out.println("inquireTime:"+inquireTime);*/
		   
			//WebElement recordLast =  CrawlerUtils.findElementByDriver(driver, "#searchTime > ul > li.on", CrawlerUtils.CSS);
			//recordLast.click();
			CrawlerUtils.sleep(2000);
			WebElement recordNumber =  CrawlerUtils.findElementByDriver(driver, "#callDetailAll > dl:nth-child(2) > dd", CrawlerUtils.CSS);
			String number = recordNumber.getText().substring(0, recordNumber.getText().length()-1); //获取记录条数的数值
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			int num = Integer.parseInt(number);
			if(num > 20)  //超过20条记录则会存在下一页这个标签
			{
				int len = num/20;
				//WebElement nextPage;
				for(int i = 0;i < len;i++)
				{
					
					if (i == 0) //第一页特殊处理
					{
						callRecord = CrawlerUtils.findElementsByDriver(driver, "#callDetailContent > table > tbody > tr", CrawlerUtils.CSS);
						for(WebElement record:callRecord)
						{
							tempRecord.add(record.getText());
							writeFileText(tempRecord,type,title); //写入到txt文档
							tempRecord.clear();
						}
						System.out.println("*******before"+driver.getPageSource());
						js.executeScript("window.scrollTo(0, document.body.scrollHeight)");//加载完本页面
						System.out.println("*******after"+driver.getPageSource());
						((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", CrawlerUtils.findElementsByDriver(driver, "#iframe_call_dan > div:nth-child(15) > div.wapContent_r", CrawlerUtils.CSS));
						CrawlerUtils.sleep(3000);
						((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", CrawlerUtils.findElementsByDriver(driver, "#callDetailContent > div.score_page > div:nth-child(4)", CrawlerUtils.CSS));
						WebElement nextPage = CrawlerUtils.findElementByDriver(driver, "#callDetailContent > div.score_page > div:nth-child(4)", CrawlerUtils.CSS);
						nextPage.click();
					}
					else
					{
						callRecord = CrawlerUtils.findElementsByDriver(driver, "#callDetailContent > table > tbody > tr", CrawlerUtils.CSS);
						for(WebElement record:callRecord)
						{
							tempRecord.add(record.getText());
							writeFileText(tempRecord,type,title); //写入到txt文档
							tempRecord.clear();
						}
						js.executeScript("window.scrollTo(0, document.body.scrollHeight)");//加载完本页面
						CrawlerUtils.sleep(3000);
						WebElement nextPage = CrawlerUtils.findElementByDriver(driver, "#callDetailContent > div.score_page > div:nth-child(5)", CrawlerUtils.CSS);
						nextPage.click();
					}
					
				}
				
			}
			else
			{
				callRecord = CrawlerUtils.findElementsByDriver(driver, "#callDetailContent > table > tbody > tr", CrawlerUtils.CSS);
				for(WebElement record:callRecord)
				{
					tempRecord.add(record.getText());
					writeFileText(tempRecord,type,title); //写入到txt文档
					tempRecord.clear();
				}
			}
			System.out.println("**********");
		}
		

		//获取短信信息**************
		/*smses                     短信信息                               list
		update_time					数据获取时间                        String
		cell_phone                 	本机号码      		   String
		place             			发送地点                               String
		other_cell_phone            对方号码                               String
		subtotal				          本次短信花费                        float
		start_time                 	发送时间      		   String
		init_type             		发送类型                                 String*/
		
		
		
		
		//流量使用情况*************
		/*nets                     	流量使用信息                        list 
		update_time					数据获取时间                        String
		cell_phone                 	本机号码      		   String
		place             			通话地点                               String
		subtotal					本次使用花费                        float
		subflow						流量使用量                           float
		start_time                 	使用时间      		   String
		net_type                 	流量类型      		   String
		use_time             		使用时长                                float*/
		
		
		
		
		
		//账单信息*************
		/*transactions              账单信息                        	   list 
		update_time					数据获取时间                        String
		cell_phone                 	本机号码      		   String
		bill_cycle                 	账单月份      		   String
		plan_amt             		计划花费                                String
		total_amt					实际花费                        	   float
		pay_amt						月付费                       	   float*/

		/*CrawlerUtils.sleep(1000);
		//进入历史账单
		WebElement historyAccout = CrawlerUtils.findElementByDriver(driver, "#mainContent > div.wrapper.minHeight > div:nth-child(2) > div.query_list_r > ul > li:nth-child(1)", CrawlerUtils.CSS);
		historyAccout.click();
		CrawlerUtils.sleep(1000);
		String title = "5.账单信息: ";
		List<String> accoutRecord = new ArrayList<String>();
		driver.switchTo().frame(CrawlerUtils.findElementByDriver(driver, "iframe[src*='/e4/query/basic/history_list.html?menuId=000100020001']", CrawlerUtils.CSS)); 
		WebElement historyAccout1 = CrawlerUtils.findElementByDriver(driver, "#mobilehistory2 > div:nth-child(1) > ul[class*='query']> li[onclick*='201610']", CrawlerUtils.CSS);
		historyAccout1.click();
		CrawlerUtils.sleep(2000);
		accoutRecord.add(CrawlerUtils.findElementByDriver(driver, "#userinfoContext", CrawlerUtils.CSS).getText());
		System.out.println(accoutRecord);
		writeFileText(accoutRecord, type, title);
		accoutRecord.clear();
			
		List<WebElement> historyAccoutTime = CrawlerUtils.findElementsByDriver(driver, "#mobilehistory2 > div:nth-child(1) > ul[class*='query']> li[onclick*='20']", CrawlerUtils.CSS);
		for(WebElement ele:historyAccoutTime)
		{
			ele.click();
			CrawlerUtils.sleep(2000);
			accoutRecord.add(CrawlerUtils.findElementByDriver(driver, "#userinfoContext", CrawlerUtils.CSS).getText());
			System.out.println(accoutRecord);
			writeFileText(accoutRecord, type, title);
			accoutRecord.clear();
		}*/

		
		
		
		//System.out.println("myMessage.getText():"+myMessage.getText());
		
		
		/*//*****获取基本信息******
		CrawlerUtils.sleep(2000);  //暂停2s
		WebElement myUnicom = CrawlerUtils.findElementByDriver(driver, "span[class*='moreService']>a", CrawlerUtils.CSS);
		driver.get(myUnicom.getAttribute("href")); //进入“我的联通”页面
		CrawlerUtils.sleep(2000);  //暂停2s
		WebElement basicInfo = CrawlerUtils.findElementByDriver(driver, "#infomgrURL", CrawlerUtils.CSS);
		basicInfo.click(); //进入“我的信息”页面
		CrawlerUtils.sleep(2000);  //暂停2s
		
		//姓名框
		WebElement realName = CrawlerUtils.findElementByDriver(driver, "input[class*='input_style'][name*='infomgrBean']", CrawlerUtils.CSS);
		String name = realName.getAttribute("value");  //获取真实名字
		
		//手机号码框
		WebElement cellPhone = CrawlerUtils.findElementByDriver(driver, "input[class*='input_style'][id*='mobilephone']", CrawlerUtils.CSS);
		cellPhone.click(); //需要点击下手机号码框，否则手机号码中间只显示****
//		Actions actions = new Actions(driver);
//		actions.click(cellPhone);
		String phone = cellPhone.getAttribute("value"); //获取号码
		
		//身份证信息框
		WebElement idCardType = CrawlerUtils.findElementByDriver(driver, "#psptnodiv > label", CrawlerUtils.CSS);
		WebElement idCardNumber = CrawlerUtils.findElementByDriver(driver, "#psptno", CrawlerUtils.CSS);
		idCardNumber.click(); //需要点击下身份证号码框，否则身份证号码中间只显示****
		String cardType = idCardType.getText(); //获取id卡类型
		String cardNumber = idCardNumber.getAttribute("value"); //获取id卡类型
		
		//将基本信息写入到txt文件中
		String type = "上海联通信息";
		String title = "1.基本信息:"; 
		String basicInfomation = name+","+phone+","+cardType+":"+cardNumber; //将所有信息整合在一起
		writeFileText(basicInfomation,type,title); //写入到txt文档
		*/
//		System.out.println("realName.getText().name:"+realName.getAttribute("value")); //获取input框中的值
//		System.out.println("cellPhone.getText().name:"+cellPhone.getAttribute("value")); //获取input框中的值
//		System.out.println("idCardType.getText().name:"+idCardType.getText()); //获取input框中的值
//		System.out.println("idCardNumber.getText().name:"+idCardNumber.getAttribute("value")); //获取input框中的值
		
		
	}
	
	public static void writeFileText (List<String> content,String name,String title)  //将content写入到txt文件夹中 
	{
		  FileOutputStream fop = null;
		  File file;
		  String enter = "\r\n";
		  try 
		  {
			  file = new File("E:/爬虫/上海联通/"+name+".txt");
			  fop = new FileOutputStream(file,true);	
			  // if file doesnt exists, then create it
			  if (!file.exists()) 
			  {
				  file.createNewFile();
			  }
			  // get the content in bytes
			  String begin = title;
			  byte[] beginByte = begin.getBytes();
			  byte[] contentInBytes = content.toString().getBytes();
			  byte[] enterInbytes = enter.getBytes();
		   
			  fop.write(beginByte);
			  fop.write(contentInBytes);
			  fop.write(enterInbytes);
			  fop.write(enterInbytes);
		   
			  fop.flush();
			  fop.close();
		  
		  } 
		  catch (IOException e) 
		  {
			  e.printStackTrace();
		  } 
		  finally 
		  {
			   try 
			   {
					if (fop != null) 
					{
						fop.close();
					}
			   } 
			   catch (IOException e) 
			   {
				   e.printStackTrace();
			   }
		  }
	}

}
