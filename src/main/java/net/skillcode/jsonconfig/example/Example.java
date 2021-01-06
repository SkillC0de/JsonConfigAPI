package net.skillcode.jsonconfig.example;

import net.skillcode.jsonconfig.JsonConfigAPI;
import org.jetbrains.annotations.NotNull;

public class Example {

    @NotNull
    private final static String PATH = "C:/Users/example/";
    @NotNull
    private final static String FILE_NAME = "example.json";

    public static void main(String[] args) {
        final JsonConfigAPI jsonConfigAPI = new JsonConfigAPI(true);
        jsonConfigAPI.registerConfig(new ExampleJsonConfig(), PATH, FILE_NAME);
        final ExampleJsonConfig exampleJsonConfig = jsonConfigAPI.getConfig(ExampleJsonConfig.class);

        System.out.println(exampleJsonConfig.getExampleString());
        System.out.println(exampleJsonConfig.getExampleInt());
        exampleJsonConfig.getExampleList().forEach(System.out::println);
    }

}
