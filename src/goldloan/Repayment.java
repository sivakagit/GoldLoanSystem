package src.goldloan;

import java.time.LocalDate;

public class Repayment {
    private String loanId;
    private double amount;
    private LocalDate date;

    public Repayment(String loanId, double amount, LocalDate date) {
        this.loanId = loanId;
        this.amount = amount;
        this.date = date;
    }

    public String getLoanId() { return loanId; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return "Repayment -> LoanID: " + loanId +
               " | Amount: " + amount +
               " | Date: " + date;
    }
}
