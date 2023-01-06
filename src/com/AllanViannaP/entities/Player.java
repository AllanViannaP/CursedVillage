package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;

public class Player extends Entity {

	public boolean right,up,left,down;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public double spd = 0.8;
	
	private int frames = 0, maxFrames=8,index=0,maxIndex = 7, an = 0, idleIndex = 8;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;

	
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		  rightPlayer = new BufferedImage[10];
		  leftPlayer = new BufferedImage[10];
		  upPlayer = new BufferedImage[10];
		  downPlayer = new BufferedImage[10];
		  
		  
		  
		  for(int i=0;i<8;i++) {
			  rightPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 0, 16, 16);
			  leftPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 16, 16, 16);
			  upPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 32, 16, 16);
			  downPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 48, 16, 16);}
		  for(int i=8;i<10;i++) {
			  rightPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 0, 16, 16);
			  leftPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 16, 16, 16);
			  upPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 32, 16, 16);
			  downPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 48, 16, 16);
			  
		  }
		  
		  
	}
	
	

	public void tick() {
		moved = false;
		
		if(right) {
			x+=spd;
			dir = right_dir; 
			moved = true;}
		
		else if(left){
			x-=spd; 
			dir = left_dir;
			moved = true;}
			
		if(up) {
			y-=spd;
			dir = up_dir; 
			moved = true;}
		
		else if(down) {
			y+=spd; 
			dir = down_dir;
			moved = true;}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		else if(moved == false){
			an++;
			if(an == 10) {
				an = 0;
				idleIndex++;
				if(idleIndex > 9) {
					idleIndex = 8;
				}
				
			}
			
		}
		}
	
	public void render(Graphics g) {
	
		if(dir == right_dir && moved) {
		g.drawImage(rightPlayer[index], this.getX(),this.getY(),null);}
		else if(dir == left_dir && moved) {
		g.drawImage(leftPlayer[index], this.getX(),this.getY(),null);}
		

		if(dir == up_dir && moved) {
		g.drawImage(upPlayer[index], this.getX(),this.getY(),null);		
		}
		else if(dir == down_dir && moved) {
		g.drawImage(downPlayer[index], this.getX(),this.getY(),null);	
		}
		
		if(dir == right_dir && moved == false) {
			g.drawImage(rightPlayer[idleIndex], this.getX(),this.getY(),null);}
			else if(dir == left_dir && moved == false) {
			g.drawImage(leftPlayer[idleIndex], this.getX(),this.getY(),null);}
			
		if(dir == up_dir && moved == false) {
			g.drawImage(upPlayer[idleIndex], this.getX(),this.getY(),null);}
			else if(dir == down_dir && moved == false) {
			g.drawImage(downPlayer[idleIndex], this.getX(),this.getY(),null);}
		
}
	}
