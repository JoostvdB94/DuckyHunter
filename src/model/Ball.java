package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

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
	private int grassHeight = 175;
	private BufferedImage[] duckImg = new BufferedImage[2];
	private int imageIndex = 0;
	private int fpsCorrect = 0;
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
		setXPosition(random.nextInt(Frame.SCREENWIDTH));
		setYPosition(random.nextInt(Frame.SCREENHEIGHT - grassHeight));
		setWidth(100);
		setHeight(100);
		
		if(random.nextBoolean()) {
			moveDirection = new Tuple<Direction, Direction>(Direction.LEFT, Direction.UP);
		} else {
			moveDirection = new Tuple<Direction, Direction>(Direction.RIGHT, Direction.DOWN);
		}
		
		try {
			duckImg[0] = ImageIO.read(new File("src/view/assets/duck0.png"));
			duckImg[1] = ImageIO.read(new File("src/view/assets/duck1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		if(fpsCorrect > 30){
			imageIndex = Math.abs(Math.abs(imageIndex) -1); 
			fpsCorrect = 0;
		}
		g.drawImage(duckImg[imageIndex],getXPosition(), getYPosition(), getWidth(),
			getHeight(),null);		
		fpsCorrect ++;
	}
	
	public void move() {
		checkCollision();
		moveBall();
	}
	
	public void changeImageDirection(){
		// Flip the image horizontally
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-duckImg[imageIndex].getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		duckImg[0] = op.filter(duckImg[0], null);
		duckImg[1] = op.filter(duckImg[1], null);
	}
	
	public Tuple<Direction, Direction> getDirection() {
		return moveDirection;
	}
	
	private void checkCollision() {
		if(getXPosition() >= Frame.SCREENWIDTH - getWidth() || getXPosition() <= 0) {
			setxSpeed(getxSpeed() * -1);
			changeImageDirection();
		}
		
		if(getYPosition() >= Frame.SCREENHEIGHT - getHeight() -grassHeight || getYPosition() <= 0) {
			setySpeed(getySpeed() * -1);
		}
		
	}
	private void moveBall() {
//		if(getDirection().x == Direction.LEFT) {
			setXPosition(getXPosition() + getxSpeed()); 
//		} else if(getDirection().x == Direction.RIGHT) {
//			setXPosition(getXPosition() - getxSpeed()); 
//		}
		
//		if(getDirection().y == Direction.UP) {
			setYPosition(getYPosition() + getySpeed()); 
//		} else if(getDirection().y == Direction.DOWN) {
//			setYPosition(getYPosition() - getySpeed()); 
//		}
		
		
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
