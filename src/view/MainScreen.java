package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

	public MainScreen() {
		ball = new Ball();
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

		if (ball.collidesWith(mouse)) {
			isHit = true;
		} else {
			isHit = false;
		}

		if (mouseInput.buttonDown(1)) {
			isRunning = false;
		} else {
			isRunning = true;
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

		g.setColor(Color.BLACK);
		g.fillRect(mouseInput.getPosition().x, mouseInput.getPosition().y, 10,
				10);

		if (isHit) {
			g.drawString("GERAAKT, PUNTEN, HIGHSCORE, FEEST",
					Frame.SCREENWIDTH / 2, Frame.SCREENHEIGHT / 2);
		}
	}
}
