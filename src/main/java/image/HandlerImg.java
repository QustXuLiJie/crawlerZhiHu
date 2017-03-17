package image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class HandlerImg {

	public static void imgMatrix(String imgfile) throws Exception
	{
		BufferedImage bufferedImage = ImageIO.read(new File(imgfile));  
        int h = bufferedImage.getHeight();  
        int w = bufferedImage.getWidth();
        
        int[][] gray = new int[w][h]; 
        for (int x = 0; x < w; x++){  
            for (int y = 0; y < h; y++){ 
            	gray[x][y] = bufferedImage.getRGB(x, y);
            	System.out.print(bufferedImage.getRGB(x, y));
            	System.out.print("   ");
            }
            System.out.println();
        }
	}
	
	public static void  main(String[] args)
	{
		try {
			HandlerImg.imgMatrix("D://imgetemp//0.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
