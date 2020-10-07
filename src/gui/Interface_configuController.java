/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import appsoft.LoginController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_autrer.initFields;
import static lib.autre.Cls_connection.Connexion;
import static lib.autre.Cls_preference1.RedKey;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_configuController implements Initializable {

    @FXML
    private TextField TfdServeur;
    @FXML
    private TextField TfdDb;
    @FXML
    private TextField TfdLogin;
    @FXML
    private PasswordField TfdPwd;
    @FXML
    private JFXButton btnConfigurer;
    Cls_autrer autre;
    LoginController login;
    @FXML
    private AnchorPane configuration;

    @FXML
    private TextField Url;
    PreparedStatement ps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        autre = new Cls_autrer();
    }

    @FXML
    private void ConfigurerAll(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnConfigurer) {
            if (champs.champs_vide.isFieldsempty(TfdServeur, TfdDb, TfdLogin, TfdPwd)) {
                Cls_autrer.alerteErreur("Vides", "Champ()s vides !!!");
            } else {
                if (RedKey(TfdServeur.getText(), TfdDb.getText(), TfdLogin.getText(), TfdPwd.getText()) == true) {
                    if (Connexion() != null) {
                        ps = Connexion().prepareCall("PsUrl '" + Url.getText() + "'");
                        ps.executeUpdate();
                        initFields(TfdServeur, TfdDb, TfdLogin, TfdPwd, Url);
                        Cls_autrer.alerteInformation("Information", "Configuration reussi !!!");
                        login._interface("/appsoft/login.fxml", "Login", 1);
                        ((Stage) configuration.getScene().getWindow()).close();
                    }
                }
            }
        }
    }

    @FXML
    private void mouseClickUrl(MouseEvent event) {
        Url.setText(Cls_autrer.chemin().toString());
    }

}
