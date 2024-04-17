package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes)
            throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty fileProperty = new FileProperty(attributes.size(),
                    file.getFileName().toString());
            map.putIfAbsent(fileProperty, new ArrayList<>());
            map.get(fileProperty).add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

        public void printDuplicate() {
            for (FileProperty  key : map.keySet()) {
                if (map.get(key).size() > 1) {
                    System.out.println("Name: "
                            + key.getName() + ", Size = " + key.getSize());
                    for (Path path : map.get(key)) {
                        System.out.println("Catalog duplicate: " + path);
                    }
                }
            }
        }
}
