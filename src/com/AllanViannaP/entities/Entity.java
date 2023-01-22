package com.AllanViannaP.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;
import com.AllanViannaP.world.Camera;

public class Entity {

	//Important vars
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	//Sprite load var
	private BufferedImage sprite;
	
	//Method for all entities
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
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
	
	//Render all graphics
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY()-Camera.y, null);
		
	}
	
	
	
}
