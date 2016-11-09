package org.com.xsx.UI.MainScene.DevicePage;

import java.util.List;

import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;
import org.com.xsx.Domain.PersonBean;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DeviceTipInfo extends VBox{
	
	public DeviceTipInfo(DeviceTableItem deviceinfo){
		this.uiinit(deviceinfo);
	}
	
	private void uiinit(DeviceTableItem deviceinfo){
		
		DeviceBean deviceBean = deviceinfo.getDevicebean();
		DevicerBean devicerBean = deviceinfo.getDeviceperson();
		
		this.setAlignment(Pos.CENTER);
		
		Label label0 = new Label("设备ID: ");
		label0.setFont(new Font("System", 16));
		label0.setPadding(new Insets(0, 16, 0, 0));
		
		Label label1 = new Label(deviceBean.getId());
		label1.setFont(new Font("System", 14));
		
		HBox hBox1 = new HBox();
		hBox1.setAlignment(Pos.CENTER_LEFT);
		hBox1.setSpacing(10);
		hBox1.setStyle("-fx-border-color:#C2E4F3;-fx-border-width:1px");
		hBox1.getChildren().addAll(label0, label1);
		this.getChildren().add(hBox1);
		
		
		Label label2 = new Label("责任人: ");
		label2.setFont(new Font("System", 16));
		label2.setPadding(new Insets(0, 16, 0, 0));
		
		VBox vBox1 = new VBox();
		vBox1.setAlignment(Pos.CENTER_LEFT);
		vBox1.setSpacing(5);
		StringBuffer userinfo = new StringBuffer();
		
		if(devicerBean != null){
			userinfo.append(((devicerBean.getName() == null)?"无":devicerBean.getName())+"  ");
			userinfo.append(((devicerBean.getSex() == null)?"无":devicerBean.getName())+"  ");
			userinfo.append(((devicerBean.getAge() == null)?"无":devicerBean.getAge())+"  ");
		}
		else {
			userinfo.append("无");
		}
		Label label3 = new Label(userinfo.toString());
		label3.setFont(new Font("System", 14));
		
		Label label6 = new Label();
		label6.setFont(new Font("System", 14));
		if(devicerBean != null){
			label6.setText(((devicerBean.getJob() == null)?"无":devicerBean.getJob()));
		}
		else {
			label6.setText("无");
		}
		
		Label label7 = new Label();
		label7.setFont(new Font("System", 14));
		if(devicerBean != null){
			label7.setText(((devicerBean.getPhone() == null)?"无":devicerBean.getPhone()));
		}
		else {
			label7.setText("无");
		}
		
		Label label8 = new Label();
		label8.setFont(new Font("System", 14));
		
		if(devicerBean != null){
			label8.setText(((devicerBean.getDsc() == null)?"无":devicerBean.getDsc()));
		}
		else {
			label8.setText("无");
		}
	
		vBox1.getChildren().addAll(label3, label6, label7, label8);
		
		HBox hBox2 = new HBox();
		hBox2.setAlignment(Pos.CENTER_LEFT);
		hBox2.setSpacing(10);
		hBox2.setStyle("-fx-border-color:#C2E4F3;-fx-border-width:0px 1px 1px 1px");
		hBox2.getChildren().addAll(label2, vBox1);
		this.getChildren().add(hBox2);
		
		Label label9 = new Label("设备地址: ");
		label9.setFont(new Font("System", 16));
		Label label10 = new Label(deviceBean.getDaddr());
		label10.setFont(new Font("System", 14));
		
		HBox hBox3 = new HBox();
		hBox3.setAlignment(Pos.CENTER_LEFT);
		hBox3.setSpacing(10);
		hBox3.setStyle("-fx-border-color:#C2E4F3;-fx-border-width:0px 1px 1px 1px");
		hBox3.getChildren().addAll(label9, label10);
		this.getChildren().add(hBox3);
		
		Label label11 = new Label("设备状态: ");
		label11.setFont(new Font("System", 16));
		
		Label label12 = new Label(deviceinfo.getDevicestatus());
		label12.setFont(new Font("System", 14));
		
		if(deviceinfo.getDevicestatus().equals("正常"))
			label12.setTextFill(Color.web("#C8EC99"));
		else
			label12.setTextFill(Color.RED);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER_LEFT);
		hBox4.setSpacing(10);
		hBox4.setStyle("-fx-border-color:#C2E4F3;-fx-border-width:0px 1px 1px 1px");
		hBox4.getChildren().addAll(label11, label12);
		this.getChildren().add(hBox4);
	}

	public void setDeviceinfo(DeviceTableItem deviceinfo) {
		this.uiinit(deviceinfo);
	}
}
