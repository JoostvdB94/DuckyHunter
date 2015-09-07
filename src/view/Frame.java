package view;
import java.awt.Color;

import javax.swing.JFrame;

import controller.events.MouseInput;

public class Frame extends JFrame {
	
	public static final int SCREENWIDTH = 1080;
	public static final int SCREENHEIGHT = 768;
	
	public Frame() {
		setTitle("Ducky hunter");
		setSize(SCREENWIDTH, SCREENHEIGHT); 
		setLocation(300, 50); 
		MainScreen t = new MainScreen();
		add(t);
	}
}