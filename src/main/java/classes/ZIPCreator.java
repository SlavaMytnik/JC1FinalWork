package classes;

import interfaces.IArchiveCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс ZIPCreator выполняет архивирование файлов в формате ZIP
 */
public class ZIPCreator implements IArchiveCreator {
    @Override
    public boolean create(Path sourcePath, File outputFile) {
        if (sourcePath != null && outputFile != null) {
            try (FileOutputStream fileOutputStream =
                         new FileOutputStream(outputFile);
                 ZipOutputStream zipOutputStream =
                         new ZipOutputStream(fileOutputStream)) {

                Files.walk(sourcePath)
                        .filter(path -> !Files.isDirectory(path))
                        .forEach(path -> {
                            ZipEntry zipEntry =
                                    new ZipEntry(sourcePath
                                            .relativize(path).toString());

                            try {
                                zipOutputStream.putNextEntry(zipEntry);
                                Files.copy(path, zipOutputStream);
                                zipOutputStream.closeEntry();
                            } catch (IOException ignored) {
                            }
                        });
            } catch (IOException e) {
                return false;
            }

            return true;
        }

        return false;
    }
}
