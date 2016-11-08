package org.com.xsx.UI.MainScene.Manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class MyInfoPage {
	
	private static MyInfoPage S_MyInfoPage = null;
	
	private AnchorPane rootpane;
	
	private ManagerBean S_Manager = null;
	
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
	ListView<ManagerListViewItem> GB_ManagerListView;
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
					S_ManagerNameLabel.setText(null);
					S_ManagerAgeLabel.setText(null);
					S_ManagerSexLabel.setText(null);
					S_ManagerPhoneLabel.setText(null);
					S_ManagerJobLabel.setText(null);
					S_ManagerDescLabel.setText(null);
				}
				else {
					ManagerBean tBean = ManagerDao.QueryReportManager(newValue.GetAccount(), null);
					
					if(tBean == null){
						S_ManagerNameLabel.setText(null);
						S_ManagerAgeLabel.setText(null);
						S_ManagerSexLabel.setText(null);
						S_ManagerPhoneLabel.setText(null);
						S_ManagerJobLabel.setText(null);
						S_ManagerDescLabel.setText(null);
					}
					else {
						
						S_ManagerNameLabel.setText((tBean.getName()==null)?"null":tBean.getName().toString());
						S_ManagerAgeLabel.setText((tBean.getAge()==null)?"null":tBean.getAge().toString());
						S_ManagerSexLabel.setText((tBean.getSex()==null)?"null":tBean.getSex().toString());
						S_ManagerPhoneLabel.setText((tBean.getPhone()==null)?"null":tBean.getPhone().toString());
						S_ManagerJobLabel.setText((tBean.getJob()==null)?"null":tBean.getJob().toString());
						S_ManagerDescLabel.setText((tBean.getDsctext()==null)?"null":tBean.getDsctext().toString());
					}
				}
			}
		});
        
        S_DeviceListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(newValue == null){
					S_DeviceIdLabel.setText(null);
					S_DeviceNameLabel.setText(null);
					S_DeviceAgeLabel.setText(null);
					S_DeviceSexLabel.setText(null);
					S_DevicePhoneLabel.setText(null);
					S_DeviceJobLabel.setText(null);
					S_DeviceDescLabel.setText(null);
					S_DeviceAddrLabel.setText(null);
				}
				else {
					DeviceDataPackage deviceinfo = DeviceInfoDao.QueryDevice(newValue);

					DeviceBean deviceBean = deviceinfo.getDeviceBean();
					DevicerBean personBean = deviceinfo.getDevicerBean();
					
					if(personBean == null){
						S_DeviceNameLabel.setText("null");
						S_DeviceAgeLabel.setText("null");
						S_DeviceSexLabel.setText("null");
						S_DevicePhoneLabel.setText("null");
						S_DeviceJobLabel.setText("null");
						S_DeviceDescLabel.setText("null");
					}
					else {
						S_DeviceNameLabel.setText((personBean.getName()==null)?"null":personBean.getName().toString());
						S_DeviceAgeLabel.setText((personBean.getAge()==null)?"null":personBean.getAge().toString());
						S_DeviceSexLabel.setText((personBean.getSex()==null)?"null":personBean.getSex().toString());
						S_DevicePhoneLabel.setText((personBean.getPhone()==null)?"null":personBean.getPhone().toString());
						S_DeviceJobLabel.setText((personBean.getJob()==null)?"null":personBean.getJob().toString());
						S_DeviceDescLabel.setText((personBean.getDsc()==null)?"null":personBean.getDsc().toString());
					}
					
					if(deviceBean == null){
						S_DeviceIdLabel.setText("null");
						S_DeviceAddrLabel.setText("null");
					}
					else {
						S_DeviceIdLabel.setText((deviceBean.getId()==null)?"null":deviceBean.getId().toString());
						S_DeviceAddrLabel.setText((deviceBean.getDaddr()==null)?"null":deviceBean.getDaddr().toString());
					}
				}
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
	
	private void UpPageValue(){
		
		S_Manager = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
		
		if(S_Manager == null){
			GB_MyNameTextField.setText(null);
			GB_MySexTextField.setText(null);
			GB_MyAgeTextField.setText(null);
			GB_MyPhoneTextField.setText(null);
			GB_MyJobTextField.setText(null);
			GB_MyDescTextField.setText(null);
			GB_MyPasswordTextField.setText(null);
		}
		else{	
			//我的信息
			GB_MyNameTextField.setText((S_Manager.getName() == null)?"null":S_Manager.getName().toString());
			GB_MySexTextField.setText((S_Manager.getSex() == null)?"null":S_Manager.getSex().toString());
			GB_MyAgeTextField.setText((S_Manager.getAge() == null)?"null":S_Manager.getAge().toString());
			GB_MyPhoneTextField.setText((S_Manager.getPhone() == null)?"null":S_Manager.getPhone().toString());
			GB_MyJobTextField.setText((S_Manager.getJob() == null)?"null":S_Manager.getJob().toString());
			GB_MyDescTextField.setText((S_Manager.getDsctext() == null)?"null":S_Manager.getDsctext().toString());
			GB_MyPasswordTextField.setText(S_Manager.getPassword());
			
			//子审核人信息
			
			if(S_Manager.getFatheraccount() != null){
				if(GB_ChildPane.getChildren().contains(GB_ChildManagerListVBox))
					GB_ChildPane.getChildren().remove(GB_ChildManagerListVBox);
			}
			else{
				if(GB_ChildPane.getChildren().contains(GB_ChildManagerListVBox) != true)
					GB_ChildPane.getChildren().add(GB_ChildManagerListVBox);
				
				List<Object[]> managernamelist = ManagerDao.QueryChildManagerNameList(S_Manager.getAccount());
				
				List<ManagerListViewItem> items = new ArrayList<>();
				for (Object[] namelist : managernamelist) {
					items.add(new ManagerListViewItem(namelist));
				}
				
				GB_ManagerListView.setItems(FXCollections.observableArrayList(items));
			}
			
			//设备列表
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(S_Manager.getDevicelist());
			S_DeviceListView.setItems(FXCollections.observableArrayList((List<String>) JSONSerializer.toJava(jsonArray)));
		}
	}
	
	@FXML
	public void GB_MyModifyAction(){

		S_Manager.setPassword(GB_MyPasswordTextField.getText());
		S_Manager.setName(GB_MyNameTextField.getText());
		S_Manager.setAge(GB_MyAgeTextField.getText());
		S_Manager.setSex(GB_MySexTextField.getText());
		S_Manager.setPhone(GB_MyPhoneTextField.getText());
		S_Manager.setJob(GB_MyJobTextField.getText());
		S_Manager.setDsctext(GB_MyDescTextField.getText());
		
		if(ManagerDao.ModifyManagerInfo(S_Manager)){
			//成功
			UpPageValue();
		}
		else {
			//失败
		}
		
	}
	
	@FXML
	public void GB_CancleMyModifyAction(){
		
		UpPageValue();
	}
}
