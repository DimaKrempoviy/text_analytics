public class ValueUse {

    private double percentage;
    private long getValue;

    public ValueUse (double percentage, long getValue){
        this.percentage = percentage;
        this.getValue = getValue;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public long getGetValue() {
        return getValue;
    }

    public void setGetValue(long getValue) {
        this.getValue = getValue;
    }
}
