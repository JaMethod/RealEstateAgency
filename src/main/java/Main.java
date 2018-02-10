import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Starts main frame of the application.
 */
public class Main extends Application {

    //values for main frame window
    private static final String TITLE = "Real Estate Agency SERVER";
    private static final String FXML_FILE_PATH = "fxml/server_main_frame.fxml";
    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;
    private static final int MIN_STAGE_WIDTH = 606;
    private static final int MIN_STAGE_HEIGHT = 426;

    /**
     * Loads visual components from fxml file and show main frame.
     * @param primaryStage - main window
     */
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(FXML_FILE_PATH));
            //set window options
            primaryStage.setScene(new Scene(root,SCENE_WIDTH, SCENE_HEIGHT));
            primaryStage.setMinWidth(MIN_STAGE_WIDTH);
            primaryStage.setMinHeight(MIN_STAGE_HEIGHT);
            primaryStage.setTitle(TITLE);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        MysqlConnector.closeConnection();
    }
}