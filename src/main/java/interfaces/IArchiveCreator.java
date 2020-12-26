package interfaces;

import java.io.File;
import java.nio.file.Path;

/**
 * Интерфейс IArchiveCreator выполняет архивирование файлов
 */
public interface IArchiveCreator {

    /**
     * Метод create выполняет архивирование файлов
     * @param sourcePath - путь к папке файлов
     * @param outputFile - сгенертрованный файл архива
     * @return возвращает сгенертрованный файл архива
     */
    boolean create(Path sourcePath, File outputFile);
}
