package root.sample.stages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import root.sample.Controller;

import java.util.ArrayList;

import static root.sample.AppEntryPoint.wndNumber;

/**
 * Created by zhenia on 22.03.17.
 */
public class HandyScene {
    public static Scene createHandyScene() {
        TextArea inputDataBox = new TextArea();
        TextArea outputDataBox = new TextArea();

        Button sort = new Button("Sort");

        GridPane root = setLayout(inputDataBox, outputDataBox, sort);

        setListeners(inputDataBox, outputDataBox, sort);

        wndNumber = 1;

        return new Scene(root, 600, 400);
    }

    private static void setListeners(TextArea inputDataBox, TextArea outputDataBox, Button sort) {
        sort.setOnAction(event -> {
            String[] inputArr = inputDataBox.getText().trim().split("\\s");

            ArrayList<Double> inputConverted = new ArrayList<>();

            try {
                for (int i = 0; i < inputArr.length; i++) {
                    inputConverted.add(Double.parseDouble(inputArr[i]));
                }

                outputDataBox.setText(Controller.sort(inputConverted));
            } catch (NumberFormatException e) {
                outputDataBox.setText("error");
            }
        });

        inputDataBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*|\\.|\\s|-"))
                inputDataBox.setText(newValue.replaceAll("[^\\d^.^\\s^-]", ""));
        });
    }

    private static GridPane setLayout(TextArea inputDataBox, TextArea outputDataBox, Button sort) {
        inputDataBox.setPrefSize(500, 75);
        outputDataBox.setPrefSize(500, 75);
        sort.setPrefSize(150, 50);

        GridPane root = new GridPane();

        ColumnConstraints[] columns = new ColumnConstraints[3];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new ColumnConstraints();
        }

        columns[0].setPercentWidth(15);
        columns[1].setPercentWidth(70);
        columns[2].setPercentWidth(15);

        root.getColumnConstraints().addAll(columns);

        RowConstraints[] rows = new RowConstraints[5];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new RowConstraints();
            rows[i].setPercentHeight(20);
        }

        root.getRowConstraints().addAll(rows);

        root.add(inputDataBox, 1, 1);
        root.add(outputDataBox, 1, 2);
        root.add(sort, 1, 3);

        return root;
    }
}
