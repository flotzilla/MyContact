package utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Date: 19.07.13
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class ModalDialog extends Stage {
    private Stage mainStage;
    private String message;

    public ModalDialog(Stage mainStage, String message) {
        this.mainStage = mainStage;
        this.message = message;
        initStage();
    }

    private void initStage() {

        setTitle("Strom Alert");
        initOwner(mainStage);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);

        Button okButt = new Button("Ok");
        okButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        Scene scene = new Scene(VBoxBuilder.create()
        .children(new Label(message), okButt)
        .alignment(Pos.CENTER)
        .padding(new Insets(10))
        .spacing(20.0)
        .build(), 300, 250);

        setScene(scene);
        sizeToScene();
        setResizable(false);
        show();

    }
}
