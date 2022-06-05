package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Add_PhieuGuiModel;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StatusListCell extends ListCell<HangHoa> {
    protected void updateItem(HangHoa item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if (item != null) {
            ImageView imageView = new ImageView(new Image(Add_PhieuGuiModel.class.getResourceAsStream("image/"+item.getMaHH()+".png")));
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            setGraphic(imageView);
            setText(item.getTenHH());
        }
    }

}
