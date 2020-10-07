/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static champs.champs_vide.isFieldsempty;
import com.jfoenix.controls.JFXButton;
import static gui.Infarce_appro_DetailController.tb;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_autrer.alerteAvertissement;
import static lib.autre.Cls_autrer.alerteErreur;
import static lib.autre.Cls_autrer.alerteInformation;
import static lib.autre.Cls_autrer.datebut;
import static lib.autre.Cls_autrer.initFields;
import static lib.autre.Cls_autrer.isNumeric;
import lib.autre.Cls_enregistrer;
import lib.autre.Cls_enter;
import lib.autre.Cls_factory;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class InterfaceApprovisionnementController implements Initializable {

    @FXML
    private TextField TfdArticle;
    @FXML
    private TextField TfdQte;
    @FXML
    private TextField TfdPA;
    @FXML
    private TextField TfdPv;
    @FXML
    private ComboBox<String> CbxAgence;
    @FXML
    private JFXButton BtnEnregistre;
    Cls_factory fac;
    Cls_enregistrer AllEnre;
    Cls_enter entete;
    @FXML
    private Label codeAppro;
    @FXML
    private JFXButton BtnAjouter;
    @FXML
    private DatePicker datePick;
    Cls_autrer autre = new Cls_autrer();
    @FXML
    private Label Bnefice;
    @FXML
    private Label QteDisponible;
    @FXML
    private Label pvente;
    @FXML
    private Button reFreshiPV;
    public static Label codeAppro1;
    public static TextField TfdArticle1;
    public static TextField TfdQte1;
    public static TextField TfdPA1;
    public static TextField TfdPv1;
    public static ComboBox<String> CbxAgence1;
    public static JFXButton BtnAjouter1;
    public static int etat = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    void init() {
        TfdArticle1 = TfdArticle;
        TfdQte1 = TfdQte;
        TfdPA1 = TfdPA;
        TfdPv1 = TfdPv;
        CbxAgence1 = CbxAgence;
        BtnAjouter1 = BtnEnregistre;
        codeAppro1 = codeAppro;
        datePick.setValue(LocalDate.now());
        AllEnre = new Cls_enregistrer();
        try {
            autre.charger_ComboBox(CbxAgence, "Select Description From TAgence");
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceApprovisionnementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceApprovisionnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        autre.ChargememtCompression(TfdArticle, "TListProduit", "Designation");
    }

    @FXML
    private void EnregistreAll(ActionEvent event) throws SQLException, Exception {
        initQuery();
        if (event.getSource() == BtnEnregistre) {
            Appro();
        } else if (event.getSource() == BtnAjouter) {
            if (AllEnre.EnregistreAll(entete) == true) {
                codeAppro.setText(Integer.toString(autre.valeurEntier()));
            }
        } else if (event.getSource() == reFreshiPV) {
            creat();
        }
    }
    int i = 0;

    @FXML
    private void Next(MouseEvent event) throws SQLException {
        i = i + 1;
        if (autre.valeurEntier1("SELECT Max(Code) x FROM TEnteteAppro") >= i) {
            codeAppro.setText(Integer.toString(autre.valeurEntier1("SELECT Code FROM TEnteteAppro WHERE Code=" + i + "")));
        } else {
            alerteInformation("Notification", "Vous êtes a la fin");
        }
    }

    @FXML
    private void Prev(MouseEvent event) throws SQLException {
        i = i - 1;
        if (autre.valeurEntier1("SELECT Min(Code) x FROM TEnteteAppro") <= i) {
            codeAppro.setText(Integer.toString(autre.valeurEntier1("SELECT Code FROM TEnteteAppro WHERE Code=" + i + "")));
        } else {
            alerteInformation("Information", "Vous êtes a la fin");
        }
    }

    @FXML
    private void isNumeriqueQte(KeyEvent event) throws SQLException {
        isNumeric(TfdQte);
        if (event.getCode() == KeyCode.ENTER) {
            if (!isFieldsempty(TfdQte)) {
                TfdPA.requestFocus();
            }
        }
    }

    @FXML
    private void IsnumeriquePA(KeyEvent event) {
        isNumeric(TfdPA);
        if (event.getCode() == KeyCode.ENTER) {
            if (!isFieldsempty(TfdPA)) {
                TfdPv.requestFocus();
            }
        }
    }

    @FXML
    private void IsnumeriquePv(KeyEvent event) throws Exception {
        isNumeric(TfdPv);
        if (event.getCode() == KeyCode.ENTER) {
            initQuery();
            if (!isFieldsempty(TfdPv, TfdPA, TfdQte)) {
                Appro();
            }
        }
    }

    void initQuery() {
        entete = new Cls_enter(datebut(datePick));
        try {
            if (etat != 0) {
                fac = new Cls_factory(
                        CbxAgence.getValue(),
                        Integer.parseInt(codeAppro.getText()),
                        TfdArticle.getText(),
                        Double.parseDouble(TfdQte.getText()),
                        Double.parseDouble(TfdPv.getText()),
                        Double.parseDouble(TfdPA.getText()), etat
                );
            } else {

                fac = new Cls_factory(
                        CbxAgence.getValue(),
                        Integer.parseInt(codeAppro.getText()),
                        TfdArticle.getText(),
                        Double.parseDouble(TfdQte.getText()),
                        Double.parseDouble(TfdPv.getText()),
                        Double.parseDouble(TfdPA.getText()), 0
                );
            }

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void TfdkeyRelesed(KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            initQuery();
            if (AllEnre.EnregistreAll(entete) == true) {
                codeAppro.setText(Integer.toString(autre.valeurEntier()));
            }
            creat();
            TfdQte.requestFocus();

        }
    }

    void creat() throws SQLException {

        if (autre.valeurDecimal("iSValeur '" + TfdArticle.getText() + "',1") != 0.0) {
            TfdPv.setText(Double.toString(autre.valeurDecimal("iSValeur '" + TfdArticle.getText() + "',1")));
            TfdPA.setText(Double.toString(autre.valeurDecimal("iSValeur '" + TfdArticle.getText() + "',2")));
        } else {
            alerteInformation("Notification", "Cela premiere fois que le produit vas etre approvisionner");
        }
    }

    void Appro() throws SQLException, Exception {
        if (!codeAppro.getText().equals("00")) {
            if (isFieldsempty(TfdArticle, TfdPA, TfdPv, TfdQte, CbxAgence)) {
                alerteErreur("Vide", "Completer Champ()s SVP !!!");
            } else {
                if (Double.parseDouble(TfdQte.getText()) <= 0 || Double.parseDouble(TfdPA.getText()) <= 0 || Double.parseDouble(TfdPv.getText()) <= 0) {
                    Cls_autrer.alerteErreur("Notification", "La Quantite ne peux pas être inferieur à 0 ");
                } else {
                    if (Double.parseDouble(TfdPv.getText()) >= Double.parseDouble(TfdPA.getText())) {
                        if (AllEnre.EnregistreAll(fac)) {
                            alerteInformation("Notification", "Vous avez enregistrer avec succès");
                            autre.chargerTable("PsAfAppro", tb, 0);
                            initFields(TfdArticle);
                            TfdQte.setText("0.0");
                            TfdPA.setText("0.0");
                            TfdPv.setText("0.0");
                            QteDisponible.setText(Double.toString(autre.valeurDecimal("Select sum(qte) x From TDetailApp Where CodeApprod='" + codeAppro.getText() + "'")));
                            pvente.setText(Double.toString(autre.valeurDecimal("Select sum(qte*pvente) x From TDetailApp Where CodeApprod='" + codeAppro.getText() + "'")));
                            Bnefice.setText(Double.toString(autre.valeurDecimal("SELECT x FROM VsCalculRecette1 Where CodeApprod='" + codeAppro.getText() + "'")));
                            System.out.println(etat);
                        }
                    } else {
                        alerteAvertissement("Notification", "Punitaire Invalider !!!");
                    }
                }
            }
        } else {
            alerteAvertissement("Notification", "Créé d'abord le numero d'approvissionnement");
        }
    }

    void createEntete() throws SQLException {
        if (AllEnre.EnregistreAll(entete) == true) {
            codeAppro.setText(Integer.toString(autre.valeurEntier()));
        }
    }

}
