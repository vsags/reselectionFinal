package reselection;
	
import java.io.IOException;

import db.config.ConfigNew;
import db.connector.EquipmentManager;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import reselection.model.Item;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

// класс, запускающий графический интерфейс

public class Main extends Application {
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	private ConfigNew dbConfig;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Переподбор оборудования");
		this.primaryStage.setMinHeight(640);
		this.primaryStage.setMinWidth(820);
		//this.primaryStage.initStyle(StageStyle.TRANSPARENT);
		
		dbConfig = new ConfigNew();
		 try {
		        dbConfig.initGlobalConfig();
		    } catch (Exception ex) {
		        ex.printStackTrace(System.out);
		        return;
		    } 
		
		showMainPage();
	}
	
	
	private void showMainPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		//scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void backToMain() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		BorderPane mainPage = loader.load();
		//mainLayout.setLeft(mainPage);
		mainLayout.setCenter(mainPage); //добавил после изменения главной
		//mainLayout.setCenter(null);
		mainLayout.setLeft(null); //добавил после изменения главной
	}
	
	public static void showDacScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("search/SearchView.fxml"));
		BorderPane searchPage = loader.load();
		mainLayout.setLeft(searchPage);
		mainLayout.setCenter(null); //добавил после изменения главной
		
	}
	
	public static void showDbDac() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("editdb/EditDbDialog.fxml"));
		BorderPane editDb = loader.load();
		
		//открывает новое окно
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("База данных");
		addDialogStage.initModality(Modality.WINDOW_MODAL); //отключает нажатия на другом окне, пока открыто это
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene (editDb);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	
	 public static void showDacResults() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("results/ResultsItems.fxml"));
		BorderPane resultsPane = loader.load();
		mainLayout.setCenter(resultsPane);
		
	 }
	 
	 
	 public static void clearSearchResults() {
		 mainLayout.setCenter(null);
	 }
	 
	 public static void  showComparisonPage() throws IOException {
		 FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("comparison/ComparisonView.fxml"));
			BorderPane comparisonPane = loader.load();
			mainLayout.setLeft(null);
			mainLayout.setCenter(comparisonPane);
	 }
	 
	public static void main(String[] args) {
		launch(args);
	}
}

