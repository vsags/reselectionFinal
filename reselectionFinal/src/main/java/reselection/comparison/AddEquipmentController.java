package reselection.comparison;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import reselection.Main;
import reselection.model.ComparisonItem;
import reselection.model.Item;
import reselection.results.ItemController;
import reselection.results.ItemList;

//окно для добавления устройств к сравнению

public class AddEquipmentController implements Initializable {
	
	private boolean addedToComparison = false;
	
	@FXML
	private VBox addDevicesBox;
	
	@FXML
	private VBox noDevicesBox;
	
	@FXML
	private VBox controlButtonsBox;
	
	@FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private void addSelectedEquipment() throws IOException {
    	System.out.println(selectedToggles);
    	ComparisonController.cc.addEquipmentToComparison(selectedToggles);
    	selectedToggles.clear();
    	addedToComparison = true;
    
    }
    
    @FXML
    private void closeAddEquipmentView() throws IOException {
    	ComparisonController.cc.closeAddMenu();
    }
    
    private List<Item> results = ItemList.newItems;
    
    private final ObservableSet<Integer> selectedToggles = FXCollections.observableSet();
    
    public final ObservableSet<Integer> getSelectedToggles(){
    	return selectedToggles;
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		int clickedButtonId = ItemController.ic.clickedButtonId;
		
		List<ComparisonItem> items = ComparisonController.cc.items;
		
		List<ToggleButton> tbs = new ArrayList<>();
		tbs.clear();
		
		for (int i = 0; i < items.size(); i++){
			
			if (i != clickedButtonId && !ComparisonController.cc.pushedToggles.contains(i)) {
				
				
				ToggleButton addDeviceButton = new ToggleButton();
				int buttonId = i;
		    	addDeviceButton.setMaxWidth(Double.MAX_VALUE);
		    	addDeviceButton.setText(items.get(i).getModelName());
		    	addDeviceButton.setId("ToggleButton_" + buttonId);
		    	addDeviceButton.setStyle("-fx-font-size:14");
		    	tbs.add(addDeviceButton);
		    	System.out.println(addDeviceButton.getId());
		    	addDevicesBox.getChildren().add(addDeviceButton);
		    	
		    	addDeviceButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
						boolean isSelected = newValue;
						if (isSelected) {
							selectedToggles.add(buttonId);
						} else {
							selectedToggles.remove(buttonId);
						}
						System.out.println(selectedToggles);
					}
		    	});
		    	
		    	}
			
			}
		
		if (tbs.isEmpty() == false) {
			
			VBox hintForAddBox = new VBox();
			List<String> hintPhrase = Arrays.asList("Другие", "устройства,", "которые", "могут", "Вам", "подойти:");
			
			for(int i = 0; i < hintPhrase.size(); i += 2) {
				HBox hboxOfWords = new HBox();
				hboxOfWords.setId("HBox_" + i);
				
				String firstWordOfHBox = hintPhrase.get(i);
				Label firstWordLabel = new Label();
				firstWordLabel.setText(firstWordOfHBox);
				firstWordLabel.setStyle("-fx-font-size:16");
				
			    String secondWordOfHBox = hintPhrase.get(i+1);
			    Label secondWordLabel = new Label();
				secondWordLabel.setText(secondWordOfHBox);
				secondWordLabel.setStyle("-fx-font-size:16");
	
			    hboxOfWords.getChildren().add(firstWordLabel);
			    hboxOfWords.getChildren().add(secondWordLabel);
			    hboxOfWords.setSpacing(5);
			    hboxOfWords.setAlignment(Pos.CENTER);
			    
			    hintForAddBox.getChildren().add(hboxOfWords);
			    
			    System.out.println(firstWordOfHBox);
			    System.out.println(secondWordOfHBox);
			}
			
			addDevicesBox.setAlignment(Pos.CENTER);
			hintForAddBox.setStyle("-fx-padding: 0 0 20 0;");
			addDevicesBox.getChildren().add(0, hintForAddBox);
			
			Button addDevicesButton = new Button();
			addDevicesButton.setText("Добавить");
			addDevicesButton.setStyle("-fx-font-size:14");
				
			addDevicesButton.setOnAction(e -> {
				try {
					if (selectedToggles.isEmpty()) {
						showAlertIfNothingIsAdded();
					}
					ComparisonController.cc.addEquipmentToComparison(selectedToggles);
					selectedToggles.clear();
					ComparisonController.cc.closeAddMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			addDevicesBox.getChildren().remove(noDevicesBox);
			controlButtonsBox.getChildren().add(0, addDevicesButton);
		} 
		
		
	}
	
	private void showAlertIfNothingIsAdded() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Предупреждение");
		alert.setHeaderText("Выберите устройства, которые хотите добавить к сравнению");
		alert.setContentText("или нажмите «Закрыть»");

		alert.showAndWait();
	}
}
    
