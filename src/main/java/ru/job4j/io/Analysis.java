package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean active = true;
            for (String s : reader.lines().toList()) {
                if (active && s.startsWith("400") || s.startsWith("500")) {
                    out.print(s.split(" ")[1].trim() + ";");
                    active = false;
                } else if (!active && s.startsWith("200") || s.startsWith("300")) {
                    out.print(s.split(" ")[1].trim() + ";" + System.lineSeparator());
                    active = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
