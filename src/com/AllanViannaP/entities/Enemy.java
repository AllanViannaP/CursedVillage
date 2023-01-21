package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;
import com.AllanViannaP.world.World;

public class Enemy extends Entity{
	
	
	
	public double spd = 0.4;

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
		if((int)x<Game.player.getX() && World.isFree((int)(x+spd), this.getY())) {
			x+=spd;
		}
		else if((int)x>Game.player.getX() && World.isFree((int)(x-spd), this.getY())) {
			x-=spd;
		}
		if((int)y<Game.player.getY()&& World.isFree(this.getX(), (int)(y+spd))) {
			y+=spd;
		}
		else if((int)y>Game.player.getY()&& World.isFree(this.getX(), (int)(y-spd))) {
			y-=spd;
		}
		
		
		
	}
	
	public void render(Graphics g){
		g.drawImage(BLOOD_PHANTOM_RIGHT[0], this.getX()-Camera.x,this.getY()-Camera.y,null);
		
	}

}
