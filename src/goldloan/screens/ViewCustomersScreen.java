package src.goldloan.screens;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.goldloan.LoanManager;

public class ViewCustomersScreen {

    public static void display(LoanManager manager) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("View Customers");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(manager.getAllCustomersInfo()); // method returns all customers as String

        VBox layout = new VBox(10);
        layout.getChildren().add(textArea);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
