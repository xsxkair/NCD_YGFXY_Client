package org.com.xsx.UI.MainScene.Report.ReportOverViewPage;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
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
	private QueryReportService S_QueryByResultService;
	
	@FXML
	PieChart GB_ItemPieChart;
	private ObservableList<PieChart.Data> GB_ItemPieChartData;
	private QueryReportService S_QueryByItemService;
	
	@FXML
	PieChart GB_DevicePieChart;
	private ObservableList<PieChart.Data> GB_DevicePieChartData;
	private QueryReportService S_QueryByDeviceService;
	
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
	private QueryReportService S_QueryDetailService;
	
	private Integer S_FilterYear = null;
	private Integer S_FilterMonth = null;
	private List<String> S_FilterItemList = new ArrayList<>();
	private List<String> S_FilterDeviceList = new ArrayList<>();
	
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
					
					//清除条件
					S_FilterYear = null;
					S_FilterMonth = null;
					S_FilterItemList.clear();
					S_FilterDeviceList.clear();
					
					UpDetailFilterUI();
					
					S_QueryByResultService.restart();
					S_QueryByItemService.restart();
					S_QueryByDeviceService.restart();
					
					S_QueryDetailService.setParm(new Object[]{S_FilterYear, S_FilterMonth, S_FilterItemList, S_FilterDeviceList});
					S_QueryDetailService.restart();
				}
			}
		});
        
        //今日报告审核饼图
        GB_ReportPieChartData = FXCollections.observableArrayList();
        GB_ReportPieChart.setData(GB_ReportPieChartData);
        S_QueryByResultService = new QueryReportService("QueryResultCount");
        S_QueryByResultService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					Map<String, Integer> data = (Map<String, Integer>) newValue;
					
					GB_ReportPieChartData.clear();
					Set<String> keyset = data.keySet();
					for (String string : keyset) {
						GB_ReportPieChartData.add(new Data(string, data.get(string)));
					}
				}
			}
		});
        
        //今日测试项目情况饼图
        GB_ItemPieChartData = FXCollections.observableArrayList();
        GB_ItemPieChart.setData(GB_ItemPieChartData);
        S_QueryByItemService = new QueryReportService("QueryItemCount");
        S_QueryByItemService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					Map<String, Integer> data = (Map<String, Integer>) newValue;

					GB_ItemPieChartData.clear();
					Set<String> keyset = data.keySet();
					for (String string : keyset) {
						GB_ItemPieChartData.add(new Data(string, data.get(string)));
					}
				}
			}
		});
        
        //今日设备使用情况饼图
        GB_DevicePieChartData = FXCollections.observableArrayList();
        GB_DevicePieChart.setData(GB_DevicePieChartData);
        S_QueryByDeviceService = new QueryReportService("QueryDeviceCount");
        S_QueryByDeviceService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					Map<String, Integer> data = (Map<String, Integer>) newValue;
	
					GB_DevicePieChartData.clear();
					Set<String> keyset = data.keySet();
					for (String string : keyset) {
						GB_DevicePieChartData.add(new Data(string, data.get(string)));
					}
				}
			}
		});
        
        GB_MonthComboBox.getItems().addAll(new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12});
        GB_MonthComboBox.getSelectionModel().select(0);
        
        S_QueryDetailService = new QueryReportService("QueryDetailCount");
        S_QueryDetailService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					List<Object[]> result = (List<Object[]>) newValue;
					
					GB_ReportDetailBarChart.getData().clear();
					//创建柱状图的数据线
					XYChart.Series<String,Number>[] series = new XYChart.Series[S_FilterItemList.size()];
					for (int i=0; i<S_FilterItemList.size(); i++) {
						
						series[i] = new XYChart.Series<String,Number>();

				        series[i].setName(S_FilterItemList.get(i));
				        
				        GB_ReportDetailBarChart.getData().add(series[i]);
					}
					
					Map<String, Map<String, Integer>> chartdatamap = new HashMap<>();
					List<String> datelist = new ArrayList<>();
					
					Map<String, Integer> map = null;
					for (Object[] objects : result) {

						if(chartdatamap.get(objects[0]) == null){
							map = new HashMap<>();
						}
						map.put((String)objects[1], ((BigInteger)objects[2]).intValue());
						chartdatamap.put((String)objects[0], map);
						
						if(datelist.contains((String)objects[0]) != true)
							datelist.add((String)objects[0]);
					}
					
					for (String string : datelist) {

						Map<String, Integer> map2 = chartdatamap.get(string);
						
						for (XYChart.Series<String,Number> serie : series) {
							Integer num = map2.get(serie.getName());
							if(num != null)
								serie.getData().add(new XYChart.Data<String, Number>(string, num));
						}
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

	//更新总图
	private void UpDetailFilterUI() {
		
		List<String> deviceidlist = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
		
		GB_DeviceFlowPane.getChildren().clear();
		S_FilterDeviceList.clear();
		for (String string : deviceidlist) {
			CheckBox checkBox = new CheckBox(string);
			GB_DeviceFlowPane.getChildren().add(checkBox);
			S_FilterDeviceList.add(string);
		}
		
		List<String> itemlist = ReportDao.QueryAllItem(deviceidlist);
		GB_ItemFlowPane.getChildren().clear();
		S_FilterItemList.clear();
		for (String string : itemlist) {
			CheckBox checkBox2 = new CheckBox(string);
			GB_ItemFlowPane.getChildren().add(checkBox2);
			S_FilterItemList.add(string);
		}

	}
	
	@FXML
	public void GB_QueryReportSummyAction(){
		
		//读取年
		String text = GB_YearTextField.getText();
		
		try {
			S_FilterYear = Integer.valueOf(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(S_FilterYear == null)
			GB_YearTextField.setText(null);
		else
			GB_YearTextField.setText(S_FilterYear.toString());
		
		//读取月
		int month = GB_MonthComboBox.getSelectionModel().getSelectedItem();
				
		if(month == 0)
			S_FilterMonth = null;
		else
			S_FilterMonth = new Integer(month);
		
		//读项目
		S_FilterItemList.clear();
		for (Node node : GB_ItemFlowPane.getChildren()) {
			CheckBox checkBox = (CheckBox) node;
			if(checkBox.isSelected())
				S_FilterItemList.add(checkBox.getText());
		}
		if(S_FilterItemList.size() == 0){
			for (Node node : GB_ItemFlowPane.getChildren()) {
				CheckBox checkBox = (CheckBox) node;
				S_FilterItemList.add(checkBox.getText());
			}
		}
			
		
		//读设备
		S_FilterDeviceList.clear();
		for (Node node : GB_DeviceFlowPane.getChildren()) {
			CheckBox checkBox = (CheckBox) node;
			if(checkBox.isSelected())
				S_FilterDeviceList.add(checkBox.getText());
		}
		if(S_FilterDeviceList.size() == 0){
			for (Node node : GB_DeviceFlowPane.getChildren()) {
				CheckBox checkBox = (CheckBox) node;
				S_FilterDeviceList.add(checkBox.getText());
			}
		}
		
		GB_ReportDetailBarChart.getData().clear();
		
		if((S_FilterItemList.size() == 0) || (S_FilterDeviceList.size() == 0) )
			return;
		else{
			S_QueryDetailService.setParm(new Object[]{S_FilterYear, S_FilterMonth, S_FilterItemList, S_FilterDeviceList});
			S_QueryDetailService.restart();
		}
			
	}
}
