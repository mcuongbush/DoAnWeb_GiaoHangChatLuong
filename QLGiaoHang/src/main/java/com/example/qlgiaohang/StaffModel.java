package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.Confirmation;
import com.example.qlgiaohang.Class.KhachHang;
import com.example.qlgiaohang.Class.NhanVien;
import com.example.qlgiaohang.Class.PhongBan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StaffModel implements Initializable {
    @FXML
    private HBox paneItem;
    @FXML
    public TextField txtF_HoTen;
    @FXML
    public TextField txtF_SoDienThoai;
    @FXML
    public RadioButton rdB_Nam;
    @FXML
    public RadioButton rdB_Nu;
    @FXML
    public TextField txtF_DiaChi;
    @FXML
    private TextField txtF_TimKiem;
    @FXML
    public ComboBox <PhongBan> cbx_PhongBan;
    @FXML
    public RadioButton rdB_QuanLy;
    @FXML
    public RadioButton rdB_NhanVien;
    @FXML
    public TextField txtF_BacLuong;
    @FXML
    public DatePicker dtP_NgaySinh;
    @FXML
    public Button btn_Sua;
    @FXML
    public Button btn_Them;
    @FXML
    private Button btn_Huy;
    String MaNV;
    String GioiTinh = "true";
    List<NhanVien> lstNV;
    String  ChucVu ;
    
    public NhanVien nv = new NhanVien();
    private Pane swap_layout;
    public static Stage stage;
    public static StaffModel instance;
    ToggleGroup tg_GioiTinh;

    public  static StaffModel getInstance() {
        if(instance==null) instance=new StaffModel();
        return instance;
    }
    public static void setInstance(StaffModel instance){
        StaffModel.instance=instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tg_GioiTinh = new ToggleGroup();
        tg_GioiTinh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton) t1;
                if( ((RadioButton) t1).getText().contains( "Nam") ) GioiTinh="false";
                else GioiTinh ="true";
            }
        });
        loadData();
        instance=this;
    }


    public void ClearText()
    {
        txtF_BacLuong.clear();
        txtF_DiaChi.clear();
        txtF_SoDienThoai.clear();
        txtF_HoTen.clear();
        cbx_PhongBan.getSelectionModel().select(0);
        dtP_NgaySinh.setValue(LocalDate.now());
        btn_Sua.setDisable(true);
        btn_Them.setDisable(false);
    }

    public void loadData()
    {
        paneItem.getChildren().clear();
        ClearText();
        rdB_Nu.setToggleGroup(tg_GioiTinh);
        rdB_Nam.setToggleGroup(tg_GioiTinh);
        rdB_Nu.setSelected(true);
        lstNV = null;
        btn_Sua.setDisable(true);
        dtP_NgaySinh.setValue(LocalDate.now());
        try {
            lstNV=new ArrayList<NhanVien>(NhanVien.AddList()) ;
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Node[] node = new Node[lstNV.size()];
        for (int i=0;i<node.length;i++){
            try {
                Item_StaffModel.Add_itemNV(lstNV.get(i));
                node[i] = (Node) FXMLLoader.load(getClass().getResource("View/item_StaffWindow.fxml"));
                paneItem.getChildren().add(node[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        txtF_SoDienThoai.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.isBlank()) {
                    if (!newValue.matches("\\d*")) {
                        txtF_SoDienThoai.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });

        ObservableList<PhongBan> lstpb = null;
        try {
            lstpb = PhongBan.AddObservable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cbx_PhongBan.setItems(lstpb);
    }
    public boolean Check()
    {
        if(txtF_HoTen.getText().isEmpty()||txtF_DiaChi.getText().isEmpty()||txtF_SoDienThoai.getText().isEmpty() || txtF_BacLuong.getText().isEmpty()|| cbx_PhongBan.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText("Chưa điền đầy đủ thông tin, vui lòng kiểm tra lại!");
            alert.showAndWait();
            return false;
        }
        else
        {
            return true;
        }
    }

    public void ThemNhanVien(MouseEvent mouseEvent) throws IOException {
        try {
            if (Check()) {
                ResultSet rs = Dataprovider.DataTable(
                        "   Select  SDT\n" +
                                "   from NhanVien\n" +
                                "   WHERE SDT = '" + txtF_SoDienThoai.getText() + "'");
                if (!rs.next()) {
                    ButtonType result = Confirmation.ShowConfirmation("Thông báo!", "Bạn có đồng ý thêm một nhân viên mới");
                    if (result == Confirmation.Co) {
                        if(rdB_NhanVien.isSelected()) ChucVu="Nhân Viên";
                        else ChucVu="Quản Lý";
                        String query = "BEGIN\n" +
                                "INSERT INTO NhanVien\n" +
                                "(MaNV, TenNV, NgaySinh , DiaChi, ChucVu, BacLuong ,MaPB,GioiTinh,SDT)\n" +
                                "VALUES\n" +
                                "(DEFAULT , N'" + txtF_HoTen.getText() + "', '" + dtP_NgaySinh.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                                "', N'" + txtF_DiaChi.getText() + "', '" + ChucVu + "', '" + txtF_BacLuong.getText() + "', '" + cbx_PhongBan.getValue().MaPB + "' , '" + GioiTinh + "','" + txtF_SoDienThoai.getText() + "');\n" +
                                "END;";
                        Dataprovider.Stmt.executeUpdate(query);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo!");
                        alert.setHeaderText("Thêm thành công nhân viên " + txtF_HoTen.getText());
                        alert.showAndWait();
                        loadData();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setHeaderText("Số điện thoại này đã được sử dụng, vui lòng kiểm tra lại!");
                    alert.showAndWait();
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void  SuaNhanVien(MouseEvent mouseEvent)   {
        ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn có đồng ý sửa thông tin nhân viên " + txtF_HoTen.getText());
        try {
            if (result == Confirmation.Co) {
                if (Check()) {
                    ResultSet rs = Dataprovider.DataTable(
                            " Select MaNV, SDT\n" +
                                    "   from NhanVien\n" +
                                    "   WHERE SDT = '" + txtF_SoDienThoai.getText() + "' or MaNV = '" + MaNV + "'");
                    rs.last();
                    if (rs.getRow() == 2) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Cảnh báo");
                        alert.setHeaderText("Số điện thoại đã được sử dụng, vui lòng kiểm tra lại!");
                        alert.showAndWait();
                    } else {
                        rs.beforeFirst();
                        if (rs.first()) {
                            if(rdB_NhanVien.isSelected()) ChucVu="Nhân Viên";
                            else ChucVu="Quản Lý";
                            String query = "BEGIN \n" +
                                    "UPDATE  NhanVien \n" +
                                    "SET TenNV = N'" + txtF_HoTen.getText() + "'\n" +
                                    ", NgaySinh ='" + dtP_NgaySinh.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "'\n" +
                                    ",ChucVu = N'"+ ChucVu +"'\n "+
                                    ", BacLuong = N'" + txtF_BacLuong.getText() + "'\n" +
                                    ", DiaChi = N'" + txtF_DiaChi.getText() + "'\n" +
                                    ", MaPB = N'" + cbx_PhongBan.getValue().MaPB + "'\n" +
                                    ", GioiTinh = '" + GioiTinh + "'\n" +
                                    ", SDT = '" + txtF_SoDienThoai.getText() + "'\n" +
                                    " WHERE MaNV = '" + MaNV + "' \n" +
                                    "END;";
                            Dataprovider.Stmt.executeUpdate(query);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Thông báo!");
                            alert.setHeaderText("Sửa thông tin thành công nhân viên " + txtF_HoTen.getText());
                            alert.showAndWait();
                            loadData();
                        }
                    }
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void setBtn_Huy(MouseEvent mouseEvent) throws IOException{
        ClearText();
    }
    public void TimKiem (MouseEvent mouseEvent) throws IOException{

    }

}
