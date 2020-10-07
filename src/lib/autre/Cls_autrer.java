/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.autre;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static lib.autre.Cls_connection.Connexion;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Akim
 */
public class Cls_autrer {

    private PreparedStatement pst;
    private ResultSet rs;
    private Statement stm;

    public ObservableList AutoCompression(String Table, String Colone) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            pst = Connexion().prepareStatement("Select " + Colone + " From " + Table);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.addAll(rs.getString(Colone));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return list;
    }

    //  Methode Auto Complete
    public static void Autocomplete(TextField txt, AnchorPane pan) {
        txt.getStyleClass().addAll("textfiled", "Search");
        txt.setPrefWidth(186);
        pan.getChildren().add(txt);
    }

    // FOnction D'affichage
    public void ChargememtCompression(TextField textFied, String Table, String Colonne) {
        textFied.setOnMouseClicked((e) -> {
            TextFields.bindAutoCompletion(textFied, AutoCompression(Table, Colonne));
        });
    }

    public void chargerTable(String SQL, TableView v, int taille) throws Exception {

        ObservableList<ObservableList> oblist = FXCollections.observableArrayList();
        v.getColumns().clear();

        rs = Connexion().createStatement().executeQuery(SQL);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
            col.setPrefWidth((v.getPrefWidth() / rs.getMetaData().getColumnCount()) + taille);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Object>, ObservableValue<Object>>() {
                @Override
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList, Object> param) {
                    return new SimpleObjectProperty(param.getValue().get(j)); //To change body of generated methods, choose Tools | Templates.
                }
            });
            v.getColumns().addAll(col);
        }
        while (rs.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                row.add(rs.getString(i));
            }
            oblist.add(row);
        }
        v.setItems(oblist);
    }

    public void dialogue(StackPane stp, String fichierFXML) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fichierFXML));
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setBody(root);
        JFXDialog dialog = new JFXDialog(stp, dl, JFXDialog.DialogTransition.CENTER);
        dialog.show(stp);
    }

    public static void alerteInformation(String titre, String message) {

        Notifications notificationBuilder = Notifications.create()
                .title(titre)
                .text("\n                               " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });

        notificationBuilder.showInformation();
    }

    public static void alerteAvertissement(String titre, String message) {

        Notifications notificationAvertissement;
        notificationAvertissement = Notifications.create()
                .title(titre)
                .text("\n                                 " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });
        notificationAvertissement.showWarning();
    }

    public static void alerteErreur(String titre, String message) {

        Notifications notificationsErreur = Notifications.create()
                .title(titre)
                .text("\n                                 " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });
        notificationsErreur.showError();

    }

    public static void initFields(Node... field) {
        for (Node f : field) {
            if (f instanceof TextField) {
                TextField text = (TextField) f;
                text.setText(null);
            } else if (f instanceof DatePicker) {
                DatePicker text = (DatePicker) f;
                text.setValue(null);
            } else if (f instanceof TextArea) {
                TextArea text = (TextArea) f;
                text.setText(null);
            } else if (f instanceof ComboBox) {
                ComboBox text = (ComboBox) f;
                text.setValue(null);
            } else if (f instanceof Label) {
                Label txt = (Label) f;
                txt.setText(null);
            } else if (f instanceof Text) {
                Text txt = (Text) f;
                txt.setText(null);
            } else if (f instanceof PasswordField) {
                PasswordField txt = (PasswordField) f;
                txt.setText(null);
            }
        }
    }

    public static Cls_autrer AutoCompressExt = new Cls_autrer();

    public static String datebut(DatePicker c) {
        String j, a, m;
        String date1;
        j = c.getEditor().getText().substring(0, 2);
        m = c.getEditor().getText().substring(3, 5);
        a = c.getEditor().getText().substring(6, 10);
        date1 = a + "-" + m + "-" + j;
        return date1;
    }

    public int valeurEntier() throws SQLException {
        stm = Connexion().createStatement();
        rs = stm.executeQuery("Select Max(Code) From TEnteteAppro");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;

    }

    public int valeurEntier1(String Query) throws SQLException {
        stm = Connexion().createStatement();
        rs = stm.executeQuery(Query);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;

    }

    public String Str(String Query) throws SQLException {
        stm = Connexion().createStatement();
        rs = stm.executeQuery(Query);
        if (rs.next()) {
            return rs.getString("x");
        }
        return null;

    }

    public double valeurDecimal(String query) throws SQLException {
        stm = Connexion().createStatement();
        rs = stm.executeQuery(query);
        if (rs.next()) {
            return rs.getDouble("x");
        }
        return 0;

    }

    public static boolean isNumeric(TextField l) {

        boolean bool = false;
        try {
            double a = Double.parseDouble(l.getText());
            bool = true;
        } catch (NumberFormatException e) {
            bool = false;
            l.setText("");
        }

        return bool;
    }

    public void charger_ComboBox(ComboBox<String> comboBox, String Requette) throws ClassNotFoundException, SQLException, Exception {
        try {
            pst = Connexion().prepareStatement(Requette);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboBox.getItems().add(rs.getObject(1).toString().toUpperCase());
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } finally {
            if (rs != null | pst != null | Connexion() != null) {
                rs.close();
                pst.close();
            }
        }
    }

    public static ObservableList<String> onTableClick(TableView maTable) {
        ObservableList<String> o = null;
        try {
            o = (ObservableList) maTable.getItems().get(maTable.getSelectionModel().getSelectedIndex());

        } catch (Exception e) {
        }
        return o;

    }

    public static File chemin() {
        File file;

        FileChooser fileChooser = new FileChooser();
        ObservableList liste = FXCollections.observableArrayList("*.*");

        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", liste));
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            return file;
        }
        return null;
    }

    public boolean Login(String nom, String pwd) {
        try {
            pst = Connexion().prepareCall("Exec PsLogin '" + nom + "','" + pwd + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cls_autrer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean SetValeur(String query) throws SQLException {
        pst = Connexion().prepareCall(query);
        pst.executeUpdate();
        return true;
    }


    public boolean RecuvaLister(Object ob, String code) throws SQLException {

        if (ob instanceof Cls_produit) {
            Cls_produit prod = (Cls_produit) ob;
            pst = Connexion().prepareCall("SELECT Designation,Unite FROM TListProduit WHERE code='" + code + "'");

            rs = pst.executeQuery();
            if (rs.next()) {
                prod.setDesignation(rs.getString(1));
                prod.setUnite(rs.getString(2));
                return true;
            }
        }
        return false;
    }
}
