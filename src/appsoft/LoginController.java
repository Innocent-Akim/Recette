/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appsoft;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_connection.Connexion;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginInterface;
    @FXML
    private TextField TfdLogin;
    @FXML
    private PasswordField TfdPwd;
    @FXML
    private JFXButton btnConfigurer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ConfigurerAll(ActionEvent event) throws IOException {
        if (event.getSource() == btnConfigurer) {
            Connect();
        }
    }

    void Connect() throws IOException {
        if (Connexion() != null) {
            if (new Cls_autrer().Login(TfdLogin.getText(), TfdPwd.getText()) == true) {
                ((Stage) loginInterface.getScene().getWindow()).close();
                _interface("Declancheur.fxml", "Interface Principale", 0);
            } else {
                Cls_autrer.alerteErreur("Notication", "Connexion échoue");
            }
        } else {
            ((Stage) loginInterface.getScene().getWindow()).close();
            _interface("/gui/interface_configu.fxml", "Configuration", 1);
        }
    }

    public void _interface(String fichierFXML, String str, int x) throws IOException {
        Stage stg = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fichierFXML));
        stg.setTitle(str);
        if (x == 1) {
            stg.setResizable(false);
        }
        stg.setScene(new Scene(root));
        stg.getIcons().add(new javafx.scene.image.Image("/lib/image/icons8_Location_100px.png"));
        stg.show();
    }

    @FXML
    private void KeyUsername(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!TfdLogin.getText().equals("")) {
                TfdPwd.requestFocus();
            }
        }
    }

    @FXML
    private void Keypassword(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            if (!TfdPwd.getText().equals("")) {
                Connect();
            }
        }
    }

}
