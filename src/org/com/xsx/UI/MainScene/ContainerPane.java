package org.com.xsx.UI.MainScene;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.Data.LoginUser;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Domain.ReportManagerBean;
import org.com.xsx.UI.AboutStage.AboutStage;
import org.com.xsx.UI.MainScene.DevicePage.DevicePage;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
		}
		
		return S_ContainerPane;
	}
	
	public void UI_Init() {
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
        
        LoginUser.GetInstance().getGB_ReportManagerBean().addListener(new ChangeListener<ReportManagerBean>() {

			@Override
			public void changed(ObservableValue<? extends ReportManagerBean> observable, ReportManagerBean oldValue,
					ReportManagerBean newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					UIMainPage.GetInstance().setGB_Page(ReportListPage.GetInstance().GetReportPane());
				}
			}
		});
        
        UIMainPage.GetInstance().getGB_Page().addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					S_ContainerPane.GB_RootPane.getChildren().clear();
					S_ContainerPane.GB_RootPane.getChildren().add(newValue);
				}	
			}
		});
	}
	
	public Scene GetScene(){
		return S_Scene;
	}
	
	@FXML
	public void ReportThumbAction(){

	}
	
	@FXML
	public void QueryReportAction(){
		UIMainPage.GetInstance().setGB_Page(ReportListPage.GetInstance().GetReportPane());
	}
	
	@FXML
	public void ShowDevicesAction(){
		UIMainPage.GetInstance().setGB_Page(DevicePage.GetInstance().GetPane());
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
