package reselection.editdb;

import java.util.function.Consumer;

import db.connector.EquipmentManager;
import db.equipment.Equipment;
import db.exceptions.EquipmentBusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import reselection.model.EditDbTable;

// окно для изменения базы данных внутри программы [не доделал]

public class AddDialogController {
	
	@FXML
    private Button addToDbButton;

    @FXML
    private TextField analogTField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField mnfctrrTField;

    @FXML
    private TextField modelTField;

    @FXML
    private TextField vcTField;
    
    private final EquipmentManager equipmentManager = new EquipmentManager();
    private Consumer<EditDbTable> callback;

    public void setup(Consumer<EditDbTable> callback) {
        this.callback = callback;
    }
    
    public AddDialogController() {
    	this(null);
    }
    
    public AddDialogController(Equipment equipment) {
    	
    }
    
    @FXML
    private void addData(ActionEvent event) throws EquipmentBusinessException {
    	EditDbTable addTable = new EditDbTable(3, mnfctrrTField.getText(), modelTField.getText(), vcTField.getText(), analogTField.getText());
    	callback.accept(addTable);
    	
    	Equipment addItem = new Equipment(3, addTable.getMnfctrr(), addTable.getModel_all(), addTable.getVc_all(), addTable.getModel_smns());
    	System.out.println(addItem);
    	equipmentManager.addEquipment(addItem);
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
   
}
