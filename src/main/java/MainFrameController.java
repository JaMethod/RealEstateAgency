import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import net.MultiThreadServer;

import java.sql.Connection;

/**
 * The class handles events from user and changes app view.
 */
public class MainFrameController {
    //fields have contain values and states from controls
    @FXML
    private Button buttonConnect;
    @FXML
    private Button buttonOpen;
    @FXML
    private TextField textFieldDatabase;
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private TextField textFieldPort;
    @FXML
    private TextField textFieldLimit;
    @FXML
    private Label labelServerStatus;

    //fields for db connection
    private String url;
    private String user;
    private String password;
    private Connection connection;

    //fields for server
    private int port;
    private int clientLimit;
    private MultiThreadServer server;

    @FXML
    public void initialize() {

    }

    /**
     * Handles "Connect" button.
     * Creates a new connection from MysqlConnector
     */
    public void connect() {
        //init fields
        url = textFieldDatabase.getText();
        user = textFieldUser.getText();
        password = passwordFieldPassword.getText();
        //connects to db
        MysqlConnector.createConnection(url, user, password);
        connection = MysqlConnector.getConnection();
        //if connection is successfully
        if (connection != null) {
            buttonConnect.setDisable(true);
            buttonConnect.setText("Connected");
        }
    }

    /**
     * Handles "Open server" button.
     */
    public void openServer() {
        //only one server will be opened
        if (server == null && connection != null) {
            //init fields
            port = Integer.parseInt(textFieldPort.getText());
            clientLimit = Integer.parseInt(textFieldLimit.getText());
            server = new MultiThreadServer(port, clientLimit);
            //нужно куда-то вставить создание потока что б закрыть потом
            Thread thread = new Thread(server);
            thread.start();
            //changes view in the app
            labelServerStatus.setText("Server is opened");
            labelServerStatus.setTextFill(Color.GREEN);
            buttonOpen.setDisable(true);
        }
    }
}
