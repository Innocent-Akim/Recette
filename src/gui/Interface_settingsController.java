/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import appsoft.DeclancheurController;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import lib.autre.Cls_Agence;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_autrer.initFields;
import lib.autre.Cls_enregistrer;
import lib.autre.Cls_produit;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_settingsController implements Initializable {

    @FXML
    private TextField TfdArticle;
    @FXML
    private TextField TfdQte;
    @FXML
    private Label nom;
    @FXML
    private Label adress;
    @FXML
    private RadioButton BtnArticle;
    @FXML
    private ToggleGroup BtnRadio;
    @FXML
    private RadioButton BtnAgence;
    @FXML
    private TextField TfdSearch;
    @FXML
    private JFXButton BtnEnregistrer;
    @FXML
    private JFXButton BtnSupprimer;
    @FXML
    private Label descriptionTfd;
    Cls_Agence agent;
    Cls_produit produit;
    Cls_enregistrer All;
    Infarce_appro_DetailController parame;
    @FXML
    private TableView<String> tbArticle;
    public static TableView<String> tbArticle1;
    @FXML
    private TableView<String> tbAgence;
    @FXML
    private JFXButton btnAgent;
    @FXML
    private MenuItem menuMofier;
    @FXML
    private MenuItem menusupprimer;
    @FXML
    private MenuItem menuannuler;
    Cls_autrer autre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        tbArticle1 = tbArticle;
    }

    void initList() throws Exception {
        new Cls_autrer().chargerTable("SELECT Code,Designation,Unite FROM TListProduit WHERE bool=1", tbArticle, 0);
    }

    void init() {
        autre = new Cls_autrer();
        All = new Cls_enregistrer();
        parame = new Infarce_appro_DetailController();
        if (BtnArticle.isSelected()) {
            try {
                descriptionTfd.setText("Articles");
                initList();
                new Cls_autrer().chargerTable("Select * From TAgence", tbAgence, 0);
            } catch (Exception ex) {
                Logger.getLogger(Interface_settingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void FonctionAllSearch(KeyEvent event) throws Exception {
        new Cls_autrer().chargerTable("Select Code,Designation,Unite From TListProduit Where"
                + " (designation Like '%" + TfdSearch.getText() + "%'OR Unite "
                + "Like '%" + TfdSearch.getText() + "%' OR Code "
                + "Like '%" + TfdSearch.getText() + "%') AND bool=1 ORDER BY designation", tbArticle, 0);
    }

    @FXML
    private void EnregistrerAll(ActionEvent event) throws SQLException, Exception {
        agent = new Cls_Agence(TfdArticle.getText(), TfdQte.getText());
        produit = new Cls_produit(TfdArticle.getText(), TfdQte.getText());
        if (event.getSource() == BtnEnregistrer) {
            if (!champs.champs_vide.isFieldsempty(TfdArticle, TfdQte)) {
                if (Double.parseDouble(TfdQte.getText()) > 0) {
                    if (BtnArticle.isSelected()) {
                        if (All.EnregistreAll(produit) == true) {
                            Cls_autrer.alerteInformation("Notification", "Enregistrement réussi !!!");
                            initFields(TfdArticle, TfdQte);
                            initList();
                        }
                    } else if (BtnAgence.isSelected()) {
                        if (All.EnregistreAll(agent)) {
                            Cls_autrer.alerteInformation("Notification", "Enregistrement réussi !!!");
                            initFields(TfdArticle, TfdQte);
                        }
                    }
                } else {
                    Cls_autrer.alerteErreur("Notification", "Quantiter invalide !!!");
                }
            } else {
                Cls_autrer.alerteErreur("Notification", "Completer Champ()s Vides !!!");
            }
        } else if (event.getSource() == BtnSupprimer) {
            if (BtnArticle.isSelected()) {

            } else if (BtnAgence.isSelected()) {
            }
        } else if (event.getSource() == btnAgent) {
            parame.dialogue(DeclancheurController.Ecran1, "/gui/interface_agent.fxml");
        }
    }
//CRAWI.2019

    @FXML
    private void SelectedArticle(ActionEvent event) throws Exception {
        if (BtnArticle.isSelected()) {
            descriptionTfd.setText("Articles");
            nom.setText("Article");
            adress.setText("Unite");
            initList();
        }
    }

    @FXML
    private void SelectedAgence(ActionEvent event) throws Exception {
        if (BtnAgence.isSelected()) {
            descriptionTfd.setText("Agences");
            nom.setText("Agence");
            adress.setText("Adresse");
            new Cls_autrer().chargerTable("Select * From TAgence", tbAgence, 0);
        }
    }
    public static String designation, unite, nombtn;
    public static int code1;

    @FXML
    private void menuTraitement(ActionEvent event) throws IOException, SQLException, Exception {
        if (event.getSource() == menuMofier) {
            lib.autre.Cls_produit list = new Cls_produit();
            if (autre.RecuvaLister(list, Cls_autrer.onTableClick(tbArticle).get(0))) {
                designation = list.getDesignation();
                unite = list.getUnite();
                nombtn = "Modifier";
                code1 = Integer.parseInt(Cls_autrer.onTableClick(tbArticle).get(0));
                autre.dialogue(Ecran1, "/gui/DialogueAjouterarticle.fxml");
                System.out.println(unite);
            }
        } else if (event.getSource() == menuannuler) {

        } else if (event.getSource() == menusupprimer) {
            if (autre.SetValeur("UPDATE TListProduit SET bool=0 WHERE code='" + Cls_autrer.onTableClick(tbArticle).get(0) + "'") == true) {
                Cls_autrer.alerteErreur("Notification", "Suppression reussi !!!");
                initList();
            }

        }
    }

}
