package org.com.xsx.UI.MainScene.Report.ReportDetailPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;

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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ReportDetailPage {
	
	private static ReportDetailPage GB_ReportDetailPage = null;
	
	private AnchorPane rootpane;
	
	TestDataBean testDataBean;	//测试数据
	PersonBean tester;			//操作人
	
	CardBean cardBean;			//检测卡信息
	DeviceBean deviceBean;		//设备信息
	PersonBean devicer;			//设备责任人信息
	
	ManagerBean managerBean;	//审核人账号
	PersonBean manager;		//审核人信息
	
	PersonBean sampleperson;	//样品信息
	
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
	Label GB_ManagerNameLabel;
	@FXML
	Label GB_ManagerTimeLabel;
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
	private Button S_CommitReportButton;
	@FXML
	private Button S_BackButton;
		
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
				
				//清空曲线
				series.getData().clear();
				
				if(newValue == null){
					
				}
				else{
					testDataBean = (TestDataBean) newValue[0];
					cardBean = (CardBean) newValue[1];
					deviceBean = (DeviceBean) newValue[2];
					tester = (PersonBean) newValue[3];
					managerBean = (ManagerBean) newValue[4];
					manager = (PersonBean) newValue[5];
					sampleperson = (PersonBean) newValue[6];
					devicer = (PersonBean) newValue[7];
					
					//更新设备信息
					S_DeviceidLabel.setText((deviceBean.getId() == null)?"null":deviceBean.getId().toString());
					S_UserNameLabel.setText((devicer.getName() == null)?"null":devicer.getName().toString());
					S_UserAgeLabel.setText((devicer.getAge() == null)?"null":devicer.getAge().toString());
					S_UserSexLabel.setText((devicer.getSex() == null)?"null":devicer.getSex().toString());
					S_UserJobLabel.setText((devicer.getJob() == null)?"null":devicer.getJob().toString());
					S_UserPhoneLabel.setText((devicer.getPhone() == null)?"null":devicer.getPhone().toString());
					S_UserDescLabel.setText((devicer.getDsc() == null)?"null":devicer.getDsc().toString());
					S_DeviceLocationLabel.setText((deviceBean.getDaddr() == null)?"null":deviceBean.getDaddr().toString());
					
					//试剂卡信息
					S_CardidLabel.setText((cardBean.getId() == null)?"null":cardBean.getId().toString());
					S_ItemNameLabel.setText((cardBean.getItem() == null)?"null":cardBean.getItem().toString());
					S_NormalLabel.setText((cardBean.getN_v() == null)?"null":cardBean.getN_v().toString());
					S_WaittimeLabel.setText((cardBean.getWaitt() == null)?"null":cardBean.getWaitt().toString());
					S_OuttimeLabel.setText((cardBean.getOutdate() == null)?"null":cardBean.getOutdate().toString());
					
					//操作人信息
					S_TesterNameLabel.setText((tester.getName() == null)?"null":tester.getName().toString());
					S_TesterAgeLabel.setText((tester.getAge() == null)?"null":tester.getAge().toString());
					S_TesterSexLabel.setText((tester.getSex() == null)?"null":tester.getSex().toString());
					S_TesterJobLabel.setText((tester.getJob() == null)?"null":tester.getJob().toString());
					S_TesterPhoneLabel.setText((tester.getPhone() == null)?"null":tester.getPhone().toString());
					S_TesterDescLabel.setText((tester.getDsc() == null)?"null":tester.getDsc().toString());

					
					//测试信息
					JSONArray jsonArray = null;
			        List<Integer> seriesdata = new ArrayList<>();

			        for(int i=0; i<4; i++){
			        	  	
			        	if(i == 0)
			        		jsonArray = (JSONArray) JSONSerializer.toJSON(testDataBean.getSerie_a());
			        	else if(i == 1)
			        		jsonArray = (JSONArray) JSONSerializer.toJSON(testDataBean.getSerie_b());
			        	else if(i == 2)
			        		jsonArray = (JSONArray) JSONSerializer.toJSON(testDataBean.getSerie_c());
			        	else if(i == 3)
			        		jsonArray = (JSONArray) JSONSerializer.toJSON(testDataBean.getSerie_d());
			        	
			        	seriesdata.addAll((List<Integer>) JSONSerializer.toJava(jsonArray));
			        }
			        
			        Integer t = testDataBean.getT_l();
			        Integer b = testDataBean.getB_l();
			        Integer c = testDataBean.getC_l();

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
	
					//S_TestLineChart;
			        S_SampleIDLabel.setText((testDataBean.getSampleid() == null)?"null":testDataBean.getSampleid().toString());
			        S_RealWaittimeLabel.setText(((cardBean.getWaitt() == null)?0:cardBean.getWaitt())+(testDataBean.getOutt()==null?0:testDataBean.getOutt())+" 秒");
			        S_CardTempLabel.setText((testDataBean.getO_t() == null)?"null":testDataBean.getO_t().toString());
			        S_EnTempLabel.setText((testDataBean.getE_t() == null)?"null":testDataBean.getE_t().toString());
			        S_TesttimeLabel.setText((testDataBean.getTesttime() == null)?"null":testDataBean.getTesttime().toString());
			        S_ReportUpTimeLabel.setText((testDataBean.getUptime() == null)?"null":testDataBean.getUptime().toString());
			        
					//报告信息
			        GB_ManagerNameLabel.setText((testDataBean.getM_name() == null)?"无":testDataBean.getM_name().toString());
			        GB_ManagerTimeLabel.setText((testDataBean.getHandletime() == null)?"无":testDataBean.getHandletime().toString());
					
			        String string = testDataBean.getResult();

					if (string == null) {	
						S_ReportResultToogleGroup.selectToggle(null);
					}
					else if (string.equals("合格")) {
						S_ReportResultToogleGroup.selectToggle(S_ReportOK);
						S_ReportDescTextArea.setText((testDataBean.getR_desc() == null)?"null":testDataBean.getR_desc().toString());
					}
					else {
						S_ReportResultToogleGroup.selectToggle(S_ReportNotOK);
						S_ReportDescTextArea.setText((testDataBean.getR_desc() == null)?"null":testDataBean.getR_desc().toString());
					}
				}
			}
		});
        
        S_TestLineChart.getData().add(series);
        
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
		if(S_ReportData.get() == null)
			return null;
		else
			return rootpane;
	}

	
	@FXML
	public void S_DeleteReportAction(){
		ReportDao.DeleteReport(S_ReportData.get());
		
		UIMainPage.GetInstance().setGB_Page(ReportListPage.GetInstance().GetReportPane());
	}
	
	@FXML
	public void S_CommitReportAction(){
		Object[] report = new Object[8];

		testDataBean.setResult((String) S_ReportResultToogleGroup.getSelectedToggle().getUserData());
		testDataBean.setR_desc(S_ReportDescTextArea.getText());
		
		testDataBean.setHandletime();
		
		ReportDao.UpdateReport(S_ReportData.get());
	}
	
	@FXML
	public void S_BackAction(){
		UIMainPage.GetInstance().setGB_Page(ReportListPage.GetInstance().GetReportPane());
	}
}
