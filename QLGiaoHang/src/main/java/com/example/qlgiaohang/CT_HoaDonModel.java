package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CT_HD;
import com.example.qlgiaohang.Class.HoaDon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.qlgiaohang.Item_CTHD.sl;

public class CT_HoaDonModel implements Initializable {
    @FXML
    public static Stage stage;
    @FXML
    private VBox LIST_CTHD;
    @FXML
    private ImageView View_image;
    @FXML
    private Label View_TenHH;
    @FXML
    private Label View_TenLoai;
    @FXML
    private Label View_MaCTHD;
    @FXML
    private Label View_MaHD;
    @FXML
    private Label View_TongTien;
    @FXML
    private TextField View_SL;
    @FXML
    private TextField View_KG;
    public static boolean isTrue = false;
    public static String MaHD;
    public CT_HD ct_hd = new CT_HD();
    public void close_app(MouseEvent mouseEvent) {
        stage.close();
        if(isTrue == false)LoginViewModel.stage.show();
        else{
            PhieuGuiModel.stage.show();
            isTrue = false;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<CT_HD> lstCTHD = null;
        String query = "EXEC USP_ShowCT_HD "+MaHD;
        try {
            lstCTHD = CT_HD.AddList(Dataprovider.DataTable(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Node[] node = new Node[lstCTHD.size()];
        for (int i=0;i<node.length;i++){
            try {
                Item_CTHD.Add_ItemCTHD(lstCTHD.get(i));
                node[i] = (Node) FXMLLoader.load(getClass().getResource("View/item_CTHD.fxml"));
                List<CT_HD> finalLstCTHD = lstCTHD;
                int finalI = i;
                node[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ct_hd = finalLstCTHD.get(finalI);
                        View_image.setImage(new Image(getClass().getResourceAsStream(ct_hd.img)));
                        View_TenHH.setText(ct_hd.TenHH);
                        View_MaCTHD.setText(ct_hd.MaCTHD);
                        View_MaHD.setText(MaHD);
                        View_SL.setText(String.valueOf(ct_hd.SL));
                        View_KG.setText(String.valueOf(ct_hd.KG));
                        View_TenLoai.setText(ct_hd.TenLHH);
                        View_TongTien.setText(String.valueOf(ct_hd.GiaTien));
                    }
                });
                LIST_CTHD.getChildren().add(node[i]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void HoanThanh(ActionEvent actionEvent) throws SQLException {
        ResultSet rs = Dataprovider.DataTable("EXEC USP_CheckDon "+MaHD);
        String mactvc = null;
        while (rs.next()) mactvc = rs.getString(1);
        if(mactvc != null){
            String s = String.format("UPDATE dbo.HoaDonVanChuyen SET TrangThai = 1 WHERE SoHD = '%s'",MaHD);
            Dataprovider.EditData(s);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thành công");
            alert.setHeaderText(null);
            alert.setContentText("Đơn hàng đã giao thành công");
            alert.showAndWait();
            stage.close();
            if(isTrue == false)LoginViewModel.stage.show();
            else{
                PhieuGuiModel.stage.show();
                isTrue = false;
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Đơn hàng chưa thể giao");
            alert.showAndWait();
        }

    }
}
