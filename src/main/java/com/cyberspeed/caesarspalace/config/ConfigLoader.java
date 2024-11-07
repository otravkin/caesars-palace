package com.cyberspeed.caesarspalace.config;

import com.cyberspeed.caesarspalace.domain.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {
    public static Config loadFromFile(final String filePath) throws IOException {
        final byte[] json = Files.readAllBytes(Path.of(filePath));
        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, Config.class);
    }
}
