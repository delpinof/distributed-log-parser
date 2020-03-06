package io.armory.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.armory.model.DistributedLogLine;

public class DistributedLogParser {

    public static final String PATH = "/temp";

    public static void main(String[] args) throws IOException {
        new DistributedLogParser().run(PATH);
    }

    public void run(String directory) throws IOException {
        List<Path> files = getFilesFromDirectory(directory);
        Queue<DistributedLogLine> logQueue = new PriorityQueue<>();

        int lineCount = 0;
        String line;
        boolean eof = false;
        while(!eof) {
            for (Path file : files) {
                line = getLine(file, lineCount);
                if (line != null) {
                    logQueue.offer(new DistributedLogLine(line));
                } else {
                    eof = true;
                    break;
                }
            }
            while (logQueue.size() > 0) {
                System.out.println(logQueue.poll().getLogLine());
            }
            lineCount++;
        }
    }

    public List<Path> getFilesFromDirectory(String directory) throws IOException {
        return Files.list(Paths.get(directory)).collect(Collectors.toList());
    }

    public String getLine(Path filePath, int lineNumber) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath.toUri()));
        String line = null;
        for (int i = 0; i <= lineNumber; i++) {
            line = scanner.hasNext() ? scanner.nextLine() : null;
        }
        scanner.close();
        return line;
    }
}
