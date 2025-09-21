package src.goldloan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    private String loanId;
    private Customer customer;
    private GoldCollateral collateral;
    private double principal;
    private double interestRate; // per annum
    private LocalDate issueDate;
    private LocalDate dueDate;
    private List<Repayment> repayments = new ArrayList<>();

    // Constructor for new loans (default 6-month term)
    public Loan(String loanId, Customer customer, GoldCollateral collateral,
                double principal, double interestRate) {
        this(loanId, customer, collateral, principal, interestRate,
             LocalDate.now(), LocalDate.now().plusMonths(6));
    }

    // Constructor for loading from CSV (preserves dates)
    public Loan(String loanId, Customer customer, GoldCollateral collateral,
                double principal, double interestRate,
                LocalDate issueDate, LocalDate dueDate) {
        this.loanId = loanId;
        this.customer = customer;
        this.collateral = collateral;
        this.principal = principal;
        this.interestRate = interestRate;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    // ===== Getters =====
    public String getLoanId() { return loanId; }
    public Customer getCustomer() { return customer; }
    public GoldCollateral getCollateral() { return collateral; }
    public double getPrincipal() { return principal; }
    public double getInterestRate() { return interestRate; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public List<Repayment> getRepayments() { return repayments; }

    // ===== Business Logic =====

    /** Simple interest for the loan term (default 6 months) */
    public double calculateSimpleInterest() {
        long months = ChronoUnit.MONTHS.between(issueDate, dueDate);
        if (months <= 0) months = 6; // fallback
        return (principal * interestRate * months) / 1200.0;
    }

    /** Total amount that must be repaid (principal + interest) */
    public double getTotalPayable() {
        return principal + calculateSimpleInterest();
    }

    /** Total amount repaid so far */
    public double getTotalRepaid() {
        return repayments.stream().mapToDouble(Repayment::getAmount).sum();
    }

    /** Outstanding balance still owed */
    public double getOutstanding() {
        return Math.max(0, getTotalPayable() - getTotalRepaid());
    }

    /** Wrapper for GUI: remaining amount */
    public double getRemainingAmount() {
        return getOutstanding();
    }

    /** True if loan is past due date and still has outstanding balance */
    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && getOutstanding() > 0;
    }

    /** Calculate penalty based on days overdue (2% per month, ~0.00066 per day) */
    public double calculatePenalty() {
        if (!isOverdue()) return 0.0;
        long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        double monthlyRate = 0.02; // 2% of outstanding per month
        return getOutstanding() * monthlyRate * (daysLate / 30.0);
    }

    /** Add a repayment record */
    public void addRepayment(Repayment repayment) {
        repayments.add(repayment);
    }

    @Override
    public String toString() {
        return "LoanID: " + loanId +
               " | Customer: " + (customer != null ? customer.getName() : "N/A") +
               " | Principal: " + principal +
               " | Total Payable: " + getTotalPayable() +
               " | Repaid: " + getTotalRepaid() +
               " | Outstanding: " + getOutstanding() +
               " | Due: " + dueDate +
               (isOverdue() ? " | ⚠ Overdue! Penalty: " + calculatePenalty() : "");
    }
}
