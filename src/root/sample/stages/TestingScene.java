package root.sample.stages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import root.sample.AppEntryPoint;
import root.sample.Controller;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by zhenia on 22.03.17.
 */
public class TestingScene {
    private static int n;
    private static String[] fileNmaeArr = {
            "1.txt", "2.txt", "3.txt", "4.txt", "5.txt", "6.txt", "7.txt", "8.txt", "9.txt", "10.txt"
    };

    public static Scene createTestingScene(int num) {
        n = num;
        Label numberOfTest = new Label("Test number: ");
        Label dataSize = new Label("Array size: ");
        Label sortingTime = new Label("Time of sorting: ");

        Button diagram = new Button("Diagram");
        Button info = new Button("Info");
        Button nextTest = new Button("Next");

        setButtons(numberOfTest, dataSize, sortingTime, diagram, info, nextTest);
        onLoad(nextTest, numberOfTest, dataSize, sortingTime, 0);

        return new Scene(setLayout(numberOfTest, dataSize, sortingTime, diagram, info, nextTest), 600, 400);
    }

    private static void setButtons(Label numberOfTests, Label dataSize, Label sortingTime, Button diagram, Button info, Button nextTest) {
        diagram.setOnAction(event -> Controller.drawDiagram());
        info.setOnAction(event -> Controller.showText(fileNmaeArr[n]));
        nextTest.setOnAction(event -> onLoad(nextTest, numberOfTests, dataSize, sortingTime, ++n));
    }

    private static void onLoad(Button nextTest, Label numberOfTest, Label dataSize, Label sortingTime, int n){
        numberOfTest.setText("Number of test: " + (n + 1));

        if(n == 9) {
            nextTest.setVisible(false);
        }

        try(BufferedReader br = new BufferedReader(new FileReader(fileNmaeArr[n]))) {
            StringBuilder sb = new StringBuilder();

            int r = -1;

            while ((r = br.read()) != -1) {
                sb.append((char)r);
            }

            String[] inputArr = sb.toString().trim().split("\\s");

            ArrayList<Double> inputConverted = new ArrayList<>();

            for (int i = 0; i < inputArr.length; i++) {
                inputConverted.add(Double.parseDouble(inputArr[i]));
            }

            dataSize.setText("Array size: " + inputConverted.size());

            String tmp = Controller.sort(inputConverted);

            sortingTime.setText("Time of sorting: " + tmp.split("Time: ")[1] + "ms");

            Controller.statistics.put(inputConverted.size(), Double.parseDouble(tmp.split("Time: ")[1]));

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            dataSize.setText(e.getMessage());
            sortingTime.setText(e.getMessage());
        }

    }

    private static GridPane setLayout(Label numberOfTest, Label dataSize, Label sortingTime, Button diagram, Button info, Button nextTest) {
        diagram.setPrefSize(200, 50);
        info.setPrefSize(200, 50);
        nextTest.setPrefSize(200, 50);

        GridPane root = new GridPane();

        ColumnConstraints[] columns = new ColumnConstraints[3];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new ColumnConstraints();
            columns[i].setPercentWidth(33);
        }

        root.getColumnConstraints().addAll(columns);

        RowConstraints[] rows = new RowConstraints[8];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new RowConstraints();
            rows[i].setPercentHeight(100/8);
        }

        root.getRowConstraints().addAll(rows);

        root.add(numberOfTest, 1, 1);
        root.add(dataSize, 1, 2);
        root.add(sortingTime, 1, 3);
        root.add(diagram, 1, 4);
        root.add(info, 1, 5);
        root.add(nextTest, 1, 6);

        return root;
    }
}
