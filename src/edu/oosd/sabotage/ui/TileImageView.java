package edu.oosd.sabotage.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Extends ImageView with x and y positions
 */
public class TileImageView extends ImageView {

	private int xPos = 0;
	private int yPos = 0;
	
	/**
	 * Constructor specifying image and tile position
	 * @param image	The image
	 * @param xPos	The x position
	 * @param yPos	The y position
	 */
	public TileImageView(Image image, int xPos, int yPos) {
		super(image);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}
}
