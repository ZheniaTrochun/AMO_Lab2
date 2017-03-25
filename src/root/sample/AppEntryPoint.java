package root.sample;

import javafx.application.Application;
import javafx.stage.Stage;
import root.sample.stages.MainScene;

public class AppEntryPoint extends Application {

    public static Stage stage;
    public static int wndNumber = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Trochun Evgenii lab 2");
        stage.setScene(MainScene.createMainScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
