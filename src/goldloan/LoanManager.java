package src.goldloan;

import java.util.*;

public class LoanManager {
    private Map<String, Customer> customers;
    private Map<String, Loan> loans;

    // Constructor loads existing data from CSV files
    public LoanManager() {
        this.customers = FileHandler.loadCustomers();
        this.loans = FileHandler.loadLoans(customers);
        FileHandler.loadRepayments(loans);  // ✅ load repayments into loans
    }

    // --- Customer Management ---
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        FileHandler.saveCustomers(customers); // persist
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.values().forEach(System.out::println);
        }
    }

    // --- Loan Management ---
    public void issueLoan(Loan loan) {
        loans.put(loan.getLoanId(), loan);
        FileHandler.saveLoans(loans); // persist
    }

    public void listLoans() {
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            loans.values().forEach(System.out::println);
        }
    }

    public Loan getLoan(String loanId) {
        return loans.get(loanId);
    }

    // --- Repayment Management ---
    public void addRepayment(String loanId, double amount) {
        Loan loan = loans.get(loanId);
        if (loan == null) {
            System.out.println("❌ Loan not found!");
            return;
        }
        Repayment r = new Repayment(loanId, amount, java.time.LocalDate.now());
        loan.addRepayment(r);
        System.out.println("✅ Repayment recorded: " + r);

        if (loan.getOutstanding() <= 0) {
            System.out.println("🎉 Loan fully repaid! Collateral can be released.");
        }

        FileHandler.saveRepayments(loans); // ✅ persist repayments
    }

    public void viewRepaymentHistory(String loanId) {
        Loan loan = loans.get(loanId);
        if (loan == null) {
            System.out.println("❌ Loan not found!");
            return;
        }
        if (loan.getRepayments().isEmpty()) {
            System.out.println("No repayments recorded for this loan.");
        } else {
            System.out.println("Repayment history for LoanID: " + loanId);
            loan.getRepayments().forEach(System.out::println);
        }
    }
    public void listDefaulters() {
    boolean found = false;
    for (Loan l : loans.values()) {
        if (l.isOverdue()) {
            System.out.println(l + " | Penalty Due: " + l.calculatePenalty());
            found = true;
        }
    }
    if (!found) {
        System.out.println("No defaulters at the moment ✅");
    }
    
}
public String getAllCustomersInfo() {
    StringBuilder sb = new StringBuilder();
    for (Customer c : customers.values()) {  // ✅ fixed
        sb.append(c).append("\n");
    }
    return sb.toString();
}


public String getAllLoansInfo() {
    StringBuilder sb = new StringBuilder();
    for (Loan l : loans.values()) {  // ✅ fixed
        sb.append(l).append("\n");
    }
    return sb.toString();
}
public String getRepaymentHistory(String loanId) {
    Loan loan = getLoan(loanId);
    if (loan == null) return "Loan not found!";
    StringBuilder sb = new StringBuilder();
    for (Repayment r : loan.getRepayments()) {
        sb.append(r).append("\n");
    }
    return sb.toString();
}
public String getDefaultersInfo() {
    StringBuilder sb = new StringBuilder();
    for (Loan l : loans.values()) {
        if (l.getRemainingAmount() > 0) {
            sb.append(l).append("\n");
        }
    }
    return sb.toString();
}


}

