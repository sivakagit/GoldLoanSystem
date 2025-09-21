package src.goldloan.screens;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.goldloan.*;

public class AddCustomerScreen {

    public static VBox getScreen(LoanManager manager) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

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
            if (idInput.getText().isEmpty() || nameInput.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ID and Name are required!");
                alert.showAndWait();
                return;
            }
            manager.addCustomer(new Customer(
                    idInput.getText(),
                    nameInput.getText(),
                    aadhaarInput.getText(),
                    phoneInput.getText()
            ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer added successfully!");
            alert.showAndWait();
            idInput.clear(); nameInput.clear(); aadhaarInput.clear(); phoneInput.clear();
        });

        VBox layout = new VBox(grid);
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        return layout;
    }
}
