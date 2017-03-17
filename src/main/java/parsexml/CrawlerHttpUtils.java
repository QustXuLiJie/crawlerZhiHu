package parsexml;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CrawlerHttpUtils {
	
	public static String RESPONSECODE = "code";
	public static String RESPONSEDATA = "data";
	public static int TRYREQUESTTOTAL = 3;
	
	/**
	 * 模拟post请求，返回responsecode和responsedata
	 * @param postUrl
	 * @param cookie
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> doPost(String postUrl, String cookie, String param)
	{
		Map<String, String> retMap = new HashMap<>();
		int responseCode = 500;
		String responseData = "";
		
		try
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
//			connection.setRequestProperty("Cookie", "mallcity=11|110; iservice=PBV3YTjBMpn7RlRlXD1LMn9r2Tg8vzDQnZF4JQQ5LRTGhpwGnTlg!1963288011!NONE; route=ed50ca68bf40ebda0ed2f36ba779410e; SHOP_PROV_CITY=; _dc_gtm_UA-27681312-1=1; e3=1BK7YT2JTS51Y6J0J0TB1XLG7M9l7H40dGCPQTgBhHzNyXhv5LGY!-1234215335; piw=%7B%22login_name%22%3A%22185****2684%22%2C%22nickName%22%3A%22%E8%AE%B8%E7%AB%8B%E6%9D%B0%22%2C%22rme%22%3A%7B%22ac%22%3A%22%22%2C%22at%22%3A%22%22%2C%22pt%22%3A%2201%22%2C%22u%22%3A%2218562672684%22%7D%2C%22verifyState%22%3A%22%22%7D; JUT=SMyxuKtDxD8PDQcDu+gtpS0Utup3gqKWETHkQ1sV+fjCP1kNDSsmUN6+vTmIIKZCNvWBQWBX/kqn7K7+WE/L4/a+q3hJI83jVo3dgk5BjNhZO5VRowWAPwqzXLtYed390Ys9FkwdD8PslrO8XfopZQeAUIj9I3XRscLQp8jJQ/l5tvCrBCUOeDqZyWgzz4sshz51fdKgaGKij1t6NpJ6rCLHinsKc2q+uc1hPhQkfJA/sI6gFgTTZYlhXbmjFT2vq088qWaWS/Zu4N2OuT6oszUfkGVGCRIWMS0MiYQkg8MeEWhiHWHqSmmFXZ6fBKxJV+uEacvR+/my16vT7HPksE0HvhNQTUI0WlMJAtwIpapn62Y+0EyvLhNBjNZBhfIBtxpvbjlJv6MqeO/dVYeDfPWdK5YvHePrGCjjv74IlL/CTXCTfeb5ebblrym8108MRplxFs5d3y0E82+8N8KtGJkCEot+7KHSdpW1g12frYUqzFMsO1NoaCS1YvXSsOE6vCTJMe3NvQ2idoO7Ft3NvXWr9BBJnCo3A4DD/pbSbFA=AKcuZIQBmx03Q4dABBVUQg==; _uop_id=af1c3ad794ddedc9c807693000e3616b; _n3fa_cid=429d32318ac34084ba66eb97b4a4319b; _n3fa_ext=ft=1481899723; _n3fa_lvt_a9e72dfe4a54a20c3d6e671b3bad01d9=1481899723; _n3fa_lpvt_a9e72dfe4a54a20c3d6e671b3bad01d9=1481899723; Hm_lvt_9208c8c641bfb0560ce7884c36938d9d=1481891986,1481892697,1481892722,1481892843; Hm_lpvt_9208c8c641bfb0560ce7884c36938d9d=1481899735; _ga=GA1.3.283735564.1481892033; WT_FPC=id=2636818929a033dca941481891985503:lv=1481899822932:ss=1481899582695");
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
			
			responseCode = connection.getResponseCode();
			retMap.put(RESPONSECODE, responseCode+"");
//			获取返回code
			if(responseCode != 200)
			{
				return retMap;
			}
			
//			连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder sb = new StringBuilder(); // 用来存储响应数据

			String line = "";
			// 循环读取流,若不到结尾处
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
			bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
			connection.disconnect(); // 销毁连接
			responseData = sb.toString();
			retMap.put(RESPONSEDATA, responseData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			retMap.put(RESPONSECODE, "500");
		}
		return retMap;
	}
	
	/**
	 * 
	 * @param postUrl
	 * @param cookie
	 * @param param
	 * @return
	 */
	public static String crawlerRequest(String postUrl, String cookie, String param)
	{
		int trycount = 0;
		Map<String, String> retMap = null;
		System.out.println("请求url:::::"+postUrl);
		System.out.println("请求参数:::::"+param);
		while(trycount < TRYREQUESTTOTAL)
		{
			retMap = doPost(postUrl, cookie, param);
			if("200".equalsIgnoreCase(retMap.get(RESPONSECODE)))
			{
				System.out.println("返回结果:::::"+retMap.get(RESPONSEDATA));
				return retMap.get(RESPONSEDATA);
			}
			else
			{
				retMap = doPost(postUrl, cookie, param);
			}
			trycount++;
		}
		System.out.println("返回结果为空,调用接口失败");
		return null;
	}
	
	public static void setAttribute(Object obj, Class<?> cls, 
			String attribute, String attributeVal) throws Exception
	{
		Field f = cls.getDeclaredField(attribute);
		f.set(obj, attributeVal);
	}
}
