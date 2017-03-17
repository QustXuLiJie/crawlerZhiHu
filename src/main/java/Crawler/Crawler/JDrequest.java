package Crawler.Crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JDrequest {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		URL url = null;
		try{
			url=new URL("");
			StringBuffer bankXmlBuffer=new StringBuffer();
			//创建URL连接，提交到数据，获取返回结果
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("User-Agent","directclient");
			PrintWriter out=new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
			out.println("");
			out.close();
			BufferedReader in=new BufferedReader(new InputStreamReader(connection
			.getInputStream(),"GBK"));
			String inputLine;
			while((inputLine=in.readLine())!=null){
				bankXmlBuffer.append(inputLine);
			}
			in.close();
			inputLine=bankXmlBuffer.toString();
		}
		catch(Exception e)
		{
			System.out.println("发送GET请求出现异常！"+e);
			e.printStackTrace();
		}
		finally
		{
		}
	}

}
