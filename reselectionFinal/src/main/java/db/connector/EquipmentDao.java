package db.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.equipment.Equipment;

import db.exceptions.*;

public interface EquipmentDao {
	
public Long addEquipment(Equipment equipment) throws EquipmentDaoException;
	
	public void updateEquipment(Equipment equipment) throws EquipmentDaoException;
	
	public Equipment getEquipment(Long eq_id) throws EquipmentDaoException;
	
	public void deleteEquipment(Long eq_id) throws EquipmentDaoException;
	
	public Map<String, ArrayList<Equipment>> searchEquipment(Equipment equipment) throws EquipmentDaoException;
	
	public Map<String, ArrayList<Equipment>> searchEquipmentNoFirm(Equipment equipment) throws EquipmentDaoException;
	
	public List<Equipment> searchEquipmentForComboBox() throws EquipmentDaoException;
	
	public List<Equipment> searchEquipmentForComboBoxWithFirm(String manufacturer) throws EquipmentDaoException;
	
	public List<Equipment> findEquipmentz() throws EquipmentDaoException;

}
