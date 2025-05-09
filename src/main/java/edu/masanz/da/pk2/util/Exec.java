package edu.masanz.da.pk2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exec {

    public static String getGitUserName() {

        String command = "git config user.name";

        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            return "nogituser";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            return "nogituser";
        }

        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            return "nogituser";
        }
        return "unknown";
    }
}
