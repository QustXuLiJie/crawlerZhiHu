package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	/** 
     * 使用httpclient进行http通信 
     *  
     * Get请求 
     */  
    public static void httpclientGet(String urlStr) throws Exception  
    {  
  
        System.out.println(urlStr);  
  
        // 创建HttpClient对象  
        HttpClient client = HttpClients.createDefault();  
  
        // 创建GET请求（在构造器中传入URL字符串即可）  
        HttpGet get = new HttpGet(urlStr);  
  
        // 调用HttpClient对象的execute方法获得响应  
        HttpResponse response = client.execute(get);  
  
        // 调用HttpResponse对象的getEntity方法得到响应实体  
        HttpEntity httpEntity = response.getEntity();  
  
        // 使用EntityUtils工具类得到响应的字符串表示  
        String result = EntityUtils.toString(httpEntity, "utf-8");  
        System.out.println(result);  
    }  
    
    
    /** 
     * 使用httpclient进行http通信 
     *  
     * Post请求 
     */  
    public static void httpclientPost(String urlStr, List<NameValuePair> parameters) throws Exception  
    {  
  
        System.out.println(urlStr);  
  
        // 创建HttpClient对象  
        HttpClient client = HttpClients.createDefault();  
  
        // 创建POST请求  
        HttpPost post = new HttpPost(urlStr);  
  
        // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）--> parameters  
  
        // 向POST请求中添加消息实体  
        post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));  
  
        // 得到响应并转化成字符串  
        HttpResponse response = client.execute(post);  
        HttpEntity httpEntity = response.getEntity();  
        String result = EntityUtils.toString(httpEntity, "utf-8");  
        System.out.println(result);  
    }  
    
    /**
     * ajax json请求
     * @param url
     * @param json
     */
    public static void httpPostWithJSON(String url, String json)  
    {  
        // 创建默认的httpClient实例  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try  
        {  
            // 创建httppost  
            HttpPost httppost = new HttpPost(url);  
            httppost.addHeader("Content-type", "application/json; charset=utf-8");  
            System.out.println("executing request " + httppost.getURI());  
  
            // 向POST请求中添加消息实体  
            StringEntity se = new StringEntity(json, "UTF-8");  
            httppost.setEntity(se);  
            System.out.println("request parameters " + json);  
  
            // 执行post请求  
            CloseableHttpResponse response = httpclient.execute(httppost); 
            try  
            {  
                // 获取响应实体  
                HttpEntity entity = response.getEntity();  
                // 打印响应状态  
                System.out.println(response.getStatusLine());  
                if (entity != null)  
                {  
                    // 打印响应内容  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            }  
            finally  
            {  
                response.close();  
            }  
        }  
        catch (Exception e)  
        {  
            System.out.println("executing httpPostWithJSON error: " + e.getMessage());  
        }  
        finally  
        {  
            // 关闭连接,释放资源  
            try  
            {  
                httpclient.close();  
            }  
            catch (IOException e)  
            {  
                System.out.println("executing httpPostWithJSON error: " + e.getMessage());  
            }  
        }  
    }  
    
    public String doPost(String postUrl, String paramValue) throws Exception {
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
		connection.setRequestProperty("Content-Type", "application/json");
		// 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
		connection.connect();
		// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
		// "storeId=" + URLEncoder.encode("32", "utf-8");
		// //URLEncoder.encode()方法 为字符串进行编码

		// 将参数输出到连接
		// dataout.writeBytes(paramHeader+"="+URLEncoder.encode(paramValue,
		// "utf-8"));
		dataout.writeBytes(paramValue);
		// 输出完成后刷新并关闭流
		dataout.flush();
		dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
		// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
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
    
    /** 
     * 上传文件 
     */  
    public void upload() {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");  
  
            FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));  
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
  
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
  
            httppost.setEntity(reqEntity);  
  
            System.out.println("executing request " + httppost.getRequestLine());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                System.out.println("----------------------------------------");  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                if (resEntity != null) {  
                    System.out.println("Response content length: " + resEntity.getContentLength());  
                }  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	
}
