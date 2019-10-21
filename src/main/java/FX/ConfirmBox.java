package FX;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ConfirmBox {

    boolean answer;

    public boolean display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);
        window.setResizable(false);

        Label lblMessage = new Label(message);

        Button btnFalse = new Button("Cancel");
        btnFalse.setOnAction(e -> {
            answer = false;
            window.close();
        });

        Button btnTrue = new Button("Confirm");
        btnTrue.setOnAction(e -> {
            answer = true;
            window.close();
        });

        HBox buttons = new HBox(25);
        buttons.getChildren().addAll(btnFalse, btnTrue);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblMessage, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
