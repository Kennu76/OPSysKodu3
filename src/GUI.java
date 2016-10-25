import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by alk_ on 10/25/16.
 */
public class GUI extends Application {

	@Override
	public void start(Stage primaryStage) {


		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		grid.getColumnConstraints().add(new ColumnConstraints(0)); // column 1 is 100 wide
		grid.getColumnConstraints().add(new ColumnConstraints(110)); // column 1 is 100 wide

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

		grid.add(t,1,1);
		grid.add(btn,1,2);
		grid.add(btn2,1,3);
		grid.add(btn3,1,4);
		grid.add(btn4,1,5);

		grid.add(btnText1,2,2);
		grid.add(btnText2,2,3);
		grid.add(btnText3,2,4);
		grid.add(btnText4,2,5);



		Scene scene = new Scene(grid, 300, 250);

		primaryStage.setTitle("Kodutöö2");
		primaryStage.setScene(scene);
		primaryStage.setHeight(300);
		primaryStage.setWidth(1000);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
