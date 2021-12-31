package json.model;

public class Tap {
    private long unixTimestamp;
    private int customerId;
    private String station;

    public Tap(long unixTimestamp, int customerId, String station) {
        this.unixTimestamp = unixTimestamp;
        this.customerId = customerId;
        this.station = station;
    }

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getStation() {
        return station;
    }
}
