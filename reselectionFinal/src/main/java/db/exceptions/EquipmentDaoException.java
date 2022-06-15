package db.exceptions;

public class EquipmentDaoException extends Exception {

	public EquipmentDaoException() {
    }
 
    public EquipmentDaoException(String message) {
        super(message);
    }
 
    public EquipmentDaoException(Throwable cause) {
        super(cause);
    }
 
    public EquipmentDaoException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
