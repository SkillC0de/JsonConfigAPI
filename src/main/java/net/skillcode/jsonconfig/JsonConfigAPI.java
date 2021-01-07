package net.skillcode.jsonconfig;

import net.skillcode.jsonconfig.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonConfigAPI {

    private Map<Class<? extends JsonConfig>, JsonConfig> configMap = new HashMap<>();
    private boolean prettyPrint;

    public JsonConfigAPI(@NotNull Boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    public void registerConfig(@NotNull JsonConfig jsonConfig, @NotNull String filePath, @NotNull String fileName) {
        if (!fileName.endsWith(".json")) fileName += ".json";
        final File file = new File(filePath + fileName);
        if (file.exists()) {
            final JsonConfig config = JsonUtils.getFromFile(jsonConfig.getClass(), file);
            configMap.put(config.getClass(), config);
        } else {
            file.getParentFile().mkdirs();
            JsonUtils.toFile(jsonConfig, file, prettyPrint);
            configMap.put(jsonConfig.getClass(), jsonConfig);
        }
    }

    public void unregisterConfig(@NotNull JsonConfig jsonConfig) {
        final Class<? extends JsonConfig> configClass = jsonConfig.getClass();
        if (configMap.containsKey(configClass)) {
            configMap.remove(configClass);
        }
    }

    @Nullable
    public <T> T getConfig(@NotNull Class<? extends JsonConfig> configClass) {
        return (T) configMap.getOrDefault(configClass, null);
    }

}
