package src.goldloan.screens;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import src.goldloan.LoanManager;

public class ViewLoansScreen {

    public static VBox getScreen(LoanManager manager) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(manager.getAllLoansInfo());

        VBox layout = new VBox(textArea);
        layout.setSpacing(10);
        layout.setStyle("-fx-padding: 10;");
        return layout;
    }
}
