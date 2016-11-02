package org.com.xsx.UI.MainScene.DevicePage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DeviceThumnPane extends VBox{
	
	private ImageView deviceimage;
	private Label deviceid;
	
	public DeviceThumnPane(Image image, String id){
		deviceimage = new ImageView(image);
		deviceimage.setFitWidth(150);
		deviceimage.setFitHeight(123);
		deviceid = new Label(id);
		deviceid.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		
		this.setCursor(Cursor.HAND);
		this.setAlignment(Pos.CENTER);
		getChildren().addAll(deviceimage, deviceid);
		
		this.setPadding(new Insets(15, 15, 15, 15));
		this.setStyle("-fx-background-color:#D8EFF2");
	}
}
