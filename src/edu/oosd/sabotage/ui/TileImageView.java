package edu.oosd.sabotage.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TileImageView extends ImageView {

	private int xPos = 0;
	private int yPos = 0;
	
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
