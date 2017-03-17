package image;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.jdesktop.swingx.util.OS;
import org.springframework.stereotype.Service;

import com.github.jaiimageio.plugins.tiff.TIFFImageWriteParam;

public class Image {

	private final static String LANG_OPTION = "-l"; // 英文字母小写l，并非数字1
	private final static String EOL = System.getProperty("line.separator");

	public static String recognizeText(File imageFile, String imageFormat) {
		StringBuffer strB = new StringBuffer();
		BufferedReader in = null;
		File outputFile = new File(imageFile.getParentFile(), "output");
		try {
			File tempImage = createImage(imageFile, imageFormat);
			List cmd = new ArrayList();
			if (OS.isWindowsXP()) {
				cmd.add("D://development//Tesseract-OCR" + "//tesseract");
			} else if (OS.isLinux()) {
				cmd.add("tesseract");
			} else {
				cmd.add("D://development//Tesseract-OCR" + "//tesseract");
			}
			cmd.add("");
			cmd.add(outputFile.getName());
			
			//System.out.println("****"+outputFile.getName());
			
			cmd.add(LANG_OPTION);
//			System.out.println("****"+cmd);
			// cmd.add("chi_sim");
			// cmd.add("eng");

			ProcessBuilder pb = new ProcessBuilder();
			pb.directory(imageFile.getParentFile());

			cmd.set(1, tempImage.getName());
			pb.command(cmd);
			pb.redirectErrorStream(true);

			Process process = pb.start();
			// tesseract.exe 1.jpg 1 -l chi_sim
			int w = process.waitFor();

			// 删除临时正在工作文件
			tempImage.delete();

			if (w == 0) {
				in = new BufferedReader(
						new InputStreamReader(new FileInputStream(outputFile.getAbsolutePath() + ".txt"), "UTF-8"));
				String str;
				while ((str = in.readLine()) != null) {
					strB.append(str).append(EOL);
				}
			} else {
				String msg;
				switch (w) {
				case 1:
					msg = "Errors accessing files.There may be spaces in your image's filename.";
					break;
				case 29:
					msg = "Cannot recongnize the image or its selected region.";
					break;
				case 31:
					msg = "Unsupported image format.";
					break;
				default:
					msg = "Errors occurred.";
				}
				tempImage.delete();
				throw new RuntimeException(msg);
			}
		} catch (Exception e) {
			return "";
		} finally {
			new File(outputFile.getAbsolutePath() + ".txt").delete();
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		return strB.toString();
	}
	
	
	public static void main(String[] args) throws IOException{
		
		
		File[] files = new File("D://image//").listFiles();
		for(File file : files)
		{
			String str = Image.recognizeText(file, "jpg");
			System.out.println(file.getName()+":::"+str);
		}
		
		 /*String PATH = "D:/image/12.jpg";  //原始图像
		 String imageFormat = "jpg";
		 File file = new File(PATH); 
		 binaryImage(file, "D:/image/imagetmp/12.jpg");
		 
	     String destDir ="E:/image/jd05.jpg";  
	     grayImage(file, destDir);    //灰度化
	     
	     String destDir1 ="E:/image/jd051.jpg";  
	     binaryImage(file, destDir1);   //二值化
	     
	    /*File file3 = new File("D:/image/imagetmp/12.jpg");
	     BufferedImage image = ImageIO.read(file3);  */
		 File file2 = new File("D:/image/12.jpg");
	     String destDir2 ="D:/image/imagetmp/12.jpg"; 
	     correde(file2,-1,destDir2);  //腐蚀
	     
	     BufferedImage image = ImageIO.read(new File("D:/image/imagetmp/12.jpg"));
	      
		    int width = image.getWidth();  
		    int height = image.getHeight();  
	     
	     for (int y = 0; y < height; y++){  
	            for (int x = 0; x < width; x++){  
//	            	System.out.print(image.getRGB(x, y)+",");
	                if (image.getRGB(x, y) != -1){  
	                    System.out.print("*");  
	                } else{  
	                    System.out.print(" ");  
	                }
	            }  
	            System.out.println();  
	        } 
	     
	     
	     
	     /*File file2 = new File(destDir1);
	     String destDir2 ="E:/image/jd0511.jpg"; 
	     correde(file2,-1,destDir2);  //腐蚀
*/	     
	      
	     //arrayToGreyImage(cor,destDir2);
	     
	     
	     
	     /*System.out.println(destDir2);
	     File file1 = new File(destDir2);
		 String ans = recognizeText(file1,imageFormat);  //OCR识别
		 
		 System.out.println("ans++++++++++++++"+ans);  //输出结果
*/	}
	public static void binaryImage(File sfile, String destDir) throws IOException{  
	     
	    BufferedImage image = ImageIO.read(sfile);  
	      
	    int width = image.getWidth();  
	    int height = image.getHeight();  
	      
	    BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY  
	    for(int i= 0 ; i < width ; i++){  
	        for(int j = 0 ; j < height; j++){  
	        int rgb = image.getRGB(i, j);  
	        grayImage.setRGB(i, j, rgb);  
	        }  
	    }  
	      
	    File newFile = new File(destDir);  
	    if (!newFile.exists()){  
	    	newFile.mkdirs();  
        } 
	    ImageIO.write(grayImage, "jpg", newFile);  
	    }  
	
	
	 public static void grayImage(File sfile, String destDir) throws IOException{  
		    
		    BufferedImage image = ImageIO.read(sfile);  
		      
		    int width = image.getWidth();  
		    int height = image.getHeight();  
		      
		    BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY  
		    for(int i= 0 ; i < width ; i++){  
		        for(int j = 0 ; j < height; j++){  
		        int rgb = image.getRGB(i, j);  
		        grayImage.setRGB(i, j, rgb);  
		        }  
		    }  
		      
		    File newFile = new File(destDir);  
		    if (!newFile.exists()){  
		    	newFile.mkdirs();  
	        } 
		    ImageIO.write(grayImage, "jpg", newFile);  
		    
		    }  
	
	 
	 //腐蚀
	 private static int sData[]={  
         0,0,0,  
         0,1,0,  
         0,1,1             
	 };  
	 public  static void  correde(File sfile,int threshold,String destDir) throws IOException{  
		 
		 	BufferedImage bufferedImage = ImageIO.read(sfile);  
	        int height = bufferedImage.getHeight();  
	        int width = bufferedImage.getWidth();  
	        // 灰度化  
	        int[][] source = new int[width][height];  
	        for (int x = 0; x < width; x++){  
	            for (int y = 0; y < height; y++){  
	                 source[x][y] = bufferedImage.getRGB(x, y); 
	            }
	        }
		    
	       /* int width=source[0].length;  
	        int height=source.length;  */
	        
	        int[][] result=new int[width][height];  
	        BufferedImage targetImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	        for(int i=0;i<width;i++){  
	            for(int j=0;j<height;j++){  
	                ///边缘不进行操作，边缘内才操作  
	                if(i>0&&j>0&&i<height-1&&j<width-1){  
	                    int max =0;  
	                      
	                    ///对结构元素进行遍历  
	                    for(int k=0;k<sData.length;k++){  
	                        int x=k/3;///商表示x偏移量  
	                        int y=k%3;///余数表示y偏移量  
	                          
	                          
	                        if(sData[k]!=0){  
	                            ///不为0时，必须全部大于阈值，否则就设置为0并结束遍历  
	                            if(source[i-1+x][j-1+y]>=threshold){  
	                                if(source[i-1+x][j-1+y]>max){  
	                                    max=source[i-1+x][j-1+y];  
	                                }  
	                            }else{  
	                                ////与结构元素不匹配,赋值0,结束遍历  
	                                max=0;  
	                                break;  
	                            }  
	                        }  
	                    }  
	                      
	                    ////此处可以设置阈值，当max小于阈值的时候就赋为0  
	                    result[i][j]=max;  
	                      
	                }else{  
	                    ///直接赋值  
	                    result[i][j]=source[i][j];  
	                      
	                }///end of the most out if-else clause .                  
	                int rgb=(result[i][j]<<16)|(result[i][j]<<8)|result[i][j];  
	                  
	                targetImage.setRGB(i, j, rgb);  
	            }  
	        }///end of outer for clause  
	        
	       /* BufferedImage targetImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	          
	        for(int j=0;j<width;j++){  
	            for(int i=0;i<height;i++){  
	                int greyRGB=result[j][i];  
	                int rgb=(greyRGB<<16)|(greyRGB<<8)|greyRGB;  
	                  
	                targetImage.setRGB(j, i, rgb);  
	            }  
	        }  */   
	          
	        //return targetImage;  
	        File newFile = new File(destDir);  
		    if (!newFile.exists()){  
		    	newFile.mkdirs();  
	        } 
		    ImageIO.write(targetImage, "jpg", newFile);
	        
	        
	    }  
	 //数组转灰度图像
	 public static void arrayToGreyImage(int[][] sourceArray,String destDir) throws IOException{  
	        int width=sourceArray[0].length;  
	        int height=sourceArray.length;  
	        BufferedImage targetImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	          
	        for(int j=0;j<height;j++){  
	            for(int i=0;i<width;i++){  
	                int greyRGB=sourceArray[j][i];  
	                int rgb=(greyRGB<<16)|(greyRGB<<8)|greyRGB;  
	                  
	                targetImage.setRGB(i, j, rgb);  
	            }  
	        }     
	          
	        //return targetImage;  
	        File newFile = new File(destDir);  
		    if (!newFile.exists()){  
		    	newFile.mkdirs();  
	        } 
		    ImageIO.write(targetImage, "jpg", newFile);  
		    
	        
	    }  
	 
	 

	private static File createImage(File imageFile, String imageFormat) throws Exception {
		File tempFile = null;
		ImageOutputStream ios = null;
		ImageWriter writer = null;
		ImageReader reader = null;
		try {
			Iterator readers = ImageIO.getImageReadersByFormatName(imageFormat);
			reader = (ImageReader) readers.next();

			ImageInputStream iis = ImageIO.createImageInputStream(imageFile);
			reader.setInput(iis);
			// Read the stream metadata
			IIOMetadata streamMetadata = reader.getStreamMetadata();

			// Set up the writeParam
			TIFFImageWriteParam tiffWriteParam = new TIFFImageWriteParam(Locale.CHINESE);
			tiffWriteParam.setCompressionMode(ImageWriteParam.MODE_DISABLED);

			// Get tif writer and set output to file
			Iterator writers = ImageIO.getImageWritersByFormatName("tiff");
			writer = (ImageWriter) writers.next();

			BufferedImage bi = reader.read(0);
			IIOImage image = new IIOImage(bi, null, reader.getImageMetadata(0));
			tempFile = tempImageFile(imageFile);
			ios = ImageIO.createImageOutputStream(tempFile);
			writer.setOutput(ios);
			writer.write(streamMetadata, image, tiffWriteParam);
			return tempFile;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ios != null) {
				ios.close();
			}
			if (writer != null) {
				writer.dispose();
			}
			if (reader != null) {
				reader.dispose();
			}
		}
	}
	

    private static int getMeans(List<Integer> data) {  
        int result = 0;  
        int size = data.size();  
        if(size == 0) return 0;
        for(Integer i : data) {  
            result += i;  
        }  
        return (result/size);  
    }  
    

	private static File tempImageFile(File imageFile) {
		String path = imageFile.getPath();
		StringBuffer strB = new StringBuffer(path);
		strB.insert(path.lastIndexOf('.'), 0);
		return new File(strB.toString().replaceFirst("(?<=//.)(//w+)$", "tif"));
	}
	
}
