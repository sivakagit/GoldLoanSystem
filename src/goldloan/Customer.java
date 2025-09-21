package src.goldloan;

public class Customer {
    private String customerId;
    private String name;
    private String aadhaar;
    private String phone;

    public Customer(String customerId, String name, String aadhaar, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.aadhaar = aadhaar;
        this.phone = phone;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getAadhaar() { return aadhaar; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "CustomerID: " + customerId +
               " | Name: " + name +
               " | Aadhaar: " + aadhaar +
               " | Phone: " + phone;
    }
}
