package reselection.results;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import db.connector.EquipmentManager;
import db.equipment.Equipment;
import db.exceptions.EquipmentBusinessException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import reselection.model.Item;

//механизм для заполенения списка найденных устройств и вывода их на экран

public class ItemListFill {
	
	private EquipmentManager equipmentManager = new EquipmentManager();
	
	//выводит список ответов графически
	public ItemList fillData(Equipment eq) throws IOException, EquipmentBusinessException {
		
		ItemList result = new ItemList();
		
		List<Equipment> foundSiemens = new LinkedList<>();
		foundSiemens = searchEquipment(eq);
		System.out.println(foundSiemens);
		
		List<Item> foundItems = new LinkedList<>();
		foundItems = createItems(foundSiemens);
		System.out.println(foundItems);
				
		result.newItems.clear();
		result.newItems.addAll(foundItems);
		
		return result;
	}

/**
	public EquipmentList fillEq(Equipment eq) throws IOException, EquipmentBusinessException {
		
		EquipmentList result = new EquipmentList();
		
		List<Equipment> foundSiemens = new LinkedList<>();
		foundSiemens = searchEquipment(eq);
		System.out.println(foundSiemens);
				
		result.foundEquipment.clear();
		result.foundEquipment.addAll(foundSiemens);
		
		return result;
	}
	*/
	
	//заполняет списки устройств, полученные от базы данных
	private List<Equipment> searchEquipment(Equipment eq) throws IOException, EquipmentBusinessException {
		
		Map<String, ArrayList<Equipment>> foundEquipment = new HashMap();
		
		List<Equipment> foundSiemens = new LinkedList<>();
		
		EquipmentList toFill = new EquipmentList();
		FoundEquipmentList toFillNew = new FoundEquipmentList();
		
		if (eq.getManufacturer().isEmpty()) {
			
			foundEquipment = equipmentManager.searchEquipmentNoFirm(eq);
			foundSiemens = foundEquipment.get("foundSiemens");
			System.out.println("Вот они, найденные аналоги от компании Сименс: " + foundSiemens);
			//foundSiemens = equipmentManager.searchEquipmentNoFirm(eq);
			
			toFillNew.foundEquipment.clear();
			toFillNew.foundEquipment = foundEquipment.get("foundDac");
			System.out.println("А это устройства, которые Вы искали: " + toFillNew.foundEquipment);
			
			
			toFill.foundEquipment.clear();
			toFill.foundEquipment = foundSiemens;
			
		} else {
			
			foundEquipment = equipmentManager.searchEquipment(eq);
			foundSiemens = foundEquipment.get("foundSiemens");
			
			toFillNew.foundEquipment.clear();
			toFillNew.foundEquipment = foundEquipment.get("foundDac");
			
			toFill.foundEquipment.clear();
			toFill.foundEquipment = foundSiemens;
			
		}
		
		return foundSiemens;
		
	}
	
	//заполняет информацию для последующей визуализации поисковых результатов
	private List<Item> createItems(List<Equipment> list) {
		
		List<Item> items = new ArrayList<>();
		Item item;
		
		for (Equipment eq : list) {
			item = new Item();
			item.setName(eq.getModel());
			item.setPurpose(eq.getPurpose());
			item.setPrice(eq.getPrice());
			item.setWeight(eq.getWeight());
			item.setEffort(eq.getEffort());
			item.setDamperArea(eq.getDamperArea());
			//item.setColor(eq.getVc());
			item.setImgSrc("/dac_1.jpg");
			items.add(item);
		}
		
		return items;
	}


	private void showAlertWithHeaderText() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Предупреждение");
		alert.setHeaderText("Параметров для поиска не хватает");
		alert.setContentText("Вы указали не все параметры");

		alert.showAndWait();
	}
}
