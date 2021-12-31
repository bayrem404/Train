package service;

import json.model.InputFile;
import json.model.Tap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TapService {

    public Map<Integer, List<Tap>> getTapsByCustomer(InputFile inputFile) {
        return  Arrays.stream(inputFile.getTaps())
                .sorted(Comparator.comparingLong(Tap::getUnixTimestamp))
                .collect(Collectors.groupingBy(Tap::getCustomerId));
    }
}
