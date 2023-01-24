package com.AllanViannaP.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.AllanViannaP.main.Game;

public class UI {

	private String Objective = "Find key";
	
	//Create UI
	public void render(Graphics g) {
		//Show sprite key 
		if(Game.player.keys == true) {
			Objective = "Open the door";
		}
		
		
		//Show objective 
		g.setFont(new Font("arial",Font.BOLD,8));
		g.setColor(Color.white);
		g.drawString(Objective, 8, 8);
		
		
		
	}
	
	
}
