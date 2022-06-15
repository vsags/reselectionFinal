package db.connector;

import db.config.ConfigNew;

public class EquipmentDaoFactory {
	
	public static EquipmentDao getEquipmentDAO() {
		try {
			Class dao = Class.forName(ConfigNew.getProperty("dao.class"));
            return (EquipmentDao)dao.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
