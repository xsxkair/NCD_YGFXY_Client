package org.com.xsx.UI.MainScene.WorkSpace;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Data.UIScence;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.UI.MainScene.Report.ReportOverViewPage.ReportOverViewPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class WorkSpacePage {

	private static WorkSpacePage S_WorkSpacePage = null;
	
	private AnchorPane rootpane;
	
	private WorkSpacePage() {
		
	}
	
	public static WorkSpacePage GetInstance() {
		if(S_WorkSpacePage == null)
			S_WorkSpacePage = new WorkSpacePage();
		
		return S_WorkSpacePage;
	}
	
	public void UI_Init() {
		AnchorPane root = null;
			
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("WorkSpacePage.fxml"));
		InputStream in = this.getClass().getResourceAsStream("WorkSpacePage.fxml");
		loader.setController(this);
		try {
			rootpane = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
					}

		//rootpane.getStylesheets().add(this.getClass().getResource("mainuistyle.css").toExternalForm());
	        
		UIMainPage.GetInstance().getGB_Page().addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				// TODO Auto-generated method stub
				if(newValue == rootpane){

				}
			}
		});
	}
	
	public AnchorPane GetPane() {
		return rootpane;
	}
}
