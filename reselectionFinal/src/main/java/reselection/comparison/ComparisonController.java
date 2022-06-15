package reselection.comparison;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.equipment.Equipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import reselection.Main;
import reselection.model.ComparisonItem;
import reselection.model.Item;
import reselection.results.EquipmentList;
import reselection.results.FoundEquipmentList;
import reselection.results.ItemController;

// окно сравнения характеристик устройств

public class ComparisonController implements Initializable {
	
	private Main main;
	
	protected static ComparisonController cc;
	
	protected final List<ComparisonItem> foundItems = new ArrayList<>();
	protected final List<ComparisonItem> items = new ArrayList<>();
	
	protected final ObservableSet<Integer> pushedToggles = FXCollections.observableSet();
	
	Image closeButtonImage = new Image(getClass().getResourceAsStream("/closeButton.png"));
	ImageView closeButtonImageView = new ImageView(closeButtonImage);
	
	@FXML
    private BorderPane ComparisonBorderPane;
	
	@FXML
	private ScrollPane CompareTab;
	
	@FXML
	protected HBox CompareBox;
	
    @FXML
    private BorderPane addEquipmentPane;
	
	@FXML
    private Button addEquipmentButton;

    @FXML
    private Button backButton;
    
    @FXML
    private void addToComparison() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(Main.class.getResource("comparison/AddEquipmentView.fxml"));
		 BorderPane addToComparisonPane = loader.load();
		 ComparisonBorderPane.setRight(addToComparisonPane);
    }
    
    protected void addEquipmentToComparison(ObservableSet<Integer> selectedToggles) throws IOException {
    	
    	ComparisonItem item;
    	
    	for (int i : selectedToggles) {
    		
    		FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ComparisonItem3.fxml"));
			VBox compareItemBox = loader.load();
			
			HBox boxForCloseButton = new HBox();
			Button closeButton = new Button();
			//closeButton.setGraphic(closeButtonImageView);
			closeButton.setGraphic(new ImageView(closeButtonImage));
			closeButton.setStyle("-fx-background-radius: 10em; "
					+ "-fx-background-color: #ffffff; ");
			closeButton.setEffect(new DropShadow(7, Color.BLACK));
			
			boxForCloseButton.getChildren().add(closeButton);
			boxForCloseButton.setAlignment(Pos.TOP_RIGHT);
			boxForCloseButton.setPadding(new Insets(5, 0, 0, 0));
			boxForCloseButton.setMinHeight(25);
			boxForCloseButton.setPrefHeight(25);
			boxForCloseButton.setMaxHeight(25);
			
			//closeButton.setText("Закр");
			//closeButton.setGraphic(closeButtonImageView);
			closeButton.setOnAction(e -> {
				CompareBox.getChildren().remove(compareItemBox);
				pushedToggles.remove(i);
			});
			
			compareItemBox.getChildren().add(0, boxForCloseButton);
			
			ComparisonItemController cic = loader.getController();
			cic.setData(items.get(i));
			
			CompareBox.getChildren().add(compareItemBox);
			
			pushedToggles.add(i);
    		
    		System.out.println(items.get(i) + " - это передается из другого контроллера");
    		System.out.println(items.size());
    	}
    	
    }
    
    protected void closeAddMenu() {
    	ComparisonBorderPane.setRight(null);
    }
    
    protected void deleteComparisonItem() {
    	CompareBox.getChildren().remove(CompareBox.lookup("addDeviceButton"));
    	CompareBox.getChildren().remove(CompareBox.lookup(".togglebutton"));
    }
    
    @FXML
    private void backToResults() throws IOException {
    	main.showDacScene();
    	main.showDacResults();
    }
    
    //protected List<ComparisonItem> items = new ArrayList<>();
    
    private ComparisonItem getDatafoundEq(List<Equipment> foundEquipment){
    	
    	List<ComparisonItem> items = new ArrayList<>();
    	ComparisonItem item;
    	
    	for (Equipment equipment : foundEquipment) {
    			
    			item = new ComparisonItem();
    			item.setDamperImage("/" + equipment.getManufacturer().replace(" ", "").toLowerCase() + "Dac.png");
    			item.setModelName(equipment.getModel());
    			item.setVc(equipment.getVc());
    			item.setPrice(equipment.getPrice());
    			item.setManufacturer(equipment.getManufacturer());
    			item.setPurpose(equipment.getPurpose());
    			item.setReplacementComments(equipment.getReplacementComments());
    			item.setWeight(equipment.getWeight());
    			item.setWarranty(equipment.getWarranty());
    			item.setCountry(equipment.getCountry());
    			item.setTypeDac(equipment.getTypeDac());
    			item.setReturnSpring(equipment.getReturnSpring());
    			item.setVoltage(equipment.getVoltage());
    			item.setEffort(equipment.getEffort());
    			item.setDamperArea(equipment.getDamperArea());
    			item.setControlSignal(equipment.getControlSignal());
    			item.setTimePosition(equipment.getTimePosition());
    			item.setTimeOpen(equipment.getTimeOpen());
    			item.setTimeClose(equipment.getTimeClose());
    			item.setAddSwitch(equipment.getAddSwitch());
    			item.setStockPerimeter(equipment.getStockPerimeter());
    			item.setStockDiameter(equipment.getStockDiameter());
    			item.setConnectionType(equipment.getConnectionType());
    			item.setIpClass(equipment.getIpClass());
    			item.setThermalDevice(equipment.getThermalDevice());
    			item.setThermalDeviceTemperature(equipment.getThermalDeviceTemperature());
    			items.add(item);
    	}
		
    	return items.get(0);
    }
    
    
    private List<ComparisonItem> getDataEq(List<Equipment> foundEquipment){
    	
    	List<ComparisonItem> items = new ArrayList<>();
    	ComparisonItem item;
    	
    	for (Equipment equipment : foundEquipment) {
    			
    			item = new ComparisonItem();
    			item.setDamperImage("/siemensDac.png");
    			item.setModelName(equipment.getModel());
    			item.setVc(equipment.getVc());
    			item.setPrice(equipment.getPrice());
    			item.setManufacturer(equipment.getManufacturer());
    			item.setPurpose(equipment.getPurpose());
    			item.setReplacementComments(equipment.getReplacementComments());
    			item.setWeight(equipment.getWeight());
    			item.setWarranty(equipment.getWarranty());
    			item.setCountry(equipment.getCountry());
    			item.setTypeDac(equipment.getTypeDac());
    			item.setReturnSpring(equipment.getReturnSpring());
    			item.setVoltage(equipment.getVoltage());
    			item.setEffort(equipment.getEffort());
    			item.setDamperArea(equipment.getDamperArea());
    			item.setControlSignal(equipment.getControlSignal());
    			item.setTimePosition(equipment.getTimePosition());
    			item.setTimeOpen(equipment.getTimeOpen());
    			item.setTimeClose(equipment.getTimeClose());
    			item.setAddSwitch(equipment.getAddSwitch());
    			item.setStockPerimeter(equipment.getStockPerimeter());
    			item.setStockDiameter(equipment.getStockDiameter());
    			item.setConnectionType(equipment.getConnectionType());
    			item.setIpClass(equipment.getIpClass());
    			item.setThermalDevice(equipment.getThermalDevice());
    			item.setThermalDeviceTemperature(equipment.getThermalDeviceTemperature());
    			items.add(item);
    	}
		
    	return items;
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cc = this;
		
		//CompareTab.getStylesheets().add(this.getClass().getResource("/scrollbar.css").toExternalForm());
		
		foundItems.clear();
		foundItems.add(getDatafoundEq(FoundEquipmentList.foundEquipment));
		
		items.clear();
		items.addAll(getDataEq(EquipmentList.foundEquipment));
		System.out.println(items);
		int clickedButtonId = ItemController.ic.clickedButtonId;
		System.out.println("Была нажата кнопка номер " + clickedButtonId);
		
		System.out.println("Добавлены к сравнению элементы под номерами" + pushedToggles);
		
		try {
			
			for (int i = 0; i < foundItems.size(); i++) {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ComparisonItem3.fxml"));
				VBox compareItemBox = loader.load();
				
				HBox boxForCloseButton = new HBox();
				boxForCloseButton.setPrefHeight(40);
				Button closeButton = new Button();
				
				boxForCloseButton.getChildren().add(closeButton);
				boxForCloseButton.setAlignment(Pos.TOP_RIGHT);
				boxForCloseButton.setPrefHeight(40);
				boxForCloseButton.setMaxHeight(40);
				
				closeButton.setText("Закр");
				closeButton.setVisible(false);
				closeButton.setOnAction(e -> {
					CompareBox.getChildren().remove(compareItemBox);
				});
				
				compareItemBox.getChildren().add(0, boxForCloseButton);
				ComparisonItemController cic = loader.getController();
				cic.setData(foundItems.get(i));
				
				CompareBox.getChildren().add(compareItemBox);
				
				//CompareTab.getStyleClass().add("edge-to-edge");
				CompareTab.setFitToWidth(true);
				
			}
			
			for (int i = 0; i < items.size(); i++) {
				
				
				if (i == clickedButtonId) {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("ComparisonItem3.fxml"));
					VBox compareItemBox = loader.load();
					
					HBox boxForCloseButton = new HBox();
					boxForCloseButton.setPrefHeight(40);
					Button closeButton = new Button();
					
					boxForCloseButton.getChildren().add(closeButton);
					boxForCloseButton.setAlignment(Pos.TOP_RIGHT);
					boxForCloseButton.setPrefHeight(40);
					
					closeButton.setText("Закр");
					closeButton.setVisible(false);
					closeButton.setOnAction(e -> {
						CompareBox.getChildren().remove(compareItemBox);
					});
					
					compareItemBox.getChildren().add(0, boxForCloseButton);
					
					//Node nodeOut = (Node) compareItemBox.getChildren();
					//System.out.println(nodeOut);
					
					ComparisonItemController cic = loader.getController();
					cic.setData(items.get(i));
					
					CompareBox.getChildren().add(compareItemBox);
					
					//CompareTab.getStyleClass().add("edge-to-edge");
					CompareTab.setFitToWidth(true);
					System.out.println("Вкладка сравнения открыта");			
				}
			}
		
	} catch (IOException e) {
		e.printStackTrace();
	}

}
}
