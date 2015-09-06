package model;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;

import view.Frame;
import view.transformers.ShapeFactory;
import view.transformers.ShapeTransformer;

public class Ball implements GameObject {
	

	private Random random;
	private int xPosition;
	private int yPosition;
	private int height;
	private int width;
	private int xSpeed;
	private int ySpeed;
	private Tuple<Direction, Direction> moveDirection;
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
		setXPosition(Frame.SCREENWIDTH / 2);
		setYPosition(Frame.SCREENHEIGHT / 2);
		setWidth(50);
		setHeight(50);
		
		if(random.nextBoolean()) {
			moveDirection = new Tuple<Direction, Direction>(Direction.LEFT, Direction.UP);
		} else {
			moveDirection = new Tuple<Direction, Direction>(Direction.RIGHT, Direction.DOWN);
		}
	}
	public void move() {
		checkCollision();
		moveBall();
	}
	
	public Tuple<Direction, Direction> getDirection() {
		return moveDirection;
	}
	
	private void checkCollision() {
		if(getXPosition() >= Frame.SCREENWIDTH - getWidth() || getXPosition() <= 0) {
			setxSpeed(getxSpeed() * -1);
		}
		
		if(getYPosition() >= Frame.SCREENHEIGHT - getHeight() || getYPosition() <= 0) {
			setySpeed(getySpeed() * -1);
		}
		
	}
	private void moveBall() {
		if(getDirection().x == Direction.LEFT) {
			setXPosition(getXPosition() + getxSpeed()); 
		} else if(getDirection().x == Direction.RIGHT) {
			setXPosition(getXPosition() - getxSpeed()); 
		}
		
		if(getDirection().y == Direction.UP) {
			setYPosition(getYPosition() + getySpeed()); 
		} else if(getDirection().y == Direction.DOWN) {
			setYPosition(getYPosition() - getySpeed()); 
		}
		
		
	}
	public int getXPosition() {
		return xPosition;
	}
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getYPosition() {
		return yPosition;
	}
	public void setYPosition(int yPosition) {
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
	@Override
	public boolean collidesWith(GameObject object) {
		ShapeTransformer rectangleTransformer = ShapeFactory.CreateShapeTransformer(getType());
		
		return rectangleTransformer.collides(this, object);
	}
	@Override
	public GameObjectType getType() {
		return GameObjectType.BALL;
	}
}
