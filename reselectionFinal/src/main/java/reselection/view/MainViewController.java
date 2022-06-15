package reselection.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import reselection.Main;

//контроллер главной страницы  

public class MainViewController {

private Main main;
	
	@FXML
	private Button valvesButton;
	
	@FXML
	private Button actuatorsButton;
	
	@FXML
	private Button dacButton;
	
	@FXML
	private Button sensorsButton;

	@FXML
	private ImageView logoImage;

	@FXML
	private void goDacSearch() throws IOException {
		main.showDacScene();
		}
	
	@FXML
	private void editDb() throws IOException {
		main.showDbDac();
	}
	
	@FXML
	private void sectionIsUnavailable() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Предупреждение");
		alert.setHeaderText("Секция пока недоступна");
		alert.setContentText("Мы работаем над добавлением этого раздела");

		alert.showAndWait();
	}
	
	@FXML
	private void initialize() {
		
		/**
		valvesButton.setEffect(new DropShadow(1, Color.BLACK));
		actuatorsButton.setEffect(new DropShadow(1, Color.BLACK));
		dacButton.setEffect(new DropShadow(1, Color.BLACK));
		sensorsButton.setEffect(new DropShadow(1, Color.BLACK));
		*/
		
		Image logoImageLoader = new Image(getClass().getResourceAsStream("/siemensLogoMain.png"));
		logoImage.setImage(logoImageLoader);
	}
	
}
