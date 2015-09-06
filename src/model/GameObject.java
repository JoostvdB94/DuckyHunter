package model;

public interface GameObject {

	public enum GameObjectType {
		BALL,
		NONE
	}
	GameObjectType getType();
	int getXPosition();
	int getYPosition();
	void setXPosition(int xPos);
	void setYPosition(int yPos);
	int getWidth();
	void setWidth(int width);
	int getHeight();
	void setHeight(int height);
	boolean collidesWith(GameObject object);
}
