package src.goldloan.screens;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.goldloan.LoanManager;
import src.goldloan.Loan;

public class ViewRepaymentScreen {

    public static VBox getScreen(LoanManager manager) {
        TextField loanIdInput = new TextField();
        Button submit = new Button("View Repayment History");
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        HBox inputBox = new HBox(10, loanIdInput, submit);
        VBox layout = new VBox(10, inputBox, textArea);
        layout.setPadding(new Insets(10));

        submit.setOnAction(e -> {
            Loan loan = manager.getLoan(loanIdInput.getText());
            if (loan == null) {
                textArea.setText("Loan not found!");
                return;
            }
            textArea.setText(manager.getRepaymentHistory(loanIdInput.getText()));
        });

        return layout;
    }
}
