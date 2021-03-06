package org.com.xsx.UI.LoginScene;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Data.UIScence;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.UI.MainScene.ContainerPane;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;
import org.com.xsx.UI.MainScene.Report.ReportOverViewPage.ReportOverViewPage;
import org.com.xsx.UI.MainScene.WorkSpace.WorkSpacePage;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LoginScene {
	
	private static LoginScene S_LoginScene = null;
	
	private Scene S_Scene = null;
	
	@FXML
	TextField UserNameText;
	
	@FXML
	PasswordField UserPasswordText;
	
	@FXML
	Button LoginButton;
	
	@FXML
	HBox LoginHBox;
	
	@FXML
	StackPane LoginStackPane;
	
	
	private LoginScene(){
		
	}
	
	public static LoginScene GetInstance(){
		if(S_LoginScene == null){
			S_LoginScene = new LoginScene();
		}

		return S_LoginScene;
	}
	
	public Boolean UI_Init() {
		AnchorPane root = null;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("LoginUI_FXML.fxml"));
        InputStream in = this.getClass().getResourceAsStream("LoginUI_FXML.fxml");
        loader.setController(this);
        try {
        	root = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

        S_Scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight()); 
        
		LoginButton.disableProperty().bind(new BooleanBinding() {
			{
				bind(UserNameText.textProperty());
				bind(UserPasswordText.textProperty());
			}
			@Override
			protected boolean computeValue() {
				// TODO Auto-generated method stub
				if((UserNameText.getText() != null)&&(UserNameText.getText().length() > 0)
					&&(UserPasswordText.getText() != null)&&(UserPasswordText.getText().length() > 0))
					return false;
				else
					return true;
			}
		});
		
		UIScence.GetInstance().getGB_Scene().addListener(new ChangeListener<Scene>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				// TODO Auto-generated method stub
				if(newValue == S_Scene){
					UserNameText.setText(null);
					UserPasswordText.setText(null);
				}
			}
		});
        
        return true;
	}
	

	public Scene getS_Scene() {
		return S_Scene;
	}
	
	@FXML
	public void LoginAction(ActionEvent e){
		
		ManagerBean tempuser = ManagerDao.QueryReportManager(UserNameText.getText(), UserPasswordText.getText());

		if(tempuser != null){
			SignedManager.GetInstance().setGB_SignedAccount(tempuser.getAccount());
			UIMainPage.GetInstance().setGB_Page(WorkSpacePage.GetInstance().GetPane());
			UIScence.GetInstance().getGB_Scene().set(ContainerPane.GetInstance().GetScene());
		}
		else {
			ButtonType loginButtonType = new ButtonType("ȷ��", ButtonData.OK_DONE);
			Dialog<String> dialog = new Dialog<>();
			dialog.getDialogPane().setContentText("��½ʧ�ܣ�");
			dialog.getDialogPane().getButtonTypes().add(loginButtonType);
			dialog.showAndWait();
		}
	}
	
	@FXML
	public void GB_LoginKeyEvent(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			
			if((UserNameText.getText().length() > 0)&&(UserPasswordText.getText().length() > 0)){
					
				ManagerBean tempuser = ManagerDao.QueryReportManager(UserNameText.getText(), UserPasswordText.getText());
				
				if(tempuser != null){
					SignedManager.GetInstance().setGB_SignedAccount(tempuser.getAccount());
					UIMainPage.GetInstance().setGB_Page(WorkSpacePage.GetInstance().GetPane());
					UIScence.GetInstance().getGB_Scene().set(ContainerPane.GetInstance().GetScene());
				}
				else {
					ButtonType loginButtonType = new ButtonType("ȷ��", ButtonData.OK_DONE);
					Dialog<String> dialog = new Dialog<>();
					dialog.getDialogPane().setContentText("��½ʧ�ܣ�");
					dialog.getDialogPane().getButtonTypes().add(loginButtonType);
					dialog.showAndWait();
				}
			}
			else{
				ButtonType loginButtonType = new ButtonType("ȷ��", ButtonData.OK_DONE);
				Dialog<String> dialog = new Dialog<>();
				dialog.getDialogPane().setContentText("��������д�˺ź����룡");
				dialog.getDialogPane().getButtonTypes().add(loginButtonType);
				dialog.showAndWait();
			}
		}
	}
}
