package src.goldloan.screens;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.goldloan.LoanManager;
import src.goldloan.Loan;

public class ViewRepaymentScreen {

    public static void display(LoanManager manager) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("View Repayment History");

        TextField loanIdInput = new TextField();
        Button submit = new Button("View History");
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        HBox inputBox = new HBox(10, loanIdInput, submit);
        VBox layout = new VBox(10, inputBox, textArea);

        submit.setOnAction(e -> {
            Loan loan = manager.getLoan(loanIdInput.getText());
            if (loan == null) {
                textArea.setText("Loan not found!");
                return;
            }
            textArea.setText(manager.getRepaymentHistory(loanIdInput.getText()));
        });

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
