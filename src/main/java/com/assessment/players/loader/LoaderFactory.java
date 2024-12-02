package com.assessment.players.loader;

import com.assessment.players.model.Player;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoaderFactory {
    private final Map<String, Loader<Player>> loaderMap;

    public LoaderFactory(List<Loader<Player>> loaders) {
        loaderMap = new HashMap<>();
        for (Loader<Player> loader : loaders) {
            loaderMap.put(loader.getTypeName(), loader);
        }
    }

    public Loader<Player> getLoader(String type) {
        Loader<Player> loader = loaderMap.get(type.toLowerCase());
        if (loader == null) {
            throw new IllegalArgumentException("Unsupported file type: " + type);
        }
        return loader;
    }
}