package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;

public class Enemy extends Entity{

	private BufferedImage[] BLOOD_PHANTOM_RIGHT;
	private BufferedImage[] BLOOD_PHANTOM_LEFT;
	private BufferedImage[] BLOOD_PHANTOM_UP;
	private BufferedImage[] BLOOD_PHANTOM_DOWN;
	public  static BufferedImage BLOOD_PHANTOM_NULL = Game.spritesheet.getSprite(160, 0, 16, 16);
	
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		BLOOD_PHANTOM_RIGHT = new BufferedImage[2];
		BLOOD_PHANTOM_LEFT = new BufferedImage[2];
		BLOOD_PHANTOM_UP = new BufferedImage[2];
		BLOOD_PHANTOM_DOWN = new BufferedImage[2];
		
		for(int i=0;i<2;i++) {
			BLOOD_PHANTOM_RIGHT[i] = Game.spritesheet.getSprite(160+(i*16), 0, 16, 16);
			BLOOD_PHANTOM_LEFT[i] = Game.spritesheet.getSprite(160+(i*16), 16, 16, 16);
			BLOOD_PHANTOM_DOWN[i] = Game.spritesheet.getSprite(160+(i*16), 32, 16, 16);
			BLOOD_PHANTOM_UP[i] = Game.spritesheet.getSprite(160+(i*16), 48, 16, 16);
		}
		
		
	}
	public void tick() {
		
		
	}
	
	public void render(Graphics g){
		g.drawImage(BLOOD_PHANTOM_RIGHT[0], this.getX(),this.getY(),null);
		
	}

}
