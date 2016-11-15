package org.com.xsx.UI.MainScene.CardPage;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class CardRecordPage {
	
	private static CardRecordPage S_CardRecordPage = null;
	
	private AnchorPane rootpane;
	
	
	@FXML
	FlowPane GB_ItemFlowPane;
	@FXML
	FlowPane GB_DeviceFlowPane;
	
	@FXML
	LineChart<String, Number> GB_CardLineChart;
	@FXML
	CategoryAxis GB_CardLineXAxis;
	@FXML
	NumberAxis GB_CardLineYAxis;
	
	@FXML
	TableView<> GB_CardTableView;
	@FXML
	TableColumn<S, T> GB_TableColumn1;
	@FXML
	TableColumn<S, T> GB_TableColumn2;
	@FXML
	TableColumn<S, T> GB_TableColumn3;
	@FXML
	TableColumn<S, T> GB_TableColumn4;
	@FXML
	TableColumn<S, T> GB_TableColumn5;
	@FXML
	TableColumn<S, T> GB_TableColumn6;
	@FXML
	TableColumn<S, T> GB_TableColumn7;
	
	@FXML
	Pagination GB_Pagination;
	
	@FXML
	StackPane GB_FreshPane;
	@FXML
	ProgressIndicator GB_RefreshBar;
	
	private CardRecordPage() {
		
	}
	
	public static CardRecordPage GetInstance() {
		if(S_CardRecordPage == null)
			S_CardRecordPage = new CardRecordPage();
		
		return S_CardRecordPage;
	}
	
	public void UI_Init() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("CardRecordPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("CardRecordPage.fxml");
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
	public void GB_QueryAction(){
		
	}
}
