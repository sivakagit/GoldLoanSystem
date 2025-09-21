package src.goldloan.screens;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.goldloan.LoanManager;
import src.goldloan.Loan;

public class RepayLoanScreen {

    public static void display(LoanManager manager) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Repay Loan");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        TextField loanIdInput = new TextField();
        TextField amountInput = new TextField();
        Button submit = new Button("Repay");

        grid.add(new Label("Loan ID:"), 0, 0); grid.add(loanIdInput, 1, 0);
        grid.add(new Label("Repayment Amount:"), 0, 1); grid.add(amountInput, 1, 1);
        grid.add(submit, 1, 2);

        submit.setOnAction(e -> {
            Loan loan = manager.getLoan(loanIdInput.getText());
            if (loan == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Loan not found!");
                alert.showAndWait();
                return;
            }
            manager.addRepayment(loanIdInput.getText(), Double.parseDouble(amountInput.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Repayment successful!");
            alert.showAndWait();
            window.close();
        });

        Scene scene = new Scene(grid, 350, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}
