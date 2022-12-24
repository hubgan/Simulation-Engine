package agh.ics.oop;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Configuration {
    File file;

    public Configuration(String name) {
        String absolutePath = new File("").getAbsolutePath();
        this.file = new File(absolutePath + "/src/main/resources/configurations/" + name + ".txt");
        createConfiguration();
    }

    public static ArrayList<String> getAllNames() {
        ArrayList<String> list = new ArrayList<>();
        String absolutePath = new File("").getAbsolutePath();

        try (Stream<Path> paths = Files.walk(Paths.get(absolutePath + "/src/main/resources/configurations/"))) {
            paths.filter(Files::isRegularFile).forEach(el -> list.add(el.getFileName().toString().replace(".txt", "")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Boolean createConfiguration() {
        try {
            return this.file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeConfiguration(Map<String, String> configuration) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry : configuration.entrySet()) {
                bufferedWriter.write(entry.getKey() + ": " + entry.getValue());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> readConfiguration() {
        BufferedReader bufferedReader;
        String record;
        Map<String, String> map = new LinkedHashMap<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(this.file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                if ((record = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] parts = record.split(": ");
            map.put(parts[0], parts[1]);
        }
        return map;
    }
}
