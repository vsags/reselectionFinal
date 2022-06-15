package reselection.editdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reselection.Main;
import reselection.model.EditDbTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import reselection.model.EditDbTable;
import db.connectionPool.ConnectionBuilder;
import db.connectionPool.ConnectionBuilderFactory;
import db.equipment.Equipment;
import db.connector.EquipmentManager;
import db.exceptions.EquipmentBusinessException;

//окно для изменения базы данных внутри программы [не доделал]

public class EditDbController implements Initializable {
	
	@FXML
	private TableView<EditDbTable> table;
	
	@FXML
	private TableColumn<EditDbTable, String> col_id;
	
	@FXML
	private TableColumn<EditDbTable, String> col_mnfctrr;
	
	@FXML
	private TableColumn<EditDbTable, String> col_model;
	
	@FXML
	private TableColumn<EditDbTable, String> col_vc;
	
	@FXML
	private TableColumn<EditDbTable, String> col_anlgSiemens;
	
	ObservableList<EditDbTable> oblist = FXCollections.observableArrayList();
	
	private final EquipmentManager equipmentManager = new EquipmentManager();
	
	//private final AddDialogController addController = new AddDialogController();
	
	// Загрузка оборудования (срабатывает сразу)
	   private void loadEquipment() throws EquipmentBusinessException {
	       List<Equipment> equipmentz = equipmentManager.findEquipmentz();
	       for (Equipment item : equipmentz) {
	    	   oblist.add(new EditDbTable(item.getId_all(), item.getManufacturer(), item.getModel(), item.getVc(), item.getSmnsAn()));
	    	   System.out.println(item.getEq_id());
	       }
	   }
	   
	   @FXML
	   private void addEquipment() throws EquipmentBusinessException, IOException {
		   FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("editdb/AddDialog.fxml"));
			BorderPane addDb = loader.load();
			AddDialogController addController = loader.getController();
			
	        addController.setup(
	                (value)->{oblist.add(value);
	                table.refresh();});
	        
			//открывает новое окно
			Stage addDialogStage = new Stage();
			addDialogStage.setTitle("База данных");
			addDialogStage.initModality(Modality.WINDOW_MODAL); //отключает нажатия на другом окне, пока открыто это
			//addDialogStage.initOwner(primaryStage);
			Scene scene = new Scene (addDb);
			addDialogStage.setScene(scene);
			addDialogStage.showAndWait();
			
			//saveEquipment(addController);
	   }
	   
	   
	// Удаление оборудования
	   @FXML
	   private void deleteEquipment() throws EquipmentBusinessException {
		   table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		   
		   ObservableList<EditDbTable> selectedRows = table.getSelectionModel().getSelectedItems();
		   ArrayList<EditDbTable> rows = new ArrayList<>(selectedRows);
		   
		   for (EditDbTable row : selectedRows) {
			   equipmentManager.deleteEquipment(row.getId_all());
		   }
		   
		   rows.forEach(row -> table.getItems().remove(row));
	   }
	   /**
	   @FXML
	   private void saveEquipmentButton() throws EquipmentBusinessException {
		   saveEquipment(addController);
	   }
	   
	// Общий метод для добавления и изменения контакта
	   private void saveEquipment(AddDialogController adc) throws EquipmentBusinessException {
	       // Если мы нажали кнопку SAVE
	       if (adc.isSave()) {
	           // Получаем контакт из диалогового окна
	           Equipment eqp = adc.gettEquipment();
	           if (eqp.getEq_id() != null) {
	               // Если ID у контакта есть, то мы его обновляем
	               equipmentManager.updateEquipment(eqp);
	           } else {
	               // Если у контакта нет ID - значит он новый и мы его добавляем
	               equipmentManager.addEquipment(eqp);
	           }
	           loadEquipment();
	       }
	   }
	   */
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try{ 
	    	   loadEquipment();
	       } catch (EquipmentBusinessException ex) {
	    	   ex.printStackTrace();
	       }
		
		/**
		try {
			Connection con = getConnection();
			
			System.out.println("Соединение для заполнения");
			ResultSet rs = con.createStatement().executeQuery(SELECT);
			
			while (rs.next()) {
				oblist.add(new EditDbTable(rs.getInt("id_all"), rs.getString("mnfctrr"),
						rs.getString("model_all"), rs.getString("vc_all")));
				System.out.println("kek");
			
		}
			
		} catch (SQLException ex) {
			Logger.getLogger(EditDbController.class.getName()).log(Level.SEVERE, null, ex);
		
		}
		*/
		col_id.setCellValueFactory(new PropertyValueFactory<>("id_all"));
		col_mnfctrr.setCellValueFactory(new PropertyValueFactory<>("mnfctrr"));
		col_model.setCellValueFactory(new PropertyValueFactory<>("model_all"));
		col_vc.setCellValueFactory(new PropertyValueFactory<>("vc_all"));
		col_anlgSiemens.setCellValueFactory(new PropertyValueFactory<>("model_smns"));
		
		table.setItems(oblist);
	}
}
