package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CTVC;
import com.example.qlgiaohang.Class.NhaKho;
import com.example.qlgiaohang.Class.PhieuGuiHang;
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
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GiaoDichModel implements Initializable {
@FXML
private FlowPane List_PhieuGui;
    @FXML
    private Label DuongBo;
    @FXML
    private Label HangKhong;
    @FXML
    private Label TauHoa;
    @FXML
    private Label TauThuy;
    public int duongbo,hangkhong,tauhoa,tauthuy;
    public static GiaoDichModel Intance;
    List<PhieuGuiHang> lstpgh = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Reset_item();
        Intance = this;
    }
    public void Reset_item(){
        List_PhieuGui.getChildren().clear();
        try {
            lstpgh = PhieuGuiHang.AddList();
            Node[] node = new Node[lstpgh.size()];
            for (int i=0;i<node.length;i++){
                try {
                    PhieuGuiHang pgh = lstpgh.get(i);
                    switch (pgh.MaLVC.replaceAll(" ","")){
                        case "LVC01":
                            duongbo ++;
                            break;
                        case "LVC02":
                            tauthuy++;
                            break;
                        case "LVC03":
                            hangkhong++;
                            break;
                        case "LVC04":
                            tauhoa++;
                            break;
                    }
                    Item_PhieuGui.addItem(pgh);
                    node[i] = (Node) FXMLLoader.load(getClass().getResource("View/Item_PhieuGui.fxml"));
                    List_PhieuGui.getChildren().add(node[i]);
                    int finalI = i;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            DuongBo.setText(String.valueOf(duongbo));
            HangKhong.setText(String.valueOf(hangkhong));
            TauHoa.setText(String.valueOf(tauhoa));
            TauThuy.setText(String.valueOf(tauthuy));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Open_AddPhieuGui(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("View/Add_PhieuGuiWindow.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        LoginViewModel.MakeDragable(root,primaryStage);
        Add_PhieuGuiModel.primaryStage = primaryStage;
        LoginViewModel.stage.hide();
        primaryStage.show();
    }
}
