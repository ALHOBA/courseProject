package application;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Anim {
	public static <T extends Node>  void fadeIn(T node , double ms) {
		FadeTransition fadeIn = new FadeTransition(Duration.millis(ms), node);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.play();
		fadeIn.setCycleCount(1);
		node.setVisible(true);
	}
	public static <T extends Node>  void fadeOut(T node , double ms) {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(ms), node);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setCycleCount(1);
		fadeOut.play();
		fadeOut.setOnFinished(e -> (node).setVisible(false));
		
	}
}
