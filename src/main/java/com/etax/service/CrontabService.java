package com.etax.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrontabService {

    public String getCrontabForUser(String username) throws IOException {
        String command = "sudo -u " + username + " crontab -l";
        return executeCommand(command);
    }

    public void updateFirstLineForUser(String username, String newCrontab) throws IOException {
        String currentCrontab = getCrontabForUser(username);
        List<String> crontabLines = new ArrayList<>();
        boolean firstLineUpdated = false;

        System.out.println("Current crontab for user " + username + ":");
        System.out.println(currentCrontab);

        try (BufferedReader reader = new BufferedReader(new StringReader(currentCrontab))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!firstLineUpdated && line.contains("/opt/EtaxStatement/generate/")) {
                    line = newCrontab;
                    firstLineUpdated = true;
                }
                crontabLines.add(line);
            }
        }

        if (!firstLineUpdated) {
            crontabLines.add(0, newCrontab);
        }

        String updatedCrontab = String.join("\n", crontabLines);
        System.out.println("Updated crontab for user " + username + ":");
        System.out.println(updatedCrontab);

        String command = "echo \"" + updatedCrontab + "\" | sudo -u " + username + " crontab -";
        executeCommand(command);
    }

    public void updateSecondLineForUser(String username, String newCrontab) throws IOException {
        String currentCrontab = getCrontabForUser(username);
        List<String> crontabLines = new ArrayList<>();
        boolean secondLineUpdated = false;
        int lineCount = 0;

        System.out.println("Current crontab for user " + username + ":");
        System.out.println(currentCrontab);

        try (BufferedReader reader = new BufferedReader(new StringReader(currentCrontab))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineCount == 1 && line.contains("/opt/EtaxStatement/send/")) {
                    line = newCrontab;
                    secondLineUpdated = true;
                }
                crontabLines.add(line);
                lineCount++;
            }
        }

        if (!secondLineUpdated) {
            while (crontabLines.size() < 2) {
                crontabLines.add("");
            }
            crontabLines.set(1, newCrontab);
        }

        String updatedCrontab = String.join("\n", crontabLines);
        System.out.println("Updated crontab for user " + username + ":");
        System.out.println(updatedCrontab);

        String command = "echo \"" + updatedCrontab + "\" | sudo -u " + username + " crontab -";
        executeCommand(command);
    }

    private String executeCommand(String command) throws IOException {
        StringBuilder output = new StringBuilder();
        Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Failed to execute command: " + command, e);
        }

        int exitCode;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted while waiting for command execution", e);
        }

        if (exitCode != 0) {
            throw new IOException("Command execution failed with exit code " + exitCode);
        }

        return output.toString();
    }
}
