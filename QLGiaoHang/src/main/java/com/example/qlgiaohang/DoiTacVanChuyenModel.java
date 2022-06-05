package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.Confirmation;
import com.example.qlgiaohang.Class.DoiTacVanChuyen;
import com.example.qlgiaohang.Class.LoaiVanChuyen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoiTacVanChuyenModel implements Initializable {

    @FXML
    private HBox paneItem;
    @FXML
    public TextField txtF_TenDTVC;
    @FXML
    public TextField txtF_SoDienThoai;
    @FXML
    public TextArea txtA_DiaChi;
    @FXML
    private TextField txtF_TimKiem;
    @FXML
    public ComboBox<LoaiVanChuyen> cbx_LoaiVanChuyen;
    @FXML
    public TextField txtF_HoaHong;
    @FXML
    public DatePicker dtP_NgayHopTac;
    @FXML
    public Button btn_Sua;
    @FXML
    public Button btn_Them;
    @FXML
    private Button btn_Huy;


    public DoiTacVanChuyen dtvc = new DoiTacVanChuyen();
    public static Stage stage;
    public static DoiTacVanChuyenModel instance;
    private PreparedStatement pStmt;
    private Connection conn ;
    public List<DoiTacVanChuyen> lstDTVC =new ArrayList<DoiTacVanChuyen>();
    String MaDTVC;

    public static DoiTacVanChuyenModel getInstance() throws InvocationTargetException{
        if(instance==null) instance = new DoiTacVanChuyenModel();
        return instance;
    }
    public static void setInstance(CustomerModel instance) {CustomerModel.instance=instance;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        loadData();
        MaDTVC= new String();
        instance=this;

    }


    public void ClearText(){
        this.txtF_TenDTVC.clear();
        this.txtA_DiaChi.clear();
        this.txtF_HoaHong.clear();
        this.txtF_SoDienThoai.clear();
        this.cbx_LoaiVanChuyen.getSelectionModel().select(0);
        this.dtP_NgayHopTac.setValue(LocalDate.now());
        btn_Them.setDisable(false);
        btn_Sua.setDisable(true);

    }
    public void loadData()
    {
        ClearText();
        paneItem.getChildren().clear();
        lstDTVC = null;
        //cbx_LoaiVanChuyen=null;
        btn_Sua.setDisable(true);
        dtP_NgayHopTac.setValue(LocalDate.now());
        try{
            lstDTVC = new ArrayList<DoiTacVanChuyen>(DoiTacVanChuyen.AddList(Dataprovider.DataTable("select * from DoiTacVanChuyen")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Node[] node = new Node[lstDTVC.size()];
        for (int i=0;i<node.length;i++) {
            try {
                Item_DoiTacVanChuyenModel.Add_Item(lstDTVC.get(i));
                node[i] = (Node) FXMLLoader.load(getClass().getResource("View/item_DoiTacVanChuyenWindow.fxml"));
                List<DoiTacVanChuyen> finalLstDTVC = lstDTVC;
                int FinalI = i;
                node[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(FinalI <= lstDTVC.size()-1){
                            dtvc = lstDTVC.get(FinalI);
                            txtF_TenDTVC.setText(dtvc.TenDTVC);
                            LoaiVanChuyen temp = null;
                            try {
                                temp = new LoaiVanChuyen(dtvc.MaLVC);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            cbx_LoaiVanChuyen.setValue(cbx_LoaiVanChuyen.getItems().filtered(t->t.MaLVC.equals(dtvc.MaLVC)).get(0));
                            dtP_NgayHopTac.setValue(dtvc.NgayHopTac);
                            txtF_SoDienThoai.setText(dtvc.SoDienThoai);
                            txtA_DiaChi.setText(dtvc.DiaChi);
                            txtF_HoaHong.setText(String.valueOf(dtvc.HoaHong));
                            btn_Sua.setDisable(false);
                            btn_Them.setDisable(true);
                        }
                    }
                });
                paneItem.getChildren().add(node[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableList<LoaiVanChuyen> lstpb = null;
        try {
            lstpb = LoaiVanChuyen.AddObservable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cbx_LoaiVanChuyen.setItems(lstpb);
        //txtF_SoDienThoai.clear();
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

    }
    public boolean Check()
    {
        if(cbx_LoaiVanChuyen.getValue()==null|| txtF_HoaHong.getText().isEmpty()|| txtF_TenDTVC.getText().isEmpty()||txtA_DiaChi.getText().isEmpty()||txtF_SoDienThoai.getText().isEmpty())
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


    public void ThemDoiTac (MouseEvent mouseEvent) throws SQLException {
        if(Check())
        {
            ResultSet rs = Dataprovider.DataTable(
                    "   Select  SDT\n" +
                            "   from DoiTacVanChuyen\n" +
                            "   WHERE SDT = '"+ txtF_SoDienThoai.getText() +"'" );
            if(!rs.next()) {
                ButtonType result = Confirmation.ShowConfirmation("Thông báo!", "Bạn có đồng ý thêm một đối tác mới");
                if (result == Confirmation.Co) {
                    String query = "BEGIN\n" +
                            "INSERT INTO DoiTacVanChuyen\n" +
                            "(MaDTVC, TenDTVC, DiaChi, SDT, NgayHopTac,HoaHong,MaLVC)\n" +
                            "VALUES\n" +
                            "(DEFAULT , N'" + txtF_TenDTVC.getText() + "', N'" + txtA_DiaChi.getText()+ "','" + txtF_SoDienThoai.getText() + "', '" + dtP_NgayHopTac.getValue().format( DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "', '" + txtF_HoaHong.getText()+ "', '" +cbx_LoaiVanChuyen.getValue().MaLVC +  "');\n" +
                            "END;";
                    Dataprovider.Stmt.executeUpdate(query);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Thêm thành công đối tác: " + txtF_TenDTVC.getText());
                    alert.showAndWait();
                    loadData();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh báo");
                alert.setHeaderText("Số điện thoại này đã được sử dụng, vui lòng kiểm tra lại!");
                alert.showAndWait();
            }
        }
    }
    public void  SuaDoiTac(MouseEvent mouseEvent) throws SQLException {
        ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn có đồng ý sửa thông tin đối tác " + txtF_TenDTVC.getText());
        if(result==Confirmation.Co)
        {
            if(Check())
            {
                ResultSet rs = Dataprovider.DataTable(
                        " Select MaDTVC, SDT\n" +
                                "   from DoiTacVanChuyen\n" +
                                "   WHERE SDT = '" +txtF_SoDienThoai.getText() +"' or MaDTVC = '" + MaDTVC +"'" );
                rs.last();
                if(rs.getRow()==2)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setHeaderText("Số điện thoại đã được sử dụng, vui lòng kiểm tra lại!");
                    alert.showAndWait();
                }
                else
                {
                    rs.beforeFirst();
                    if(rs.first()) {
                        String query = "BEGIN \n" +
                                "UPDATE  DoiTacVanChuyen \n" +
                                "SET TenDTVC = N'" + txtF_TenDTVC.getText() + "'\n" +
                                ", SDT = '" + txtF_SoDienThoai.getText() + "'\n" +
                                ", DiaChi = N'" + txtA_DiaChi.getText() + "'\n" +
                                ", MaLVC = N'" + cbx_LoaiVanChuyen.getValue().MaLVC + "'\n" +
                                ", HoaHong = '" + txtF_HoaHong.getText() + "'\n" +
                                ", NgayHopTac ='" + dtP_NgayHopTac.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "'\n" +
                                " WHERE MaDTVC = '" + MaDTVC + "' \n" +
                                "END;";
                        Dataprovider.Stmt.executeUpdate(query);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo!");
                        alert.setHeaderText("Sửa thông tin thành công đối tác: " + txtF_TenDTVC.getText());
                        alert.showAndWait();
                        loadData();
                    }
                }
            }
        }
    }
    public void setBtn_Huy(MouseEvent mouseEvent) throws IOException{
        ClearText();

    }
}
