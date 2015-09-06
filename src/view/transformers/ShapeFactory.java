package view.transformers;

import model.GameObject;
import model.*;

public class ShapeFactory {

	public static ShapeTransformer CreateShapeTransformer(GameObject.GameObjectType objectType) {
		switch (objectType) {
			case BALL: {
				return new RectangleTransformer();
			}
			default: {
				return null;
			}
		}
	}
}
