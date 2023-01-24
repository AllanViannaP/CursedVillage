package com.AllanViannaP.entities;

import java.awt.image.BufferedImage;



public class Door extends Entity{
	
	public static int TypeDoor;
	private int PactDoor = 0,KeyDoor=1;
	
	public Door(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
	}

	public void tick() {
		
	}
}
