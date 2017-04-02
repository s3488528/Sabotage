package edu.oosd.sabotage.controllers;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public interface GameListener {
	void onTurnUpdate(String text);
	void onHandUpdate(ArrayList<ImageView> handImages);
	void onLogUpdate(String logAppendText);
	void onBoardUpdate(ImageView[][] boardImages);
}
