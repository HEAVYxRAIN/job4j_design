package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName argsName) {
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(
                    "Parameter 'd' must contain path to directory!");
        }
        if (!argsName.get("e").startsWith(".")
                || argsName.get("e").length() < 2) {
            throw new IllegalArgumentException(
                    "Parameter 'e' must begin from '.' "
                            + "and length must bigger than 1 symbol!");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(
                    "Parameter 'o' must have '.zip' extension!");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        List<Path> paths = Search.search(Path.of(argsName.get("d")), predicate
                -> !predicate.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packFiles(paths, Path.of(argsName.get("o")).toFile());
        /*Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
    }
}
