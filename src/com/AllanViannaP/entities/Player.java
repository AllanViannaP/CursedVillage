package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;
import com.AllanViannaP.world.World;

public class Player extends Entity {

	//Move var
	public boolean right,up,left,down;
	//Speed 
	public double spd = 0.8;
	
	//Animation vars
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	private int frames = 0, maxFrames=8,index=0,maxIndex = 7, an = 0, idleIndex = 8, timeAn= 0;
	private boolean moved = false;
	
	//Sprites var
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;

	public int life = 1;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		//Sprite var init
		  rightPlayer = new BufferedImage[10];
		  leftPlayer = new BufferedImage[10];
		  upPlayer = new BufferedImage[10];
		  downPlayer = new BufferedImage[10];
		  
		  
		  //Sprite movement load
		  for(int i=0;i<8;i++) {
			  rightPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 0, 16, 16);
			  leftPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 16, 16, 16);
			  downPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 32, 16, 16);
			  upPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 48, 16, 16);
			}
		  
		  //Sprite idle load
		  for(int i=8;i<10;i++) {
			  rightPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 0, 16, 16);
			  leftPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 16, 16, 16);
			  downPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 32, 16, 16);
			  upPlayer[i] = Game.spritesheet.getSprite(0+((i-8)*16), 48, 16, 16);  
		  }  
	}
	
	
    //All logic
	public void tick() {
		moved = false;
		
		//Movement and collision
		if(right && World.isFree((int)(x+spd),this.getY())) {
			x+=spd;
			dir = right_dir; 
			moved = true;}
		
		else if(left &&  World.isFree((int)(x-spd),this.getY())) {
			x-=spd; 
			dir = left_dir;
			moved = true;}
			
		if(up &&  World.isFree(this.getX(),(int)(y-spd))) {
			y-=spd;
			dir = up_dir; 
			moved = true;}
		
		else if(down && World.isFree(this.getX(),(int)(y+spd))) {
			y+=spd; 
			dir = down_dir;
			moved = true;}
		
		
		//Move animation
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
		
		//Idle animation and delay between blinks
		else if(moved == false){
			an++;
			if(an >= 8) {
				an = 0;
				timeAn++;
				if(timeAn == 1 && idleIndex == 9) {
					idleIndex++;
					timeAn = 0;
				} else if(timeAn == 20 && idleIndex == 8 ) {
					idleIndex++;
					timeAn = 0;
				}
				if(idleIndex > 9) {
					idleIndex = 8;
				}
			}
		}
		
		//Set camera in player
		Camera.x =  Camera.clamp(this.getX()-(Game.WIDTH/2), 0, World.WIDTH*16-Game.WIDTH);
		Camera.y =   Camera.clamp(this.getY()-(Game.HEIGHT/2), 0, World.HEIGHT*16-Game.HEIGHT);
		}
	
	
	//All render
	public void render(Graphics g) {
	
		//Move animation left and right
		if(dir == right_dir && moved) {
		g.drawImage(rightPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == left_dir && moved) {
		g.drawImage(leftPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		
		//Move animation up and down
		if(dir == up_dir && moved) {
		g.drawImage(upPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);		
		}
		else if(dir == down_dir && moved) {
		g.drawImage(downPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);	
		}
		
		//Idle animation right and left
		if(dir == right_dir && moved == false) {
		g.drawImage(rightPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == left_dir && moved == false) {
		g.drawImage(leftPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
			
		//Idle animation up and down
		if(dir == up_dir && moved == false) {
		g.drawImage(upPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == down_dir && moved == false) {
		g.drawImage(downPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		
}
	}
