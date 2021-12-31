package json.model;

import java.util.List;

public class CustomerSummary {
    private int customerId;
    private int totalCostInCents;
    private List<Trip> trips;

    public CustomerSummary(int customerId, int totalCostInCents, List<Trip> trips) {
        this.customerId = customerId;
        this.totalCostInCents = totalCostInCents;
        this.trips = trips;
    }
}
