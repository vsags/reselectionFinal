package db.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import db.equipment.Equipment;

public class EquipmentSimpleDao implements EquipmentDao {

private final List<Equipment> equipmentz = new ArrayList<Equipment>();

private final List<Equipment> foundEquipment = new ArrayList<Equipment>();

private final Map<String, ArrayList<Equipment>> foundEquipment2 = new HashMap();

private final List<Equipment> foundEquipmentForComboBox = new ArrayList<Equipment>();
	
	@Override
	public Long addEquipment(Equipment equipment) {
		Long id = generateEquipmentId();
		equipment.setEq_id(id);
		equipmentz.add(equipment);
		return id;
	}
	
	@Override
	public Equipment getEquipment(Long eq_id) {
		for (Equipment equipment : equipmentz) {
			if(equipment.getEq_id().equals(eq_id)) {
				return equipment;
			}
		}
		return null;
	}
	
	@Override
    public void updateEquipment(Equipment equipment) {
        Equipment oldEquipment = getEquipment(equipment.getEq_id());
        if(oldEquipment != null) {
        	oldEquipment.setModel(equipment.getModel());
        	oldEquipment.setVc(equipment.getVc());
        	oldEquipment.setPrice(equipment.getPrice());
        }
    }
	
	
	@Override
	public void deleteEquipment(Long eq_id) {
        for(Iterator<Equipment> it = equipmentz.iterator(); it.hasNext();) {
            Equipment cnt = it.next();
            if(cnt.getEq_id().equals(eq_id)) {
                it.remove();
            }
        }
    }
	
	@Override
	public Map<String, ArrayList<Equipment>> searchEquipment(Equipment equipment) {
				return foundEquipment2;
		}
	
	@Override
	public Map<String, ArrayList<Equipment>> searchEquipmentNoFirm(Equipment equipment) {
				return foundEquipment2;
		}
	
	@Override
	public List<Equipment> searchEquipmentForComboBox(){
		return foundEquipmentForComboBox;
	}
	
	public List<Equipment> searchEquipmentForComboBoxWithFirm(String manufacturer){
		return foundEquipmentForComboBox;
	}
	
	@Override
	public List<Equipment> findEquipmentz() {
        return equipmentz;
}
	
	private Long generateEquipmentId() {
		Long eq_id = Math.round(Math.random() * 1000 + System.currentTimeMillis());
		while(getEquipment(eq_id) != null) {
			eq_id = Math.round(Math.random() * 1000 + System.currentTimeMillis());
		}
		return eq_id;
	}
	
}
