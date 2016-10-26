
package org.com.xsx.UI.MainScene.Report.ReportListPage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Service.ReadReportCountService;
import org.com.xsx.Service.ReadReportService;
import org.com.xsx.UI.MainScene.Report.ReportDetailPage.ReportDetailPage;

import com.sun.javafx.scene.control.skin.PaginationSkin;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class ReportListPage {
	
	private static ReportListPage S_ReportPage = null;
	
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
	TableView<ReportListTableItem>	GB_TableView;
	@FXML
	TableColumn<ReportListTableItem, Integer> TableColumn1;
	@FXML
	TableColumn<ReportListTableItem, String> TableColumn2;
	@FXML
	TableColumn<ReportListTableItem, java.sql.Date> TableColumn3;
	@FXML
	TableColumn<ReportListTableItem, Float> TableColumn4;
	@FXML
	TableColumn<ReportListTableItem, String> TableColumn5;
	@FXML
	TableColumn<ReportListTableItem, String> TableColumn6;
	@FXML
	TableColumn<ReportListTableItem, String> TableColumn7;
	@FXML
	TableColumn<ReportListTableItem, String> TableColumn8;
	
	@FXML
	Pagination GB_Pagination;
	
	@FXML
	StackPane GB_FreshPane;
	@FXML
	Region GB_Viel;
	@FXML
	ProgressIndicator GB_RefreshBar;
	
	ContextMenu myContextMenu;
	MenuItem myMenuItem1;
	
	private ReportListPage(){
		
	}
	
	public static ReportListPage GetInstance() {
		if(S_ReportPage == null){
			S_ReportPage = new ReportListPage();
		}
		
		return S_ReportPage;
	}
	
	public void UI_Init(){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ReportListPage.fxml"));
        InputStream in = this.getClass().getResourceAsStream("ReportListPage.fxml");
        loader.setController(this);
        try {
        	reportpane = loader.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        TableColumn1.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, Integer>("index"));
        TableColumn1.setCellFactory(new TableColumnModel<ReportListTableItem, Integer>());
        
        TableColumn2.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, String>("testitem"));
        TableColumn2.setCellFactory(new TableColumnModel<ReportListTableItem, String>());
        
        TableColumn3.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, java.sql.Date>("testdate"));
        TableColumn3.setCellFactory(new TableColumnModel<ReportListTableItem, java.sql.Date>());
        
        TableColumn4.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, Float>("testresult"));
        TableColumn4.setCellFactory(new TableColumnModel<ReportListTableItem, Float>());
        
        TableColumn5.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, String>("tester"));
        TableColumn5.setCellFactory(new TableColumnModel<ReportListTableItem, String>());
        
        TableColumn6.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, String>("deviceid"));
        TableColumn6.setCellFactory(new TableColumnModel<ReportListTableItem, String>());
        
        TableColumn7.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, String>("simpleid"));
        TableColumn7.setCellFactory(new TableColumnModel<ReportListTableItem, String>());
        
        TableColumn8.setCellValueFactory(new PropertyValueFactory<ReportListTableItem, String>("reportresult"));
        TableColumn8.setCellFactory(new TableColumnModel<ReportListTableItem, String>());
    	
        GB_TableView.itemsProperty().bind(ReadReportService.GetInstance().valueProperty());
        
        GB_RefreshBar.progressProperty().bind(ReadReportService.GetInstance().progressProperty().add(ReadReportCountService.GetInstance().progressProperty()));
        GB_ReportResultFilterCombox.getItems().addAll("All", "未审核", "合格", "不合格");
        
        SignedManager.GetInstance().getGB_SignedManager().addListener(new ChangeListener<Object[]>() {
			@Override
			public void changed(ObservableValue<? extends Object[]> observable, Object[] oldValue, Object[] newValue) {
				// TODO Auto-generated method stub
				if(newValue != null){
					UIMainPage.GetInstance().setGB_Page(ReportListPage.GetInstance().GetReportPane());
				}
				if(newValue != null){
					GB_TestDeviceFilterCombox.getItems().clear();
					GB_TestDeviceFilterCombox.getItems().add("All");
			        GB_TestDeviceFilterCombox.getItems().addAll(SignedManager.GetInstance().GetManagerDeviceIdList());
				}
			}
		});
        
        GB_FreshPane.visibleProperty().bind(new BooleanBinding() {
			
			{
				bind(ReadReportCountService.GetInstance().runningProperty());
				bind(ReadReportService.GetInstance().runningProperty());
			}
			@Override
			protected boolean computeValue() {
				// TODO Auto-generated method stub
				if((ReadReportCountService.GetInstance().isRunning()) || (ReadReportService.GetInstance().isRunning()))
					return true;
				else
					return false;
			}
		});
		
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
					System.out.println(newValue);
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
		
        reportpane.getStylesheets().add(this.getClass().getResource("reportpage.css").toExternalForm());
        
        GB_Pagination.setSkin(new coutompanition(GB_Pagination));
        GB_Pagination.setStyle("-fx-page-information-visible: false");
        
        myMenuItem1 = new MenuItem("删除");
        myContextMenu = new ContextMenu();
        myContextMenu.getItems().add(myMenuItem1);
        
        AnchorPane.setTopAnchor(reportpane, 0.0);
        AnchorPane.setBottomAnchor(reportpane, 0.0);
        AnchorPane.setLeftAnchor(reportpane, 0.0);
        AnchorPane.setRightAnchor(reportpane, 0.0);
        
	}
	
	public void Data_Init(){

		
	}

	public AnchorPane GetReportPane(){	
		ReadReportCountService.GetInstance().restart();
		StartReportService();
		return reportpane;
	}
	
	private void StartReportService(){
		ReadReportService.GetInstance().restart();
	}
	
	@FXML
	public void ClearFilterButtonActionHandle(){
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
		
		ReadReportCountService.GetInstance().restart();
		StartReportService();
	}
	
	@FXML
	public void RefreshButtonActionHandle(){
		ReadReportCountService.GetInstance().restart();
		StartReportService();
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
						
						tooltip.setGraphic(new ReportTipInfo(GB_TableView.getItems().get(row.getIndex()).getReportdata()));
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
					
					if((row != null)&&(row.getIndex() < GB_TableView.getItems().size())){
						if(event.getClickCount() == 2){
							//ReportDetailPage.GetInstance().setS_TestDataBean(GB_TableView.getItems().get(row.getIndex()).getReportdata());
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
	
	class coutompanition extends PaginationSkin {

	    private HBox controlBox;
	    private Button prev;
	    private Button next;
	    private Button first;
	    private Button last;
	    private TextField pageindexinput;
	    private Label pagecountlabel;

	    private void patchNavigation() {
	        Pagination pagination = getSkinnable();

	        Node control = pagination.lookup(".control-box");
	        if (!(control instanceof HBox))
	            return;
	        controlBox = (HBox) control;
	        
	        prev = (Button) controlBox.getChildren().get(0);
	        next = (Button) controlBox.getChildren().get(controlBox.getChildren().size() - 1);
	        
	        first = new Button("首页");
	        first.setAlignment(Pos.CENTER);
	        first.setOnAction(e -> {
	            pagination.setCurrentPageIndex(0);
	        });
	        first.disableProperty().bind(
	                pagination.currentPageIndexProperty().isEqualTo(0));

	        last = new Button("尾页");
	        last.setAlignment(Pos.CENTER);
	        last.setOnAction(e -> {
	            pagination.setCurrentPageIndex(pagination.getPageCount());
	        });
	        last.disableProperty().bind(
	                pagination.currentPageIndexProperty().isEqualTo(
	                        pagination.getPageCount() - 1));
	        
	        pageindexinput = new TextField();
	        pageindexinput.prefColumnCountProperty().bind(pageindexinput.textProperty().length());
	        pageindexinput.setStyle("-fx-font-size: 15; -fx-text-fill: dodgerblue  ;");
	        pageindexinput.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
					if(event.getCode() == KeyCode.ENTER){
						
						if(pageindexinput.getText().length() > 0){

							Integer temp = null;
							
							try {
								temp = Integer.valueOf(pageindexinput.getText());
							} catch (Exception e2) {
								// TODO: handle exception

							}
							
							if(temp != null){
								if(temp > 0)
									temp -= 1;
								if(temp < pagination.getPageCount())
									pagination.setCurrentPageIndex(temp);

							}
						}
					}
				}
			});
	        
	        StringBinding indexlabel = new StringBinding() {
	        	{
	        		bind(pagination.currentPageIndexProperty());
	        	}
				@Override
				protected String computeValue() {
					// TODO Auto-generated method stub
					int index = pagination.getCurrentPageIndex();
					
					return index+1+"";
				}
			};

			pageindexinput.textProperty().bind(indexlabel);
	        pageindexinput.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					if(newValue){
						pageindexinput.textProperty().unbind();
					}	
					else{
						pageindexinput.textProperty().bind(indexlabel);
					}
				}
			});
	        
	        pagecountlabel = new Label("");
	        pagecountlabel.setStyle("-fx-font-size: 15; -fx-text-fill: dodgerblue  ;");
	        pagecountlabel.textProperty().bind(new StringBinding() {
	        	{
	        		bind(pagination.pageCountProperty());
	        	}
				@Override
				protected String computeValue() {
					// TODO Auto-generated method stub
					return "/ "+pagination.getPageCount();
				}
			});
	        
	        ListChangeListener childrenListener = c -> {
	            while (c.next()) {
	                // implementation detail: when nextButton is added, the setup is complete
	                if (c.wasAdded() && !c.wasRemoved() // real addition
	                        && c.getAddedSize() == 1 // single addition
	                        && c.getAddedSubList().get(0) == next) { 
	                    addCustomNodes();
	                }
	            }
	        };
	        controlBox.getChildren().addListener(childrenListener);
	        
	        addCustomNodes();
	    }

	    protected void addCustomNodes() {
	        // guarding against duplicate child exception 
	        // (some weird internals that I don't fully understand...)
	        if (first.getParent() == controlBox) return;
	        controlBox.getChildren().add(0, first);
	        controlBox.getChildren().add(last);
	        controlBox.getChildren().add(controlBox.getChildren().size()/2, pageindexinput);
	        controlBox.setMargin(pageindexinput, new Insets(0, 10, 0, 100));
	        
	        controlBox.getChildren().add(controlBox.getChildren().size()/2 + 1, pagecountlabel);
	        controlBox.setMargin(pagecountlabel, new Insets(0, 100, 0, 0));
	    }

	    /**
	     * @param pagination
	     */
	    public coutompanition(Pagination pagination) {
	        super(pagination);
	        patchNavigation();
	    }
	    
	}
}
