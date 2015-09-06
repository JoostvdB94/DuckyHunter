package view.transformers;

import java.awt.*;

import model.GameObject;
public interface ShapeTransformer {

	 Shape getShape(GameObject object);
	 boolean collides(GameObject object, GameObject otherObject);
}
