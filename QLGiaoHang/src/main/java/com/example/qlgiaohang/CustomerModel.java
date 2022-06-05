package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.Confirmation;
import com.example.qlgiaohang.Class.KhachHang;
import com.oracle.javafx.scenebuilder.app.util.MessageBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerModel implements Initializable  {
    @FXML
    private HBox paneKhachHang ;
    @FXML
    protected TextField txtF_HoTen;
    @FXML
    protected TextField txtF_SoDienThoai;
    @FXML
    protected RadioButton rdB_Nam;
    @FXML
    protected RadioButton rdB_Nu;
    @FXML
    protected TextArea txtF_DiaChi;
    @FXML
    private TextField txtF_TimKiem;

    @FXML
    protected Button btn_Sua = new Button();
    @FXML
    protected Button btn_Them;
    @FXML
    private Button btn_Huy;
    public KhachHang kh = new KhachHang();
    private Pane swap_layout;
    public static   Stage stage;
    String  GioiTinh= "true";
    String MaKH;
    String TenKH;
    ToggleGroup tg_GioiTinh;
    public List<KhachHang> lstKH;
    private PreparedStatement pStmt;
    private Connection conn ;
    public static CustomerModel instance ;

    public static CustomerModel getInstance()throws InvocationTargetException{
        if(instance==null) instance= new CustomerModel();
        return instance;
    }

    public static void setInstance(CustomerModel instance)
    {
        CustomerModel.instance=instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tg_GioiTinh = new ToggleGroup();
        tg_GioiTinh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton) t1;
                if( ((RadioButton) t1).getText().contains( "Nam") ) GioiTinh="False";
                else GioiTinh ="True";
            }
        });

        MaKH=new String();
        loadData();
        instance= this;
    }


    public void loadData()
    {
        ClearText();
        rdB_Nu.setToggleGroup(tg_GioiTinh);
        rdB_Nam.setToggleGroup(tg_GioiTinh);
        rdB_Nu.setSelected(true);

        paneKhachHang.getChildren().clear();
        lstKH = null;
        try {
            lstKH= new ArrayList<KhachHang>(KhachHang.AddList(Dataprovider.DataTable("select * from KhachHang")));
        }catch (SQLException e){
            e.printStackTrace();
        }
        Node[] node = new Node[lstKH.size()];
        for (int i=0;i<node.length;i++){
            try {
                Item_CustomerModel.Add_itemKH(lstKH.get(i));
                node[i] = (Node)FXMLLoader.load(getClass().getResource("View/item_CustomerWindow.fxml"));

                paneKhachHang.getChildren().add(node[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        txtF_SoDienThoai.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if(!newValue.isBlank() )
                {
                    if (!newValue.matches("\\d*") ) {
                        txtF_SoDienThoai.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }

            }
        });
    }

    public void ClearText()
    {
        txtF_SoDienThoai.clear();
        txtF_HoTen.clear();
        rdB_Nu.setSelected(true);
        txtF_DiaChi.clear();
        btn_Them.setDisable(false);
        btn_Sua.setDisable(true);
    }
    public boolean Check()
    {
        if(txtF_HoTen.getText().isEmpty()||txtF_DiaChi.getText().isEmpty()||txtF_SoDienThoai.getText().isEmpty())
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


    public void setBtn_Huy(MouseEvent mouseEvent) throws IOException{
        ClearText();
    }
    public void TimKiem (MouseEvent mouseEvent) throws IOException{

    }

//    public void ThemKhachHang()throws IOException, SQLException{
//        String query = "BEGIN\n" +
//                "INSERT INTO KhachHang\n" +
//                "(MaKH, TenKH, SDT, DiaChi, GioiTinh)\n" +
//                "VALUES\n" +
//                "(DEFAULT , N'" + txtF_HoTen.getText() + "', '" + txtF_SoDienThoai.getText() + "',N'" + txtF_DiaChi.getText() + "', '" + GioiTinh + "');\n" +
//                "END;";
//        Dataprovider.Stmt.executeUpdate(query);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Thông báo!");
//        alert.setHeaderText("Thêm thành công khách hàng: " + txtF_HoTen.getText());
//        alert.showAndWait();
//    }

    public void btn_ThemKhachHang(MouseEvent mouseEvent) throws IOException, SQLException {

        if(Check())
        {
            ResultSet rs = Dataprovider.DataTable(
                    "   Select TenKH, SDT\n" +
                            "   from KhachHang\n" +
                            "   WHERE SDT = '"+ txtF_SoDienThoai.getText() +"'" );
            if(!rs.next()) {
                ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn có đồng ý thêm một khách hàng mới");
                if(result==Confirmation.Co)
                {
                    if(rdB_Nam.isSelected()) GioiTinh="false";
                    String query = "BEGIN\n" +
                            "INSERT INTO KhachHang\n" +
                            "(MaKH, TenKH, SDT, DiaChi, GioiTinh)\n" +
                            "VALUES\n" +
                            "(DEFAULT , N'" + txtF_HoTen.getText() + "', '" + txtF_SoDienThoai.getText() + "',N'" + txtF_DiaChi.getText() + "', '" + GioiTinh + "');\n" +
                            "END;";
                    Dataprovider.Stmt.executeUpdate(query);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Thêm thành công khách hàng: " + txtF_HoTen.getText());
                    alert.showAndWait();
                    loadData();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh báo");
                alert.setHeaderText("Số điện thoại này đã có người sử dụng, vui lòng kiểm tra lại!");
                alert.showAndWait();
            }
        }

    }

    public void btn_SuaKhachHang(MouseEvent mouseEvent) throws SQLException, IOException {
        ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn có đồng ý sửa thông tin khách hàng " + TenKH);
        if(result==Confirmation.Co)
        {
            if(Check())
            {
                ResultSet rs = Dataprovider.DataTable(
                        " Select SDT,MaKH\n" +
                        "   from KhachHang\n" +
                        "   WHERE SDT = '" +txtF_SoDienThoai.getText() +"' or MaKH= '" +MaKH +"'");
                rs.last();
                if(rs.getRow()==2)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setHeaderText("Số điện thoại này đã được sử dụng, vui lòng kiểm tra lại!");
                    alert.showAndWait();
                }
                else
                {
                    rs.beforeFirst();
                    if(rs.next())
                    {
                        if (rdB_Nam.isSelected()) GioiTinh = "false";
                        String query = "BEGIN \n" +
                                "UPDATE  KhachHang \n" +
                                "SET TenKH = N'" + txtF_HoTen.getText() + "'\n" +
                                ", SDT = '" + txtF_SoDienThoai.getText() + "'\n" +
                                ", DiaChi = N'" + txtF_DiaChi.getText() + "'\n" +
                                ", GioiTinh = '" + GioiTinh + "'\n" +
                                " WHERE MaKH = '" + MaKH + "' \n" +
                                "END;";
                        Dataprovider.Stmt.executeUpdate(query);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo!");
                        alert.setHeaderText("Sửa thông tin thành công khách hàng: " + txtF_HoTen.getText());
                        alert.showAndWait();
                        loadData();
                    }
                }
            }
        }
    }

}


