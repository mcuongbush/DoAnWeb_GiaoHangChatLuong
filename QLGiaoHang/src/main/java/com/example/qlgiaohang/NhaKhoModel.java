package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CTVC;
import com.example.qlgiaohang.Class.NhaKho;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NhaKhoModel implements Initializable {
    public static String MaKV;
    @FXML
    public static javafx.stage.Stage Stage;
    @FXML
    private TextField tfdiachi;

    @FXML
    private TextField tfdientich;

    @FXML
    private TextField tfnhakho;

    @FXML
    private TextField tfsucchua;
    @FXML
    private FlowPane List_NhaKho;
    @FXML
    private VBox List_itemHD;
    public static String mank;
    public static NhaKhoModel Instance;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<NhaKho> lstNK = NhaKho.AddList(Dataprovider.DataTable("EXEC USP_ShowNhaKho "+MaKV)); //add list nhà kho
            Node[] node = new Node[lstNK.size()];
            for (int i=0;i<node.length;i++){
                Item_NhaKhoModel.Add_item(lstNK.get(i));
                node[i] = (Node) FXMLLoader.load(getClass().getResource("View/Item_NhaKhoWindow.fxml"));
                List_NhaKho.getChildren().add(node[i]);
                int finalI = i;
                node[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {//event khi kich vao nha kho
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        List_itemHD.getChildren().clear();
                        NhaKho nk = lstNK.get(finalI);
                        tfnhakho.setText(nk.getTenNK());
                        tfdiachi.setText(nk.getDiaChi());
                        tfdientich.setText(String.valueOf(nk.getDienTich()));
                        tfsucchua.setText(String.valueOf(nk.getSucChua()));
                        try {
                            mank = nk.getMaNK();
                           ResetItemCTVC(mank);
                        } catch (SQLException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (SQLException | IOException | ParseException e) {
            e.printStackTrace();
        }
        Instance = this;
    }
    public void ResetItemCTVC(String MaNk ) throws SQLException, ParseException {
        List_itemHD.getChildren().clear();
        List<CTVC> lstCTVC = CTVC.AddList(MaNk);
        //add list CTVC của nhà kho
        Node[] node1 = new Node[lstCTVC.size()];
        for (int z = 0; z < node1.length; z++) {
            try {
                ItemHDNhaKhoModel.addItem(lstCTVC.get(z));
                node1[z] = (Node) FXMLLoader.load(getClass().getResource("View/ItemHD_NhaKho.fxml"));
                List_itemHD.getChildren().add(node1[z]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Close_app(MouseEvent mouseEvent) {
        Stage.close();
        LoginViewModel.stage.show();
    }

//    public void Add_CTVC(ActionEvent actionEvent) throws IOException {
//        Stage primaryStage = new Stage();
//        Add_CTVCModel.MaNK = mank;
//        Parent root = FXMLLoader.load(getClass().getResource("View/Add_CTVC.fxml"));
//        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
//        Scene screen = new Scene(root);
//        primaryStage.setScene(screen);
//        Add_CTVCModel.stage = primaryStage;
//        LoginViewModel.MakeDragable(root,primaryStage);
//        primaryStage.show();
//    }
}
