import json.model.CustomerSummary;
import json.model.InputFile;
import json.model.Tap;
import json.service.JsonService;
import service.CustomerSummaryService;
import service.TapService;

import java.util.*;

public class TrainCompany {

    public static void main(String[] args) {
        String inputPathParam = args[0];
        String ouputPathParam = args[1];
        JsonService jsonService = new JsonService();
        InputFile inputFile = jsonService.readJsonInput(inputPathParam);
        TapService tapService = new TapService();
        Map<Integer, List<Tap>> tapsByCustomer = tapService.getTapsByCustomer(inputFile);
        CustomerSummaryService customerSummaryService = new CustomerSummaryService();
        List<CustomerSummary> customerSummaries = customerSummaryService.getCustomerSummaries(tapsByCustomer);
        jsonService.writeJsonOutput(ouputPathParam, "customerSummaries", customerSummaries);

    }

    }

