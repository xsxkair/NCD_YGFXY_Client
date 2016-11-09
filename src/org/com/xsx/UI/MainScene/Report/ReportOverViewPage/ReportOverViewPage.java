package org.com.xsx.UI.MainScene.Report.ReportOverViewPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class ReportOverViewPage {
	
	private static ReportOverViewPage S_ReportOverViewPage = null;
	
	private AnchorPane rootpane;
	
	@FXML
	PieChart GB_ReportPieChart;
	private ObservableList<PieChart.Data> GB_ReportPieChartData;
	
	@FXML
	PieChart GB_ItemPieChart;
	private ObservableList<PieChart.Data> GB_ItemPieChartData;
	
	@FXML
	PieChart GB_DevicePieChart;
	private ObservableList<PieChart.Data> GB_DevicePieChartData;
	
	@FXML
	TextField GB_YearTextField;
	@FXML
	ComboBox<Integer> GB_MonthComboBox;
	@FXML
	FlowPane GB_ItemFlowPane;
	@FXML
	FlowPane GB_DeviceFlowPane;
	
	@FXML
	BarChart<String, Number> GB_ReportDetailBarChart;
	@FXML
	CategoryAxis GB_ReportDetailBarChartX;
	@FXML
	NumberAxis GB_ReportDetailBarChartY;
	
	private ReportOverViewPage(){
		
	}
	
	public static ReportOverViewPage GetInstance() {
		if(S_ReportOverViewPage == null)
			S_ReportOverViewPage = new ReportOverViewPage();
		
		return S_ReportOverViewPage;
	}
	
	public void UI_Init(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ReportOverViewPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("ReportOverViewPage.fxml");
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
        
        GB_ReportPieChartData = FXCollections.observableArrayList();
        GB_ReportPieChart.setData(GB_ReportPieChartData);
        
        GB_ItemPieChartData = FXCollections.observableArrayList();
        GB_ItemPieChart.setData(GB_ItemPieChartData);
        
        GB_DevicePieChartData = FXCollections.observableArrayList();
        GB_DevicePieChart.setData(GB_DevicePieChartData);
        
        GB_MonthComboBox.getItems().addAll(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
        
        AnchorPane.setTopAnchor(rootpane, 0.0);
        AnchorPane.setBottomAnchor(rootpane, 0.0);
        AnchorPane.setLeftAnchor(rootpane, 0.0);
        AnchorPane.setRightAnchor(rootpane, 0.0);
	}
	
	public AnchorPane GetPane() {
		return rootpane;
	}
	
	private void UpPageValue() {
		UpReportPieChartValue();
		UpItemPieChartValue();
		UpDevicePieChartValue();
		UpDetailChartValue();
	}
	
	//更新今日报告饼图
	private void UpReportPieChartValue() {
		Map<String, Integer> data = ReportDao.QueryReportCountGroupByResult(new java.sql.Date(System.currentTimeMillis()));
		
		GB_ReportPieChartData.clear();
		Set<String> keyset = data.keySet();
		for (String string : keyset) {
			GB_ReportPieChartData.add(new Data(string, data.get(string)));
		}
	}
	
	//更新今日项目饼图
	private void UpItemPieChartValue() {
		
	}

	//更新今日设备饼图
	private void UpDevicePieChartValue() {
	
	}

	//更新总图
	private void UpDetailChartValue() {
		
		List<String> deviceidlist = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
		
		GB_DeviceFlowPane.getChildren().clear();
		for (String string : deviceidlist) {
			CheckBox checkBox = new CheckBox(string);
			GB_DeviceFlowPane.getChildren().add(checkBox);
		}
		
		List<String> itemlist = ReportDao.QueryAllItem(deviceidlist);
		GB_ItemFlowPane.getChildren().clear();
		for (String string : itemlist) {
			CheckBox checkBox2 = new CheckBox(string);
			GB_ItemFlowPane.getChildren().add(checkBox2);
		}
	}
}
