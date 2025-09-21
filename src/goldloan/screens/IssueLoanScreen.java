package src.goldloan.screens;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.goldloan.*;

public class IssueLoanScreen {

    public static void display(LoanManager manager) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Issue Loan");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        TextField loanIdInput = new TextField();
        TextField custIdInput = new TextField();
        TextField weightInput = new TextField();
        TextField purityInput = new TextField();
        TextField rateInput = new TextField();
        TextField principalInput = new TextField();
        TextField interestInput = new TextField();
        Button submit = new Button("Issue Loan");

        grid.add(new Label("Loan ID:"), 0, 0); grid.add(loanIdInput, 1, 0);
        grid.add(new Label("Customer ID:"), 0, 1); grid.add(custIdInput, 1, 1);
        grid.add(new Label("Gold Weight (g):"), 0, 2); grid.add(weightInput, 1, 2);
        grid.add(new Label("Purity (%):"), 0, 3); grid.add(purityInput, 1, 3);
        grid.add(new Label("Market Rate:"), 0, 4); grid.add(rateInput, 1, 4);
        grid.add(new Label("Principal Amount:"), 0, 5); grid.add(principalInput, 1, 5);
        grid.add(new Label("Interest Rate (% p.a.):"), 0, 6); grid.add(interestInput, 1, 6);
        grid.add(submit, 1, 7);

        submit.setOnAction(e -> {
            Customer cust = manager.getCustomer(custIdInput.getText());
            if (cust == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Customer not found!");
                alert.showAndWait();
                return;
            }
            GoldCollateral gold = new GoldCollateral(
                    Double.parseDouble(weightInput.getText()),
                    Double.parseDouble(purityInput.getText()),
                    Double.parseDouble(rateInput.getText())
            );
            Loan loan = new Loan(
                    loanIdInput.getText(),
                    cust,
                    gold,
                    Double.parseDouble(principalInput.getText()),
                    Double.parseDouble(interestInput.getText())
            );
            manager.issueLoan(loan);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Loan issued successfully!");
            alert.showAndWait();
            window.close();
        });

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
}
