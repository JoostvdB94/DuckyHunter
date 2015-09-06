package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Ball;

public class MainScreen extends JPanel {

	private Ball ball;

	public MainScreen() {
		ball = new Ball();
		new Thread( new Runnable() {
			public void run() {
				loop();
			}
		}).start();
	}

	private void loop() {
		while (true) {
			
			ball.move();
			System.out.println("xPos" + ball.getXPosition() + "  xSpeed" + ball.getxSpeed());
			System.out.println("yPos" + ball.getYPosition() + "  ySpeed" + ball.getySpeed());
				
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillOval(ball.getXPosition(), ball.getYPosition(), ball.getWidth(),
				ball.getHeight());
	}
}
