package util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestTess4j {

	public static void main(String[] args) { 


		String str = "金额￥     322.232";
		Pattern pattern = Pattern.compile("[^\\d*\\.?\\d*]");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find())
        {
        	str = str.replaceAll(matcher.group(), "");
        }
        System.out.println(str);


	
        File root = new File("D:/image/");  
        ITesseract instance = new Tesseract();  
        instance.setDatapath("D:\\development\\Tess4J\\tessdata");
        try {  
            File[] files = root.listFiles();
            int total = files.length;
            int right = 0;
            for (File file : files) {  
                String result = instance.doOCR(file);  
                String fileName = file.toString().substring(file.toString().lastIndexOf("\\")+1);  
                System.out.println("图片名：" + fileName +" 识别结果："+result);  
                if(result.contains("8"))
                {
                	right++;
                }
            }  
            System.out.println("right:::::"+right);
            System.out.println("total:::::"+total);
            System.out.println("rate:::::"+((double)right/(double)total));
        } catch (TesseractException e) {  
            System.err.println(e.getMessage());  
        }  
    }  
	
}
