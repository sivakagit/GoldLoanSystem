package src.goldloan.screens;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.goldloan.LoanManager;
import src.goldloan.Loan;
import src.goldloan.Repayment;

import java.time.LocalDate;

public class RepayLoanScreen {

    public static VBox getScreen(LoanManager manager) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField loanIdInput = new TextField();
        TextField amountInput = new TextField();
        Button submit = new Button("Repay Loan");

        grid.add(new Label("Loan ID:"), 0, 0); grid.add(loanIdInput, 1, 0);
        grid.add(new Label("Amount:"), 0, 1); grid.add(amountInput, 1, 1);
        grid.add(submit, 1, 2);

        submit.setOnAction(e -> {
            Loan loan = manager.getLoan(loanIdInput.getText());
            if (loan == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Loan not found!");
                alert.showAndWait();
                return;
            }
            double amt = Double.parseDouble(amountInput.getText());
            loan.addRepayment(new Repayment(amt, LocalDate.now()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Repayment successful!");
            alert.showAndWait();
            loanIdInput.clear(); amountInput.clear();
        });

        VBox layout = new VBox(grid);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));
        return layout;
    }
}
