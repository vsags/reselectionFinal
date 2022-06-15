package db.connectionPool;

public class ConnectionBuilderFactory {

	public static ConnectionBuilder getConnectionBuilder() {
        return new ComboConnectionBuilder();
    }
	
}
