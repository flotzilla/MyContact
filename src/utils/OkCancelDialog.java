package utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Date: 27.07.13
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class OkCancelDialog extends Stage {
    public EventHandler confirmHandler;
    public EventHandler cancelHandler;
    private Button okButt;
    private Button cancleButt;

    public OkCancelDialog(String title) {
        setTitle(title);
        initModality(Modality.WINDOW_MODAL);
        initStyle(StageStyle.UTILITY);
        setResizable(false);
        centerOnScreen();

        okButt = new Button("Ok");
        cancleButt = new Button("Cancel");

        setScene(new Scene(VBoxBuilder.create()
        .children(okButt, cancleButt)
        .spacing(20.0)
        .padding(new Insets(10.0))
        .alignment(Pos.CENTER)
        .build(), 300, 200));

        sizeToScene();
    }


    public EventHandler getConfirmHandler() {
        return confirmHandler;
    }

    public void setConfirmHandler(EventHandler confirmHandler) {
        this.confirmHandler = confirmHandler;
        okButt.setOnAction(confirmHandler);
    }

    public EventHandler getCancelHandler() {
        return cancelHandler;
    }

    public void setCancelHandler(EventHandler cancelHandler) {
        this.cancelHandler = cancelHandler;
        cancleButt.setOnAction(cancelHandler);
    }
}
