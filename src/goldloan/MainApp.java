package src.goldloan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.goldloan.screens.*;

public class MainApp extends Application {

    private LoanManager manager = new LoanManager();
    private BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) {
        VBox menu = new VBox(10);
        menu.setStyle("-fx-padding: 10; -fx-background-color: #2c7be5;");
        menu.setPrefWidth(180);

        Button btnAddCustomer = new Button("Add Customer");
        Button btnIssueLoan = new Button("Issue Loan");
        Button btnViewCustomers = new Button("View Customers");
        Button btnViewLoans = new Button("View Loans");
        Button btnRepayLoan = new Button("Repay Loan");
        Button btnViewRepayments = new Button("Repayment History");
        Button btnDefaulters = new Button("Defaulters");
        Button btnExit = new Button("Exit");

        menu.getChildren().addAll(
                btnAddCustomer, btnIssueLoan, btnViewCustomers, btnViewLoans,
                btnRepayLoan, btnViewRepayments, btnDefaulters, btnExit
        );

        root.setLeft(menu);
        root.setCenter(new VBox()); // empty center initially

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/src/goldloan/style.css").toExternalForm());

        primaryStage.setTitle("Gold Loan Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Button actions (load screens into center)
        btnAddCustomer.setOnAction(e -> root.setCenter(AddCustomerScreen.getScreen(manager)));
        btnIssueLoan.setOnAction(e -> root.setCenter(IssueLoanScreen.getScreen(manager)));
        btnViewCustomers.setOnAction(e -> root.setCenter(ViewCustomersScreen.getScreen(manager)));
        btnViewLoans.setOnAction(e -> root.setCenter(ViewLoansScreen.getScreen(manager)));
        btnRepayLoan.setOnAction(e -> root.setCenter(RepayLoanScreen.getScreen(manager)));
        btnViewRepayments.setOnAction(e -> root.setCenter(ViewRepaymentScreen.getScreen(manager)));
        btnDefaulters.setOnAction(e -> root.setCenter(DefaultersScreen.getScreen(manager)));
        btnExit.setOnAction(e -> primaryStage.close());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
