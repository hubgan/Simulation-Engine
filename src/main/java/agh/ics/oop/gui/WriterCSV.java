package agh.ics.oop.gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WriterCSV {
    private File file;
    private List<String[]> dataLines = new ArrayList<>();
    private PrintWriter printWriter;

    public WriterCSV(String name) {
        String absolutePath = new File("").getAbsolutePath();
        this.file = new File(absolutePath + "/src/main/resources/csv/" + name + ".csv");
        createFile();
        try {
            this.printWriter = new PrintWriter(new FileWriter(this.file, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Header
        addLine(new String[]{"Day", "Number of animals", "Number of plants", "Number of free fields", "Average energy", "Average life time", "Most popular genotype"});
    }

    public void addLine(String[] line) {
        printWriter.println(toCSV(line));
        printWriter.flush();
    }
    public void closeWriter() {
        this.printWriter.close();
    }

    private String toCSV(String[] data) {
        return Stream.of(data).map(this::replaceSpecialCharacters).collect(Collectors.joining(","));
    }

    private String replaceSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private Boolean createFile() {
        try {
            return this.file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
