package src.goldloan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.goldloan.screens.*;

public class MainApp extends Application {

    private LoanManager manager = new LoanManager(); // your existing backend

    @Override
    public void start(Stage primaryStage) {
        Button btnAddCustomer = new Button("Add Customer");
        Button btnIssueLoan = new Button("Issue Loan");
        Button btnViewCustomers = new Button("View Customers");
        Button btnViewLoans = new Button("View Loans");
        Button btnRepayLoan = new Button("Repay Loan");
        Button btnViewRepayments = new Button("View Repayment History");
        Button btnDefaulters = new Button("View Defaulters");
        Button btnExit = new Button("Exit");

        VBox root = new VBox(10);
        root.getChildren().addAll(
                btnAddCustomer, btnIssueLoan, btnViewCustomers, btnViewLoans,
                btnRepayLoan, btnViewRepayments, btnDefaulters, btnExit
        );

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Gold Loan Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnAddCustomer.setOnAction(e -> AddCustomerScreen.display(manager));
        btnIssueLoan.setOnAction(e -> IssueLoanScreen.display(manager));
        btnViewCustomers.setOnAction(e -> ViewCustomersScreen.display(manager));
        btnViewLoans.setOnAction(e -> ViewLoansScreen.display(manager));
        btnRepayLoan.setOnAction(e -> RepayLoanScreen.display(manager));
        btnViewRepayments.setOnAction(e -> ViewRepaymentScreen.display(manager));
        btnDefaulters.setOnAction(e -> DefaultersScreen.display(manager));
        btnExit.setOnAction(e -> primaryStage.close());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
