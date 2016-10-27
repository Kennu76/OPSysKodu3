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
 * Et ei oleks default file template on siin see rida
 * Kunagi äkki mõni kommentaar ka juurde
 */


public class GUI extends Application {
	static String sample = "4,5;2,7;9,2;4,6;7,1;6,4;8,8;3,6;1,10;9,2";
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

		Text t = new Text("Vali või sisesta kuni 10 kohaline järjend");
		Text btnText1 = new Text("4,5;2,7;9,2;4,6;7,1;6,4;8,8;3,6;1,10;9,2");
		Text btnText2 = new Text("1,10;6,6;3,9;2,4;1,6;5,2;1,4;5,2;2,1;2,7");
		Text btnText3 = new Text("5,10;6,6;3,9;8,4;3,6;5,12;1,4;15,2;3,4;9,7");
		TextField btnText4 = new TextField("1,8;6,4;3,6;4,2;1,4;3,3;1,2;35,1;50,1");
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

		Button algo1 = new Button("First-Fit");
		Button algo2 = new Button("Worst-Fit");
		Button algo3 = new Button("Best-Fit");
		Button algo4 = new Button("Random-Fit");
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


		algo1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.firstFit(btnText4.getText())),vBox,primaryStage);
			}
		});

		algo2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.firstFit(sample)),vBox,primaryStage);
			}
		});

		algo3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.firstFit(sample)),vBox,primaryStage);
			}
		});

		algo4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane(Functions.firstFit(sample)),vBox,primaryStage);
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

		primaryStage.setTitle("Kodutöö2");
		primaryStage.setScene(scene);
		primaryStage.setHeight(500);
		primaryStage.setWidth(1200);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

	public static void update(GridPane newPane, VBox vBox, Stage primaryStage){
		vBox.getChildren().remove(vBox.getChildren().size()-1);
		vBox.getChildren().add(newPane);
		primaryStage.show();

	}
	/**
	 *Method takes some kind of input to create the graphical output noted in the sample program pictures
	 *
	 * That input will most likely be formatted as an array
	 * [[metadata, 'a','a','b'....],[metadata,"a"..]]
	 */
	public static GridPane createNewPane(String[][] in){
		for (String[] strings : in) {
			String s = "";
			for (String si : strings) {
				s += si + " ";
			}
			System.out.println(s);
		}

		GridPane grid = new GridPane();


		Text txt = new Text("Lisatud\nProtsess");
		txt.setTextAlignment(TextAlignment.CENTER);

		Text txt1 = new Text("Etapp");
		txt1.setTextAlignment(TextAlignment.CENTER);
		grid.add(txt1,0,0);
		grid.add(txt,1,0);


		grid.getColumnConstraints().add(new ColumnConstraints(40));
		grid.getColumnConstraints().add(new ColumnConstraints(60));

		for (int i = 0; i < 50; i++) {
			Text tempText = new Text(Integer.toString(i));
			GridPane.setHalignment(tempText, HPos.CENTER);

			grid.add(tempText,i+2,0);
			grid.getColumnConstraints().add(new ColumnConstraints(20));

		}
		int index = 1;
		for (String[] strings : in) {
			addRow(strings,grid, index++);
		}

		return grid;
	}

	/**
	 * While the method above takes the whole stack of strings, this one takes just one line
	 * metadata format "index;data"
	 * @param in
	 * @return
	 */

	public static GridPane addRow(String[] in,GridPane grid, int row){

		Color[] list = {Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.PALEGREEN,
				Color.SALMON, Color.GREEN, Color.YELLOW, Color.LAVENDER, Color.PINK, Color.GRAY};
		List<String> chars = new ArrayList();
		String[] cs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "-"};
		chars = Arrays.asList(cs);


		Text txt1 = new Text(Integer.toString(row));
		Text txt2 = new Text(in[0]);


		txt1.setTextAlignment(TextAlignment.CENTER);
		txt2.setTextAlignment(TextAlignment.CENTER);

		grid.add(txt1,0,row);
		grid.add(txt2,1,row);

		for (int i = 0; i < in.length-1; i++) {

			Rectangle rekt = new Rectangle(20,20,list[chars.indexOf(in[i+1])]);
			rekt.setStroke(Color.BLACK);
			Text text = new Text(in[i+1]);
			text.setTextAlignment(TextAlignment.CENTER);
			StackPane pane = new StackPane();
			pane.getChildren().addAll(rekt,text);

			grid.add(pane,i+2,row);
		}
		if(in.length < 51){
			for (int i = 0; i < 51-in.length; i++) {
				Rectangle rekt = new Rectangle(20,20,Color.GREY);
				rekt.setStroke(Color.BLACK);
				Text text = new Text("-");
				StackPane pane = new StackPane();
				pane.getChildren().addAll(rekt,text);

				grid.add(pane,in.length+1+i,row);
			}
		}
		return grid;
	}
}
