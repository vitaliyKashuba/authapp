package vitaliy94.passworder.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * class to read and write from\into file
 * need to be create instance to avoid exception in static constructor
 */
public class FileUtil
{
    private static final String FILE_NAME = "passwords";
    private static final Path FILE_PATH = Paths.get(FILE_NAME);

    public FileUtil() throws IOException
    {
        if (!Files.exists(FILE_PATH))
        {
            Files.createFile(FILE_PATH);
        }
    }

    /**
     * @return list with lines like "user[separator]hash"
     */
    public List<String> read() throws IOException
    {
        return Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8);
    }

    /**
     * write lines into file
     */
    public void write(List<String> lines) throws IOException
    {
        Files.write(FILE_PATH, lines);
    }

}
