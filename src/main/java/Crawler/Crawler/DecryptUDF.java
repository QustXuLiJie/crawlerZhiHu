/*package com.mobanker.bd.hive.udf;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.bd.hive.tools.LogUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

*//**
 * Created by haofeiL on 2016/11/28.
 *//*
public class DecryptUDF extends UDF {
	private static String postUrl = "http://192.168.1.136:8086/decrypt/decrypt";//测试环境
//	private static String postUrl = "http://192.168.1.158:8080/decrypt/decrypt";//测试环境
//	private static String postUrl = "http://10.253.102.112:8080/decrypt/decrypt";// 生产环境

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public Text evaluate(String colName, Text colValue, Text businessId) {
		if (StringUtils.isBlank(colName))
			return null;
		if (colValue == null || StringUtils.isBlank(colValue.toString()))
			return null;
		if (businessId == null || StringUtils.isBlank(businessId.toString()))
			return null;
		Text result = new Text();
		JSONObject retJson = null;
		String outStr = null;
		try {
			// 调用http批量解密接口
			JSONObject json = new JSONObject();
			json.put("businessId", Long.parseLong(businessId.toString()));
			// List<Map> list = new ArrayList<>();
			// Map map1 = new HashMap<>();
			// map1.put(colName, colValue.toString());
			// list.add(map1);
			// json.put("list", list);
			json.put("data", colValue.toString());
			System.out.println(new Date());
			String ret = doPost(JSONObject.toJSONString(json));
			System.out.println(new Date());
			retJson = JSONObject.parseObject(String.valueOf(ret));
			// result.set(retJson.getJSONObject("data").getJSONArray("list").getJSONObject(0).get(colName).toString());
			result.set(retJson.get("data").toString());
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.info(e);
		}
		return result;
	}

	public String doPost(String paramValue) throws Exception {
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

	public static void main(String[] args) {
		DecryptUDF udf = new DecryptUDF();
		udf.evaluate("cell_phone", new Text("IGUfRghNeH/cXaSQBRvIxg=="), new Text("1473389148411"));
	}
}*/
