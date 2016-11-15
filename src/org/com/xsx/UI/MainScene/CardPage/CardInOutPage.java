package org.com.xsx.UI.MainScene.CardPage;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class CardInOutPage {
	
	private static CardInOutPage S_CardInOutPage = null;
	
	private AnchorPane rootpane;
	
	private CardInOutPage() {
		
	}
	
	public static CardInOutPage GetInstance() {
		if(S_CardInOutPage == null)
			S_CardInOutPage = new CardInOutPage();
		
		return S_CardInOutPage;
	}
	
	public void UI_Init() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("CardInOutPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("CardInOutPage.fxml");
        loader.setController(this);
        try {
        	rootpane = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        AnchorPane.setTopAnchor(rootpane, 0.0);
        AnchorPane.setBottomAnchor(rootpane, 0.0);
        AnchorPane.setLeftAnchor(rootpane, 0.0);
        AnchorPane.setRightAnchor(rootpane, 0.0);
	}
	
	public AnchorPane GetPane() {
		return rootpane;
	}
}
