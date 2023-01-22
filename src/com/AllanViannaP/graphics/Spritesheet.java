package com.AllanViannaP.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	//Var sprite
	private BufferedImage spritesheet;

	public Spritesheet(String path) {
		try {
			//Select image "spritesheet" in folder
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method for choose image in spritesheet
		public BufferedImage getSprite(int x, int y, int width, int height){
			return spritesheet.getSubimage(x, y, width, height);
		}
	
}
