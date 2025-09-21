package src.goldloan.screens;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import src.goldloan.LoanManager;

public class DefaultersScreen {

    public static VBox getScreen(LoanManager manager) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(manager.getDefaultersInfo());

        VBox layout = new VBox(textArea);
        layout.setPadding(new javafx.geometry.Insets(10));
        layout.setSpacing(10);
        return layout;
    }
}
