package com.AllanViannaP.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.AllanViannaP.entities.Enemy;
import com.AllanViannaP.entities.Entity;
import com.AllanViannaP.main.Game;

public class World {

	//var tiles
	public static Tile[] tiles; 
	
	//Sprites size
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			//Load map
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			// load pixels map
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			//Check map
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			//set RGB in map
			map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
			
			for(int xx = 0; xx < WIDTH;xx++) {	
				for(int yy = 0; yy < HEIGHT; yy++) {
					//pixel current check
					int pixelNow = pixels[xx+(yy*map.getWidth())];
					//Default pixel
					tiles[xx+(yy*WIDTH)] = new FloorTile(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TITLE_FLOOR_GRASS);
					
					//
					if(pixelNow == 0xFFbd6868) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_FLOOR_YELLOW);
						Game.entities.add(new Enemy(xx*TILE_SIZE,yy*TILE_SIZE,TILE_SIZE,TILE_SIZE,Enemy.BLOOD_PHANTOM_NULL));
					}
					//
					else if(pixelNow == 0xFF87c293) { 
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TITLE_FLOOR_GRASS_CURVE_UP_R_UP);
						
					}
				}
			}

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//Collision in wall
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext/TILE_SIZE;
		int y1 = ynext/TILE_SIZE;
		
		int x2 =( xnext+TILE_SIZE-1)/TILE_SIZE ;
		int y2 = ynext /TILE_SIZE ;
		
		int x3 = xnext /TILE_SIZE ;
		int y3 = (ynext+TILE_SIZE-1) /TILE_SIZE ;
		
		int x4 = (xnext+TILE_SIZE-1) /TILE_SIZE ;
		int y4 = (ynext+TILE_SIZE-1) /TILE_SIZE ;
		
		
		return !((tiles[x1+(y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2+(y2*World.WIDTH)] instanceof WallTile)  ||
				(tiles[x3+(y3*World.WIDTH)] instanceof WallTile)  ||
				(tiles[x4+(y4*World.WIDTH)] instanceof WallTile));
		
	}
	
	//Render and set camera
	public void render(Graphics g) {
		int xstart = Camera.x>>4;
		int ystart = Camera.y>>4;
		int xfinal = xstart + (Game.WIDTH>>4)+1;
		int yfinal = ystart + (Game.HEIGHT>>4)+1;
		
		
		
		for(int xx = 0; xx <= xfinal; xx++) {
			for(int yy = 0; yy <= yfinal; yy++) {
				if(xx<0||yy<0||xx>=WIDTH||yy>=HEIGHT) {
					continue;}
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			}
		}
	}
}
