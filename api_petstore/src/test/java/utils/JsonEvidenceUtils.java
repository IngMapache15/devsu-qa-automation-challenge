package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonEvidenceUtils {

    public static void saveJson(
            String fileName,
            String content) {

        try {

            Files.createDirectories(
                    Paths.get("target/evidence"));

            Files.writeString(
                    Paths.get(
                            "target/evidence/" +
                                    fileName +
                                    ".json"),
                    content);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error saving evidence: "
                            + e.getMessage());
        }
    }
}