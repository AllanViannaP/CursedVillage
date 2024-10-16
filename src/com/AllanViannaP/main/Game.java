package com.AllanViannaP.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.AllanViannaP.entities.Entity;
import com.AllanViannaP.entities.Player;
import com.AllanViannaP.entities.SwordHit;
import com.AllanViannaP.graphics.Spritesheet;
import com.AllanViannaP.graphics.UI;
import com.AllanViannaP.world.World;


public class Game extends Canvas implements Runnable,KeyListener{

	//Config JFrame and logic
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	//Tam window
	public static final int WIDTH = 320;
	public static final int HEIGHT = 180;
	private final int SCALE = 4;
	
	//Current map
	private String CUR_MAP = "Village";
	
	//Var sprite
	private  BufferedImage image;
	//Entities list
	public static List<Entity> entities;
	public static List<SwordHit> hits;
	//Load class 
	public static Spritesheet spritesheet;
	public static Player player;
	public static World world;
	public UI ui;

	
	public Game() {
		//Keys add
		addKeyListener(this);
		//Set window size and graphics 
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		
		//Obj start
		ui = new UI();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		hits = new ArrayList<SwordHit>();
		spritesheet = new Spritesheet("/SpriteSheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(0,0,16,16));
		entities.add(player);
		world = new World("/VillageMap.png");
		
		
		
	}
	
	//Set frames
	public void initFrame() {
		frame = new JFrame("Cursed Village");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//synchro and start game
	public synchronized void start() {
		thread =  new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	//synchro and stop game
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//main init game
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}
	
	//all logic
	public void tick() {
		//Init entities logic
		for(int i=0; i<entities.size();i++) {
			Entity e =  entities.get(i);
			e.tick();
		}
		//Hits tick
		for(int i=0; i<hits.size();i++) {
			hits.get(i).tick();
		}
		
		
		//-----------------------------------------------------------------------------------------------------------------------//
														//	CHANGE MAP
		//-----------------------------------------------------------------------------------------------------------------------//
		/*
		if(entities.size() == 1 && CUR_MAP !="House") {
			CUR_MAP = "House";
			String newMap = CUR_MAP+"Map.png";
			World.restartGame(newMap);
		}*/
		//-----------------------------------------------------------------------------------------------------------------------//
		//-----------------------------------------------------------------------------------------------------------------------//
		//-----------------------------------------------------------------------------------------------------------------------//
		
	}
	
	//render all game
	public void render() {
		//Set buffer strategy and load 
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//Clean and start graphics
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Render World and game
		//Graphics2d g2 = (Graphics2D) g;
		world.render(g);
		
		//Render entities
		for(int i=0; i<entities.size();i++) {
			Entity e =  entities.get(i);
			e.render(g);
		}
		//Render hits
		for(int i=0; i<hits.size();i++) {
			hits.get(i).render(g);
		}
		//Draw UI
		ui.render(g);
		
		//Draw renders
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	
	
	
	public void run() {
		//Fps limiter 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/ amountOfTicks;
		double check = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		
		while(isRunning) {
			long now = System.nanoTime();
			check+=(now - lastTime) / ns;
			lastTime = now;
			if(check >= 1) {
				tick();
				render();
				frames++;
				check--;	
			}
			
			//Fps check
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS" + frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		}

	//Press move player
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT|| e.getKeyCode()==KeyEvent.VK_D) {
			player.right = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT|| e.getKeyCode()==KeyEvent.VK_A) {
				player.left = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP|| e.getKeyCode()==KeyEvent.VK_W) {
			player.up = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			player.down = true;
	}
		
	}

	//Released stop player
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D ) {
			player.right = false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
				player.left = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP|| e.getKeyCode()==KeyEvent.VK_W) {
			player.up = false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			player.down = false;
	}
		//Hit 
		if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_Z || e.getKeyCode()==KeyEvent.VK_SHIFT   ) {
			player.Hit = true;
		}
		
	}

	
	

}
