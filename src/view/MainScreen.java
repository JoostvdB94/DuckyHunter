package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.events.MouseInput;
import model.Ball;
import model.GameObject;
import model.GameObject.GameObjectType;

public class MainScreen extends JPanel {

	private Ball ball;
	private MouseInput mouseInput;
	private boolean isRunning = true;
	private boolean isHit = false;
	private BufferedImage bgImg;
	private BufferedImage targetImg;

	public MainScreen() {
		ball = new Ball();
		try {
			bgImg = ImageIO.read(new File("src/view/assets/DuckhuntBG.png"));
			targetImg = ImageIO.read(new File("src/view/assets/Target.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mouseInput = new MouseInput();
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		new Thread(new Runnable() {
			public void run() {
				loop();
			}
		}).start();
	}

	private void loop() {
		while (true) {
			handleInput();

			if (isRunning) {
				ball.move();

			}

			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void handleInput() {
		mouseInput.poll();

		GameObject mouse = new GameObject() {
			@Override
			public void setYPosition(int yPos) {
			}

			@Override
			public void setXPosition(int xPos) {
			}

			@Override
			public void setWidth(int width) {
			}

			@Override
			public void setHeight(int height) {
			}

			@Override
			public int getYPosition() {
				return mouseInput.getPosition().y;
			}

			@Override
			public int getXPosition() {
				return mouseInput.getPosition().x;
			}

			@Override
			public int getWidth() {
				return 10;
			}

			@Override
			public GameObjectType getType() {
				return GameObjectType.NONE;
			}

			@Override
			public int getHeight() {
				return 10;
			}

			@Override
			public boolean collidesWith(GameObject object) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		if (mouseInput.buttonDown(MouseEvent.BUTTON1)) {
			if (ball.collidesWith(mouse)) {
				isHit = true;
			} else {
				isHit = false;
			}
			//isRunning = false;
		} else {
			//isRunning = true;
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);

		g.setColor(Color.BLACK);
		g.drawImage(targetImg,mouseInput.getPosition().x -25, mouseInput.getPosition().y- 25, 50,50,null);
		g.drawImage(bgImg, 0, 0, Frame.SCREENWIDTH, Frame.SCREENHEIGHT, null);
		if (isHit) {
			g.drawString("GERAAKT, PUNTEN, HIGHSCORE, FEEST",
					Frame.SCREENWIDTH / 2, Frame.SCREENHEIGHT / 2);
		}
	}
}
