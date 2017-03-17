package parsexml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ParseXml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String json = "{\"errorMessage\":{\"respCode\":\"2114030170\",\"respDesc\":\"?????????????????2114030170?\"},\"querynowdate\":\"2016?12?21?\",\"endDate\":\"2016-12-21\",\"isSuccess\":false,\"beginDate\":\"2016-12-01\",\"userInfo\":{\"packageName\":\"??2G/3G??-???36???\",\"status\":\"??\",\"provincecode\":\"031\",\"usernumber\":\"13120523886\",\"expireTime\":\"1482315955577\",\"nettype\":\"02\",\"areaCode\":\"\",\"certnum\":\"432522199107297034\",\"opendate\":\"20140903153215\",\"paytype\":\"1\",\"productId\":\"13120523886\",\"custName\":\"??\",\"citycode\":\"310\",\"brand\":\"9\",\"productType\":\"01\",\"packageID\":\"99117938\",\"customid\":\"4314090324408188\",\"custlvl\":\"????\",\"currentID\":\"13120523886\",\"loginType\":\"01\",\"nickName\":\"??\",\"subscrbstat\":\"??\",\"laststatdate\":\"20160901044758\",\"brand_name\":\"?\",\"is_wo\":\"2\",\"is_20\":false,\"is_36\":false,\"certtype\":\"11\",\"loginCustid\":\"4314090324408188\",\"verifyState\":\"\",\"lastLoginTime\":\"2016-12-21 16:25:55\",\"defaultFlag\":\"00\",\"isNUser\":\"0000\",\"mapExtraParam_rls\":\"03\",\"custsex\":\"1\",\"certaddr\":\"??????????????????\"}}";
		JSONObject retJson = JSON.parseObject(json);*/
		ParseXml obj = new ParseXml();
		String json = "{\"querynowdate\":\"2016年12月19日\",\"mmsCount\":14,\"totalfee\":\"0.00\",\"endDate\":\"2016-12-19\",\"pageMap\":{\"result\":[{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-15\",\"smstime\":\"11:21:25\",\"othernum\":\"13573810161\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-08\",\"smstime\":\"15:59:02\",\"othernum\":\"13573810161\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.10\",\"smsdate\":\"2016-12-08\",\"smstime\":\"11:02:00\",\"othernum\":\"15265233680\",\"smstype\":\"2\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-08\",\"smstime\":\"11:01:43\",\"othernum\":\"15265233680\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.10\",\"smsdate\":\"2016-12-06\",\"smstime\":\"19:44:10\",\"othernum\":\"15020925148\",\"smstype\":\"2\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-06\",\"smstime\":\"19:43:21\",\"othernum\":\"15020925148\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-06\",\"smstime\":\"19:43:21\",\"othernum\":\"15020925148\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.10\",\"smsdate\":\"2016-12-06\",\"smstime\":\"19:15:25\",\"othernum\":\"15020925148\",\"smstype\":\"2\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-06\",\"smstime\":\"19:14:35\",\"othernum\":\"15020925148\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-06\",\"smstime\":\"14:06:30\",\"othernum\":\"18963198086\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-06\",\"smstime\":\"11:09:34\",\"othernum\":\"18963198086\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.00\",\"smsdate\":\"2016-12-06\",\"smstime\":\"11:09:30\",\"othernum\":\"18963198086\",\"smstype\":\"1\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.10\",\"smsdate\":\"2016-12-05\",\"smstime\":\"14:01:45\",\"othernum\":\"18028022606\",\"smstype\":\"2\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"},{\"amount\":\"0.00\",\"fee\":\"0.10\",\"smsdate\":\"2016-12-05\",\"smstime\":\"13:59:00\",\"othernum\":\"18028022606\",\"smstype\":\"2\",\"otherarea\":\"\",\"homearea\":\"0532\",\"businesstype\":\"01\",\"deratefee\":\"\"}],\"totalCount\":14,\"pageNo\":1,\"pages\":[{\"pageNo\":1,\"curr\":true}],\"pageSize\":20,\"totalPages\":1},\"isSuccess\":true,\"beginDate\":\"2016-12-01\",\"userInfo\":{\"packageName\":\"4G校园套餐-26元B套餐\",\"status\":\"开通\",\"provincecode\":\"017\",\"usernumber\":\"18562672684\",\"expireTime\":\"1482121250483\",\"nettype\":\"11\",\"areaCode\":\"\",\"certnum\":\"3713****3510\",\"opendate\":\"20150520101400\",\"paytype\":\"2\",\"productId\":\"18562672684\",\"custName\":\"许立杰\",\"citycode\":\"166\",\"brand\":\"4G00\",\"productType\":\"01\",\"packageID\":\"89013013\",\"customid\":\"7915060457741083\",\"custlvl\":\"二星用户\",\"currentID\":\"18562672684\",\"loginType\":\"01\",\"nickName\":\"许立杰\",\"subscrbstat\":\"开通\",\"laststatdate\":\"\",\"brand_name\":\"沃4G后付费\",\"is_wo\":\"2\",\"is_20\":false,\"is_36\":false,\"certtype\":\"02\",\"loginCustid\":\"7915060457741083\",\"verifyState\":\"\",\"lastLoginTime\":\"2016-12-19 10:20:50\",\"defaultFlag\":\"00\",\"isINUser\":\"0000\",\"mapExtraParam_rls\":\"03\",\"custsex\":\"1\",\"certaddr\":\"山东省莒南县大店镇后会子坡村64号\"}}";
		JSONObject retJson = JSON.parseObject(json);
		System.out.println(JSON.toJSONString(retJson));
		try {
			Map<String, CrawlerData> map = ParseXml.parseXml();
			CrawlerData template = map.get(ChinaunicomConstant.SMS);
//			System.out.println(obj.getPageTotal(retJson, template.getPage()));
//			System.out.println(obj.parseSroucedata(retJson, template));
			System.out.println(obj.generateTargetdata(obj.parseSroucedata(retJson, template),
					template));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取页数
	 * 其特点为必须从事于某个jsonobject
	 * @param retJson
	 * @param page
	 * @return
	 */
	public int getPageTotal(JSONObject retJson, CrawlerPage page)
	{
//		JSONObject jsonObj = null;
		/*if("mutiple".equalsIgnoreCase(page.getType()))
		{
//			jsonarray
			JSONArray jsonArr = retJson.getJSONArray(page.getPath());
			jsonObj = jsonArr.getJSONObject(page.getIndex());
		}
		else
		{
//			json
			jsonObj = retJson.getJSONObject(page.getPath());
		}*/
		JSONObject jsonObj = (JSONObject) this.getDataByPath(retJson, page.getPathes());
		return (jsonObj == null || StringUtils.isBlank(jsonObj.getString(page.getAttribute()))) ? 0 : 
			Integer.parseInt(jsonObj.getString(page.getAttribute()));
	}
	
	
	public Map<String, Object> parseSroucedata(JSONObject retJson, CrawlerData template)
	{
		Map<String, CrawlerDatasource> sourceMap = template.getDataMap();
		Map<String, Object> dataMap = new HashMap<>();
		for(Entry<String, CrawlerDatasource> entry : sourceMap.entrySet())
		{
			CrawlerDatasource sourceObj = (CrawlerDatasource)entry.getValue();
			dataMap.put(sourceObj.getId(), this.getDataByPath(retJson, sourceObj.getPathes()));
		}
		/*Iterator iter = sourceMap.entrySet().iterator();
		Map<String, Object> dataMap = new HashMap<>();
		while(iter.hasNext())
		{
			Entry entry = (Entry)iter.next();
			CrawlerDatasource sourceObj = (CrawlerDatasource)entry.getValue();
			dataMap.put(sourceObj.getId(), this.getDataByPath(retJson, sourceObj.getPathes()));
			if("mutiple".equalsIgnoreCase(sourceObj.getType()))
			{
				JSONArray jsonArr = retJson.getJSONArray(sourceObj.getPath());
				if(-1 == sourceObj.getIndex())
				{
					dataMap.put(sourceObj.getId(), jsonArr);
				}
				else
				{
					dataMap.put(sourceObj.getId(), jsonArr.getJSONObject(sourceObj.getIndex()));
				}
			}
			else
			{
				dataMap.put(sourceObj.getId(), retJson.getJSONObject(sourceObj.getPath()));
			}
		}*/
		return dataMap;
	}
	
	
	//根据数据拼装新的返回数据(可用json、class反射)
	public Object generateTargetdata(Map<String, Object> dataMap, CrawlerData template)
	{
		if("mutiple".equalsIgnoreCase(template.getType()))
		{
//			jsonarray
			JSONArray jsonArray = null;
			for(Entry<String, Object> entry : dataMap.entrySet())
			{
				String dataId = entry.getKey();
				List<CrawlerAttribute> attributes = template.getAttributeMap().get(dataId);
				if(jsonArray == null)
				{
					jsonArray = new JSONArray();
					JSONArray temp = (JSONArray)entry.getValue();
					for(int i=0, size=temp.size(); i<size; i++)
					{
						JSONObject jsonObj = new JSONObject();
						jsonArray.add(jsonObj);
						this.assembleAttribute(jsonObj, temp.getJSONObject(i), attributes);
					}
				}
				else
				{
					for(int i=0, size=jsonArray.size(); i<size; i++)
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						Object obj = entry.getValue();
						if(obj instanceof JSONArray)
						{
							JSONArray temp = (JSONArray)obj;
							for(int j=0, tempsize=temp.size(); j<tempsize; j++)
							{
								assembleAttribute(jsonObj, temp.getJSONObject(j), attributes);
							}
						}
						else
						{
							assembleAttribute(jsonObj, (JSONObject)obj, attributes);
						}
					}
				}
			}
			return jsonArray;
		}
		else
		{
//			jsonobject
			JSONObject jsonObject = null;
			for(Entry<String, Object> entry : dataMap.entrySet())
			{
				String dataId = entry.getKey();
				List<CrawlerAttribute> attributes = template.getAttributeMap().get(dataId);
				if(jsonObject == null)
				{
					jsonObject = (JSONObject)entry.getValue();
				}
				else
				{
					assembleAttribute(jsonObject, (JSONObject)entry.getValue(), attributes);
				}
			}
			return jsonObject;
		}
	}
	
	public void assembleAttribute(JSONObject targetObj, JSONObject sourceObj, 
			List<CrawlerAttribute> attributes)
	{
		for(CrawlerAttribute attribute : attributes)
		{
			targetObj.put(attribute.getTargetAttribute(), 
					getValueByAttribute(sourceObj, attribute.getSourceAttribute()));
		}
	}
	
	public static Map<String, CrawlerData> parseXml() throws Exception
	{
		SAXReader reader = new SAXReader();  
//        Document document = reader.read(new File("D:\\Workspace\\github\\crawler\\src\\main\\java\\crawlerresult.xml"));
		
		ClassLoader classLoader = ParseXml.class.getClassLoader();
//		System.out.println(classLoader.getResource("crawlerresult.xml").toString());
	    InputStream inputStream = classLoader.getResourceAsStream("crawlerresult.xml");
		Document document = reader.read(inputStream);
//		Document document = reader.read(new File("crawlerresult.xml"));
		Element rootEle = document.getRootElement();
        List<Element> infoEles = rootEle.element("infos").elements("info");
        Map<String, CrawlerData> dataTemplates = new HashMap<>();
        CrawlerData obj = null;
        for(Element infoEle : infoEles)
        {
        	obj = new CrawlerData();
        	
//        	拼装基本数据
        	obj.setName(infoEle.attributeValue("name"));
        	obj.setClassName(infoEle.attributeValue("class"));
        	obj.setType(infoEle.attributeValue("type"));
        	dataTemplates.put(obj.getName(), obj);
//        	拼装page
        	Element pageEle = infoEle.element("page");
        	CrawlerPage page = new CrawlerPage();
        	page.setAttribute(pageEle.attributeValue("attribute"));
        	page.setPathes(generatePathes(pageEle));
        	/*page.setIndex(StringUtils.isBlank(pageEle.attributeValue("index")) ? 0 : 
        		Integer.parseInt(pageEle.attributeValue("index")));
        	page.setPath(pageEle.attributeValue("path"));
        	page.setType(pageEle.attributeValue("type"));*/
        	obj.setPage(page);
//        	拼装datasouce
        	Map<String, List<CrawlerAttribute>> attributeMap = new HashMap<>();
        	obj.setAttributeMap(attributeMap);
        	List<Element> attrEles = infoEle.element("attributes").elements("attribute");
        	for(Element attrEle : attrEles)
        	{
        		CrawlerAttribute attrObj = new CrawlerAttribute();
        		attrObj.setDataId(attrEle.attributeValue("dataid"));
        		attrObj.setSourceAttribute(attrEle.attributeValue("source"));
        		attrObj.setTargetAttribute(attrEle.attributeValue("target"));
        		if(!attributeMap.containsKey(attrObj.getDataId()))
        		{
        			attributeMap.put(attrObj.getDataId(), new ArrayList<CrawlerAttribute>());
        		}
        		attributeMap.get(attrObj.getDataId()).add(attrObj);
        	}
//        	拼装attribute
        	Map<String, CrawlerDatasource> dataMap = new HashMap<>();
        	obj.setDataMap(dataMap);
        	List<Element> dataEles = infoEle.element("datas").elements("data");
        	for(Element dataEle : dataEles)
        	{
        		CrawlerDatasource dataObj = new CrawlerDatasource();
        		dataObj.setId(dataEle.attributeValue("id"));
        		/*dataObj.setIndex(StringUtils.isBlank(dataEle.attributeValue("index")) ? -1 : 
        			Integer.parseInt(dataEle.attributeValue("index")));
        		dataObj.setPath(dataEle.attributeValue("path"));
        		dataObj.setType(dataEle.attributeValue("type"));*/
        		dataObj.setPathes(generatePathes(dataEle));
        		dataMap.put(dataObj.getId(), dataObj);
        	}
        }
        System.out.println(JSON.toJSONString(dataTemplates));
		return dataTemplates;
	}

	public static List<CrawlerPath> generatePathes(Element ele)
	{
		List<CrawlerPath> pathes = new ArrayList<>();
		List<Element> pathEles = ele.elements("path");
		if(pathEles == null || pathEles.size() == 0)
		{
			return pathes;
		}
		CrawlerPath path = null;
		for(Element pathEle : pathEles)
		{
			path = new CrawlerPath();
			pathes.add(path);
			path.setType(pathEle.attributeValue("type"));
			path.setValue(pathEle.attributeValue("value"));
			path.setIndex(StringUtils.isBlank(pathEle.attributeValue("index")) ? -1 :
				Integer.parseInt(pathEle.attributeValue("index")));
		}
		return pathes;
	}
	
	/**
	 * 针对全路径，寻找其数据对象
	 * @param retJson
	 * @param pathes
	 * @return
	 */
	public Object getDataByPath(JSONObject retJson, List<CrawlerPath> pathes)
	{
		Object targetObj = null;
		if(pathes == null || pathes.size() == 0)
		{
			return retJson;
		}
		for(CrawlerPath path : pathes)
		{
			if(targetObj == null)
			{
				targetObj = getDataByPath(retJson, path);
			}
			else
			{
				targetObj = getDataByPath(targetObj, path);
			}
		}
		return targetObj;
	}
	
	/**
	 * 针对其单一路径，寻找其父亲数据对象的孩子数据对象
	 * @param targetObj
	 * @param path
	 * @return
	 */
	public Object getDataByPath(Object targetObj, CrawlerPath path)
	{
		if(targetObj instanceof JSONArray)
		{
//			jsonarray
			JSONArray retArray = new JSONArray();
			JSONArray tempArray = (JSONArray)targetObj;
			for(int i=0, size=tempArray.size(); i<size; i++)
			{
				JSONObject jsonObject = tempArray.getJSONObject(i);
				if("mutiple".equalsIgnoreCase(path.getType()))
				{
					if(-1 == path.getIndex())
					{
						retArray.addAll(jsonObject.getJSONArray(path.getValue()));
					}
					else
					{
						retArray.add(jsonObject.getJSONArray(path.getValue()).get(path.getIndex()));
					}
				}
				else
				{
					retArray.add(jsonObject.getJSONObject(path.getValue()));
				}
			}
			return retArray;
		}
		else
		{
//			jsonobj
			JSONObject jsonObject = (JSONObject)targetObj;
			if("mutiple".equalsIgnoreCase(path.getType()))
			{
				if(-1 == path.getIndex())
				{
					return jsonObject.getJSONArray(path.getValue());
				}
				else
				{
					return jsonObject.getJSONArray(path.getValue()).getJSONObject(path.getIndex());
				}
			}
			else
			{
				return jsonObject.getJSONObject(path.getValue()); 
			}
		}
		/*if("mutiple".equalsIgnoreCase(path.getType()))
		{
//			jsonarray
			if(-1 == path.getIndex())
			{
				return jsonObj.getJSONArray(path.getValue());
			}
			else
			{
				return jsonObj.getJSONArray(path.getValue()).getJSONObject(path.getIndex());
			}
		}
		else
		{
//			jsonobject
			return jsonObj.getJSONObject(path.getValue());
		}*/
	}
	
	/**
	 * 根据源属性获取jsonobject的value
	 * @param json
	 * @param sourceAttribute
	 * @return
	 */
	public static String getValueByAttribute(JSONObject json, String sourceAttribute)
	{
		StringBuffer buff = new StringBuffer();
		if(StringUtils.isNoneBlank(sourceAttribute))
		{
			System.out.println("****************"+sourceAttribute);
			String[] arr = sourceAttribute.split("\\+");
			for(String str : arr)
			{
				String val = json.getString(str);
				buff.append(StringUtils.isNotBlank(val) ? val : str);
			}
		}
		return buff.toString();
	}
}
