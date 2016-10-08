package org.com.xsx.UI.MainScene.ReportPage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Service.ReadReportService;
import org.hibernate.criterion.BetweenExpression;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class ReportPage {
	
	private static ReportPage S_ReportPage = null;
	
	private AnchorPane reportpane;
	
	@FXML
	ComboBox<String> GB_TestItemFilterCombox;
	@FXML
	DatePicker GB_TestTimeFilterDateChoose;
	@FXML
	ComboBox<String> GB_TesterFilterCombox;
	@FXML
	ComboBox<String> GB_TestDeviceFilterCombox;
	@FXML
	ComboBox<String> GB_TestSampleFilterCombox;
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
        
        GB_ReportResultFilterCombox.getItems().addAll("All", "未处理", "合格", "不合格");
        
        AnchorPane.setTopAnchor(reportpane, 0.0);
        AnchorPane.setBottomAnchor(reportpane, 0.0);
        AnchorPane.setLeftAnchor(reportpane, 0.0);
        AnchorPane.setRightAnchor(reportpane, 0.0);
	}
	
	private void Data_Init(){
		
		GB_TestItemFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("项目"+newValue);
				if((newValue != null)&&(newValue.equals("All")))
					ReportFilterData.GetInstance().setTestitem(null);
				else
					ReportFilterData.GetInstance().setTestitem(newValue);

				FreshPanition();
				
				StartReportService();
			}
		});

		GB_TestTimeFilterDateChoose.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				// TODO Auto-generated method stub
				System.out.println("时间"+newValue);
				java.sql.Date tempdate = null;
				try {
					tempdate = java.sql.Date.valueOf(newValue);
				} catch (Exception e2) {
					// TODO: handle exception

				}
				
				ReportFilterData.GetInstance().setTesttime(tempdate);
				FreshPanition();
				
				StartReportService();
			}
		});
		
		GB_TesterFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("人"+newValue);
				ReportFilterData.GetInstance().setTestername(newValue);
				FreshPanition();
				
				StartReportService();
			}
		});
		
		GB_TestDeviceFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("设备"+newValue);
				if((newValue != null)&&(newValue.equals("All")))
					ReportFilterData.GetInstance().setDeviceid(null);
				else
					ReportFilterData.GetInstance().setDeviceid(newValue);

				FreshPanition();
				
				StartReportService();
			}
		});
		
		GB_TestSampleFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("样品"+newValue);
				
				if((newValue != null)&&(newValue.equals("All")))
					ReportFilterData.GetInstance().setSimpleid(null);
				else
					ReportFilterData.GetInstance().setSimpleid(newValue);
				FreshPanition();
				
				StartReportService();
			}
		});
		
		GB_ReportResultFilterCombox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("报告"+newValue);
				
				if((newValue != null)&&(newValue.equals("All")))
					ReportFilterData.GetInstance().setReportresult(null);
				else
					ReportFilterData.GetInstance().setReportresult(newValue);
				
				FreshPanition();
				
				StartReportService();
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
		List<String> templist = ReportDao.QueryTestItemS();
		GB_TestItemFilterCombox.getItems().clear();
		GB_TestItemFilterCombox.getItems().add("All");
		GB_TestItemFilterCombox.getItems().addAll(templist);
		GB_TestItemFilterCombox.setValue(null);
		
		//测试时间
		GB_TestTimeFilterDateChoose.setValue(null);
		
		//测试人
		GB_TesterFilterCombox.setValue(null);
		
		//测试设备
		templist = ReportDao.QueryTestDeviceS();
		GB_TestDeviceFilterCombox.getItems().clear();
		GB_TestDeviceFilterCombox.getItems().add("All");
		GB_TestDeviceFilterCombox.getItems().addAll(templist);
		GB_TestDeviceFilterCombox.setValue(null);
		
		//测试样品
		templist = ReportDao.QueryTestSampleS();
		GB_TestSampleFilterCombox.getItems().clear();
		GB_TestSampleFilterCombox.getItems().add("All");
		GB_TestSampleFilterCombox.getItems().addAll(templist);
		GB_TestSampleFilterCombox.setValue(null);
		
		//测试结果
		GB_ReportResultFilterCombox.setValue(null);
	}
	
	private void FreshPanition(){
		Long num = ReportDao.QueryTestDataNum();
		ReportFilterData.GetInstance().setDatatotalnum(num);

		if(num == 0)
			GB_Pagination.setPageCount(1);
		else{
			long num1 = num;
			int pagesize = ReportFilterData.GetInstance().getPagesize();
			GB_Pagination.setPageCount((int) ((num1%pagesize == 0)?(num1/pagesize):(num1/pagesize+1)));
		}
	}
	
	public AnchorPane GetReportPane(){
		FilterUI_Init();
		FreshPanition();
		StartReportService();
		return reportpane;
	}
	
	private void StartReportService(){
		if(!ReadReportService.GetInstance().isRunning())
			ReadReportService.GetInstance().restart();
	}
	
	@FXML
	public void ClearFilterButtonActionHandle(){
		FilterUI_Init();
		FreshPanition();
		
		if(GB_Pagination.getCurrentPageIndex() != 0)
			GB_Pagination.setCurrentPageIndex(0);
		else
			StartReportService();
	}
	
	@FXML
	public void RefreshButtonActionHandle(){
		
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
					
					if(row.getIndex() < GB_TableView.getItems().size()){
				        tooltip.setGraphic(new Label("xsx"));
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
