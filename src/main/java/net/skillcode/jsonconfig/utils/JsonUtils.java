package net.skillcode.jsonconfig.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {

    @Nullable
    public static <T> T getFromFile(@NotNull Class<T> clazz, File file) {
        try {
            if (!file.exists() || !file.getName().endsWith(".json")) return null;
            return new Gson().fromJson(new JsonReader(new FileReader(file)), clazz);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void toFile(@NotNull Object object, @NotNull String path, @NotNull Boolean prettyPrint) {
        new File(path).getParentFile().mkdirs();
        try (final FileWriter fileWriter = new FileWriter(path)) {
            final Gson gson = prettyPrint ? new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create() : new GsonBuilder().disableHtmlEscaping().create();
            final String json = gson.toJson(object);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toFile(@NotNull Object object, @NotNull File file, @NotNull Boolean prettyPrint) {
        toFile(object, file.getPath(), prettyPrint);
    }
}
