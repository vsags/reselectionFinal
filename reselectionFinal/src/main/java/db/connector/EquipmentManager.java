package db.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.equipment.Equipment;
import db.exceptions.*;

//класс для общения с бд

public class EquipmentManager {
	
private EquipmentDao dao;
	
	public EquipmentManager() {
		dao = EquipmentDaoFactory.getEquipmentDAO();
	}
	
	public Long addEquipment(Equipment equipment) throws EquipmentBusinessException {
		try {
			return dao.addEquipment(equipment);
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
		}
	}
	
	public Equipment getEquipment(Long eq_id) throws EquipmentBusinessException {
		try {
			return dao.getEquipment(eq_id);
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
		}
	}
	
	public void updateEquipment(Equipment equipment) throws EquipmentBusinessException {
        try {
        	dao.updateEquipment(equipment);
        } catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
        }
    }
	
	public void deleteEquipment(Long eq_id) throws EquipmentBusinessException {
		try {
			dao.deleteEquipment(eq_id);
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
        }
	}
	
	public Map<String, ArrayList<Equipment>> searchEquipment(Equipment equipment) throws EquipmentBusinessException {
		try {
			return dao.searchEquipment(equipment);
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
		}
	}
	
	public Map<String, ArrayList<Equipment>> searchEquipmentNoFirm(Equipment equipment) throws EquipmentBusinessException {
		try {
			return dao.searchEquipmentNoFirm(equipment);
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
		}
	}
	
	public List<Equipment> searchEquipmentForComboBox() throws EquipmentBusinessException {
		try {
			return dao.searchEquipmentForComboBox();
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
		}
	}
	
	public List<Equipment> searchEquipmentForComboBoxWithFirm(String manufacturer) throws EquipmentBusinessException {
		try {
			return dao.searchEquipmentForComboBoxWithFirm(manufacturer);
		} catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
		}
	}
	
	public List<Equipment> findEquipmentz() throws EquipmentBusinessException {
        try {
        	return dao.findEquipmentz();
        } catch (EquipmentDaoException ex) {
			throw new EquipmentBusinessException (ex);
        }
	}

}
