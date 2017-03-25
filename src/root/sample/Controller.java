package root.sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import root.sample.stages.HandyScene;
import root.sample.stages.TestingScene;
import root.sort.BinaryInsertSorter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static root.sample.AppEntryPoint.stage;

public class Controller {
    public static Map<Integer, Double> statistics = new LinkedHashMap<>();

    public static void toHandy() {
        stage.setScene(HandyScene.createHandyScene());
    }

    public static void toTestScene(int n) {
        if(n >= 10) {
            n = 0;
            statistics.clear();
        }

        statistics.put(0, .0);

        stage.setScene(TestingScene.createTestingScene(n));
    }

    public static String sort(ArrayList<Double> inputConverted) {
        Instant start = Instant.now();

        Double[] sorted = new BinaryInsertSorter<Double>().sort(inputConverted.toArray(new Double[inputConverted.size()]));

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sorted.length; i++) {
            sb.append(sorted[i].toString() + "\t");
        }

        sb.append("\nTime: " + timeElapsed.toMillis());

        return sb.toString();
    }

    public static void drawDiagram() {
        Stage modal = new Stage();

        modal.setTitle("info");

        GridPane root = new GridPane();

        Canvas canvas = new Canvas(600, 400);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setLineWidth(1);

        gc.strokeLine(30, 30, 30, 370);
        gc.strokeLine(30, 370, 570, 370);
        gc.strokeLine(30, 30, 27, 35);
        gc.strokeLine(30, 30, 33, 35);
        gc.strokeLine(570, 370, 565, 367);
        gc.strokeLine(570, 370, 565, 373);

        ArrayList<Integer> keys = new ArrayList<>(statistics.keySet());

        for (int i = 1; i < keys.size(); i++) {
            gc.strokeLine(30 + keys.get(i-1) / 100, 370 - statistics.get(keys.get(i-1)) / 10, 30 + keys.get(i) / 100, 370 - statistics.get(keys.get(i)) / 10);
        }

        root.add(canvas, 0, 0);

        modal.setScene(new Scene(root, 600, 400));

        modal.showAndWait();
    }

    public static void showText(String s) {
        Stage modal = new Stage();

        modal.setTitle("info");

        GridPane root = new GridPane();

        Label header = new Label("Input data: ");
        Label arr = new Label("");

        try(BufferedReader br = new BufferedReader(new FileReader(s))) {
            StringBuilder sb = new StringBuilder();

            int r = -1;

            while ((r = br.read()) != -1) {
                sb.append((char)r);
            }

            String[] strArr = sb.toString().split("\\s");
            sb = new StringBuilder();

            for (int i = 0; i < strArr.length; i++) {
                sb.append(strArr[i] + "\t");
                if((i + 1) % 10 == 0) sb.append("\n");
            }

            arr.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        root.add(header, 0, 0);
        root.add(arr, 0, 1);

        modal.setScene(new Scene(root, 400, 400));

        modal.showAndWait();
    }
}
