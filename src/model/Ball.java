package model;

import java.util.Random;

import view.Frame;

public class Ball {

	private Random random;
	private int xPosition;
	private int yPosition;
	private int height;
	private int width;
	private int xSpeed;
	private int ySpeed;
	private Direction xDirection;
	private Direction yDirection;
	public enum Direction {
		LEFT,
		RIGHT,
		UP,
		DOWN
	}
	
	public Ball() {
		random = new Random();
		setxSpeed(1);
		setySpeed(1);
		setxPosition(Frame.SCREENWIDTH / 2);
		setyPosition(Frame.SCREENHEIGHT / 2);
		setWidth(50);
		setHeight(50);
		
		if(random.nextBoolean()) {
			setxDirection(Direction.LEFT);
			setyDirection(Direction.UP);
		} else {
			setxDirection(Direction.RIGHT);
			setyDirection(Direction.DOWN);
		}
	}
	public void move() {
		checkCollision();
		moveBall();
	}
	
	private void checkCollision() {
		if(getxPosition() >= Frame.SCREENWIDTH - getWidth() || getxPosition() <= 0) {
			setxSpeed(getxSpeed() * -1);
		}
		
		if(getyPosition() >= Frame.SCREENHEIGHT - getHeight() || getyPosition() <= 0) {
			setySpeed(getySpeed() * -1);
		}
		
	}
	private void moveBall() {
		if(getxDirection() == Direction.LEFT) {
			setxPosition(getxPosition() + getxSpeed()); 
		} else if(getxDirection() == Direction.RIGHT) {
			setxPosition(getxPosition() - getxSpeed()); 
		}
		
		if(getyDirection() == Direction.UP) {
			setyPosition(getyPosition() + getySpeed()); 
		} else if(getyDirection() == Direction.DOWN) {
			setyPosition(getyPosition() - getySpeed()); 
		}
		
		
	}
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Direction getyDirection() {
		return yDirection;
	}

	public void setyDirection(Direction yDirection) {
		this.yDirection = yDirection;
	}

	public Direction getxDirection() {
		return xDirection;
	}

	public void setxDirection(Direction xDirection) {
		this.xDirection = xDirection;
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}
