package src.goldloan;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileHandler {
    private static final String CUSTOMER_FILE = "customers.csv";
    private static final String LOAN_FILE = "loans.csv";
    private static final String REPAYMENT_FILE = "repayments.csv";

    // --- Save Customers ---
    public static void saveCustomers(Map<String, Customer> customers) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer c : customers.values()) {
                pw.println(c.getCustomerId() + "," + escape(c.getName()) + "," +
                           c.getAadhaar() + "," + c.getPhone());
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    // --- Load Customers ---
    public static Map<String, Customer> loadCustomers() {
        Map<String, Customer> customers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length >= 4) {
                    Customer c = new Customer(data[0], unescape(data[1]), data[2], data[3]);
                    customers.put(c.getCustomerId(), c);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing customer file, starting fresh...");
        }
        return customers;
    }

    // --- Save Loans ---
    public static void saveLoans(Map<String, Loan> loans) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOAN_FILE))) {
            for (Loan l : loans.values()) {
                if (l.getCustomer() == null || l.getCollateral() == null) continue;
                String[] parts = new String[] {
                    l.getLoanId(),
                    l.getCustomer().getCustomerId(),
                    String.valueOf(l.getCollateral().getWeight()),
                    String.valueOf(l.getCollateral().getPurity()),
                    String.valueOf(l.getCollateral().getMarketRate()),
                    String.valueOf(l.getPrincipal()),
                    String.valueOf(l.getInterestRate()),
                    l.getIssueDate().toString(),
                    l.getDueDate().toString()
                };
                pw.println(String.join(",", parts));
            }
        } catch (IOException e) {
            System.out.println("Error saving loans: " + e.getMessage());
        }
    }

    // --- Load Loans ---
    public static Map<String, Loan> loadLoans(Map<String, Customer> customers) {
        Map<String, Loan> loans = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOAN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length >= 9) {
                    String loanId = data[0];
                    String custId = data[1];
                    Customer c = customers.get(custId);
                    if (c == null) continue;

                    double weight = Double.parseDouble(data[2]);
                    double purity = Double.parseDouble(data[3]);
                    double mRate = Double.parseDouble(data[4]);
                    double principal = Double.parseDouble(data[5]);
                    double interest = Double.parseDouble(data[6]);
                    LocalDate issueDate = LocalDate.parse(data[7]);
                    LocalDate dueDate = LocalDate.parse(data[8]);

                    GoldCollateral g = new GoldCollateral(weight, purity, mRate);
                    Loan l = new Loan(loanId, c, g, principal, interest, issueDate, dueDate);
                    loans.put(l.getLoanId(), l);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing loan file, starting fresh...");
        }
        return loans;
    }

    // --- Save Repayments ---
    public static void saveRepayments(Map<String, Loan> loans) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(REPAYMENT_FILE))) {
            for (Loan l : loans.values()) {
                for (Repayment r : l.getRepayments()) {
                    pw.println(r.getLoanId() + "," + r.getAmount() + "," + r.getDate());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving repayments: " + e.getMessage());
        }
    }

    // --- Load Repayments ---
    public static void loadRepayments(Map<String, Loan> loans) {
        try (BufferedReader br = new BufferedReader(new FileReader(REPAYMENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length >= 3) {
                    String loanId = data[0];
                    double amt = Double.parseDouble(data[1]);
                    LocalDate date = LocalDate.parse(data[2]);

                    Loan l = loans.get(loanId);
                    if (l != null) {
                        l.addRepayment(new Repayment(loanId, amt, date));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No existing repayment file, starting fresh...");
        }
    }

    // Helpers
    private static String escape(String s) { return s.replace(",", "\\,"); }
    private static String unescape(String s) { return s.replace("\\,", ","); }
}
