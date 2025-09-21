package src.goldloan;

import java.time.LocalDate;

/**
 * Represents a repayment made on a loan.
 */
public class Repayment {
    private String loanId;      // Optional: loan this repayment belongs to
    private double amount;      // Amount paid
    private LocalDate date;     // Date of repayment

    /** Existing constructor without loanId */
    public Repayment(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    /** New constructor with loanId */
    public Repayment(String loanId, double amount, LocalDate date) {
        this.loanId = loanId;
        this.amount = amount;
        this.date = date;
    }

    // ===== Getters =====
    public String getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (loanId != null) {
            return "LoanID: " + loanId + " | Amount: " + amount + " | Date: " + date;
        }
        return "Amount: " + amount + " | Date: " + date;
    }
}
