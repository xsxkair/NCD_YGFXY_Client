package org.com.xsx.UI.MainScene;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.UI.AboutStage.AboutStage;
import org.com.xsx.UI.MainScene.DevicePage.DevicePage;
import org.com.xsx.UI.MainScene.Manager.ManagerManagementPage;
import org.com.xsx.UI.MainScene.Manager.MyInfoPage;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;
import org.com.xsx.UI.MainScene.Report.ReportOverViewPage.ReportOverViewPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
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
		UIMainPage.GetInstance().setGB_Page(ReportOverViewPage.GetInstance().GetPane());
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
	public void ManagerManagementAction(){
		
		if(UIMainPage.GetInstance().getGB_Page().get().equals(ManagerManagementPage.GetInstance().GetPane()))
			return;
		
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
		
		//如果有父账号，说明无权限
		if(admin.getFatheraccount() != null){
			Alert alert = new Alert(AlertType.ERROR, "禁止操作  Access denied!", ButtonType.OK);
			alert.initOwner(S_Scene.getWindow());
			alert.showAndWait();
		}
		else {
			TextInputDialog inputDialog = new TextInputDialog("input admin password");
			inputDialog.initOwner(S_Scene.getWindow());
			Optional<String> result = inputDialog.showAndWait();
			
			if(result.isPresent()){
				if(result.get().equals(admin.getPassword()))
					UIMainPage.GetInstance().setGB_Page(ManagerManagementPage.GetInstance().GetPane());
				else {
					Alert alert = new Alert(AlertType.ERROR, "禁止操作  Access denied!", ButtonType.OK);
					alert.initOwner(S_Scene.getWindow());
					alert.showAndWait();
				}
			}
		}
	}
	
	@FXML
	public void ShowMyInfoAction(){
		UIMainPage.GetInstance().setGB_Page(MyInfoPage.GetInstance().GetPane());
		
	}
	
	@FXML
	public void AboutMeAction(){
		AboutStage.GetInstance().ShowAbout();
	}
}
