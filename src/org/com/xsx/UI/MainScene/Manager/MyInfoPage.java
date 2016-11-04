package org.com.xsx.UI.MainScene.Manager;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.UI.SystemInitScene.UI_SystemInit;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MyInfoPage {
	
	private static MyInfoPage S_MyInfoPage = null;
	
	private AnchorPane rootpane;
	
	@FXML
	TextField GB_MyNameTextField;
	@FXML
	TextField GB_MySexTextField;
	@FXML
	TextField GB_MyAgeTextField;
	@FXML
	TextField GB_MyPhoneTextField;
	@FXML
	TextField GB_MyJobTextField;
	@FXML
	TextField GB_MyDescTextField;
	@FXML
	PasswordField GB_MyPasswordTextField;
	@FXML
	Button GB_MyModifyButton;
	
	
	private MyInfoPage() {
		
	}
	
	public static MyInfoPage GetInstance() {
		if(S_MyInfoPage == null)
			S_MyInfoPage = new MyInfoPage();
		
		return S_MyInfoPage;
	}
	
	public void UI_Init(){

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("MyInfoPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("MyInfoPage.fxml");
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
	
	@FXML
	public void GB_MyModifyAction(){
		
	}
}
