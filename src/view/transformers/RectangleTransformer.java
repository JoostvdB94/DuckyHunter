package view.transformers;

import java.awt.Rectangle;
import java.awt.Shape;

import model.GameObject;

public class RectangleTransformer implements ShapeTransformer{

	
	@Override
	public Shape getShape(GameObject object) {
		return new Rectangle(object.getXPosition(), object.getYPosition(), object.getWidth(), object.getHeight());	
	}

	@Override
	public boolean collides(GameObject object, GameObject otherObject) {
		Rectangle gameObject = new Rectangle(object.getXPosition(), object.getYPosition(), object.getWidth(), object.getHeight());
		Rectangle otherGameObject = new Rectangle(otherObject.getXPosition(), otherObject.getYPosition(), otherObject.getWidth(), otherObject.getHeight());
		
		return gameObject.intersects(otherGameObject);
	}

}
