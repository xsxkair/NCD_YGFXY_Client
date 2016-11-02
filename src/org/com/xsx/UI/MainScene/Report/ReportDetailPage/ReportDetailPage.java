package org.com.xsx.UI.MainScene.Report.ReportDetailPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.TestDataBean;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.sf.json.JSONObject;

public class ReportDetailPage {
	
	private static ReportDetailPage GB_ReportDetailPage = null;
	
	private AnchorPane rootpane;
	
	private ObjectProperty<Object[]> S_ReportData;
	
	
	//设备信息
	@FXML
	private Label S_DeviceidLabel;
	@FXML
	private Label S_UserNameLabel;
	@FXML
	private Label S_UserAgeLabel;
	@FXML
	private Label S_UserSexLabel;
	@FXML
	private Label S_UserJobLabel;
	@FXML
	private Label S_UserPhoneLabel;
	@FXML
	private Label S_UserDescLabel;
	@FXML
	private Label S_DeviceLocationLabel;
	
	//试剂卡信息
	@FXML
	private Label S_CardidLabel;
	@FXML
	private Label S_ItemNameLabel;
	@FXML
	private Label S_NormalLabel;
	@FXML
	private Label S_WaittimeLabel;
	@FXML
	private Label S_OuttimeLabel;
	
	//操作人信息
	@FXML
	private Label S_TesterNameLabel;
	@FXML
	private Label S_TesterAgeLabel;
	@FXML
	private Label S_TesterSexLabel;
	@FXML
	private Label S_TesterJobLabel;
	@FXML
	private Label S_TesterPhoneLabel;
	@FXML
	private Label S_TesterDescLabel;
	
	//测试信息
	@FXML
	LineChart<Number, Number> S_TestLineChart;
	@FXML
	private Label S_SampleIDLabel;
	@FXML
	private Label S_RealWaittimeLabel;
	@FXML
	private Label S_CardTempLabel;
	@FXML
	private Label S_EnTempLabel;
	@FXML
	private Label S_TesttimeLabel;
	@FXML
	private Label S_ReportUpTimeLabel;
	
	//报告信息
	@FXML
	private RadioButton S_ReportOK;
	@FXML
	private RadioButton S_ReportNotOK;
	@FXML
	private ToggleGroup S_ReportResultToogleGroup;
	@FXML
	private TextArea S_ReportDescTextArea;
	@FXML
	private Button S_DeleteReportButton;
	@FXML
	private Button S_ModifyReportButton;
	@FXML
	private Button S_CommitReportButton;
	@FXML
	private Button S_BackButton;
	
	Map<String, List<Integer>> typemap = new HashMap<>();
	Map<String, List<Integer>> outmap = null;
		
	String datajson;
	Series<Number, Number> series = new Series<>();
	
	private ReportDetailPage() {
		
	}
	
	public static ReportDetailPage GetInstance() {
		if(GB_ReportDetailPage == null){
			GB_ReportDetailPage = new ReportDetailPage();
		}
		
		return GB_ReportDetailPage;
	}
	
	public void UI_Init(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ReportDetailPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("ReportDetailPage.fxml");
        loader.setController(this);
        try {
        	rootpane = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        S_ReportData = new SimpleObjectProperty<>(null);
        S_ReportData.addListener(new ChangeListener<Object[]>() {

			@Override
			public void changed(ObservableValue<? extends Object[]> observable, Object[] oldValue, Object[] newValue) {
				// TODO Auto-generated method stub
				TestDataBean testDataBean;
				CardBean cardBean;
				DeviceBean deviceBean;
				PersonBean personBean;
				SampleBean sampleBean;
				ManagerBean managerBean;
				if(newValue == null){
					
				}
				else{
					//更新设备信息
					S_DeviceidLabel.setText(newValue.getDid().toString());
					S_UserNameLabel.setText(newValue.getDid().toString());
					S_UserAgeLabel.setText(newValue.getDid().toString());
					S_UserSexLabel.setText(newValue.getDid().toString());
					S_UserJobLabel.setText(newValue.getDid().toString());
					S_UserPhoneLabel.setText(newValue.getDid().toString());
					S_UserDescLabel.setText(newValue.getDid().toString());
					S_DeviceLocationLabel.setText(newValue.getDid().toString());
					
					//试剂卡信息
					S_CardidLabel.setText(newValue.getCid().toString());
					S_ItemNameLabel.setText(newValue.getC_item().toString());
					S_NormalLabel.setText(newValue.getC_n_v().toString());
					S_WaittimeLabel.setText(newValue.getC_waitt().toString());
					S_OuttimeLabel.setText(newValue.getC_outt().toString());
					
					//操作人信息
					S_TesterNameLabel.setText(newValue.getT_name().toString());
					S_TesterAgeLabel.setText(newValue.getT_age().toString());
					S_TesterSexLabel.setText(newValue.getT_sex().toString());
					S_TesterJobLabel.setText(newValue.getT_job().toString());
					S_TesterPhoneLabel.setText(newValue.getT_phone().toString());
					S_TesterDescLabel.setText(newValue.getT_desc().toString());

					
					//测试信息
					datajson = newValue.getSerie();

					series.setName(null);
					S_TestLineChart.getData().add(series);

					if(datajson != null){
						JSONObject jsonObject = JSONObject.fromObject(datajson);	
						
						outmap = (Map<String, List<Integer>>) jsonObject.toBean(jsonObject, Map.class, typemap);
						
						List<Integer> seriesdata = new ArrayList<>();
						
						for (String key : outmap.keySet()) {
							seriesdata.addAll(outmap.get(key));
						}
						
						Integer t = newValue.getT_l();
				        Integer b = newValue.getB_l();
				        Integer c = newValue.getC_l();
				        
				        for(int i=0; i<seriesdata.size(); i++){
				        	Data<Number, Number> data = new Data<Number, Number>(i, seriesdata.get(i));
				        	StackPane stackPane = new StackPane();
				        	
				        	stackPane.setPrefSize(0, 0);
				        	
				        	if((t != null) && (i == t.intValue())){
				        		stackPane.setStyle("-fx-background-color:red");
				        		stackPane.setPrefSize(10, 10);
				        	}
				        	else if((b != null) && (i == b.intValue())){
				        		stackPane.setStyle("-fx-background-color:green");
				        		stackPane.setPrefSize(10, 10);
				        	}
				        	else if((c != null) && (i == c.intValue())){
				        		stackPane.setStyle("-fx-background-color:blue");
				        		stackPane.setPrefSize(10, 10);
				        	}
				        	
				        	data.setNode(stackPane);
				        	series.getData().add(data);
						}
					}
					//S_TestLineChart;
					S_SampleIDLabel.setText(newValue.getSid().toString());
					S_RealWaittimeLabel.setText(newValue.getOutt()+newValue.getC_waitt()+"");
					S_CardTempLabel.setText(newValue.getO_t().toString());;
					S_EnTempLabel.setText(newValue.getE_t().toString());
					S_TesttimeLabel.setText(newValue.getTestd()+" "+newValue.getTestt());
					S_ReportUpTimeLabel.setText(newValue.getR_uptime().toString());
					
					//报告信息
					String string = newValue.getR_re();
					S_ReportDescTextArea.setDisable(true);
					S_ReportOK.setDisable(true);
					S_ReportNotOK.setDisable(true);
					if (string == null) {
						
						S_ReportResultToogleGroup.selectToggle(null);
						S_ReportOK.setDisable(false);
						S_ReportNotOK.setDisable(false);
						S_ReportDescTextArea.setDisable(false);
						S_ModifyReportButton.setDisable(true);
					}
					else if (string.equals("合格")) {
						S_ReportResultToogleGroup.selectToggle(S_ReportOK);
						S_ReportDescTextArea.setText(newValue.getR_desc().toString());
					}
					else {
						S_ReportResultToogleGroup.selectToggle(S_ReportNotOK);
						S_ReportDescTextArea.setText(newValue.getR_desc().toString());
					}
				}
			}
		});
        
        AnchorPane.setTopAnchor(rootpane, 0.0);
        AnchorPane.setBottomAnchor(rootpane, 0.0);
        AnchorPane.setLeftAnchor(rootpane, 0.0);
        AnchorPane.setRightAnchor(rootpane, 0.0);
	}
	
	public ObjectProperty<Object[]> getS_ReportData() {
		return S_ReportData;
	}

	public void setS_ReportData(Object[] s_ReportData) {
		S_ReportData.set(s_ReportData);
	}

	public AnchorPane getPane(){
		if(S_TestDataBean == null)
			return null;
		else
			return rootpane;
	}

	
	@FXML
	public void S_DeleteReportAction(){
		
	}
	
	@FXML
	public void S_ModifyReportAction(){
		
	}
	
	@FXML
	public void S_CommitReportAction(){
		
	}
	
	@FXML
	public void S_BackAction(){
		
	}
}
