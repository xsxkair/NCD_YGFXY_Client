package org.com.xsx.UI.MainScene.Report.ReportOverViewPage;

import java.io.IOException;
import java.io.InputStream;

import org.com.xsx.Data.UIMainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ReportOverViewPage {
	
	private static ReportOverViewPage S_ReportOverViewPage = null;
	
	private AnchorPane rootpane;
	
	@FXML
	PieChart GB_ReportPieChart;
	@FXML
	PieChart GB_ItemPieChart;
	@FXML
	PieChart GB_DevicePieChart;
	
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
	
	private void UpReportPieChartValue() {
		
	}
	
	private void UpItemPieChartValue() {
		
	}

	private void UpDevicePieChartValue() {
	
}

	private void UpDetailChartValue() {
	
}
}
