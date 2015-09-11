package view;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

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
		t.setBackground(Color.getHSBColor(new Float(50.5419),new Float(0.79),new Float(1)));
		add(t);
	}
}