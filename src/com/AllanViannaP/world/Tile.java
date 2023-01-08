package com.AllanViannaP.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.AllanViannaP.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR_YELLOW =  Game.spritesheet.getSprite(272, 80, 16, 16);
	public static BufferedImage TILE_FLOOR_YELLOW2 = Game.spritesheet.getSprite(272, 128, 16, 16);
	public static BufferedImage TILE_FLOOR_YELLOW3 = Game.spritesheet.getSprite(256, 144, 16, 16);
	public static BufferedImage TILE_FLOOR_YELLOW4 = Game.spritesheet.getSprite(272, 144, 16, 16);
	public static BufferedImage TILE_FLOOR_YELLOW5 = Game.spritesheet.getSprite(288, 144, 16, 16);
	public static BufferedImage TILE_FLOOR_YELLOW6 = Game.spritesheet.getSprite(272, 160, 16, 16);
	
	public static BufferedImage TITLE_FLOOR_GRASS = Game.spritesheet.getSprite(304, 176, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS2 = Game.spritesheet.getSprite(272, 176, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS3 = Game.spritesheet.getSprite(304, 144, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS4 = Game.spritesheet.getSprite(304, 112, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS5 = Game.spritesheet.getSprite(272, 122, 16, 16);	
	
	public static BufferedImage TITLE_FLOOR_GRASS_N = Game.spritesheet.getSprite(272, 64, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_NL = Game.spritesheet.getSprite(256, 64, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_NR = Game.spritesheet.getSprite(288, 64, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_L = Game.spritesheet.getSprite(256, 80, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_R = Game.spritesheet.getSprite(288, 80, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_S = Game.spritesheet.getSprite(272, 96, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_SL = Game.spritesheet.getSprite(256, 96, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_SR = Game.spritesheet.getSprite(288, 96, 16, 16);
	
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_UP_L_UP = Game.spritesheet.getSprite(256, 112, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_UP_L_MID = Game.spritesheet.getSprite(256, 128, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_UP_L_L = Game.spritesheet.getSprite(240, 128, 16, 16);
	
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_UP_R_UP = Game.spritesheet.getSprite(288, 112, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_UP_R_MID = Game.spritesheet.getSprite(288, 128, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_UP_R_R = Game.spritesheet.getSprite(304, 128, 16, 16);
	
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_DW_L_DW = Game.spritesheet.getSprite(256, 176, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_DW_L_MID = Game.spritesheet.getSprite(256, 160, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_DW_L_L = Game.spritesheet.getSprite(240, 160, 16, 16);
	
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_DW_R_DW = Game.spritesheet.getSprite(288, 176, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_DW_R_MID = Game.spritesheet.getSprite(288, 160, 16, 16);
	public static BufferedImage TITLE_FLOOR_GRASS_CURVE_DW_R_R = Game.spritesheet.getSprite(304, 160, 16, 16);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int  y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	
	public void render(Graphics g) {
		g.drawImage(sprite,x,y,null);
		
	}
	
	
	
}
