package com.cyberspeed.caesarspalace.util;

import com.cyberspeed.caesarspalace.domain.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class ResultPrinter {
    public static void printResult(final Result result) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

        writer.writeValue(new File("result.json"), result);
    }
}
