package io.armory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import io.armory.main.DistributedLogParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FileTest {

    String directoryPath = "src/test/resources/temp";

    @Test
    public void filesInDirectoryTest() throws IOException {
        List<Path> files = new DistributedLogParser().getFilesFromDirectory(directoryPath);
        for (Path file : files) {
            System.out.println(file);
        }
    }

    @Test
    public void getLineFromFileTest() throws FileNotFoundException {
        DistributedLogParser parser = new DistributedLogParser();
        String line = parser.getLine(Paths.get(directoryPath.concat("/server-ac329xbv.log")), 1);
        assertEquals("2016-12-20T19:01:25Z, Server A completed job.", line);
    }

    @Test
    public void getNullFromLastLineTest() throws FileNotFoundException {
        DistributedLogParser parser = new DistributedLogParser();
        String line = parser.getLine(Paths.get(directoryPath.concat("/server-ac329xbv.log")), 3);
        assertNull(line);
    }

    @Test
    public void printLinesTest() throws IOException {
        new DistributedLogParser().run(directoryPath);
    }
}
