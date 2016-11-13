package org.com.xsx.UI.MainScene.WorkSpace;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import org.com.xsx.Dao.ManagerDao;
import org.com.xsx.Data.QueryReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Data.UIScence;
import org.com.xsx.Define.ReportTableItem;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Service.QueryReportService;
import org.com.xsx.UI.MainScene.Report.ReportDetailPage.ReportDetailPage;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportTipInfo;
import org.com.xsx.UI.MainScene.Report.ReportOverViewPage.ReportOverViewPage;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class WorkSpacePage {

	private static WorkSpacePage S_WorkSpacePage = null;
	
	private AnchorPane rootpane;
	
	@FXML
	TabPane GB_TabPane;
	
	//今日待审核报告
	@FXML
	TableView<ReportTableItem> GB_TableView0;
	@FXML
	TableColumn<ReportTableItem, Integer> TableColumn01;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn02;
	@FXML
	TableColumn<ReportTableItem, java.sql.Timestamp> TableColumn03;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn04;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn05;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn06;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn07;
	
	@FXML
	Pagination GB_Pagination0;
	
	@FXML
	StackPane GB_FreshPane0;
	@FXML
	ProgressIndicator GB_RefreshBar0;
	
	//今日已审核报告
	@FXML
	TableView<ReportTableItem> GB_TableView1;
	@FXML
	TableColumn<ReportTableItem, Integer> TableColumn11;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn12;
	@FXML
	TableColumn<ReportTableItem, java.sql.Timestamp> TableColumn13;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn14;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn15;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn16;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn17;
	@FXML
	TableColumn<ReportTableItem, String> TableColumn18;
	
	@FXML
	Pagination GB_Pagination1;
	
	@FXML
	StackPane GB_FreshPane1;
	@FXML
	ProgressIndicator GB_RefreshBar1;
	
	ContextMenu myContextMenu;
	MenuItem myMenuItem1;
	//数据
	
	//今日待审核
	private QueryReportService S_QueryTodayNotHandledReportService;
	private QueryReportFilterData S_QueryTodayNotHandledReportFilterData;
	//今日已审核
	private QueryReportService S_QueryTodayHandledReportService;
	private QueryReportFilterData S_QueryTodayHandledReportFilterData;
	
	private WorkSpacePage() {
		
	}
	
	public static WorkSpacePage GetInstance() {
		if(S_WorkSpacePage == null){
			S_WorkSpacePage = new WorkSpacePage();
			
			S_WorkSpacePage.S_QueryTodayNotHandledReportFilterData = new QueryReportFilterData(null, new java.sql.Date(System.currentTimeMillis()), null, null, null, null, "未审核", true, 0);
			S_WorkSpacePage.S_QueryTodayNotHandledReportService = new QueryReportService(S_WorkSpacePage.S_QueryTodayNotHandledReportFilterData);
			
			S_WorkSpacePage.S_QueryTodayHandledReportFilterData = new QueryReportFilterData(null, new java.sql.Date(System.currentTimeMillis()), null, null, null, null, "已审核", true, 0);
			S_WorkSpacePage.S_QueryTodayHandledReportService = new QueryReportService(S_WorkSpacePage.S_QueryTodayHandledReportFilterData);
			
		}
		
		return S_WorkSpacePage;
	}
	
	public void UI_Init() {
		AnchorPane root = null;
			
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("WorkSpacePage.fxml"));
		InputStream in = this.getClass().getResourceAsStream("WorkSpacePage.fxml");
		loader.setController(this);
		try {
			rootpane = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//未处理
		TableColumn01.setCellValueFactory(new PropertyValueFactory<ReportTableItem, Integer>("index"));
        TableColumn01.setCellFactory(new TableColumnModel<ReportTableItem, Integer>());
        
        TableColumn02.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("testitem"));
        TableColumn02.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn03.setCellValueFactory(new PropertyValueFactory<ReportTableItem, java.sql.Timestamp>("testdate"));
        TableColumn03.setCellFactory(new TableColumnModel<ReportTableItem, java.sql.Timestamp>());
        
        TableColumn04.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("testresult"));
        TableColumn04.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn05.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("tester"));
        TableColumn05.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn06.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("deviceid"));
        TableColumn06.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn07.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("simpleid"));
        TableColumn07.setCellFactory(new TableColumnModel<ReportTableItem, String>());

        GB_FreshPane0.visibleProperty().bind(S_QueryTodayNotHandledReportService.runningProperty());
        GB_RefreshBar0.progressProperty().bind(S_QueryTodayNotHandledReportService.progressProperty());
        
        //已审核
        TableColumn11.setCellValueFactory(new PropertyValueFactory<ReportTableItem, Integer>("index"));
        TableColumn11.setCellFactory(new TableColumnModel<ReportTableItem, Integer>());
        
        TableColumn12.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("testitem"));
        TableColumn12.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn13.setCellValueFactory(new PropertyValueFactory<ReportTableItem, java.sql.Timestamp>("testdate"));
        TableColumn13.setCellFactory(new TableColumnModel<ReportTableItem, java.sql.Timestamp>());
        
        TableColumn14.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("testresult"));
        TableColumn14.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn15.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("tester"));
        TableColumn15.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn16.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("deviceid"));
        TableColumn16.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn17.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("simpleid"));
        TableColumn17.setCellFactory(new TableColumnModel<ReportTableItem, String>());
        
        TableColumn18.setCellValueFactory(new PropertyValueFactory<ReportTableItem, String>("reportresult"));
        TableColumn18.setCellFactory(new TableColumnModel<ReportTableItem, String>());

        GB_FreshPane1.visibleProperty().bind(S_QueryTodayHandledReportService.runningProperty());
        GB_RefreshBar1.progressProperty().bind(S_QueryTodayHandledReportService.progressProperty());
        
		UIMainPage.GetInstance().getGB_Page().addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				// TODO Auto-generated method stub
				if(newValue == rootpane){
					S_QueryTodayNotHandledReportService.start();
				}
			}
		});
		
		GB_TabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ChangeListener<Number> pagevaluechangedlistener = new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				S_QueryTodayNotHandledReportFilterData.setPageindex(newValue.intValue());
				S_QueryTodayNotHandledReportService.restart();
			}
		};
		GB_Pagination0.currentPageIndexProperty().addListener(pagevaluechangedlistener);
		
		S_QueryTodayNotHandledReportService.valueProperty().addListener(new ChangeListener<Object[]>() {

			@Override
			public void changed(ObservableValue<? extends Object[]> observable, Object[] oldValue, Object[] newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					
					//更新页数
					BigInteger totalnum = (BigInteger) newValue[1];
					
					if(totalnum != null){

						int pagesize = S_QueryTodayNotHandledReportFilterData.getPagesize();

						GB_Pagination0.setPageCount((int) ((totalnum.longValue()%pagesize == 0)?(totalnum.longValue()/pagesize):(totalnum.longValue()/pagesize+1)));
						
						GB_Pagination0.setCurrentPageIndex(0);
					}

					//更新数据
					GB_TableView0.getItems().clear();
					GB_TableView0.getItems().addAll((ObservableList<ReportTableItem>) newValue[0]);
					
					S_QueryTodayNotHandledReportFilterData.setFilterisnew(false);
				}
			}
		});
	}
	
	public AnchorPane GetPane() {
		return rootpane;
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
					
					if((row != null)&&(row.getIndex() < GB_TableView0.getItems().size())){
						
						if(!row.getStyleClass().contains("tablerow"))
							row.getStyleClass().add("tablerow");
						
						tooltip.setGraphic(new ReportTipInfo(GB_TableView0.getItems().get(row.getIndex()).getTestdata()));
						Tooltip.install(cell, tooltip);	
					}
					else
						Tooltip.uninstall(cell, tooltip);
				}
			});

	    	cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					TableRow<T> row = cell.getTableRow();
					
					if((row != null)&&(row.getIndex() < GB_TableView0.getItems().size())){
						if(event.getClickCount() == 2){
							ReportDetailPage.GetInstance().setS_ReportData(GB_TableView0.getItems().get(row.getIndex()).getTestdata());
							UIMainPage.GetInstance().setGB_Page(ReportDetailPage.GetInstance().getPane());
						}
						else if(event.getButton().equals(MouseButton.SECONDARY)){
							myContextMenu.show(cell, event.getScreenX(), event.getScreenY());
						}
					}
				}
			});
	    	
	        return cell;
	    }
	}
}
