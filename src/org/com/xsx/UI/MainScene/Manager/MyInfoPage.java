package org.com.xsx.UI.MainScene.Manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MyInfoPage {
	
	private static MyInfoPage S_MyInfoPage = null;
	
	private AnchorPane rootpane;
	
	@FXML
	VBox GB_ChildPane;
	
	//我的信息
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
	@FXML
	Button GB_CancleMyModifyButton;
	
	//子账户列表
	@FXML
	VBox GB_ChildManagerListVBox;
	@FXML
	ListView<String> GB_ManagerListView;
	@FXML
	Label S_ManagerNameLabel;
	@FXML
	Label S_ManagerSexLabel;
	@FXML
	Label S_ManagerAgeLabel;
	@FXML
	Label S_ManagerPhoneLabel;
	@FXML
	Label S_ManagerJobLabel;
	@FXML
	Label S_ManagerDescLabel;
	
	//设备列表
	@FXML
	ListView<String> S_DeviceListView;
	@FXML
	Label S_DeviceIdLabel;
	@FXML
	Label S_DeviceNameLabel;
	@FXML
	Label S_DeviceAgeLabel;
	@FXML
	Label S_DeviceSexLabel;
	@FXML
	Label S_DevicePhoneLabel;
	@FXML
	Label S_DeviceJobLabel;
	@FXML
	Label S_DeviceDescLabel;
	@FXML
	Label S_DeviceAddrLabel;
	
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
        
        SignedManager.GetInstance().getGB_SignedManager().addListener(new ChangeListener<ManagerBean>() {

			@Override
			public void changed(ObservableValue<? extends ManagerBean> observable, ManagerBean oldValue, ManagerBean newValue) {
				// TODO Auto-generated method stub
				UpPageValue(newValue);
			}
		});
        
        AnchorPane.setTopAnchor(rootpane, 0.0);
        AnchorPane.setBottomAnchor(rootpane, 0.0);
        AnchorPane.setLeftAnchor(rootpane, 0.0);
        AnchorPane.setRightAnchor(rootpane, 0.0);
	}
	
	public AnchorPane GetPane() {
		return rootpane;
	}
	
	private void UpPageValue(ManagerBean managerinfo){
		if(managerinfo == null){
			GB_MyNameTextField.setText(null);
			GB_MySexTextField.setText(null);
			GB_MyAgeTextField.setText(null);
			GB_MyPhoneTextField.setText(null);
			GB_MyJobTextField.setText(null);
			GB_MyDescTextField.setText(null);
			GB_MyPasswordTextField.setText(null);
		}
		else{			
			GB_MyNameTextField.setText((managerinfo.getName() == null)?"null":managerinfo.getName().toString());
			GB_MySexTextField.setText((managerinfo.getSex() == null)?"null":managerinfo.getSex().toString());
			GB_MyAgeTextField.setText((managerinfo.getAge() == null)?"null":managerinfo.getAge().toString());
			GB_MyPhoneTextField.setText((managerinfo.getPhone() == null)?"null":managerinfo.getPhone().toString());
			GB_MyJobTextField.setText((managerinfo.getJob() == null)?"null":managerinfo.getJob().toString());
			GB_MyDescTextField.setText((managerinfo.getDsctext() == null)?"null":managerinfo.getDsctext().toString());
			GB_MyPasswordTextField.setText(managerinfo.getPassword());
			
			if(managerinfo.getFatheraccount() != null){
				if(GB_ChildPane.getChildren().contains(GB_ChildManagerListVBox) != true)
					GB_ChildPane.getChildren().add(GB_ChildManagerListVBox);
				
				List<String> managernamelist = ManagerDao.QueryChildManagerName(managerinfo.getAccount());
				
				GB_ManagerListView.setItems(FXCollections.observableArrayList(managernamelist));
			}
		}
	}
	
	@FXML
	public void GB_MyModifyAction(){
		
	}
	
	@FXML
	public void GB_CancleMyModifyAction(){
		
	}
}
