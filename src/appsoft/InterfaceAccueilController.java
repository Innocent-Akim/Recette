/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appsoft;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_connection.Connexion;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class InterfaceAccueilController implements Initializable {

    @FXML
    private PieChart Pchart;
    XYChart.Series<String, Double> series = new XYChart.Series<>();
    PreparedStatement ps;
    ResultSet rs;
    @FXML
    private ComboBox<String> cbxAgence;
    Cls_autrer autre;
    @FXML
    private JFXButton actualiser;
    ObservableList<PieChart.Data> staticView;
    ArrayList<Integer> cout = new ArrayList<>();
    ArrayList<String> produit = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        autre = new Cls_autrer();
//        Pchart.getData().add(series);
        try {

            autre.charger_ComboBox(cbxAgence, "Select Description From TAgence");
//            statistique(autre.Str("Select Max(Description) x From TAgence"));

        } catch (SQLException ex) {

        } catch (Exception ex) {
            Logger.getLogger(InterfaceAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void statistique(String agence) throws SQLException {
        String query = "SELECT * FROM STAT WHERE AGENCE='" + agence + "'";
        staticView = FXCollections.observableArrayList();
        ps = Connexion().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            //series.getData().add(new XYChart.Data<>(rs.getString(2), rs.getDouble(3)));
            staticView.add(new PieChart.Data(rs.getString(3)+"      " +rs.getString(2), rs.getInt(2)));
//            produit.add(rs.getString(3) + rs.getString(2));
//            cout.add(Integer.parseInt(rs.getString(1)));
        }
    }

    @FXML
    private void SelectedAgence(ActionEvent event) throws SQLException {
        statistique(cbxAgence.getValue());
        Pchart.setData(staticView);
    }

}
