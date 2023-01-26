package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;

public class SwordHit extends Entity{

	//Direction 
	private int dx;
	private int dy;
	
	//Speed
	private double spd=0.2;
	
	//Control animation
	private int frames = 0, maxFrames=5,index=0,maxIndex = 6;
	
	
	private BufferedImage[] HIT_RIGHT;
	private BufferedImage[] HIT_LEFT;
	private BufferedImage[] HIT_UP;
	private BufferedImage[] HIT_DOWN;
	
	
	
	
	public SwordHit(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy=dy;
		
		
		
		//Init sprites var
		 HIT_RIGHT = new BufferedImage[6];
		 HIT_LEFT = new BufferedImage[6];
		 HIT_UP = new BufferedImage[6];
		 HIT_DOWN = new BufferedImage[6];
		 
		//Load sprites
			for(int i=0;i<6;i++) {
				 HIT_RIGHT[i] = Game.spritesheet.getSprite(0+(i*16), 80, 16, 16);
				 HIT_LEFT[i] = Game.spritesheet.getSprite(0+(i*16), 80, 16, 16);
				 HIT_DOWN[i] = Game.spritesheet.getSprite(0+(i*16), 80, 16, 16);
				 HIT_UP[i] = Game.spritesheet.getSprite(0+(i*16), 80, 16, 16);
			}
			
	}
	

	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		
		//Animation
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index >= maxIndex) {
				index=0;
			}
	}}
	
	public void render(Graphics g){
		//Sprites render right and left
		if(Game.player.dir == Game.player.right_dir) {
			g.drawImage(HIT_RIGHT[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
			else if(Game.player.dir == Game.player.left_dir) {
			g.drawImage(HIT_LEFT[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
			}
			
			//Sprites render up and down
			if(Game.player.dir == Game.player.up_dir) {
			g.drawImage(HIT_UP[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
			}
			else if(Game.player.dir == Game.player.down_dir) {
			g.drawImage(HIT_DOWN[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
			}
		}
	}

