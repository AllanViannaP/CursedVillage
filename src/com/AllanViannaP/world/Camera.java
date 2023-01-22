package com.AllanViannaP.world;

public class Camera {

	//Set x and y 
	public static int x;
	public static int y;
	
	//Camera move and stay on the map
	public static int clamp(int Atual, int Min, int Max) {
		if(Atual < Min) {
			Atual = Min;
		}
		if(Atual >  Max) {
			Atual = Max;
		}
		
		return Atual;
	}
}
