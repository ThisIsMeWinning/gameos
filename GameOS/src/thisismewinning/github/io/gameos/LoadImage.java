package thisismewinning.github.io.gameos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadImage {

	public static BufferedImage loadImage(Class<?> classfile, String path) {
		URL url = classfile.getResource(path);
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
