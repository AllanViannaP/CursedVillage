package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;
import com.AllanViannaP.world.World;

public class Enemy extends Entity{
	
	//Enemy speed
	public double spd = 0.4;
	//HP
	private int life = 1;
	
	
    //Animation var
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	private int frames = 0, maxFrames=15,index=0,maxIndex = 2;
	
	//Sprites animation
	private BufferedImage[] BLOOD_PHANTOM_RIGHT;
	private BufferedImage[] BLOOD_PHANTOM_LEFT;
	private BufferedImage[] BLOOD_PHANTOM_UP;
	private BufferedImage[] BLOOD_PHANTOM_DOWN;
	public  static BufferedImage BLOOD_PHANTOM_NULL = Game.spritesheet.getSprite(160, 0, 16, 16);
	
	//Mask collision
	private int maskx = 10, masky = 10, maskh = 10, maskw = 10;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		//Init sprites var
		super(x, y, width, height, sprite);
		BLOOD_PHANTOM_RIGHT = new BufferedImage[3];
		BLOOD_PHANTOM_LEFT = new BufferedImage[3];
		BLOOD_PHANTOM_UP = new BufferedImage[3];
		BLOOD_PHANTOM_DOWN = new BufferedImage[3];
		
		//Load sprites
		for(int i=0;i<2;i++) {
			BLOOD_PHANTOM_RIGHT[i] = Game.spritesheet.getSprite(160+(i*16), 0, 16, 16);
			BLOOD_PHANTOM_LEFT[i] = Game.spritesheet.getSprite(160+(i*16), 16, 16, 16);
			BLOOD_PHANTOM_DOWN[i] = Game.spritesheet.getSprite(160+(i*16), 32, 16, 16);
			BLOOD_PHANTOM_UP[i] = Game.spritesheet.getSprite(160+(i*16), 48, 16, 16);
		}
		
		
	}
	
	//All logic
	public void tick() {
		
		if(this.isCollidingPlayer() == false) {
		
		//Move
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
		}}else {
			//Collision with player
			Game.player.life--;
			//Game.entities.remove(this);
		}
		
		//Animation
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
	}
			
	collisionHit();
	
	if(life <= 0) {
		Game.entities.remove(this);
		return;
	}
	}
	
	//Collision with player hit
	public void collisionHit() {
		for(int i=0;i<Game.hits.size();i++) {
			Entity e = Game.hits.get(i);
			if(e instanceof SwordHit) {
				if(Entity.isColliding(this, e)) {
					life--;
					return;
				}
			}
		}
	}
	
	
	//Collision with Player 
	public  boolean isCollidingPlayer() {
		//set collision mask
		Rectangle enemyCurrent = new Rectangle(this.getX()+maskx,this.getY()+masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	
	//All render
	public void render(Graphics g){
		//Sprites render right and left
		if(dir == right_dir) {
		g.drawImage(BLOOD_PHANTOM_RIGHT[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == left_dir) {
		g.drawImage(BLOOD_PHANTOM_LEFT[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		
		//Sprites render up and down
		if(dir == up_dir) {
		g.drawImage(BLOOD_PHANTOM_UP[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		else if(dir == down_dir) {
		g.drawImage(BLOOD_PHANTOM_DOWN[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
	}

}
