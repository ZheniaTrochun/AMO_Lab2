package root.sample.stages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import root.sample.*;

/**
 * Created by zhenia on 22.03.17.
 */
public class MainScene {
    public static Scene createMainScene(){
        Button handy = new Button("Handy");
        Button test = new Button("Test");

        GridPane root = setLayout(handy, test);

        setButtons(handy, test);

        root.setStyle("-fx-background-color: white");

        AppEntryPoint.stage.setTitle("Трочун Євгеній ІП-54");

        AppEntryPoint.wndNumber = 1;

        return new Scene(root, 600, 400);
    }

    private static void setButtons(Button handy, Button test) {
        handy.setOnAction(event -> Controller.toHandy());
        test.setOnAction(event -> Controller.toTestScene(0));
    }

    private static GridPane setLayout(Button handy, Button test) {
        handy.setPrefSize(150, 50);
        test.setPrefSize(150, 50);

        GridPane root = new GridPane();

        ColumnConstraints[] columns = new ColumnConstraints[4];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new ColumnConstraints();
            columns[i].setPercentWidth(25);
        }

        root.getColumnConstraints().addAll(columns);

        RowConstraints[] rows = new RowConstraints[3];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new RowConstraints();
            rows[i].setPercentHeight(33);
        }

        root.getRowConstraints().addAll(rows);

        root.add(handy, 1, 1);
        root.add(test, 2, 1);

        return root;
    }
}
