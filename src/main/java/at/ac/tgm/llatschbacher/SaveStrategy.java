package at.ac.tgm.llatschbacher;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public interface SaveStrategy {
    void save(JsonNode node) throws IOException;
}