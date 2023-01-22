package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;
import com.AllanViannaP.world.World;

public class Enemy extends Entity{
	
	
	
	public double spd = 0.4;

	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	private int frames = 0, maxFrames=15,index=0,maxIndex = 2;
	
	private BufferedImage[] BLOOD_PHANTOM_RIGHT;
	private BufferedImage[] BLOOD_PHANTOM_LEFT;
	private BufferedImage[] BLOOD_PHANTOM_UP;
	private BufferedImage[] BLOOD_PHANTOM_DOWN;
	public  static BufferedImage BLOOD_PHANTOM_NULL = Game.spritesheet.getSprite(160, 0, 16, 16);
	
	
	
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		BLOOD_PHANTOM_RIGHT = new BufferedImage[3];
		BLOOD_PHANTOM_LEFT = new BufferedImage[3];
		BLOOD_PHANTOM_UP = new BufferedImage[3];
		BLOOD_PHANTOM_DOWN = new BufferedImage[3];
		
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
			dir = right_dir;
		}
		else if((int)x>Game.player.getX() && World.isFree((int)(x-spd), this.getY())) {
			x-=spd;
			dir = left_dir;
		}
		if((int)y<Game.player.getY()&& World.isFree(this.getX(), (int)(y+spd))) {
			y+=spd;
			dir = up_dir;
		}
		else if((int)y>Game.player.getY()&& World.isFree(this.getX(), (int)(y-spd))) {
			y-=spd;
			dir = down_dir;
		}
		
		
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			
		
	}}
	
	public void render(Graphics g){
		if(dir == right_dir) {
		g.drawImage(BLOOD_PHANTOM_RIGHT[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == left_dir) {
		g.drawImage(BLOOD_PHANTOM_LEFT[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		
		if(dir == up_dir) {
		g.drawImage(BLOOD_PHANTOM_UP[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		else if(dir == down_dir) {
		g.drawImage(BLOOD_PHANTOM_DOWN[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
	}

}
