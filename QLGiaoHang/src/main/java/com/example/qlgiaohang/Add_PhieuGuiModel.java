package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class Add_PhieuGuiModel implements Initializable {
    final String imgMacDinh = "file:/D:/QLGiaoHang/target/classes/com/example/qlgiaohang/icon/plus.png";
    final FileChooser fileChooser = new FileChooser();
    private String ImageName;
    private File file;
    @FXML
    private VBox VBox_HH;
    @FXML
    private VBox VBox_KH;
    @FXML
    private VBox VBox_KN;
    @FXML
    private VBox VBox_LVC;
    @FXML
    private CheckBox COD;
    @FXML
    private ComboBox<String> DVT_HH;
    @FXML
    private TextField DiaChi_KH;
    @FXML
    private TextField DiaChi_KN;
    @FXML
    private TextField Gia_HH;
    @FXML
    private ComboBox<LoaiVC> LVC;
    @FXML
    private RadioButton Nam_KH;

    @FXML
    private RadioButton Nam_KN;
    @FXML
    private RadioButton Nu_KH;
    @FXML
    private RadioButton Nu_KN;
    @FXML
    private ComboBox<LoaiHH> Loai_HH;
    @FXML
    private TextArea MoTa_HH;
    @FXML
    private ComboBox<com.example.qlgiaohang.Class.NhaKho> NhaKho;
    @FXML
    private TextField SDT_KH;
    @FXML
    private TextField SDT_KN;
    @FXML
    private TextField Ten_HH;
    @FXML
    private TextField Ten_KH;
    @FXML
    private TextField Ten_KN;
    @FXML
    private Label TongTien;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleGroup gender1;
    @FXML
    private TextField KG;
    @FXML
    private TextField SL;
    @FXML
    private ImageView img_HangHoa;
    @FXML
    private ComboBox<HangHoa> cbx_itemHH;
    @FXML
    private VBox list_itemHH;
    @FXML
    private Pane Add_PhieuGui;
    public String MaNK = MainViewModel.MaNK;
    public static Add_PhieuGuiModel Instance;
    public static Stage primaryStage;
    public List<CT_HH_HD> lstCTHD = new ArrayList<CT_HH_HD>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Add_LVC();
            Add_LHH();
            Add_NhaKho();
            ObservableList<String> list  = FXCollections.observableArrayList("Cái","Hộp","Bao","Chiếc","Gói");
            DVT_HH.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Gia_HH.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Gia_HH.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        SDT_KH.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    SDT_KH.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(SDT_KH.getText().length() == 10){
                    try {
                        ResultSet rs = Dataprovider.DataTable("SELECT * FROM dbo.KhachHang WHERE SDT = "+SDT_KH.getText());
                        if(rs != null){
                            while (rs.next()){
                                Ten_KH.setText(rs.getString(2));
                                DiaChi_KH.setText(rs.getString(4));
                                if(rs.getInt(5) == 0)Nam_KH.setSelected(true);
                                else Nu_KH.setSelected(true);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            });
        SDT_KN.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    SDT_KN.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(SDT_KN.getText().length() == 10){
                    try {
                        ResultSet rs = Dataprovider.DataTable("SELECT * FROM dbo.KhachNhan WHERE SDT = "+SDT_KN.getText());
                        if(rs != null){
                            while (rs.next()){
                                Ten_KN.setText(rs.getString(2));
                                DiaChi_KN.setText(rs.getString(4));
                                if(rs.getInt(5) == 0)Nam_KN.setSelected(true);
                                else Nu_KN.setSelected(true);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Instance = this;
    }
    public void Add_ImgHangHoa(MouseEvent mouseEvent) {
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));

        file = fileChooser.showOpenDialog(primaryStage);
        img_HangHoa.setImage(new Image(file.toURI().toString()));
        ImageName = file.getPath();
    }
    private void Reset() throws IOException {
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
    private void Add_PhieuGui() throws SQLException, IOException {
        if(checkNullPhieuGui()){
            if(lstCTHD.size() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Phiếu gửi phải kèm hàng hóa");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng thêm hàng hóa muốn gửi");
                alert.showAndWait();
            }
            else{
                String genderkh="0",genderkn="0";
                RadioButton bt1 = (RadioButton)gender.getSelectedToggle();
                RadioButton bt2 = (RadioButton)gender1.getSelectedToggle();
                if(bt1.getText().equals("Nữ"))genderkh = "1";
                if(bt2.getText().equals("Nữ"))genderkn = "1";
                String q = String.format("EXEC USP_CreatePhieuGui N'%s','%s',N'%s',%s,N'%s','%s',N'%s',%s,'%s','%s',%s,'%s'",Ten_KH.getText(),SDT_KH.getText(),DiaChi_KH.getText(), genderkh, Ten_KN.getText(),SDT_KN.getText(),DiaChi_KN.getText(),genderkn,LVC.getValue().getMaLVC().replaceAll(" ",""),MaNK,COD.isSelected()?"1":"0","NV0001");
                Dataprovider.EditData(q);
                Add_listHH();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thêm Thành Công");
                alert.setHeaderText(null);
                alert.setContentText("Thêm Phiếu Thành Công");
                alert.showAndWait();
                primaryStage.close();
                Reset();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi nhập liệu");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin Phiếu Gửi");
            alert.showAndWait();
        }
    }
    private void Add_listHH() throws SQLException {
        String MaHD = null;
        ResultSet rs1 = Dataprovider.DataTable("SELECT MAX(SoHD) FROM dbo.HoaDonVanChuyen");
        while (rs1.next()){MaHD = rs1.getString(1);}
        String temp = Tim_MaTD(MaNK,NhaKho.getValue().getMaNK());
        if(temp!= null){
            String add = String.format("UPDATE dbo.HoaDonVanChuyen SET MaTD = '%s' WHERE SoHD = '%s'",temp,MaHD);
            Dataprovider.EditData(add);
        }
        for (CT_HH_HD hh:lstCTHD) {
            String qrt = String.format("SELECT MaHH FROM dbo.HangHoa WHERE TenHH = N'%s'",hh.getTenHH());
            String MaHH= null;
            ResultSet rs = Dataprovider.DataTable(qrt);
            while (rs.next()){ MaHH = rs.getString(1);}
            if(MaHH != null){
                String qr = String.format("EXEC USP_AddCT_HD %s,%s,%s,%s",MaHD,MaHH,String.valueOf(hh.getSL()),String.valueOf(hh.getKG()));
                Dataprovider.EditData(qr);
                String qr2 = String.format("EXEC USP_AddCTVC %s,%s",MaHD,MaNK);
                Dataprovider.EditData(qr2);
            }
            else {
                String MaLHH = null;
                String qrtemp = String.format("SELECT MaLHH FROM dbo.LoaiHH WHERE TenLHH = N'%s'",hh.getTenLHH());
                ResultSet rs2 = Dataprovider.DataTable(qrtemp);
                while (rs2.next())MaLHH  = rs2.getString(1);
                String qr = String.format("EXEC USP_AddHangHoa N'%s',N'%s',%s,%s",hh.getTenHH(),hh.getDVT(),MaLHH ,String.valueOf(hh.getGiaTien()));
                Dataprovider.EditData(qr);
                ResultSet rs3 = Dataprovider.DataTable("SELECT MAX(MaHH) FROM dbo.HangHoa");
                String MaHH1= null;
                while (rs3.next())MaHH1 = rs3.getString(1);
                String qr1 = String.format("EXEC USP_AddCT_HD %s,%s,%s,%s",MaHD,MaHH1,String.valueOf(hh.getSL()),String.valueOf(hh.getKG()));
                Dataprovider.EditData(qr1);
                String qr2 = String.format("EXEC USP_AddCTVC %s,%s",MaHD,MaNK);
                Dataprovider.EditData(qr2);
                Dataprovider.ConnecSQL();
                File file1 = new File(hh.getImg());
                file1.renameTo(new File("src/main/resources/com/example/qlgiaohang/image/"+MaHH1.replaceAll(" ","")+".png"));
            }
        }
    }
    public String Tim_MaTD(String NKDi, String NKDen) throws SQLException {
        List<String> lst1 = new ArrayList<>();
        String qr = String.format("SELECT * FROM dbo.CT_TuyenDuong WHERE MaNK = '%s'",NKDi);
        ResultSet rs = Dataprovider.DataTable(qr);
        while (rs.next()){
            if(rs.getString(4).equals(NKDen))
                return rs.getString(3);
            lst1.add(rs.getString(4));
        }
        for (String mank:lst1)
        {
            String tem = Tim_MaTD(mank,NKDen);
           if(tem != null) return tem;
        }
        return null;
    }
    private void Add_NhaKho() throws SQLException {
        ResultSet rs = Dataprovider.DataTable("SELECT * FROM dbo.NhaKho");
        List<NhaKho> list = com.example.qlgiaohang.Class.NhaKho.AddList(rs);
        ObservableList<NhaKho> listLNK = FXCollections.observableArrayList(list);
        NhaKho.setItems(listLNK);
    }
    private void Add_LVC() throws SQLException, ParseException {

        List<LoaiVC> list = LoaiVC.AddList();
        ObservableList<LoaiVC> listLVC = FXCollections.observableArrayList(list);
        LVC.setItems(listLVC);
    }
    private void Add_LHH() throws SQLException, ParseException {
        List<LoaiHH> list = LoaiHH.AddList();
        ObservableList<LoaiHH> listLHH = FXCollections.observableArrayList(list);
        Loai_HH.setItems(listLHH);
    }
    private boolean checkNullPhieuGui(){
        if(Ten_KH.getText().isEmpty()|| SDT_KH.getText().isEmpty()||DiaChi_KH.getText().isEmpty()||Ten_KN.getText().isEmpty()||SDT_KN.getText().isEmpty()||DiaChi_KN.getText().isEmpty()||
        LVC.getSelectionModel().isEmpty() || NhaKho.getSelectionModel().isEmpty()) return false;
        return true;
    }
    private boolean checkNullHH(){
        if(ImageName.isEmpty()||Ten_HH.getText().isEmpty() || Loai_HH.getSelectionModel().getSelectedItem().getMaLHH().isEmpty()
                || Gia_HH.getText().isEmpty() || DVT_HH.getSelectionModel().isEmpty() || SL.getText().isEmpty() || KG.getText().isEmpty()) return false;
        return true;
    }
    public void ClearHH(){
        img_HangHoa.setImage(new Image(imgMacDinh));
        Ten_HH.clear();
Loai_HH.getSelectionModel().clearSelection();
        Gia_HH.clear();
        DVT_HH.getSelectionModel().clearSelection();
        SL.clear();
        KG.clear();
        MoTa_HH.clear();
    }
    public void ClearKh(){
        Ten_KH.clear();
        Ten_KN.clear();
        SDT_KH.clear();
        SDT_KN.clear();
        DiaChi_KH.clear();
        DiaChi_KN.clear();
        Nam_KH.setSelected(true);
        Nam_KN.setSelected(true);
        LVC.getSelectionModel().clearSelection();
        NhaKho.getSelectionModel().clearSelection();
        COD.setSelected(false);
    }
    public void Close_app(MouseEvent mouseEvent) {
        primaryStage.close();
        LoginViewModel.stage.show();
    }

    public void Add_ItemHH(ActionEvent actionEvent) {
        if(checkNullHH()){
            if(lstCTHD.stream().filter(t->t.getTenHH().equals(Ten_HH.getText())).count() > 0){
                CT_HH_HD temp = lstCTHD.stream().filter(t->t.getTenHH().equals(Ten_HH.getText())).findAny().orElse(null);
                temp.setSL(temp.getSL() +Integer.parseInt(SL.getText()));
                temp.setKG(temp.getKG() + Integer.parseInt(KG.getText()));
            }
            else {
                lstCTHD.add(new CT_HH_HD(ImageName,Ten_HH.getText(),"",Integer.parseInt(SL.getText()),Integer.parseInt(KG.getText()), Loai_HH.getValue().toString(), Integer.parseInt(Gia_HH.getText()),MoTa_HH.getText(),DVT_HH.getValue().toString()));
            }
            Instance.Reset_ItemHH();
            int sum = 0;
            for (CT_HH_HD hd: lstCTHD
                 ) {
                sum+= hd.getGiaTien();
            }
            TongTien.setText(String.valueOf(sum)+"VNĐ");
            ClearHH();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi nhập liệu");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin hàng hóa");
            alert.showAndWait();
        }

    }
    public void Reset_ItemHH(){
        list_itemHH.getChildren().clear();
        Node[] node = new Node[lstCTHD.size()];
        for (int i=0;i<node.length;i++){
            try {
                Add_ItemCTHDModel.Add_ItemCTHD(lstCTHD.get(i));
                node[i] = (Node) FXMLLoader.load(Add_PhieuGuiModel.class.getResource("View/Add_ItemCTHDWindow.fxml"));
                list_itemHH.getChildren().add(node[i]);
                int finalI = i;
                node[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(finalI <= lstCTHD.size()-1){
                            CT_HH_HD ct = lstCTHD.get(finalI);
                            img_HangHoa.setImage(new Image(ct.getImg()));
                            Ten_HH.setText(ct.getTenHH());
                            Loai_HH.setValue(Loai_HH.getItems().filtered(t->t.getTenLHH().equals(ct.getTenLHH())).get(0));
                            Gia_HH.setText(String.valueOf(ct.getGiaTien()));
                            DVT_HH.setValue(ct.getDVT());
                            SL.setText(String.valueOf(ct.getSL()));
                            KG.setText(String.valueOf(ct.getKG()));
                            MoTa_HH.setText(ct.getMota());
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Text_Changed(KeyEvent keyEvent) {
        cbx_itemHH.getItems().clear();
        if(!cbx_itemHH.getEditor().getText().equals("")){
            ObservableList<HangHoa> options = FXCollections.observableArrayList();
            try {
                options.addAll(HangHoa.AddList(cbx_itemHH.getEditor().getText()));
                cbx_itemHH.setItems(options);
                cbx_itemHH.setCellFactory(c -> new StatusListCell());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void Click_Item(ActionEvent actionEvent) {
        try {
            HangHoa hh = cbx_itemHH.getValue();
            img_HangHoa.setImage(new Image(getClass().getResourceAsStream("image/"+hh.getMaHH().replaceAll(" ","")+".png")));
            ImageName = getClass().getResource("image/"+hh.getMaHH().replaceAll(" ","")+".png").toString();
            Ten_HH.setText(hh.getTenHH());
            Loai_HH.setValue(Loai_HH.getItems().filtered(t->t.getMaLHH().equals(hh.getMaLHH())).get(0));
            Gia_HH.setText(hh.getGiaTien().toString());
            DVT_HH.getSelectionModel().select(hh.getDonVT());
            MoTa_HH.setText(hh.getMota());
        }
        catch (Exception e){

        }
    }

    public void Them_PhieuGui(ActionEvent actionEvent) throws SQLException, IOException {
        Add_PhieuGui();
    }
}
