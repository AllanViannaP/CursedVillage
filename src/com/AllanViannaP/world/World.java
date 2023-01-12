package com.AllanViannaP.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.AllanViannaP.entities.Enemy;
import com.AllanViannaP.entities.Entity;
import com.AllanViannaP.main.Game;

public class World {

	private Tile[] tiles; 
	public static int WIDTH,HEIGHT;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
			
			for(int xx = 0; xx < WIDTH;xx++) {	
				for(int yy = 0; yy < HEIGHT; yy++) {
					int pixelNow = pixels[xx+(yy*map.getWidth())];
					tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TITLE_FLOOR_GRASS);
					if(pixelNow == 0xFFbd6868) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_YELLOW);
					}
					else if(pixelNow == 0xFF87c293) { 
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TITLE_FLOOR_GRASS_CURVE_UP_R_UP);
						Game.entities.add(new Enemy(xx*16,yy*16,16,16,Enemy.BLOOD_PHANTOM_NULL));
					}
				}
			}

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void render(Graphics g) {
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			}
		}
	}
}
