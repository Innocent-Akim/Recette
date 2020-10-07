/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appsoft;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import lib.autre.Cls_autrer;

/**
 *
 * @author Akim
 */
public class DeclancheurController implements Initializable {

    public static StackPane Ecran1;
    Cls_autrer autre = new Cls_autrer();
    @FXML
    private AnchorPane i;
    @FXML
    private StackPane Ecran;
    @FXML
    private Label labNew;
    @FXML
    private Label labRapport;
    @FXML
    private Label labParametre;
    @FXML
    private Label labStat;
    @FXML
    private Circle profil;
    @FXML
    private Label labuserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        init();
    }

    private void init() {
        Ecran1 = Ecran;
        Call(Ecran, "InterfaceAccueil.fxml");
    }

    public void Call(StackPane Ecran, String ecran) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            Ecran.getChildren().removeAll();
            Ecran.getChildren().setAll(root);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void CallNew(MouseEvent event) {
        Call(Ecran, "/gui/infarce_appro_Detail.fxml");
    }

    @FXML
    private void CallReport(MouseEvent event) {
        Call(Ecran, "/gui/interface_rapport.fxml");
    }

    @FXML
    private void CallParametre(MouseEvent event) {
        Call(Ecran, "/gui/interface_settings.fxml");
    }

    @FXML
    private void CallStat(MouseEvent event) {
        Call(Ecran, "InterfaceAccueil.fxml");
    }

    @FXML
    private void callLock(MouseEvent event) {
    }
}
