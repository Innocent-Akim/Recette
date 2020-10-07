/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static appsoft.DeclancheurController.Ecran1;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lib.autre.Cls_agent;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_autrer.onTableClick;
import lib.autre.Cls_enregistrer;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_agentController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField username;
    @FXML
    private TextField postnom;
    @FXML
    private TextField password;
    @FXML
    private TextField prenom;
    @FXML
    private ComboBox<String> genre;
    @FXML
    private JFXButton btnAgent;
    Cls_agent agent;
    Cls_enregistrer All;
    Cls_autrer autre;
    @FXML
    private Label Etat;
    @FXML
    private TableView<?> tab;
    private MenuItem MenuModifier;
    @FXML
    private MenuItem MenuSpprimer;
    @FXML
    private MenuItem MenuAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    void init() {
        try {
            // TODO
            genre.getItems().addAll("Homme", "Femme");
            autre = new Cls_autrer();
            autre.chargerTable("Select * From TAgent", tab, 0);
            All = new Cls_enregistrer();
        } catch (Exception ex) {
            Logger.getLogger(Interface_agentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EnregistrerAll(ActionEvent event) throws SQLException {
        try {
            agent = new Cls_agent(
                    nom.getText(),
                    postnom.getText(),
                    prenom.getText(),
                    password.getText(),
                    username.getText(),
                    genre.getValue(),
                    Integer.parseInt(Etat.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (event.getSource() == btnAgent) {
            if (champs.champs_vide.isFieldsempty(nom, postnom, prenom, password, username, genre)) {
                Cls_autrer.alerteErreur("Vides", "Champ()s Vide !!!");
                Etat.setText("0");
            } else {
                if (All.EnregistreAll(agent) == true) {
                    Cls_autrer.alerteInformation("Information", "Enregistrement reussi !!!");
                    Cls_autrer.initFields(nom, postnom, prenom, password, username, genre);
                    Etat.setText("0");
                    init();
                }
            }
        }
    }

    @FXML
    private void clicTba(MouseEvent event) {
        Etat.setText(onTableClick(tab).get(0));
    }

    @FXML
    private void menuTraitement(ActionEvent event) throws IOException {
//        if (event.getSource() == MenuModifier) {
//            autre.dialogue(Ecran1, "/gui/DialogueAjouterarticle.fxml");
//        }
    }

}
