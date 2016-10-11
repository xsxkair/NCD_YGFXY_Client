
package org.com.xsx.UI.MainScene.ReportPage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.LoginUser;
import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Service.ReadReportCountService;
import org.com.xsx.Service.ReadReportService;
import org.com.xsx.UI.MainScene.DevicePage.DeviceTipInfo;
import org.hibernate.criterion.BetweenExpression;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class ReportPage {
	
	private static ReportPage S_ReportPage = null;
	
	private AnchorPane reportpane;
	
	@FXML
	TextField GB_TestItemFilterTextfield;
	@FXML
	DatePicker GB_TestTimeFilterDateChoose;
	@FXML
	TextField GB_TesterFilterTextfield;
	@FXML
	ComboBox<String> GB_TestDeviceFilterCombox;
	@FXML
	TextField GB_TestSampleFilterTextField;
	@FXML
	ComboBox<String> GB_ReportResultFilterCombox;
	
	@FXML
	TableView<ReportTableItem>	GB_TableView;
	@FXML
	TableColumn<ReportTableItem, Boolean> TableColumn0;
	@FXML
	TableColumn<ReportTableItem, Integer> TableColumn1;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn2;
	@FXML
	TableColumn<ReportTableItem, java.sql.Date> TableColumn3;
	@FXML
	TableColumn<ReportTableItem, Float> TableColumn4;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn5;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn6;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn7;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn8;
	
	@FXML
	Pagination GB_Pagination;
	
	@FXML
	StackPane GB_FreshPane;
	@FXML
	Region GB_Viel;
	@FXML
	ProgressIndicator GB_RefreshBar;
	
	
	private ReportPage(){
		
	}
	
	public static ReportPage GetInstance() {
		if(S_ReportPage == null){
			S_ReportPage = new ReportPage();
			S_ReportPage.UI_Init();
			S_ReportPage.Data_Init();
		}
		
		return S_ReportPage;
	}
	
	private void UI_Init(){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ReportPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("ReportPage.fxml");
        loader.setController(this);
        try {
        	reportpane = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        TableColumn0.setCellValueFactory(new PropertyValueFactory<ReportTableItem, Boolean>("isselected"));
        TableColumn0.setCellFactory(CheckBoxTableCell.forTableColumn(TableColumn0));
        
        TableColumn1.setCellValueFactory(new PropertyValueFactory<ReportTableItem, Integer>("index"));
        TableColumn1.setCellFactory(new TableColumnModel<ReportTableItem, Integer>());
        
        TableColumn2.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("testitem"));
        TableColumn2.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn3.setCellValueFactory(new PropertyValueFactory<ReportTableItem, java.sql.Date>("testdate"));
        TableColumn3.setCellFactory(new TableColumnModel<ReportTableItem, java.sql.Date>());
        
        TableColumn4.setCellValueFactory(new PropertyValueFactory<ReportTableItem, Float>("testresult"));
        TableColumn4.setCellFactory(new TableColumnModel<ReportTableItem, Float>());
        
        TableColumn5.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("tester"));
        TableColumn5.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn6.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("deviceid"));
        TableColumn6.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn7.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("simpleid"));
        TableColumn7.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn8.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("reportresult"));
        TableColumn8.setCellFactory(new TableColumnModel<ReportTableItem, String>());
    	
        GB_TableView.itemsProperty().bind(ReadReportService.GetInstance().valueProperty());
        
        GB_RefreshBar.progressProperty().bind(ReadReportService.GetInstance().progressProperty());
        GB_FreshPane.visibleProperty().bind(ReadReportService.GetInstance().runningProperty());
        
        GB_ReportResultFilterCombox.getItems().addAll("All", "未审核", "合格", "不合格");
        GB_TestDeviceFilterCombox.getItems().add("All");
        GB_TestDeviceFilterCombox.getItems().addAll(LoginUser.GetInstance().getMy_deviceids());
        
        reportpane.getStylesheets().add(this.getClass().getResource("reportpage.css").toExternalForm());
        
        AnchorPane.setTopAnchor(reportpane, 0.0);
        AnchorPane.setBottomAnchor(reportpane, 0.0);
        AnchorPane.setLeftAnchor(reportpane, 0.0);
        AnchorPane.setRightAnchor(reportpane, 0.0);
        
	}
	
	private void Data_Init(){
		
		GB_TestItemFilterTextfield.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub

				if((newValue == null) || (newValue.length() == 0))
					ReportFilterData.GetInstance().setTestitem(null);
				else
					ReportFilterData.GetInstance().setTestitem(newValue);
				
				ReadReportCountService.GetInstance().restart();
				StartReportService();
			}
		});

		GB_TestTimeFilterDateChoose.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				// TODO Auto-generated method stub

				java.sql.Date tempdate = null;
				try {
					tempdate = java.sql.Date.valueOf(newValue);
				} catch (Exception e2) {
					// TODO: handle exception

				}
				
				ReportFilterData.GetInstance().setTesttime(tempdate);
				
				ReadReportCountService.GetInstance().restart();
				StartReportService();
			}
		});
		
		GB_TesterFilterTextfield.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub

				if((newValue == null) || (newValue.length() == 0))
					ReportFilterData.GetInstance().setTestername(null);
				else
					ReportFilterData.GetInstance().setTestername(newValue);
				
				ReadReportCountService.GetInstance().restart();
				StartReportService();
			}
		});
		
		GB_TestDeviceFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub

				if((newValue != null)&&(newValue.equals("All")))
					ReportFilterData.GetInstance().setDeviceid(null);
				else
					ReportFilterData.GetInstance().setDeviceid(newValue);
				
				ReadReportCountService.GetInstance().restart();
				StartReportService();
			}
		});
		
		GB_TestSampleFilterTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if((newValue == null) || (newValue.length() == 0))
					ReportFilterData.GetInstance().setSimpleid(null);
				else
					ReportFilterData.GetInstance().setSimpleid(newValue);
				
				ReadReportCountService.GetInstance().restart();
				StartReportService();
			}
		});
		
		GB_ReportResultFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				
				if((newValue != null)&&(newValue.equals("All")))
					ReportFilterData.GetInstance().setReportresult(null);
				else
					ReportFilterData.GetInstance().setReportresult(newValue);
				
				ReadReportCountService.GetInstance().restart();
				StartReportService();
			}
		});

		ReadReportCountService.GetInstance().valueProperty().addListener(new ChangeListener<Long>() {

			@Override
			public void changed(ObservableValue<? extends Long> observable, Long oldValue, Long newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					int pagesize = ReportFilterData.GetInstance().getPagesize();

					GB_Pagination.setPageCount( (int) ((newValue%pagesize == 0)?(newValue/pagesize):(newValue/pagesize+1)));
					
					GB_Pagination.setCurrentPageIndex(0);
				}
			}
		});

		GB_Pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				ReportFilterData.GetInstance().setPageindex(newValue.intValue());
				StartReportService();
			}
		});
	}
	
	private void FilterUI_Init(){
		
		//测试项目
		GB_TestItemFilterTextfield.setText(null);
		
		//测试时间
		GB_TestTimeFilterDateChoose.setValue(null);
		
		//测试人
		GB_TesterFilterTextfield.setText(null);
		
		//测试设备
		GB_TestDeviceFilterCombox.setValue(null);
		
		//测试样品
		GB_TestSampleFilterTextField.setText(null);
		
		//测试结果
		GB_ReportResultFilterCombox.setValue(null);
	}
	
	public AnchorPane GetReportPane(){	
		ReadReportCountService.GetInstance().restart();
		StartReportService();
		return reportpane;
	}
	
	public void initdata(){
//		FilterUI_Init();
//		FreshPanition();
//		StartReportService();
	}
	
	private void StartReportService(){
		ReadReportService.GetInstance().restart();
	}
	
	@FXML
	public void ClearFilterButtonActionHandle(){
		FilterUI_Init();
		
		ReadReportCountService.GetInstance().restart();
		StartReportService();
	}
	
	@FXML
	public void RefreshButtonActionHandle(){
		ReadReportCountService.GetInstance().restart();
		StartReportService();
	}
	
	@FXML
	public void GB_ExportReportAction(){
		
	}

	class TableColumnModel<T,S> implements Callback<TableColumn<T, S>, TableCell<T, S>> {
		
	    @Override
	    public TableCell<T, S> call(TableColumn<T, S> param) {
	    	TextFieldTableCell<T, S> cell = new TextFieldTableCell<>();
	    	
	    	Tooltip tooltip = new Tooltip();
	    	
	    	cell.setAlignment(Pos.CENTER);
	    	cell.setEditable(false);
	    	
	    	cell.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					TableRow<T> row = cell.getTableRow();
					
					if((row != null)&&(row.getIndex() < GB_TableView.getItems().size())){
						
						if(!row.getStyleClass().contains("tablerow"))
							row.getStyleClass().add("tablerow");
						
						tooltip.setGraphic(new ReportTipInfo(GB_TableView.getItems().get(row.getIndex()).getTestdatabean()));
						Tooltip.install(cell, tooltip);	
					}
					else
						Tooltip.uninstall(cell, tooltip);
				}
			});

	        return cell;
	    }
	}
}
