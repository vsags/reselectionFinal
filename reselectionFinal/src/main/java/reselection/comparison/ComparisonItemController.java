package reselection.comparison;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import reselection.Main;
import reselection.model.ComparisonItem;

//характеристики добавленного устройства

public class ComparisonItemController {
	
	private Main main;
	
	@FXML
	private VBox itemBox;
	
	@FXML
	private GridPane titleGridPane;
	
	@FXML
	private GridPane parametersGridPane;
	
	@FXML
    private ImageView damperImage;
	
	@FXML
    private Label modelNameLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
    private Label vcLabel;
	
	@FXML
	private HBox clearButtonBox;
	
	@FXML
    private Label manufacturerLabel;
	
	@FXML
	private Label purposeLabel;
	
	@FXML
	private Label replacementCommentsLabel;
	
	@FXML
	private Label weightLabel;
	
	@FXML
    private Label warrantyLabel;
	
	@FXML
    private Label countryLabel;
	
	@FXML
    private Label typeDacLabel;
	
	@FXML
    private Label returnSpringLabel;
	
	@FXML
    private Label voltageLabel;
	
	@FXML
    private Label effortLabel;
	
	@FXML
    private Label damperAreaLabel;
	
	@FXML
    private Label controlSignalLabel;
	
	@FXML
    private Label timePositionLabel;
	
	@FXML
    private Label timeOpenLabel;
	
	@FXML
    private Label timeCloseLabel;
	
	@FXML
    private Label addSwitchLabel;
	
	@FXML
    private Label stockPerimeterLabel;
	
	@FXML
    private Label stockDiameterLabel;
	
	@FXML
    private Label connectionTypeLabel;
	
	@FXML
    private Label ipClassLabel;
	
	@FXML
	private Label thermalDeviceLabel;
	
	@FXML
	private Label thermalDeviceTemperatureLabel;

	private ComparisonItem comparisonItem;
	
	public void setData(ComparisonItem comparisonItem) {
		this.comparisonItem = comparisonItem;
		
		Image damperImg = new Image (getClass().getResourceAsStream(comparisonItem.getDamperImage()));
		damperImage.setImage(damperImg);
		
		modelNameLabel.setText(comparisonItem.getModelName());
		vcLabel.setText(comparisonItem.getVc());
		
		if (comparisonItem.getPrice().replace(" ", "").equals("") || comparisonItem.getPrice() == null){
			priceLabel.setText(" - ");
		} else {
			priceLabel.setText(comparisonItem.getPrice() + " € без НДС");
		}
		//priceLabel.setText(comparisonItem.getPrice() + " € без НДС");
		manufacturerLabel.setText(comparisonItem.getManufacturer());
		purposeLabel.setText(comparisonItem.getPurpose());
		
		if (comparisonItem.getReplacementComments().replace(" ", "").equals("") || comparisonItem.getReplacementComments() == null){
			replacementCommentsLabel.setText(" - ");
		} else {
			replacementCommentsLabel.setText(comparisonItem.getReplacementComments());
		}
		
		weightLabel.setText(comparisonItem.getWeight() + " кг");
		
		if (comparisonItem.getWarranty().replace(" ", "").equals("") || comparisonItem.getWarranty() == null){
			warrantyLabel.setText(" - ");
		} else {
			warrantyLabel.setText(comparisonItem.getWarranty() + " лет");
		}
		
		//warrantyLabel.setText(comparisonItem.getWarranty() + " лет");
		
		if (comparisonItem.getCountry().replace(" ", "").equals("") || comparisonItem.getCountry() == null){
			countryLabel.setText(" - ");
		} else {
			countryLabel.setText(comparisonItem.getCountry());
		}
		
		//countryLabel.setText(comparisonItem.getCountry());
		
		if (comparisonItem.getTypeDac().replace(" ", "").equals("") || comparisonItem.getTypeDac() == null){
			typeDacLabel.setText(" - ");
		} else {
			typeDacLabel.setText(comparisonItem.getTypeDac());
		}
		
		
		returnSpringLabel.setText(comparisonItem.getReturnSpring());
		voltageLabel.setText(comparisonItem.getVoltage());
		effortLabel.setText(comparisonItem.getEffort() + " Нм");
		damperAreaLabel.setText(comparisonItem.getDamperArea() + " м²");
		controlSignalLabel.setText(comparisonItem.getControlSignal());
		timePositionLabel.setText(comparisonItem.getTimePosition() + " с");
		timeOpenLabel.setText(comparisonItem.getTimeOpen() + " с");
		timeCloseLabel.setText(comparisonItem.getTimeClose() + " с");
		addSwitchLabel.setText(comparisonItem.getAddSwitch());
		
		if (comparisonItem.getStockPerimeter().replace(" ", "").equals("") || comparisonItem.getStockPerimeter() == null || comparisonItem.getStockPerimeter().replace(" ", "").equals("нетданных")){
			stockPerimeterLabel.setText(" - ");
		} else {
			stockPerimeterLabel.setText(comparisonItem.getStockPerimeter() + " мм");
		}
		
		if (comparisonItem.getStockDiameter().replace(" ", "").equals("") || comparisonItem.getStockDiameter() == null || comparisonItem.getStockDiameter().replace(" ", "").equals("нетданных")){
			stockDiameterLabel.setText(" - ");
		} else {
			stockDiameterLabel.setText(comparisonItem.getStockDiameter() + " мм");
		}
		
		//stockPerimeterLabel.setText(comparisonItem.getStockPerimeter() + " мм");
		//stockDiameterLabel.setText(comparisonItem.getStockDiameter() + " мм");
		connectionTypeLabel.setText(comparisonItem.getConnectionType());
		ipClassLabel.setText(comparisonItem.getIpClass());
		
		if (comparisonItem.getThermalDevice().replace(" ", "").equals("") || comparisonItem.getThermalDevice() == null){
			thermalDeviceLabel.setText("нет");
		} else {
			thermalDeviceLabel.setText(comparisonItem.getThermalDevice());
		}
		
		if (comparisonItem.getThermalDeviceTemperature().replace(" ", "").equals("") || comparisonItem.getThermalDeviceTemperature() == null){
			thermalDeviceTemperatureLabel.setText(" - ");
		} else {
			thermalDeviceTemperatureLabel.setText(comparisonItem.getThermalDeviceTemperature());
		}
		
		
		
	}
	
	/**
	private Label checkIfEmpty(Label label, String attribute) {
		
		if (attribute == null) {
			label.setText(" - ");
		}
		
		return label;
	}
	*/
	
}
