package json.service;

import com.google.gson.Gson;
import json.model.InputFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonService {

    private Gson gson;

    public JsonService() {
        this.gson = new Gson();
    }

    public InputFile readJsonInput(String inputPathParam) {
        try (Reader reader = new FileReader(inputPathParam)) {
            return gson.fromJson(reader, InputFile.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void writeJsonOutput(String ouputPathParam, String entryListname, List<T> entryList) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put(entryListname, entryList);
        Writer writer = null;
        try {
            writer = new FileWriter(ouputPathParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(map, writer);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
