package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.ItemQL;
import com.example.qlgiaohang.Class.NhaKho;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class QuanLyModel implements Initializable {
    @FXML
    private VBox List_item;
    public List<ItemQL> lst;
    @FXML
    private Label TongDon;
    @FXML
    private Label TongTien;
    @FXML
    private Label KhoiLuong;
    @FXML
    private Label TenNK;
    public static QuanLyModel Instance;
    public static String MaNK;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TenNK.setText((new NhaKho(MaNK).getTenNK()));
            LoadData();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        Instance = this;
    }


    public void LoadData() throws IOException, SQLException {
        List_item.getChildren().clear();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        lst = ItemQL.AddList(MaNK);
        Node[] node = new Node[lst.size()];
        int KG =0;long tongtien = 0;
        TongDon.setText(String.valueOf(lst.size()));
        for (int i = 0;i<node.length;i++){
            ItemQL temp = lst.get(i);
            tongtien += temp.getTongTien();
            ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowKG "+temp.getSoHD());
            while (rs.next()) KG+=rs.getInt(1);
            KhoiLuong.setText(String.valueOf(KG));
            Item_QuanLyModel.Add_Item(temp);
            node[i] = FXMLLoader.load(getClass().getResource("View/Item_QuanLyWindow.fxml"));
            List_item.getChildren().add(node[i]);
            int finalI = i;
//            node[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                        Stage primaryStage = new Stage();
//                        Parent root = null;
//                        TheoDoiModel.lvc = lst.get(finalI).getMaLVC().replaceAll(" ", "");
//                        TheoDoiModel.SoHD = lst.get(finalI).getSoHD();
//                        try {
//                            root = FXMLLoader.load(getClass().getResource("View/TheoDoiWinDow.fxml"));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
//                        Scene screen = new Scene(root);
//                        primaryStage.setScene(screen);
//                        TheoDoiModel.stage = primaryStage;
//                        LoginViewModel.MakeDragable(root, primaryStage);
//                        primaryStage.show();
//                }
//            });
        }
        KhoiLuong.setText(String.valueOf(KG));
        TongTien.setText(formatter.format(tongtien) ) ;
    }

    public void Add_Item(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Add_CTVCModel.MaNK = MaNK;
        Parent root = FXMLLoader.load(getClass().getResource("View/Add_CTVC.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        Add_CTVCModel.stage = primaryStage;
        LoginViewModel.MakeDragable(root,primaryStage);
        primaryStage.show();
    }
}
