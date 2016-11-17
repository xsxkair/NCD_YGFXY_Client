package org.com.xsx.UI.MainScene.CardPage;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.com.xsx.Dao.CardRecordDao;
import org.com.xsx.Data.UIMainPage;
import org.com.xsx.Domain.CardRecordBean;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class CardRecordPage {
	
	private static CardRecordPage S_CardRecordPage = null;
	
	private AnchorPane rootpane;
	
	//��ʾͼ��
	@FXML
	AnchorPane GB_MainPane;
	
		//�ܿ��
		@FXML
		PieChart GB_CardRepertoryPieChart;
		//�Ҽ��˵�
		ContextMenu myContextMenu;
		MenuItem myMenuItem1 = new MenuItem("ˢ��");
		MenuItem myMenuItem2 = new MenuItem("�鿴����");
		
		//��ʾ���صȴ�����
		@FXML
		StackPane GB_FreshPane;
		@FXML
		ProgressIndicator GB_RefreshBar;
		
		//�豸���ͼ
		@FXML
		VBox GB_DeviceVBox;

		@FXML
		FlowPane GB_ItemFlowPane;
		ToggleGroup GB_ItemToggleGroup = new ToggleGroup();
		@FXML
		FlowPane GB_DeviceFlowPane;
		ToggleGroup GB_DeviceToggleGroup = new ToggleGroup();
		
		@FXML
		LineChart<String, Number> GB_CardLineChart;
		@FXML
		CategoryAxis GB_CardLineXAxis;
		@FXML
		NumberAxis GB_CardLineYAxis;
		//�Ҽ��˵�
		ContextMenu myContextMenu1;
		MenuItem myMenuItem11 = new MenuItem("ˢ��");
		MenuItem myMenuItem21 = new MenuItem("�鿴����");
		
		//��ʾ���صȴ�����
		@FXML
		StackPane GB_FreshPane1;
		@FXML
		ProgressIndicator GB_RefreshBar1;
	
	//��ʾ���������¼
	@FXML
	StackPane GB_CardDetailPane;
	@FXML
	TableView<CardRecordBean> GB_CardTableView;
	@FXML
	TableColumn<CardRecordBean, java.sql.Timestamp> GB_TableColumn1;
	@FXML
	TableColumn<CardRecordBean, String> GB_TableColumn2;
	@FXML
	TableColumn<CardRecordBean, Integer> GB_TableColumn3;
	@FXML
	TableColumn<CardRecordBean, String> GB_TableColumn4;
	@FXML
	TableColumn<CardRecordBean, String> GB_TableColumn5;
	@FXML
	TableColumn<CardRecordBean, String> GB_TableColumn6;
	
	@FXML
	Pagination GB_Pagination;
	
	//��ʾ���صȴ�����
	@FXML
	StackPane GB_FreshPane2;
	@FXML
	ProgressIndicator GB_RefreshBar2;
	
	//��ѯ�ܿ��
	private ObservableList<PieChart.Data> GB_CardSummyChartData;
	private QueryCardRecordService S_QueryCardSummyService;
	//��ѯ�豸���
	private String S_FilterItem0 = null;
	private String S_FilterDeviceID0 = null;
	private QueryCardRecordService S_QueryCardDeviceService;
	//��ѯ������¼
	private String S_FilterItem = null;
	private String S_FilterDeviceID = null;
	private Integer S_FilterPageIndex = 0;
	private Boolean S_FilterIsGetPageNum = true;
	private QueryCardRecordService S_QueryCardRecordService;
	
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
        
        rootpane.getStylesheets().add(this.getClass().getResource("cardrecordpage.css").toExternalForm());
        
        //��ѯ�ܿ��
        GB_CardSummyChartData = FXCollections.observableArrayList();
        GB_CardRepertoryPieChart.setData(GB_CardSummyChartData);
        
        //�Ҽ��˵�
        myContextMenu = new ContextMenu(myMenuItem1, myMenuItem2);
        //ˢ��
        myMenuItem1.setOnAction(new EventHandler<ActionEvent>() {

        	@Override
        	public void handle(ActionEvent event) {
      				// TODO Auto-generated method stub
        		S_QueryCardSummyService.restart();
        	}
        });
      		
        //�鿴����
        myMenuItem2.setOnAction(new EventHandler<ActionEvent>() {

        	@Override
        	public void handle(ActionEvent event) {
      				// TODO Auto-generated method stub
        		GB_CardDetailPane.setVisible(true);
        		
        		GB_MainPane.setEffect(new GaussianBlur(5));
        		
        		S_FilterItem = (String) myContextMenu.getUserData();
        		S_FilterIsGetPageNum = true;
        		S_FilterDeviceID = null;
        		
        		S_QueryCardRecordService.setParm(new Object[]{S_FilterItem, S_FilterDeviceID, S_FilterPageIndex, S_FilterIsGetPageNum});
        		S_QueryCardRecordService.restart();
        	}
        });
        
        S_QueryCardSummyService = new QueryCardRecordService("��ѯ�ܿ��");
        GB_FreshPane.visibleProperty().bind(S_QueryCardSummyService.runningProperty());
        GB_RefreshBar.progressProperty().bind(S_QueryCardSummyService.progressProperty());
        
        S_QueryCardSummyService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if(newValue == null){
					GB_CardSummyChartData.clear();
				}
				else{
					Map<String, Integer> data = (Map<String, Integer>) newValue;

					Set<String> keyset = data.keySet();
					for (String string : keyset) {
						Data temp = new Data(string, data.get(string));
						GB_CardSummyChartData.add(temp);
						
						HBox tempbox = new HBox();
						tempbox.setAlignment(Pos.CENTER);
	
						Label label2 = new Label(string);
						label2.getStyleClass().add("textstyle1");
						
						Label label5 = new Label(" : ���  ");
						label5.setFont(new Font("System", 16));
						
						Label label4 = new Label((int)(temp.getPieValue())+"");
						label4.getStyleClass().add("textstyle2");
						
						Label label1 = new Label(" �˷� ");
						label1.setFont(new Font("System", 16));
						
						tempbox.getChildren().addAll(label2, label5, label4, label1);
						tempbox.setSpacing(5);
						
						Tooltip tooltip = new Tooltip();
						tooltip.setGraphic(tempbox);
				        Tooltip.install(temp.getNode(), tooltip);
				        
				        temp.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent event) {
								// TODO Auto-generated method stub
								if(event.getButton().equals(MouseButton.SECONDARY)){
									myContextMenu.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
									myContextMenu.setUserData(temp.getName());
								}
							}

						});
					}
				}
			}
		});
        
        //�豸���
        S_QueryCardDeviceService = new QueryCardRecordService("��ѯ�豸���");
        GB_FreshPane1.visibleProperty().bind(S_QueryCardDeviceService.runningProperty());
        GB_RefreshBar1.progressProperty().bind(S_QueryCardDeviceService.progressProperty());
        
        //�Ҽ��˵�
        myContextMenu1 = new ContextMenu(myMenuItem11, myMenuItem21);
        //ˢ��
        myMenuItem11.setOnAction(new EventHandler<ActionEvent>() {

        	@Override
        	public void handle(ActionEvent event) {
      				// TODO Auto-generated method stub
        		FreshFilterData();
        	}
        });
      		
        //�鿴����
        myMenuItem21.setOnAction(new EventHandler<ActionEvent>() {

        	@Override
        	public void handle(ActionEvent event) {
      				// TODO Auto-generated method stub
 /*       		GB_CardDetailPane.setVisible(true);
        		
        		GB_MainPane.setEffect(new GaussianBlur(5));
        		
        		Object[] userdata = (Object[]) myContextMenu.getUserData();
        		S_FilterItem = (String) userdata[0];
        		S_FilterDeviceID = (String) userdata[1];
        		S_FilterIsGetPageNum = true;
        		
        		S_QueryCardRecordService.setParm(new Object[]{S_FilterItem, S_FilterDeviceID, S_FilterPageIndex, S_FilterIsGetPageNum});
        		S_QueryCardRecordService.restart();*/
        	}
        });
        
        GB_DeviceVBox.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getButton().equals(MouseButton.SECONDARY)){
					myContextMenu1.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
					myContextMenu1.setUserData(new Object[]{((ToggleButton)GB_ItemToggleGroup.getSelectedToggle()).getText(),
							((ToggleButton)GB_DeviceToggleGroup.getSelectedToggle()).getText()});
				}
			}
		});
        
        GB_ItemToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				S_FilterItem0 = ((ToggleButton)newValue).getText();

				S_QueryCardDeviceService.setParm(new Object[]{S_FilterItem0, S_FilterDeviceID0});
				S_QueryCardDeviceService.restart();
			}
		});
        GB_DeviceToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				S_FilterDeviceID0 = ((ToggleButton)newValue).getText();

				S_QueryCardDeviceService.setParm(new Object[]{S_FilterItem0,  S_FilterDeviceID0});
				S_QueryCardDeviceService.restart();
			}
		});
        
        S_QueryCardDeviceService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //��ϸ������¼
        S_QueryCardRecordService = new QueryCardRecordService("��ѯ������¼");
        GB_FreshPane2.visibleProperty().bind(S_QueryCardRecordService.runningProperty());
        GB_RefreshBar2.progressProperty().bind(S_QueryCardRecordService.progressProperty());
        
        GB_TableColumn1.setCellValueFactory(new PropertyValueFactory<CardRecordBean, java.sql.Timestamp>("dotime"));
        
        GB_TableColumn2.setCellValueFactory(new PropertyValueFactory<CardRecordBean, String>("item"));
        
        GB_TableColumn3.setCellValueFactory(new PropertyValueFactory<CardRecordBean, Integer>("num"));
        
        GB_TableColumn4.setCellValueFactory(new PropertyValueFactory<CardRecordBean, String>("handler"));
        
        GB_TableColumn5.setCellValueFactory(new PropertyValueFactory<CardRecordBean, String>("name"));
        
        GB_TableColumn6.setCellValueFactory(new PropertyValueFactory<CardRecordBean, String>("deviceid"));
        
        GB_Pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				S_FilterPageIndex = newValue.intValue()*50;
				S_QueryCardRecordService.setParm(new Object[]{S_FilterItem, S_FilterDeviceID, S_FilterPageIndex, S_FilterIsGetPageNum});
        		S_QueryCardRecordService.restart();
			}
		});
        
        S_QueryCardRecordService.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				
				if(newValue != null){
					
					Object[] data = (Object[]) newValue;
					//����ҳ��
					BigInteger totalnum = (BigInteger) data[1];

					if(totalnum != null){

						GB_Pagination.setPageCount((int) ((totalnum.longValue()%50 == 0)?(totalnum.longValue()/50):(totalnum.longValue()/50+1)));
							
						GB_Pagination.setCurrentPageIndex(0);
					}

					//��������
					GB_CardTableView.getItems().clear();
					
					List<CardRecordBean> list = (List<CardRecordBean>) data[0];

					GB_CardTableView.getItems().addAll(list);
					
					S_FilterIsGetPageNum = false;
				}
			}
		});
      
      		
        UIMainPage.GetInstance().getGB_Page().addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				// TODO Auto-generated method stub
				if(rootpane.equals(newValue)){
					
					GB_CardDetailPane.setVisible(false);
					
					GB_MainPane.setEffect(null);
					GB_MainPane.getStyleClass().remove("backeffict");
					
					//��ѯ�ܿ��
					S_QueryCardSummyService.restart();
					
					//��ѯ�豸�����Ŀ���豸�б�
					FreshFilterData();
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
	
	private void FreshFilterData() {
		List<String> items = CardRecordDao.QueryOutBoundItemList();
		List<String> devices = CardRecordDao.QueryOutBoundDeviceList();
		
		GB_ItemFlowPane.getChildren().clear();
		for (String string : items) {
			RadioButton rb = new RadioButton(string);
			rb.setToggleGroup(GB_ItemToggleGroup);
			GB_ItemFlowPane.getChildren().add(rb);
		}
		
		GB_DeviceFlowPane.getChildren().clear();
		for (String string : devices) {
			RadioButton rb = new RadioButton(string);
			rb.setToggleGroup(GB_DeviceToggleGroup);
			GB_DeviceFlowPane.getChildren().add(rb);
		}
		
		GB_DeviceToggleGroup.selectToggle((Toggle) GB_DeviceFlowPane.getChildren().get(0));
		GB_ItemToggleGroup.selectToggle((Toggle) GB_ItemFlowPane.getChildren().get(0));
	}
	
	@FXML
	public void GB_CloseDetailPaneAction(){
		GB_CardDetailPane.setVisible(false);
		
		GB_MainPane.setEffect(null);
	}

}
