package net.skillcode.jsonconfig.example;

import net.skillcode.jsonconfig.JsonConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleJsonConfig implements JsonConfig {

    private String exampleString = "This is an example String";
    private int exampleInt = 0;
    private List<String> exampleList = new ArrayList<>(Arrays.asList("example1", "example2", "example3"));

    public String getExampleString() {
        return exampleString;
    }

    public int getExampleInt() {
        return exampleInt;
    }

    public List<String> getExampleList() {
        return exampleList;
    }
}
