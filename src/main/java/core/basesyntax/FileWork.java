package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileWork {
    public String[] readFromFile(String fileName) {
        File file = new File(fileName);
        String fileRegex = "[^\\p{L}\\p{N}'-]+";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            int value = reader.read();

            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }

            if (builder.toString().isEmpty()) {
                return new String[0];
            }

            String[] stringTokens = builder.toString().split(fileRegex);
            List<String> wList = new ArrayList<>();

            for (String token : stringTokens) {
                String lowerToken = token.trim().toLowerCase();
                if (!lowerToken.startsWith("w")) continue;
                wList.add(lowerToken);
            }

            wList.sort(Comparator.naturalOrder());

            return wList.toArray(new String[0]);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
