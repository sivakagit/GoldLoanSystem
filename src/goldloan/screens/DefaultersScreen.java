package src.goldloan.screens;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.goldloan.LoanManager;
import src.goldloan.Loan;

public class DefaultersScreen {

    public static void display(LoanManager manager) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Defaulters");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(manager.getDefaultersInfo());

        VBox layout = new VBox(10, textArea);
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
