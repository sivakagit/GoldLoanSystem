package src.goldloan;

public class GoldCollateral {
    private double weight;   // grams
    private double purity;   // percentage
    private double marketRate; // per gram

    public GoldCollateral(double weight, double purity, double marketRate) {
        this.weight = weight;
        this.purity = purity;
        this.marketRate = marketRate;
    }

    public double getWeight() { return weight; }
    public double getPurity() { return purity; }
    public double getMarketRate() { return marketRate; }

    public double calculateValue() {
        return (weight * (purity / 100.0) * marketRate);
    }

    @Override
    public String toString() {
        return "Gold: " + weight + "g | Purity: " + purity + "% | Value: " + calculateValue();
    }
}
