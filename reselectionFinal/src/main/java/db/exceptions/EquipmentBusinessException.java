package db.exceptions;

public class EquipmentBusinessException extends Exception {

	public EquipmentBusinessException() {
    }
 
    public EquipmentBusinessException(String message) {
        super(message);
    }
 
    public EquipmentBusinessException(Throwable cause) {
        super(cause);
    }
 
    public EquipmentBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
