package org.com.xsx.UI.MainScene.Manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.com.xsx.Dao.DeviceInfoDao;
import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.UI.MainScene.DevicePage.DeviceDataPackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
	ListView<ManagerListViewItem> GB_ManagerListView;
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
	@FXML
	Button GB_DeleteDeviceButton;

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
        
        UIMainPage.GetInstance().getGB_Page().addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				// TODO Auto-generated method stub
				if(rootpane.equals(newValue)){
					UpPageValue();
				}
			}
		});
        
        GB_ManagerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ManagerListViewItem>() {

			@Override
			public void changed(ObservableValue<? extends ManagerListViewItem> observable, ManagerListViewItem oldValue, ManagerListViewItem newValue) {
				// TODO Auto-generated method stub
				if(newValue == null){
					GB_ManagerAccoutTextFiled.setText(null);
					GB_ManagerPasswordTextFiled.setText(null);
					GB_ManagerNameTextFiled.setText(null);
					GB_ManagerSexTextFiled.setText(null);
					GB_ManagerAgeTextFiled.setText(null);
					GB_ManagerPhoneTextFiled.setText(null);
					GB_ManagerJobTextFiled.setText(null);
					GB_ManagerDescTextFiled.setText(null);
				}
				else {
					ManagerBean tBean = ManagerDao.QueryReportManager(newValue.GetAccount(), null);
					
					if(tBean == null){
						GB_ManagerAccoutTextFiled.setText(null);
						GB_ManagerPasswordTextFiled.setText(null);
						GB_ManagerNameTextFiled.setText(null);
						GB_ManagerSexTextFiled.setText(null);
						GB_ManagerAgeTextFiled.setText(null);
						GB_ManagerPhoneTextFiled.setText(null);
						GB_ManagerJobTextFiled.setText(null);
						GB_ManagerDescTextFiled.setText(null);
					}
					else {
						GB_ManagerAccoutTextFiled.setText((tBean.getAccount()==null)?"null":tBean.getAccount().toString());
						GB_ManagerPasswordTextFiled.setText((tBean.getPassword()==null)?"null":tBean.getPassword().toString());
						GB_ManagerNameTextFiled.setText((tBean.getName()==null)?"null":tBean.getName().toString());
						GB_ManagerAgeTextFiled.setText((tBean.getAge()==null)?"null":tBean.getAge().toString());
						GB_ManagerSexTextFiled.setText((tBean.getSex()==null)?"null":tBean.getSex().toString());
						GB_ManagerPhoneTextFiled.setText((tBean.getPhone()==null)?"null":tBean.getPhone().toString());
						GB_ManagerJobTextFiled.setText((tBean.getJob()==null)?"null":tBean.getJob().toString());
						GB_ManagerDescTextFiled.setText((tBean.getDsctext()==null)?"null":tBean.getDsctext().toString());
					}
				}
			}
		});
        
        GB_DeviceListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(newValue == null){
					GB_DeviceIdLabel.setText(null);
					GB_DeviceNameLabel.setText(null);
					GB_DeviceAgeLabel.setText(null);
					GB_DeviceSexLabel.setText(null);
					GB_DevicePhoneLabel.setText(null);
					GB_DeviceJobLabel.setText(null);
					GB_DeviceDescLabel.setText(null);
					GB_DeviceAddrLabel.setText(null);
				}
				else {
					DeviceDataPackage deviceinfo = DeviceInfoDao.QueryDevice(newValue);

					DeviceBean deviceBean = deviceinfo.getDeviceBean();
					DevicerBean personBean = deviceinfo.getDevicerBean();
					
					if(personBean == null){
						GB_DeviceNameLabel.setText("null");
						GB_DeviceAgeLabel.setText("null");
						GB_DeviceSexLabel.setText("null");
						GB_DevicePhoneLabel.setText("null");
						GB_DeviceJobLabel.setText("null");
						GB_DeviceDescLabel.setText("null");
					}
					else {
						GB_DeviceNameLabel.setText((personBean.getName()==null)?"null":personBean.getName().toString());
						GB_DeviceAgeLabel.setText((personBean.getAge()==null)?"null":personBean.getAge().toString());
						GB_DeviceSexLabel.setText((personBean.getSex()==null)?"null":personBean.getSex().toString());
						GB_DevicePhoneLabel.setText((personBean.getPhone()==null)?"null":personBean.getPhone().toString());
						GB_DeviceJobLabel.setText((personBean.getJob()==null)?"null":personBean.getJob().toString());
						GB_DeviceDescLabel.setText((personBean.getDsc()==null)?"null":personBean.getDsc().toString());
					}
					
					if(deviceBean == null){
						GB_DeviceIdLabel.setText("null");
						GB_DeviceAddrLabel.setText("null");
					}
					else {
						GB_DeviceIdLabel.setText((deviceBean.getId()==null)?"null":deviceBean.getId().toString());
						GB_DeviceAddrLabel.setText((deviceBean.getDaddr()==null)?"null":deviceBean.getDaddr().toString());
					}
				}
			}
		});
        
        GB_DeleteDeviceButton.disableProperty().bind(GB_DeviceListView.getSelectionModel().selectedIndexProperty().isEqualTo(-1));
        
        AnchorPane.setTopAnchor(rootpane, 0.0);
        AnchorPane.setBottomAnchor(rootpane, 0.0);
        AnchorPane.setLeftAnchor(rootpane, 0.0);
        AnchorPane.setRightAnchor(rootpane, 0.0);
	}
	
	public AnchorPane GetPane() {
		return rootpane;
	}
	
	private void UpPageValue() {
		UpAdminValue();
		UpChildManagerValue();
		UpDeviceValue();
	}
	
	private void UpAdminValue() {
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
		
		GB_AdminNameTextField.setText(admin.getName());
		GB_AdminSexTextField.setText(admin.getSex());
		GB_AdminAgeTextField.setText(admin.getAge());
		GB_AdminPhoneTextField.setText(admin.getPhone());
		GB_AdminJobTextField.setText(admin.getJob());
		GB_AdminDescTextField.setText(admin.getDsctext());
		GB_AdminPasswordTextField.setText(admin.getPassword());
	}
	
	private void UpChildManagerValue() {
		List<Object[]> managernamelist = ManagerDao.QueryChildManagerNameList(SignedManager.GetInstance().getGB_SignedAccount());
		
		List<ManagerListViewItem> items = new ArrayList<>();
		for (Object[] namelist : managernamelist) {
			items.add(new ManagerListViewItem(namelist));
		}
		
		GB_ManagerListView.getItems().clear();
		GB_ManagerListView.setItems(FXCollections.observableArrayList(items));
	}

	private void UpDeviceValue() {
		GB_DeviceListView.getItems().clear();
		GB_DeviceListView.setItems(FXCollections.observableArrayList(ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount())));
		
	}
	
	@FXML
	public void GB_AdminModifyAction(){
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
		
		admin.setPassword(GB_AdminPasswordTextField.getText());
		admin.setName(GB_AdminNameTextField.getText());
		admin.setAge(GB_AdminAgeTextField.getText());
		admin.setSex(GB_AdminSexTextField.getText());
		admin.setPhone(GB_AdminPhoneTextField.getText());
		admin.setJob(GB_AdminJobTextField.getText());
		admin.setDsctext(GB_AdminDescTextField.getText());
		
		if(ManagerDao.ModifyManagerInfo(admin)){
			//成功

		}
		else {
			//失败
		}
	}
	
	@FXML
	public void GB_RefreshManagerAction(){
		UpAdminValue();
	}
	
	@FXML
	public void GB_ModifyManagerAction(){
		
		if((GB_ManagerAccoutTextFiled.getText() != null) && (GB_ManagerAccoutTextFiled.getText().length() > 0) 
			&& (GB_ManagerPasswordTextFiled.getText() != null) && (GB_ManagerPasswordTextFiled.getText().length() > 0) ){
			
			//获取选中的审核人
			ManagerBean manager = ManagerDao.QueryReportManager(GB_ManagerListView.getSelectionModel().getSelectedItem().GetAccount(), null);
			
			if(manager == null){
				manager = new ManagerBean();
			}
			
			manager.setAccount(GB_ManagerAccoutTextFiled.getText());
			manager.setPassword(GB_ManagerPasswordTextFiled.getText());
			manager.setName(GB_ManagerNameTextFiled.getText());
			manager.setAge(GB_ManagerAgeTextFiled.getText());
			manager.setSex(GB_ManagerSexTextFiled.getText());
			manager.setPhone(GB_ManagerPhoneTextFiled.getText());
			manager.setJob(GB_ManagerJobTextFiled.getText());
			manager.setDsctext(GB_ManagerDescTextFiled.getText());
			
			if(ManagerDao.ModifyManagerInfo(manager)){
				//成功

			}
			else {
				//失败
			}
			
			UpChildManagerValue();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "数据不完整!", ButtonType.OK);
			alert.initOwner(rootpane.getScene().getWindow());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void GB_AddNewManagerAction(){
		
	}
	
	@FXML
	public void GB_DeleteManagerAction(){
		ManagerBean manager = ManagerDao.QueryReportManager(GB_ManagerListView.getSelectionModel().getSelectedItem().GetAccount(), null);
		
		if(manager != null){
			ManagerDao.DeleteManager(manager);
			
			UpChildManagerValue();
		}
	}
	
	@FXML
	public void GB_DeleteDeviceAction(){
		
		DeviceInfoDao.DeleteDevice(GB_DeviceListView.getSelectionModel().getSelectedItem());
		
		UpDeviceValue();
	}
}
