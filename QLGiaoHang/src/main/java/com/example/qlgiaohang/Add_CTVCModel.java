package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.HangHoa;
import com.example.qlgiaohang.Class.HoaDon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Add_CTVCModel implements Initializable {
    public static String MaNK;
    public static Stage stage;
    @FXML
    private VBox Vbox_Item;
    @FXML
    private VBox Vbox_ItemAdd;
    public static Add_CTVCModel Instance;
    public static List<HoaDon> LstAdd = new ArrayList<HoaDon>();
    public void Reset_Item() throws IOException, SQLException {
        Vbox_Item.getChildren().clear();
        List<HoaDon> options = new ArrayList<HoaDon>();
            options.addAll(HoaDon.AddLists("0", MaNK));
            for (HoaDon hd : LstAdd
            ) {
                options.removeIf(t -> t.SoHD.equals(hd.SoHD));
            }
            Node[] node = new Node[options.size()];
            for (int i = 0; i < node.length; i++) {
                Item_CTVCModel.Add_item(options.get(i));
                node[i] = (Node) FXMLLoader.load(Add_PhieuGuiModel.class.getResource("View/Item_CTVCWindow.fxml"));
                Vbox_Item.getChildren().addAll(node[i]);
            }
    }
    public void Reset_AddItem() throws IOException {
        Vbox_ItemAdd.getChildren().clear();
        Node[] node = new Node[LstAdd.size()];
        for (int i=0;i<node.length;i++){
            Item_AddCTVCModel.Add_item(LstAdd.get(i));
            node[i] = (Node) FXMLLoader.load(Add_PhieuGuiModel.class.getResource("View/Item_AddCTVC.fxml"));
            Vbox_ItemAdd.getChildren().addAll(node[i]);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Reset_Item();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Instance = this;
    }

    public void Add_Item(ActionEvent actionEvent) throws SQLException, IOException {
        for (HoaDon hd:LstAdd
             ) {
            String qr2 = String.format("EXEC USP_AddCTVC %s,%s",hd.SoHD,MaNK);
            Dataprovider.EditData(qr2);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thêm Thành Công");
        alert.setHeaderText(null);
        alert.setContentText("Thêm Hóa Đơn Thành Công");
        alert.showAndWait();
        LstAdd.clear();
        Reset_AddItem();
        Reset_Item();
    }

    public void Huy(ActionEvent actionEvent) throws SQLException, IOException {
        LstAdd.clear();
        Reset_AddItem();
        Reset_Item();
    }

    public void Close(MouseEvent mouseEvent) throws SQLException, IOException {
        QuanLyModel.Instance.LoadData();
        stage.close();
    }
}
