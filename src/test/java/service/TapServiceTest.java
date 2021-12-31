package service;

import json.model.InputFile;
import json.model.Tap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TapServiceTest {

    TapService tapservice = new TapService();

    @Test
    public void getTapsByCustomerShouldReturnCustomerTaps(){
        InputFile inputFile = new InputFile();
        Tap tap1 = new Tap(1,1,"A");
        Tap tap2 = new Tap(2,1,"B");
        Tap tap3 = new Tap(3,2,"C");
        Tap tap4 = new Tap(4,2,"D");
        Tap tap5 = new Tap(5,2,"E");
        Tap tap6 = new Tap(6,2,"F");
        inputFile.setTaps(new Tap[]{tap1, tap2, tap3, tap4, tap5, tap6});

        Map<Integer, List<Tap>> result = tapservice.getTapsByCustomer(inputFile);

        Assert.assertThat(result.size(), CoreMatchers.is(2));
        Assert.assertThat(result, IsMapContaining.hasEntry(1, Arrays.asList(tap1,tap2)));
        Assert.assertThat(result, IsMapContaining.hasEntry(2, Arrays.asList(tap3,tap4,tap5,tap6)));
    }

    @Test
    public void getTapsByCustomerShouldReturnCustomerTapsWhenCutomersHaveSameJourney(){
        InputFile inputFile = new InputFile();
        Tap tap1 = new Tap(1,1,"A");
        Tap tap2 = new Tap(2,1,"B");
        Tap tap3 = new Tap(1,2,"C");
        Tap tap4 = new Tap(2,2,"D");
        inputFile.setTaps(new Tap[]{tap1, tap2, tap3, tap4});

        Map<Integer, List<Tap>> result = tapservice.getTapsByCustomer(inputFile);

        Assert.assertThat(result.size(), CoreMatchers.is(2));
        Assert.assertThat(result, IsMapContaining.hasEntry(1, Arrays.asList(tap1,tap2)));
        Assert.assertThat(result, IsMapContaining.hasEntry(2, Arrays.asList(tap3,tap4)));
    }

}
