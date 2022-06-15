package reselection.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import db.connector.EquipmentDbDao;
import db.connector.EquipmentManager;
import db.equipment.Equipment;
import db.exceptions.EquipmentBusinessException;
import db.exceptions.EquipmentDaoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import reselection.Main;
import reselection.model.Item;
import reselection.results.EquipmentList;
import reselection.results.ItemList;
import reselection.results.ItemListFill;
import org.controlsfx.control.textfield.TextFields;

//класс, отвечающий за поиск устройств

public class SearchViewController extends BorderPane {
	
	private Main main;
	
	private final EquipmentManager equipmentManager = new EquipmentManager();
	
	private List<Equipment> dropDownList = new ArrayList<Equipment>();

	ObservableList<String> firmList = FXCollections
			.observableArrayList("", "Belimo", "Danfoss", "Gruner", "Honeywell", "Lufberg", "Siemens", "Schneider Electric");
	
	
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private BorderPane searchPane;
	
	@FXML
	private VBox titleVBox;
	
	@FXML
	protected ComboBox<String> firmBox;
	
	//@FXML
	//protected TextField modelTField;
	
	@FXML
	protected ComboBox<Equipment> searchBox;
	
	//@FXML
	//protected AutoCompletionTextField modelField;
	
	@FXML
	protected TextField vcTField;
	
	@FXML
	private Button clearButton;
	
	@FXML
    private Button homeButton;
	
	@FXML
	private Button searchButton;
	
	
	
	protected List<Item> items = new ArrayList<>();
	private Image image;
	
	@FXML
	private void initialize() throws EquipmentDaoException, SQLException {
		
		firmBox.setItems(firmList);
		firmBox.setStyle("-fx-font-size:14");
		firmBox.setValue("");
		
		searchBox.setStyle("-fx-font-size:14");
		
		try {
			dropDownList.clear();
			dropDownList = equipmentManager.searchEquipmentForComboBox();
		} catch (EquipmentBusinessException e) {
			e.printStackTrace();
		}
		
		searchBox.getItems().clear();
		searchBox.getItems().addAll(dropDownList);
		
		searchBox.setConverter(new StringConverter<Equipment>() {
            
			@Override
			public String toString(Equipment equip) {
                //return equip == null? "----- ": equip.getModel() + " — " + equip.getVc();
				if (equip != null){
					if (equip.getVc().isEmpty()) {
						return equip.getModel();
					} else {
						return equip.getModel() + " — " + equip.getVc();
					}
				} else {
					return null;
				}
            }

            @Override
            public Equipment fromString(String string) {
            	Equipment eq = new Equipment();
            	
            	if (string != null) {
            		String[] attributes = string.strip().split(" — ");
            		
            		System.out.println("КОЛ-ВО АТТРИБУТОВ: " + attributes.length);
                	
                	if (attributes.length == 1) {
                		eq.setModel(attributes[0]);
                		eq.setVc("");
                	} else {
                		eq.setModel(attributes[0]);
                    	eq.setVc(attributes[1]);
                	}
                	
                	System.out.println("Вот это мы получаем из комбобокса: " + string);
                	System.out.println("Вот разделенный список: " + attributes.length);
                	System.out.println("А вот это наша оборудка: " + eq);
                    return eq;
            	}
            	
            	return null;
            }
		});
		
		
		AutoCompleteComboBoxListener<Equipment> comboBox = new AutoCompleteComboBoxListener(searchBox, false);
		
		//AutoCompletionTextField field = new AutoCompletionTextField();
		//field.getEntries().addAll(firmList);
		//modelTField = field;
		
		//modelField.getEntries().addAll(firmList);
		
	}
	
	
	//фильтр всплывающего списка
	@FXML
	private void chooseFirm() throws EquipmentBusinessException {
		
		String firm = (String) firmBox.getValue().toUpperCase();
		switch(firm) {
		case "":
			searchBox.setValue(null);
			dropDownList.clear();
			dropDownList = equipmentManager.searchEquipmentForComboBox();
			searchBox.getItems().clear();
			searchBox.getItems().addAll(dropDownList);
			break;
		default:
			searchBox.setValue(null);
			dropDownList.clear();
			dropDownList = equipmentManager.searchEquipmentForComboBoxWithFirm(firm);
			searchBox.getItems().clear();
			searchBox.getItems().addAll(dropDownList);
		}
		
	}
	
	//кнопка "на главную"
	@FXML
	private void goHome() throws IOException {
		main.backToMain();
	}
	
	//кнопка очистки полей для поиска
	@FXML
	private void clearAllFields() throws IOException, EquipmentBusinessException {
		firmBox.setValue("");
		//modelTField.clear();
		searchBox.setValue(null);
		
		main.clearSearchResults();
	}
	
	//реализация поиска: вывод графически результатов поисковой выдачи, формирование списка найденных устройств
	@FXML
	public List<Item> showResults() throws IOException, EquipmentBusinessException, IllegalAccessException {
		
		List<Item> itemzzz = new LinkedList<>();
		
		//boolean isFirmBoxEmpty = firmBox.getSelectionModel().isEmpty();
		//boolean isModelTField = modelTField.getText().isEmpty();
		//boolean isSearchBoxEmpty = searchBox.getSelectionModel().getSelectedItem().toString().isEmpty();
		
		if(//isModelTField == true
				searchBox.getValue() == null || searchBox.getValue().toString().replace(" ", "").replace("—", "").equals("")) {
			
			showAlertWithHeaderText();
			
			return null;
		
		} else {
			
			Equipment eq = new Equipment();
			eq = searchFields();
			
			ItemListFill itemListFill = new ItemListFill();
			ItemList itemList = itemListFill.fillData(eq);
			
			itemzzz = itemList.newItems;
			
			//main.clearDacResults();
			
			if (itemzzz.isEmpty()) {
				
				showAlertWithHeaderText2();
				
				return null;
				
			} else if (checkNull(itemzzz) == true) {
				
				showAlertWithHeaderText3();
				
				return null;
				
			} else {
				
				main.showDacResults();
				
				return itemzzz;
			}
		}
	}
	
	//формируем поисковый запрос на основе введенных данных
	private Equipment searchFields() throws IOException, EquipmentBusinessException {
		
		//boolean vc = vcTField.isDisabled();
		/**
		Equipment eq = new Equipment(firmBox.getValue().toString().toUpperCase(), 
					modelTField.getText().replace(" ", "").toUpperCase(), 
					modelTField.getText().replace(" ", "").toUpperCase());
					*/
		
		Equipment eq = searchBox.getSelectionModel().getSelectedItem();
		eq.setManufacturer(firmBox.getValue().toString().toUpperCase());
		
		if (eq.getModel().isEmpty()) {
			showAlertWithHeaderText();
			return null;
		}
		
		System.out.println(eq);
		
		System.out.println("Производитель искомого Вами устройства это " + eq.getManufacturer());
		System.out.println("Модель искомого Вами устройства это " + eq.getModel());
		
		return eq;
		
		
		//return searchBox.getSelectionModel().getSelectedItem();
	}
	
	private boolean checkNull(List<Item> items) throws IllegalAccessException {
	    
		Item emptyItem = items.get(0);
		
		return emptyItem.hasAllDetailsEntered();            
	}
	
	//если не ввели артикул, модель
	private void showAlertWithHeaderText() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Предупреждение");
		alert.setHeaderText("Параметров для поиска не хватает");
		alert.setContentText("Укажите модель, артикул или заказной номер");

		alert.showAndWait();
	}
	
	//если не ответ от бд пришел пустой
	private void showAlertWithHeaderText2() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Предупреждение");
		alert.setHeaderText("Мы ничего не нашли по вашему запросу");
		alert.setContentText("Введенного устройства пока нет в нашей базе данных");

		alert.showAndWait();
	}
	
	private void showAlertWithHeaderText3() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Предупреждение");
		alert.setHeaderText("Нет подходящего аналога");
		alert.setContentText("У введенного Вами устройства нет аналога в линейке Siemens");

		alert.showAndWait();
	}
	/**
	@FXML
	private void showResults() throws IOException {
		//public static void showDacResults() throws IOException {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("results/ResultsItems.fxml"));
			BorderPane resultsPane = loader.load();
			mainPane.setCenter(resultsPane);
	}
	*/
}
