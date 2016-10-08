package org.com.xsx.UI.LoginScene;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.Dao.ReportManageDao;
import org.com.xsx.Data.LoginUser;
import org.com.xsx.Data.UIScence;
import org.com.xsx.Domain.ReportManagerBean;
import org.com.xsx.UI.MainScene.ContainerPane;

import javafx.beans.binding.BooleanBinding;
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
			S_LoginScene.UI_Init();
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
				if((UserNameText.getText().length() > 0)&&(UserPasswordText.getText().length() > 0))
					return false;
				else
					return true;
			}
		});
		
		
        
        return true;
	}
	

	public Scene getS_Scene() {
		return S_Scene;
	}
	
	@FXML
	public void LoginAction(ActionEvent e){
		
		ReportManagerBean tempuser = ReportManageDao.QueryReportManager(UserNameText.getText(), UserPasswordText.getText());
		
		if(tempuser != null){
			LoginUser.GetInstance().setGB_ReportManagerBean(tempuser);
			UIScence.GetInstance().getGB_Scene().set(ContainerPane.GetInstance().GetScene());
		}
		else {
			ButtonType loginButtonType = new ButtonType("确定", ButtonData.OK_DONE);
			Dialog<String> dialog = new Dialog<>();
			dialog.getDialogPane().setContentText("登陆失败！");
			dialog.getDialogPane().getButtonTypes().add(loginButtonType);
			dialog.showAndWait();
		}
	}
	
	@FXML
	public void GB_LoginKeyEvent(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			
			if((UserNameText.getText().length() > 0)&&(UserPasswordText.getText().length() > 0)){
					
				ReportManagerBean tempuser = ReportManageDao.QueryReportManager(UserNameText.getText(), UserPasswordText.getText());
				
				if(tempuser != null){
					LoginUser.GetInstance().setGB_ReportManagerBean(tempuser);
					UIScence.GetInstance().getGB_Scene().set(ContainerPane.GetInstance().GetScene());
				}
				else {
					ButtonType loginButtonType = new ButtonType("确定", ButtonData.OK_DONE);
					Dialog<String> dialog = new Dialog<>();
					dialog.getDialogPane().setContentText("登陆失败！");
					dialog.getDialogPane().getButtonTypes().add(loginButtonType);
					dialog.showAndWait();
				}
			}
			else{
				ButtonType loginButtonType = new ButtonType("确定", ButtonData.OK_DONE);
				Dialog<String> dialog = new Dialog<>();
				dialog.getDialogPane().setContentText("请完整填写账号和密码！");
				dialog.getDialogPane().getButtonTypes().add(loginButtonType);
				dialog.showAndWait();
			}
		}
	}
}
