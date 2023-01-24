package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;

public class Entity {

	//Important vars
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	//Mask var
	private int maskx,masky,mwidth,mheight;
	
	//Sprite load var
	private BufferedImage sprite;
	
	//Method for all entities
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	
	//Set masks
	public void setMask(int maskx,int masky,int mwidth, int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	
	
	//Move X
	public void setX(int newX) {
		this.x = newX;
	}
	//Move Y
	public void setY(int newY) {
		this.y = newY;
	}
	//Check x
	public int getX() {
		return (int)this.x;
	}
	//Check y
	public int getY() {
		return (int)this.y;
	}
	//Check Width
	public int getWidth() {
		return this.width;
	}
	//Check Height
	public int getHeight() {
		return this.height;
	}
	
	//Logic empty
	public void tick() {
				
	}
	
	//Collision with entity
	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY()+e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY()+e2.masky,e2.mwidth,e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	
	//Render all graphics
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY()-Camera.y, null);
		
	}
	
	
	
}
