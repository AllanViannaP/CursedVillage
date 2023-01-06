package com.AllanViannaP.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity {

	public boolean right,up,left,down;
	public double spd = 0.8;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void tick() {
		if(right) {x+=spd;}
		else if(left){x-=spd;}
		if(up) {y-=spd;}
		else if(down) {y+=spd;}
	}
	
	
}
