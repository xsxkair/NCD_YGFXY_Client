package org.com.xsx.UI.MainScene.Report.ReportListPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.xsx.Domain.TestDataBean;

import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import net.sf.json.JSONObject;

public class ReportTipInfo extends AnchorPane{

	private Object[] reportdata;
	
	public ReportTipInfo(){
	}
	
	public ReportTipInfo(Object[] data){
		this.reportdata = data;
		this.UI_Init();
	}
	
	private void UI_Init(){
		
		NumberAxis xAxis = new NumberAxis();
    	NumberAxis yAxis = new NumberAxis();
    	LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
    	chart.setStyle("-fx-legend-visible:false");
    	
 /*   	Series<Number, Number> series = new Series<>();

        chart.getData().add(series);
        
        Map<String, List<Integer>> typemap = new HashMap<>();
		Map<String, List<Integer>> outmap = null;
		
		String datajson = testdatabean.getSerie();

		if(datajson != null){
			JSONObject jsonObject = JSONObject.fromObject(datajson);	
			
			outmap = (Map<String, List<Integer>>) jsonObject.toBean(jsonObject, Map.class, typemap);
			
			List<Integer> seriesdata = new ArrayList<>();
			
			for (String key : outmap.keySet()) {
				seriesdata.addAll(outmap.get(key));
			}
			
			Integer t = testdatabean.getT_l();
	        Integer b = testdatabean.getB_l();
	        Integer c = testdatabean.getC_l();
	        
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
	        
	        VBox root = new VBox();
	        root.setSpacing(10);
	        root.setAlignment(Pos.CENTER);
	        root.setLayoutX(60);
	        root.setLayoutY(20);
	        
	        HBox hBox1 = new HBox();
	        hBox1.setSpacing(10);
	        
	        Label label1_1 = new Label("测试结果: ");
	        label1_1.setFont(new Font("System", 16));
	        Label label1_2 = new Label(testdatabean.getA_v()+" "+testdatabean.getC_dw());
	        label1_2.setFont(new Font("System", 16)); 
	        hBox1.getChildren().addAll(label1_1, label1_2);
	        
	        HBox hBox2 = new HBox();
	        hBox2.setSpacing(10);
	        
	        Label label2_1 = new Label("报告状态: ");
	        label2_1.setFont(new Font("System", 16));
	        Label label2_2 = new Label();
	        if(testdatabean.getR_re() == null)
	        	label2_2.setText("未审核");
	        else {
	        	label2_2.setText(testdatabean.getR_re());
			}
	        label2_2.setFont(new Font("System", 16)); 
	        hBox2.getChildren().addAll(label2_1, label2_2);
	        
	        HBox hBox3 = new HBox();
	        hBox3.setSpacing(10);
	        
	        Label label3_1 = new Label("报告说明: ");
	        label3_1.setFont(new Font("System", 16)); 
	        
	        Label label3_2 = new Label();
	        label3_2.setWrapText(true);
	        label3_2.setFont(new Font("System", 16));
	        label3_2.setPrefWidth(165);
	        if(testdatabean.getR_desc() == null)
	        	label3_2.setText("无");
	        else
	        	label3_2.setText(testdatabean.getR_desc());
	        
	        hBox3.getChildren().addAll(label3_1, label3_2);
	        
	        root.getChildren().addAll(hBox1, hBox2, hBox3);
	        
	        this.getChildren().addAll(chart, root);
		}
		else{
			this.getChildren().add(new Label("错误"));
		}*/
    	this.getChildren().addAll(chart);
	}
}
