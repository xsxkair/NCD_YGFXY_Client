package org.com.xsx.UI.MainScene.Report;

import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class ReportRootPage {

	private static ReportRootPage S_ReportRootPage = null;
	
	private AnchorPane S_ReportRootPane = null;
	
	private ObjectProperty<Node> S_ReportChildPane = null;
	
	private ReportRootPage() {
		
	}
	
	public static ReportRootPage GetInstance() {
		if(S_ReportRootPage == null){
			S_ReportRootPage = new ReportRootPage();
			
			S_ReportRootPage.UI_Init();
		}
		
		return S_ReportRootPage;
	}
	
	public void UI_Init() {
		S_ReportRootPane = new AnchorPane();
		
		AnchorPane.setTopAnchor(S_ReportRootPane, 0.0);
        AnchorPane.setBottomAnchor(S_ReportRootPane, 0.0);
        AnchorPane.setLeftAnchor(S_ReportRootPane, 0.0);
        AnchorPane.setRightAnchor(S_ReportRootPane, 0.0);
        
        S_ReportChildPane = new SimpleObjectProperty<>();
        
        S_ReportChildPane.addListener(new ChangeListener<Node>() {

			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				// TODO Auto-generated method stub
				S_ReportRootPane.getChildren().clear();
				S_ReportRootPane.getChildren().add(newValue);
			}
		});
	}
	
	public AnchorPane GetReportRootPane(){
		S_ReportRootPane.getChildren().clear();
		S_ReportRootPane.getChildren().add(ReportListPage.GetInstance().GetReportPane());
		return S_ReportRootPane;
	}
	
	public void SetReportContentPane(Node pane){
		this.S_ReportChildPane.set(pane);
	}
}
