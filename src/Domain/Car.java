package Domain;

public class Car {
    private int id, standNumber;
    private String licenseNumber;
    private int numberOfDays;
    private String report;
    private double billedPrice;

    private boolean leftService;

    public Car(int id, int standNumber, String licenseNumber, int numberOfDays) {
        this(id, standNumber, licenseNumber, numberOfDays, "", 0);
        leftService = false;
    }

    public Car(int id, int standNumber, String licenseNumber, int numberOfDays, String report, double billedPrice) {
        this.id = id;
        this.standNumber = standNumber;
        this.licenseNumber = licenseNumber;
        this.numberOfDays = numberOfDays;
        this.report = report;
        this.billedPrice = billedPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(int standNumber) {
        this.standNumber = standNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public double getBilledPrice() {
        return billedPrice;
    }

    public void setBilledPrice(double billedPrice) {
        this.billedPrice = billedPrice;
    }

    public boolean hasLeftService() {
        return leftService;
    }

    public void setLeftService(boolean leftService) {
        this.leftService = leftService;
    }
}
