package src.goldloan.screens;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.goldloan.*;

public class AddCustomerScreen {

    public static void display(LoanManager manager) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Customer");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        TextField idInput = new TextField();
        TextField nameInput = new TextField();
        TextField aadhaarInput = new TextField();
        TextField phoneInput = new TextField();
        Button submit = new Button("Add Customer");

        grid.add(new Label("Customer ID:"), 0, 0); grid.add(idInput, 1, 0);
        grid.add(new Label("Name:"), 0, 1); grid.add(nameInput, 1, 1);
        grid.add(new Label("Aadhaar:"), 0, 2); grid.add(aadhaarInput, 1, 2);
        grid.add(new Label("Phone:"), 0, 3); grid.add(phoneInput, 1, 3);
        grid.add(submit, 1, 4);

        submit.setOnAction(e -> {
            manager.addCustomer(new Customer(
                    idInput.getText(),
                    nameInput.getText(),
                    aadhaarInput.getText(),
                    phoneInput.getText()
            ));
            window.close();
        });

        Scene scene = new Scene(grid, 350, 250);
        window.setScene(scene);
        window.showAndWait();
    }
}
