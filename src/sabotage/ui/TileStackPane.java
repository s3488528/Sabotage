package sabotage.ui;

import javafx.scene.layout.StackPane;

/**
 * Extends StackPane with x and y positions
 */
public class TileStackPane extends StackPane {

	private int xPos = 0;
	private int yPos = 0;
	
	/**
	 * Constructor specifying image and tile position
	 * @param xPos	The x position
	 * @param yPos	The y position
	 */
	public TileStackPane(int xPos, int yPos) {
		super();
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
