package reselection.results;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.exceptions.EquipmentBusinessException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import reselection.model.Item;
import reselection.search.SearchViewController;

//класс для вывода ответов на запрос

public class ResultsItemsController extends SearchViewController implements Initializable {
	
	@FXML
	private BorderPane resultsPane;
	
	@FXML
	private ScrollPane scroll;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private TilePane tp;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
	
		try {
			try {
				items.addAll(ItemList.newItems);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int column = 0;
		int row = 1;
		System.out.println(items.size());

		try {
			for (int i=0; i<items.size(); i++) {
				System.out.println(items.size());
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("Item.fxml"));
				AnchorPane itemsBox = loader.load();
			
				ItemController itemController = loader.getController();
				itemController.setData(items.get(i));
				itemController.toCompareButton.setId("Button_" + i);
				System.out.println(itemController.toCompareButton.getId());
				System.out.println(items.size());
				
				if (column == 3) {
					column = 0;
					row++;
				}
				
				tp.getChildren().add(itemsBox);
				TilePane.setMargin(itemsBox, new Insets(5));
				
				scroll.getStyleClass().add("edge-to-edge");
				scroll.setFitToWidth(true);
				
			}
		} catch (IOException e) {
					e.printStackTrace();
				}
		for (Item it : items) {
			System.out.println("Аналоги найдены");
			System.out.println("Теперь размер найденного: " + EquipmentList.foundEquipment.size());
			System.out.println(it.getName());
		}
		}
		
		
	}
