/*
package parsexml;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mobanker.crawler.common.model.Task;
import com.mobanker.crawler.common.model.chinamobile.Basic;
import com.mobanker.crawler.common.model.chinamobile.Bill;
import com.mobanker.crawler.common.model.chinamobile.Call;
import com.mobanker.crawler.common.model.chinamobile.CrawlerHttpUtils;
import com.mobanker.crawler.common.model.chinamobile.Net;
import com.mobanker.crawler.common.model.chinamobile.Sms;
import com.mobanker.crawler.common.utils.CrawlerUtils;

@Service("crawlerService")
public class CrawlerServiceImpl extends AbstractCrawlerServiceImpl
{
	
	@Override
	public int login(WebDriver driver, Task task, boolean isFirstLogin) throws Exception {
		// TODO Auto-generated method stub
//		1.登陆
		String targetUrl = "http://uac.10010.com/portal/hallLogin";
		driver.get(targetUrl);
		
		WebElement errorEle = CrawlerUtils.findElementByDriver(driver, ".mpLogin .error", CrawlerUtils.CSS);
		System.out.println("错误提示框====="+errorEle.getText());
		
//		2.获取userName、passWord、confirmbtn
		WebElement userEle = CrawlerUtils.findElementByDriver(driver, "#userName", CrawlerUtils.CSS);
//		userEle.sendKeys("18562672684");
		userEle.sendKeys("13120523886");
//		userEle.sendKeys("18656305135");
		WebElement passEle = CrawlerUtils.findElementByDriver(driver, "#userPwd", CrawlerUtils.CSS);
//		passEle.sendKeys("337744");
		passEle.sendKeys("742839");
//		passEle.sendKeys("151199");
		WebElement btn = CrawlerUtils.findElementByDriver(driver, "#login1", CrawlerUtils.CSS);
		btn.click();
		CrawlerUtils.sleep(5000);
		
//		3.错误提示
		errorEle = CrawlerUtils.findElementByDriver(driver, ".mpLogin .error", CrawlerUtils.CSS);
		System.out.println("错误提示框====="+errorEle.getText());
		return 0;
	}

	@Override
	public int crawlerInfo(WebDriver driver, Task task) throws Exception {
		// TODO Auto-generated method stub
//		String basicInfoUrl = "http://iservice.10010.com/e4/query/basic/personal_xx_iframe.html?menuCode=000800010001";
		String basicInfoUrl="http://iservice.10010.com/e4/index_server.html";
		driver.get(basicInfoUrl);
		
		Set<Cookie> cookies = driver.manage().getCookies();
		StringBuffer result=new StringBuffer("");
		for(Cookie cookie:cookies)
		{
			result.append(cookie.getName()+"="+cookie.getValue()+";");
		}
		String cookie =  result.substring(0, result.length()-1);
		
		long starttime = System.currentTimeMillis();
		
		JSONObject json =  new JSONObject();
		System.out.println("**********抓取基本信息 开始**********");
		json.put("basic", crawlerBasicInfo(cookie));

		System.out.println("**********抓取账单信息 开始**********");
		json.put("bill", crawlerBillInfo(cookie));
		
		System.out.println("**********抓取通话信息 开始**********");
		json.put("call", crawlerCallInfo(cookie));

		System.out.println("**********抓取短信信息 开始**********");
		json.put("sms", crawlerSmsInfo(cookie));

		System.out.println("**********抓取上网信息 开始**********");
		json.put("net", crawlerNetInfo(cookie));
		
		System.out.println(JSON.toJSONString(json));
		System.out.println("耗时时间:::::"+(System.currentTimeMillis()-starttime));
		return 0;
	}
	
	private Basic crawlerBasicInfo(String cookie)
	{
		String postUrl = "http://iservice.10010.com/e3/static/check/checklogin/?_="+new Date().getTime();
		String retData = CrawlerHttpUtils.crawlerRequest(postUrl, cookie, "");
		Basic basic = new Basic();
		if(StringUtils.isNotBlank(retData))
		{
			JSONObject json = JSON.parseObject(retData);
			JSONObject userinfo = json.getJSONObject("userInfo");
			if(userinfo != null)
			{
				basic.setCell_phone(userinfo.getString("usernumber"));
				basic.setIdcard(userinfo.getString("certnum"));
				basic.setReal_name(userinfo.getString("custName"));
				basic.setReg_time(userinfo.getString("opendate"));
				basic.setUpdate_time("");
			}
		}
		return basic;
	}
	
	private List<Bill> crawlerBillInfo(String cookie)
	{
		List<Bill> bills = new ArrayList<>();
		String postUrl = "http://iservice.10010.com/e3/static/query/queryHistoryBill?_="+new Date().getTime();
		int count = 0;
		String billdate = "";
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar cal = Calendar.getInstance();
        */
/* 设置为当前时间 *//*

        cal.setTime(nowdate);
        int flag = 1;
		while(count < 6)
		{
			cal.add(Calendar.MONTH, -1);
			// 得到前一个月的第一天
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			billdate = sdf.format(cal.getTime());
			if(count != 0)
			{
				
				flag = 2;
			}
	        String param = "querytype='0001'&querycode='0001'&billdate="+billdate+"&flag="+flag;
	        String retData = CrawlerHttpUtils.crawlerRequest(postUrl, 
	        		cookie,
	        		param);
	        if(StringUtils.isNotBlank(retData))
	        {
	        	JSONObject json = JSON.parseObject(retData);
	        	JSONObject result = json.getJSONObject("result");
	        	if(result != null)
	        	{
	        		Bill bill = new Bill();
	        		bill.setBill_cycle(result.getString("cycleid"));
	        		bill.setCell_phone(result.getString("serialnumber"));
	        		JSONArray infoArray = result.getJSONArray("billinfo");
	        		if(infoArray != null && infoArray.size() > 0)
	        		{
	        			JSONObject obj = infoArray.getJSONObject(0);
	        			if(obj != null)
	        			{
//	    	        		计划费用 0
	    	        		bill.setPlan_amt(obj.getString("fee"));
	        			}
	        		}
//	        		应交费用
	        		bill.setPay_amt(result.getString("balance"));
//	        		费用合计
	        		bill.setTotal_amt(result.getString("allfee"));
	        		bill.setUpdate_time("");
	        		bills.add(bill);
	        	}
	        }
			count++;
		}
		return bills;
	}
	
	private List<Call> crawlerCallInfo(String cookie)
	{
		List<Call> calls = new ArrayList<>();
		String postUrl = "http://iservice.10010.com/e3/static/query/callDetail?_="+new Date().getTime();
		int pageSize = 100;
		
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        */
/* 设置为当前时间 *//*

        cal.setTime(nowdate);
		for(int i=0; i<7; i++)
		{
			int pageNo = 1;
			//初次默认是2，查询为第一次后会根据实际页数进行重新复制
			int pageTotal = 2;
			*/
/* 当前日期月份-1 *//*

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
			
	        while(pageNo <= pageTotal)
	        {
	        	String param = "beginDate="+startMonth+"&endDate="+endMonth+"&pageNo="+pageNo+"&pageSize="+pageSize;
	        	String retData = CrawlerHttpUtils.crawlerRequest(postUrl,
	        			cookie, 
	        			param);
	        	if(StringUtils.isNotBlank(retData))
	        	{
	        		JSONObject json = JSON.parseObject(retData);
	        		JSONObject page = json.getJSONObject("pageMap");
	        		if(page != null)
	        		{
	        			JSONArray array = page.getJSONArray("result");
	        			if(array != null && array.size() > 0)
	        			{
	        				calls.addAll(crawlerCallInfo(array));
	        			}
	        			try
	        			{
	        				pageTotal = page.getIntValue("totalPages");
	        				System.out.println("pageTotal:::::"+pageTotal);
	        			}
	        			catch(Exception e)
	        			{
//	        			e.printStackTrace();
	        				pageTotal = 1;
	        			}
	        		}
	        	}
	        	else
	        	{
	        		pageTotal = 1;
	        	}
	        	pageNo++;
	        }
	        
		}
		return calls;
	}
	
	private List<Call> crawlerCallInfo(JSONArray jsonArray)
	{
		List<Call> calls = new ArrayList<>();
		int size = jsonArray.size();
		System.out.println("通话记录数量:::::"+size);
		for(int j=0; j<size; j++)
		{
			JSONObject obj = jsonArray.getJSONObject(j);
			Call call = new Call();
			call.setCall_type(obj.getString("landtype"));
			call.setCell_phone(obj.getString(""));
			call.setInit_type(obj.getString("calltypeName"));
			call.setOther_cell_phone(obj.getString("othernum"));
			call.setPlace(obj.getString("homeareaName"));
			call.setStart_time(obj.getString("calldate")+" "+obj.getString("calltime"));
			call.setSubtotal(obj.getString("totalfee"));
			call.setUse_time(obj.getString("calllonghour"));
			call.setUpdate_time("");
			calls.add(call);
		}
		return calls;
	}
	
	private List<Sms> crawlerSmsInfo(String cookie)
	{
		List<Sms> smses = new ArrayList<>();
		String postUrl = "http://iservice.10010.com/e3/static/query/sms?_="+new Date().getTime();
		int pageSize = 20;
		
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        */
/* 设置为当前时间 *//*

        cal.setTime(nowdate);
        int i=0;
//		for(int i=0; i<6; i++)
//		{
			int pageNo = 1;
			//初次默认是2，查询为第一次后会根据实际页数进行重新复制
			int pageTotal = 2;
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
	        System.out.println("startMonth::::::::::" + startMonth);
	        System.out.println("endMonth::::::::::" + endMonth);
			
//	        while(pageNo <= pageTotal)
//	        {
	        	String param = "begindate="+startMonth+"&enddate="+endMonth+"&pageNo="+pageNo+"&pageSize="+pageSize;
		    	String retData = CrawlerHttpUtils.crawlerRequest(postUrl,
		    			cookie, 
		    			param);
		    	if(StringUtils.isNotBlank(retData))
		    	{
		    		JSONObject json = JSON.parseObject(retData);
		    		*/
/*JSONObject page = json.getJSONObject("pageMap");
		    		if(page != null)
		    		{
		    			JSONArray array = page.getJSONArray("result");
		    			if(array != null && array.size() > 0)
		    			{	
		    				int size = array.size();
		    				System.out.println("短信记录数量:::::"+size);
		    				for(int j=0; j<size; j++)
		    				{
		    					JSONObject obj = array.getJSONObject(j);
		    					Sms sms = new Sms();
		    					sms.setCell_phone("");
		    					sms.setInit_type("");
		    					sms.setOther_cell_phone(obj.getString("othernum"));
		    					sms.setPlace(obj.getString("homearea"));
		    					sms.setStart_time(obj.getString("smsdate")+" "+obj.getString("smstime"));
		    					sms.setSubtotal(obj.getString("fee"));
		    					sms.setUpdate_time("");
		    					smses.add(sms);
		    				}
		    			}
		    			try
		        		{
		        			pageTotal = page.getIntValue("totalPages");
		        			System.out.println("pageTotal:::::"+pageTotal);
		        		}
		        		catch(Exception e)
		        		{
//		        			e.printStackTrace();
		        			pageTotal = 1;
		        		}
		    		}*//*

		    	}
		    	else
	        	{
	        		pageTotal = 1;
	        	}
	        	pageNo++;
//	        }
//		}
		return smses;
	}
	
	
	private List<Net> crawlerNetInfo(String cookie)
	{
		List<Net> nets = new ArrayList<>();
		String postUrl = "http://iservice.10010.com/e3/static/query/callFlow?_="+new Date().getTime();
		
		*/
/*String postUrl = "http://iservice.10010.com/e3/static/query/callFlow?_="+new Date().getTime()
				+ "&accessURL=http://iservice.10010.com/e4/query/basic/call_flow_iframe1.html?menuCode=000100030004"
				+ "&menuid=000100030004";*//*

		
		int pageSize = 100;
		
		Date nowdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        */
/* 设置为当前时间 *//*

        cal.setTime(nowdate);
		for(int i=0; i<6; i++)
		{
			int pageNo = 1;
			//初次默认是2，查询为第一次后会根据实际页数进行重新复制
			int pageTotal = 2;
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
	        System.out.println("startMonth::::::::::" + startMonth);
	        System.out.println("endMonth::::::::::" + endMonth);
			
	        while(pageNo <= pageTotal)
	        {
	        	String param = "beginDate="+startMonth+"&endDate="+endMonth+"&pageNo="+pageNo+"&pageSize="+pageSize;
		    	String retData = CrawlerHttpUtils.crawlerRequest(postUrl,
		    			cookie, 
		    			param);
		    	if(StringUtils.isNotBlank(retData))
		    	{
		    		JSONObject json = JSON.parseObject(retData);
		    		if(json != null)
		    		{
		    			JSONArray array = json.getJSONArray("pagelist");
		    			if(array != null && array.size() > 0)
		    			{	
		    				int size = array.size();
		    				System.out.println("上网记录数量:::::"+size);
		    				for(int j=0; j<size; j++)
		    				{
		    					JSONObject obj = array.getJSONObject(j);
		    					Net net = new Net();
		    					net.setCell_phone("");
		    					net.setNet_type(obj.getString("nettyepformat"));
		    					net.setPlace(obj.getString("homearea"));
		    					net.setStart_time(obj.getString("begindateformat")+" "+obj.getString("begintimeformat"));
		    					net.setSubflow(obj.getString("fee"));
		    					net.setSubtotal(obj.getString("pertotalsm"));
		    					net.setUse_time(obj.getString("longhour"));
		    					net.setUpdate_time("");
		    					nets.add(net);
		    				}
		    			}
		    			try
		        		{
		        			pageTotal = json.getIntValue("totalpage");
		        			System.out.println("pageTotal:::::"+pageTotal);
		        		}
		        		catch(Exception e)
		        		{
//		        			e.printStackTrace();
		        			pageTotal = 1;
		        		}
		    		}
		    	}
		    	else
	        	{
	        		pageTotal = 1;
	        	}
	        	pageNo++;
	        }
		}
		return nets;
	}
	
	public int crawler(String taskHashcode)
	{
		WebDriver driver = null;
		try
		{
			driver = CrawlerUtils.initWebDriver();
			this.login(driver, null, true);
			this.crawlerInfo(driver, null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			CrawlerUtils.destoryWebDriver(driver, taskHashcode);
		}
		return 0;
	}
	
	*/
/*private int getPageNo(JSONObject json, String entityName)
	{
		JSONObject pageObj = json.getJSONObject(entityName+ChinaunicomConstant.PAGESOURCE);
		return pageObj.getIntValue(entityName+ChinaunicomConstant.PAGENO);
	}
	
	*//*
*/
/**
	 * 根据request返回的json对象和实体标识生成爬取数据
	 * @param retJson
	 * @param entityName
	 * @return
	 *//*
*/
/*
	private Object getData(JSONObject retJson, String entityName)
	{
		System.out.println(JSON.toJSONString(retJson));
		Object targetData = null;
		String dataSouce = PropertiesUtil.getInstance().
				getParamValue(entityName+ChinaunicomConstant.DATASOURCE);
		if(StringUtils.isBlank(dataSouce))
		{
			return null;
		}
		String[] arr = dataSouce.split(",");
		for(int i=0, size=arr.length; i<size; i++)
		{
			if(targetData == null)
			{
				String souceType = PropertiesUtil.getInstance().
						getParamValue(entityName+ChinaunicomConstant.DATATYPE);
				if("array".equalsIgnoreCase(souceType))
				{
					targetData = retJson.getJSONArray(arr[i]);
				}
				else
				{
					targetData = retJson.getJSONObject(arr[i]);
				}
			}
			else
			{
				this.assembleData(retJson, targetData, entityName, arr[i]);
			}
		}
		
		
		if(targetData instanceof JSONArray)
		{
//			jsonarray
//			return this.getEntityObjs(entityName, targetData, sourceName);
		}
		else
		{
//			json
		}
		
		return targetData;
	}
	
	*//*
*/
/**
	 * 组装数据
	 * @param retJson
	 * @param targetDataObj
	 * @param entityName
	 * @param sourceName
	 * @return
	 * @throws Exception 
	 *//*
*/
/*
	private Object assembleData(JSONObject retJson, Object targetDataObj, 
			String entityName, String sourceName)
	{
		String attribute = PropertiesUtil.getInstance().
				getParamValue(entityName+ChinaunicomConstant.DATAATTRSOURCE+sourceName);
		if(StringUtils.isBlank(attribute))
		{
			return targetDataObj;
		}
		String sourceType = PropertiesUtil.getInstance().
				getParamValue(entityName+ChinaunicomConstant.DATASOURCETYPE+sourceName);
		String sourceIndex = PropertiesUtil.getInstance().
				getParamValue(entityName+
						ChinaunicomConstant.DATASOURCEINDEX+sourceName);
		if(targetDataObj instanceof JSONArray)
		{
//			jsonarray
			JSONArray array = (JSONArray)targetDataObj;
			for(int i=0, targeSize=array.size(); i<targeSize; i++)
			{
				JSONObject targetObj = array.getJSONObject(i);
				if("array".equalsIgnoreCase(sourceType))
				{
					JSONArray sourceObj = retJson.getJSONArray(sourceName);
					if("all".equalsIgnoreCase(sourceIndex))
					{
						for(int j=0, size=sourceObj.size(); j>size; j++)
						{
							this.assembleAttribute(sourceObj.getJSONObject(j),
									targetObj, attribute);
						}
					}
					else
					{
						this.assembleAttribute(array.getJSONObject(Integer.parseInt(sourceIndex)),
								targetObj, attribute);
					}
				}
				else
				{
					this.assembleAttribute(retJson.getJSONObject(sourceName),
							targetObj, attribute);
				}
			}
			return array;
//			组装数据
			try {
				return this.getEntityObjs(entityName, array, sourceName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else
		{
//			json
			JSONObject json = (JSONObject)targetDataObj;
			return json;
			try {
				return this.getEntityObj(entityName, json, sourceName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	
	*//*
*/
/**
	 * 组装属性
	 * @param sourceObj
	 * @param targetObj
	 * @param attribute
	 *//*
*/
/*
	private void assembleAttribute(JSONObject sourceObj, JSONObject targetObj, String attribute)
	{
		String[] attributeArr = attribute.split(",");
		for(String attr : attributeArr)
		{
			targetObj.put(attr, sourceObj.getString(attr));
		}
	}
	
	*//*
*/
/**
	 * 数据结构转换 返回jsonarray
	 * @param entityName
	 * @param array
	 * @param resourceName
	 * @return
	 * @throws Exception
	 *//*
*/
/*
	private List<Object> getEntityObjs(String entityName, JSONArray array, 
			String sourceName) throws Exception
	{
		List<Object> list = new ArrayList<>();
		for(int i=0, size=array.size(); i<size; i++)
		{
			JSONObject json = array.getJSONObject(i);
			Object obj = this.getEntityObj(entityName, json, sourceName);
			if(obj == null)
			{
				list.add(obj);
			}
		}
		return list;
	}
	
	*//*
*/
/**
	 * 数据结构转换 返回配置对象
	 * @param entityName
	 * @param json
	 * @param sourceName
	 * @return
	 * @throws Exception
	 *//*
*/
/*
	private Object getEntityObj(String entityName, JSONObject json,
			String sourceName) throws Exception
	{
		String className = PropertiesUtil.getInstance().getParamValue(entityName+".class");
		String attr = PropertiesUtil.getInstance().
				getParamValue(entityName+ChinaunicomConstant.DATAATTR+sourceName);
		
		String attrsource = PropertiesUtil.getInstance().
				getParamValue(entityName+ChinaunicomConstant.DATASOURCE+sourceName);
		Class<?> cls = Class.forName(className);
		return this.getEntryObject(cls, json, attr, attrsource);
	}
	
	*//*
*/
/**
	 * 组装对象属性
	 * @param cls
	 * @param json
	 * @param attr
	 * @param attrsource
	 * @return
	 * @throws Exception
	 *//*
*/
/*
	private Object getEntryObject(Class<?> cls, JSONObject json,
			String attr, String attrsource) throws Exception
	{
		Object obj = null;
		if(StringUtils.isNotBlank(attr) && StringUtils.isNotBlank(attrsource))
		{
			String[] attrArray = attr.split(",");
			String[] srouceArray = attrsource.split(",");
			int attrSize = attrArray.length;
			int sourceSize = srouceArray.length;
			if(attrSize == sourceSize)
			{
				obj = cls.newInstance();
				for(int i=0; i<attrSize; i++)
				{
					cls.getField(attrArray[i]).set(obj, 
							this.getValue(json, srouceArray[i]));
				}
			}
		}
		return obj;
	}
	
	*//*
*/
/**
	 * 组装对象属性的值
	 * @param json
	 * @param key
	 * @return
	 *//*
*/
/*
	private String getValue(JSONObject json, String key)
	{
		StringBuffer val = new StringBuffer();
		if(key.contains("+"))
		{
			String[] arr = key.split("+");
			for(String s : arr)
			{
				if(json.containsKey(s))
				{
					val.append(json.getString(s));
				}
				else
				{
					val.append(s);
				}
			}
		}
		else
		{
			val.append(json.getString(key));
		}
		return val.toString();
	}

	*//*
*/
/**
	 * 根据request请求获取的json、datasource、datatype获取对象的数据集合
	 * 其中datasource支持多级嵌套，其中用">>"表示深度
	 * @param retJson
	 * @param dataSource
	 * @param dataType
	 * @return
	 *//*
*/
/*
	private Object getData(JSONObject retJson, String dataSource, String dataType)
	{
		Object dataObj = retJson;
		if(StringUtils.isBlank(dataSource))
		{
			return dataObj;
		}
		String[] arr = dataSource.split(">>");
		for(int i=0, size=arr.length; i<size; i++)
		{
			try
			{
				dataObj = this.getDataObj(dataObj, dataSource, dataType);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		return dataObj;
	}
	
	*//*
*/
/**
	 * 根据parentJSON、datasource、datatype,寻找其子对象 JSON/JSONARRAY
	 * 其中datasouce可能是多级嵌套，其中用">>"表示深度
	 * @param parentObj
	 * @param dataSource
	 * @param dataType
	 * @return
	 *//*
*/
/*
	private Object getDataObj(Object parentObj, String dataSource, String dataType) throws Exception
	{
		Object retObj = null;
		if("array".equalsIgnoreCase(dataType))
		{
//			jsonarray
			if(parentObj instanceof JSONArray)
			{
				retObj = new JSONArray();
				JSONArray tempArr = (JSONArray)parentObj;
				for(int i=0, size=tempArr.size(); i<size; i++)
				{
					((JSONArray)retObj).add(tempArr.getJSONObject(i).getJSONArray(dataSource));
				}
			}
			else
			{
				retObj = ((JSONObject)parentObj).getJSONArray(dataSource);
						
			}
		}
		else
		{
//			json
			retObj = ((JSONObject)parentObj).getJSONObject(dataSource);
		}
		parentObj = null;
		return retObj;
	}*//*

	
	public static void main(String[] args)
	{
		CrawlerServiceImpl obj = new CrawlerServiceImpl();
	}
}*/
