package org.com.xsx.UI.MainScene.Manager;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ManagerManagementPage {

	private static ManagerManagementPage S_ManagerManagementPage = null;
	
	private AnchorPane rootpane;
	
	@FXML
	TextField GB_AdminNameTextField;
	@FXML
	TextField GB_AdminSexTextField;
	@FXML
	TextField GB_AdminAgeTextField;
	@FXML
	TextField GB_AdminPhoneTextField;
	@FXML
	TextField GB_AdminJobTextField;
	@FXML
	TextField GB_AdminDescTextField;
	@FXML
	PasswordField GB_AdminPasswordTextField;
	@FXML
	Button GB_AdminModifyButton;
	
	@FXML
	ListView<String> GB_ManagerListView;
	@FXML
	TextField GB_ManagerAccoutTextFiled;
	@FXML
	PasswordField GB_ManagerPasswordTextFiled;
	@FXML
	TextField GB_ManagerNameTextFiled;
	@FXML
	TextField GB_ManagerSexTextFiled;
	@FXML
	TextField GB_ManagerAgeTextFiled;
	@FXML
	TextField GB_ManagerPhoneTextFiled;
	@FXML
	TextField GB_ManagerJobTextFiled;
	@FXML
	TextField GB_ManagerDescTextFiled;
	
	@FXML
	ListView<String> GB_DeviceListView;
	@FXML
	Label GB_DeviceIdLabel;
	@FXML
	Label GB_DeviceNameLabel;
	@FXML
	Label GB_DeviceAgeLabel;
	@FXML
	Label GB_DeviceSexLabel;
	@FXML
	Label GB_DevicePhoneLabel;
	@FXML
	Label GB_DeviceJobLabel;
	@FXML
	Label GB_DeviceDescLabel;
	@FXML
	Label GB_DeviceAddrLabel;

	private ManagerManagementPage() {
		
	}
	
	public static ManagerManagementPage GetInstance() {
		if(S_ManagerManagementPage == null)
			S_ManagerManagementPage = new ManagerManagementPage();
		
		return S_ManagerManagementPage;
	}
	
	public void UI_Init(){

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ManagerManagementPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("ManagerManagementPage.fxml");
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
	public void GB_AdminModifyAction(){
		
	}
	
	@FXML
	public void GB_ModifyManagerAction(){
		
	}
	
	@FXML
	public void GB_AddNewManagerAction(){
		
	}
	
	@FXML
	public void GB_DeleteManagerAction(){
		
	}
	
	@FXML
	public void GB_DeleteDeviceAction(){
		
	}
}
