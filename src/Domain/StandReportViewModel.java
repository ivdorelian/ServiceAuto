package Domain;

public class StandReportViewModel {

    private int standNumber;
    private double priceAverage;

    public StandReportViewModel(int standNumber, double priceAverage) {
        this.standNumber = standNumber;
        this.priceAverage = priceAverage;
    }

    public int getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(int standNumber) {
        this.standNumber = standNumber;
    }

    public double getPriceAverage() {
        return priceAverage;
    }

    public void setPriceAverage(double priceAverage) {
        this.priceAverage = priceAverage;
    }
}
