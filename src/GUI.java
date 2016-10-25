import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * Created by alk_ on 10/25/16.
 *
 * Et ei oleks default file template on siin see rida
 * Kunagi äkki mõni kommentaar ka juurde
 */
public class GUI extends Application {

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
		Text btnText1 = new Text("Situ pihku prill");
		Text btnText2 = new Text("Situ pihku prill");
		Text btnText3 = new Text("Situ pihku prill");
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

		algo1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				update(createNewPane("test"),vBox,primaryStage);
			}
		});

		GridPane test = new GridPane();
		test.add(new Button("ehhe"),1,1);
		vBox.getChildren().addAll(test);


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
		vBox.getChildren().addAll(newPane);
		primaryStage.show();

	}

	public static GridPane createNewPane(String in){
		GridPane grid = new GridPane();

		Text txt = new Text("Lisatud\nProtsess");
		txt.setTextAlignment(TextAlignment.CENTER);

		Text txt1 = new Text("Etapp");
		txt1.setTextAlignment(TextAlignment.CENTER);
		grid.add(txt1,0,0);
		grid.add(txt,1,0);


		grid.getColumnConstraints().add(new ColumnConstraints(40));
		grid.getColumnConstraints().add(new ColumnConstraints(60));


		for (int i = 0; i <= 50; i++) {
			grid.add(new Text(Integer.toString(i)),i+2,0);
			grid.getColumnConstraints().add(new ColumnConstraints(20));

		}

		return grid;
	}
}
