package reselection.results;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.equipment.Equipment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import reselection.Main;
import reselection.comparison.ComparisonController;
import reselection.model.Item;

//контроллер для устройства, появляющегося после поискового запроса

public class ItemController implements Initializable {
	
	private Main main;
	
	public static ItemController ic;
	public static int clickedButtonId;
	
	@FXML
	private VBox siemensDeviceBox;
	
	@FXML
	private Label dacName;
	
	@FXML
	private Label purposeLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label weightLabel;
	
	@FXML
	private Label effortLabel;
	
	@FXML
	private Label damperAreaLabel;
	
	@FXML
	private ImageView img;
	
	@FXML
	protected Button toCompareButton;
	
	private Item item;
	
	public void setData(Item item) {
		this.item = item; 
		dacName.setText(item.getName());
		purposeLabel.setText(item.getPurpose());
		priceLabel.setText(item.getPrice() + " €");
		weightLabel.setText(item.getWeight() + " кг");
		effortLabel.setText(item.getEffort() + " Нм");
		damperAreaLabel.setText(item.getDamperArea() + " м²");
		Image image = new Image (getClass().getResourceAsStream(item.getImgSrc()));
		img.setImage(image);
	}
	
	
	@FXML
	private void goCompare() throws IOException {
		
		for (Equipment eq : EquipmentList.foundEquipment) {
			int eqIndex = EquipmentList.foundEquipment.indexOf(eq);
			System.out.println("Номер этого элемента в списке: " + eqIndex);
			String number = Integer.toString(eqIndex);
			
			System.out.println("Мы перевели это в строку, и он равен: " + number);
			
			if (toCompareButton.getId().contains(number)) {
				
				clickedButtonId = eqIndex;
				System.out.println(clickedButtonId + "lel");
				System.out.println("Вы выбрали оборудование " + eq.getSmnsAn());
			}
		}
		main.showComparisonPage();
	}
	
	@FXML
	private void goHome() throws IOException {
		main.backToMain();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ic = this;
		
		siemensDeviceBox.setEffect(new DropShadow(7, Color.BLACK));
	}

}
