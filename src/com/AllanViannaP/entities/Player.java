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
	//HP
	public int life = 1;
	
	//Hit control
	public boolean Hit = false;
	private int countHit = 0;
	
	//Animation vars
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	private int frames = 0, maxFrames=8,index=0,maxIndex = 7, an = 0, idleIndex = 8, timeAn= 0, hitAn =0, hitIndex = 10, hitMaxInd = 15, maxHitAn = 10;
	private boolean moved = false;
	
	
	//Sprites var
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;

	//Itens
	public boolean keys = false;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		//Sprite var init
		  rightPlayer = new BufferedImage[16];
		  leftPlayer = new BufferedImage[16];
		  upPlayer = new BufferedImage[16];
		  downPlayer = new BufferedImage[16];
		  
		  
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
		  
		  //Sprite hits load
		  for(int i=10;i<16;i++) {
			  rightPlayer[i] = Game.spritesheet.getSprite(0+((i-10)*16), 64, 16, 16);
			  leftPlayer[i] = Game.spritesheet.getSprite(0+((i-10)*16), 64, 16, 16);
			  downPlayer[i] = Game.spritesheet.getSprite(0+((i-10)*16), 64, 16, 16);
			  upPlayer[i] = Game.spritesheet.getSprite(0+((i-10)*16), 64, 16, 16);  
		  }  
		  //
		  
		  
		  
		  
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
		
	
		
		//Check collisions
		checkWell();
		checkKeyDoor();
		
		//Check life
		if(Game.player.life <=0) {
			//reset quest	
			
			}
		
		
		//Create player hit
		if(Hit) {
			spd=0;
			Hit = false;
			//Control animation and direction var
			int dx = 0;
			int dy = 0;
			int px = 0;
			int py = 0;
			
			//Direction hit
			if(dir == right_dir) {
			dx = 1;
			px = 8;
			py = +2;}
			else if(dir== left_dir) {
			dx = -1;
			px = -8;
			}
			if(dir == up_dir) {
			dy = -1;
			py = 8;}
			else if(dir == down_dir) {
			dy = 1;
			py = -8;
				}
			SwordHit hit = new SwordHit(this.getX()+px,this.getY()+py,10,10,null, dx, dy);
			Game.hits.add(hit);	
			}
		
		
		
		
		//Clean list
		if(!Game.hits.isEmpty()) {
			//Hit animation
			hitAn++;
			if(hitAn == maxHitAn) {
				hitAn = 0;
				hitIndex++;
				if(hitIndex > hitMaxInd) {
					hitIndex = 10;
				}
			}
			countHit++;
			if(countHit>=26) {
				Game.hits.clear();
				countHit=0;
				spd = 0.8;
			}
		}
		
		
		//Set camera in player
		Camera.x =  Camera.clamp(this.getX()-(Game.WIDTH/2), 0, World.WIDTH*16-Game.WIDTH);
		Camera.y =   Camera.clamp(this.getY()-(Game.HEIGHT/2), 0, World.HEIGHT*16-Game.HEIGHT);
		}
	
	//Check well and pick key
	public void checkWell() {
		for(int i= 0; i<Game.entities.size();i++) {
			Entity now = Game.entities.get(i);
			if(now instanceof Well) {
				if(Entity.isColliding(this, now) && Well.avaliable == true) {
					keys = true;
					Well.avaliable = false;
				}
			}
		}
		
	}
	
	//Open lock door 
	public void checkKeyDoor() {
		for(int i= 0; i<Game.entities.size();i++) {
			Entity now = Game.entities.get(i);
			if(now instanceof Door) {
				if(Entity.isColliding(this, now) && keys == true && Door.TypeDoor==1) {
					
					//Change map
				}
			}
		}
		
	}
	
	
	//All render
	public void render(Graphics g) {
	
		//Move animation left and right
		if(dir == right_dir && moved && spd!=0) {
		g.drawImage(rightPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == left_dir && moved  && spd!=0) {
		g.drawImage(leftPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		
		//Move animation up and down
		if(dir == up_dir && moved  && spd!=0) {
		g.drawImage(upPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);		
		}
		else if(dir == down_dir && moved  && spd!=0) {
		g.drawImage(downPlayer[index], this.getX()-Camera.x,this.getY()-Camera.y,null);	
		}
		
		//Idle animation right and left
		if(dir == right_dir && moved == false  && spd!=0) {
		g.drawImage(rightPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == left_dir && moved == false  && spd!=0) {
		g.drawImage(leftPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
			
		//Idle animation up and down
		if(dir == up_dir && moved == false  && spd!=0) {
		g.drawImage(upPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		else if(dir == down_dir && moved == false  && spd!=0) {
		g.drawImage(downPlayer[idleIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		
		
		//Hit animation
		if(dir == up_dir && spd==0 ) {
			g.drawImage(upPlayer[hitIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
			else if(dir == down_dir && spd==0) {
			g.drawImage(downPlayer[hitIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		
		if(dir == right_dir && spd==0) {
			g.drawImage(rightPlayer[hitIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
			else if(dir == left_dir && spd==0) {
			g.drawImage(leftPlayer[hitIndex], this.getX()-Camera.x,this.getY()-Camera.y,null);}
		
}
	}
