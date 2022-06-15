package db.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//конфиг для подключения к бд

public class ConfigNew {
	private static final Properties props = new Properties();
	private static final InputStream in = ConfigNew.class.getResourceAsStream("/sqliteCon.properties");
	
	public static void initGlobalConfig() throws IOException {
		System.out.println("Соединение установлено 1");
		props.load(in);
		in.close();
		System.out.println("Соединение установлено 2");
	}
	
	public static String getProperty(String property) {
        return props.getProperty(property);
	}
}
