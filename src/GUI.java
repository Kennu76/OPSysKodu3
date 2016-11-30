import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.FunctionCall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by alk_ on 10/25/16.
 *
 * Kasutajaliidese kirjutas Ott Oopkaup, mis on ka üleval minu GIT'is
 * https://github.com/Alkhatraz/OPSysKodu2
 *
 * Kasutajaliides sai jagatud teiste ]pilastega, kirjutaja ei vastuta kopeeritud funktsioonide eest,
 *
 */


public class GUI extends Application {
	//Default value is the 1st example, is set here
	static String sample = "4,5;2,7;9,2;4,6;7,1;6,4;8,8;3,6;1,10;9,2";

	//All the buttons and the neccesary hooks to make the UI work
	@Override
	public void start(Stage primaryStage) {


		VBox vBox = new VBox();
		vBox.setPadding(new Insets(0,10,0,10));

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		grid.getColumnConstraints().add(new ColumnConstraints(110));

		vBox.getChildren().add(grid);

		Text t = new Text("Vali või sisesta järjend");
		Text btnText1 = new Text("2,5,13,29,7,1,18,40,49,4");
		Text btnText2 = new Text("1,10,44,2,12,3,13,20");
		Text btnText3 = new Text("45,6,16,9,33,28,11,37,49,25");
		TextField btnText4 = new TextField("1,2,3,4,5,6,7,8,10");
		btnText4.setMinWidth(300);
		btnText4.setPadding(new Insets(5,0,5,0));

		final ToggleGroup group = new ToggleGroup();

		RadioButton btn = new RadioButton();
		RadioButton btn2 = new RadioButton();
		RadioButton btn3 = new RadioButton();
		RadioButton btn4 = new RadioButton();

		btn.setToggleGroup(group);
		btn2.setToggleGroup(group);
		btn3.setToggleGroup(group);
		btn4.setToggleGroup(group);
		btn.setSelected(true);

		btn.setText("Esimene");
		btn2.setText("Teine");
		btn3.setText("Kolmas");
		btn4.setText("Enda oma");

		grid.add(t,0,1);
		grid.add(btn,0,2);
		grid.add(btn2,0,3);
		grid.add(btn3,0,4);
		grid.add(btn4,0,5);

		grid.add(btnText1,1,2);
		grid.add(btnText2,1,3);
		grid.add(btnText3,1,4);
		grid.add(btnText4,1,5);


		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		vBox.getChildren().add(hBox);

		Button algo1 = new Button("FCFS");
		Button algo2 = new Button("SSTF");
		Button algo3 = new Button("LOOK");
		Button algo4 = new Button("C-SCAN");
		Button puhasta = new Button("Puhasta väljund");

		hBox.getChildren().addAll(algo1,algo2,algo3,algo4,puhasta);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText1.getText();
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText2.getText();
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText3.getText();
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sample = btnText4.getText();
			}
		});

		//firstFit
		algo1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.test(sample)),vBox,primaryStage);
			}
		});
		//worstFit
		algo2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.test(sample)),vBox,primaryStage);
			}
		});
		//bestFit
		algo3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.test(sample)),vBox,primaryStage);
			}
		});
		//randomFit
		algo4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.test(sample)),vBox,primaryStage);
			}
		});

		puhasta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(new GridPane(),vBox,primaryStage);
			}
		});


		vBox.getChildren().add(new GridPane());
		Scene scene = new Scene(vBox, 300, 100);

		primaryStage.setTitle("Kodutöö3");
		primaryStage.setScene(scene);
		primaryStage.setHeight(500);
		primaryStage.setWidth(1200);
		primaryStage.show();
	}
	public static void main(String[] args) {
		System.out.println(Functions.FCFS("1,10,44,2,12,3,13,20"));
		launch(args);
	}


	//Method updates the UI with a new Gridpane that is used as the output field
	public static void update(GridPane newPane, VBox vBox, Stage primaryStage){
		vBox.getChildren().remove(vBox.getChildren().size()-1);
		vBox.getChildren().add(newPane);
		primaryStage.show();

	}
	/**
	 *Method takes some input to create the graphical output noted in the sample program pictures
	 *
	 * That input is formatted as an array like
	 * [[metadata, 'a','a','b'....],[metadata,"a"..]]
	 * metadata is the process description
	 */
	public static GridPane createNewPane(String[][] in){

		GridPane grid = new GridPane();


		Text txt = new Text("Lisatud\nProtsess");
		txt.setTextAlignment(TextAlignment.CENTER);

		Text txt1 = new Text("Etapp");
		txt1.setTextAlignment(TextAlignment.CENTER);
		grid.add(txt1,0,0);
		grid.add(txt,1,0);


		grid.getColumnConstraints().add(new ColumnConstraints(40));
		grid.getColumnConstraints().add(new ColumnConstraints(60));
		//Sets the column headers and width
		for (int i = 0; i < 50; i++) {
			Text tempText = new Text(Integer.toString(i));
			GridPane.setHalignment(tempText, HPos.CENTER);

			grid.add(tempText,i+2,0);
			grid.getColumnConstraints().add(new ColumnConstraints(20));

		}
		return grid;
	}

	/**
	 * While the method above takes the whole stack of strings, this one takes just one line
	 * metadata format "data"
	 *
	 */

	public static GridPane createGraph(String[] in,GridPane grid, int row){
		return null;
	}

}
