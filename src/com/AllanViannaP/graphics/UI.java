package com.AllanViannaP.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.AllanViannaP.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setFont(new Font("arial",Font.BOLD,8));
		g.setColor(Color.white);
		g.drawString("Find", 8, 8);
		
	}
	
	
}
