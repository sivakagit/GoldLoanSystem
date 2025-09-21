package src.goldloan;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddLoanScreen {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Loan");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        // Labels & Inputs
        Label nameLabel = new Label("Customer Name:");
        TextField nameInput = new TextField();

        Label amountLabel = new Label("Loan Amount:");
        TextField amountInput = new TextField();

        Button submit = new Button("Submit");

        grid.add(nameLabel, 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(amountLabel, 0, 1);
        grid.add(amountInput, 1, 1);
        grid.add(submit, 1, 2);

        submit.setOnAction(e -> {
            System.out.println("Customer: " + nameInput.getText() + ", Loan: " + amountInput.getText());
            window.close();
        });

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}
