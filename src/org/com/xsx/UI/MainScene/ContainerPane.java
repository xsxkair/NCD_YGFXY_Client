package org.com.xsx.UI.MainScene;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.UI.AboutStage.AboutStage;
import org.com.xsx.UI.MainScene.DevicePage.DevicePage;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ContainerPane {
	
	private static ContainerPane S_ContainerPane = null;
	
	private Scene S_Scene = null;
	
	@FXML
	AnchorPane GB_RootPane;
	
	private ContainerPane(){
		
	}
	
	public static ContainerPane GetInstance(){
		if(S_ContainerPane == null){
			S_ContainerPane = new ContainerPane();
			S_ContainerPane.UI_Init();
			
			S_ContainerPane.GB_RootPane.getChildren().clear();
			S_ContainerPane.GB_RootPane.getChildren().add(ReportListPage.GetInstance().GetReportPane());
		}
		
		return S_ContainerPane;
	}
	
	private void UI_Init() {
		AnchorPane root = null;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ContainerPane.fxml"));
        InputStream in = this.getClass().getResourceAsStream("ContainerPane.fxml");
        loader.setController(this);
        try {
        	root = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        S_Scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight()); 
	}
	
	public Scene GetScene(){
		return S_Scene;
	}
	
	@FXML
	public void ReportThumbAction(){

	}
	
	@FXML
	public void QueryReportAction(){
		GB_RootPane.getChildren().clear();
		GB_RootPane.getChildren().add(ReportListPage.GetInstance().GetReportPane());
	}
	
	@FXML
	public void ShowDevicesAction(){
		GB_RootPane.getChildren().clear();
		GB_RootPane.getChildren().add(DevicePage.GetInstance().GetPane());
	}
	
	@FXML
	public void ShowInOutRecordAction(){
		
	}
	
	@FXML
	public void InCardAction(){
		
	}
	
	@FXML
	public void OutCardAction(){
		
	}
	
	@FXML
	public void ShowMyInfoAction(){
		
	}
	
	@FXML
	public void AboutMeAction(){
		AboutStage.GetInstance().ShowAbout();
	}
}
