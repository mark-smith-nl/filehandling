package nl.smith.filehandling.domain;

import nl.smith.filehandling.enums.Encoding;
import nl.smith.filehandling.enums.Filetype;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.String.format;

/**
 * Immutable class to store file or byte  data to be stored in a database table.
 *
 * @author m.smithhva.nl
 */
public class PersistentFile {

    private Long id;

    private String originalFilename;

    private String filename;

    private Filetype filetype;

    private Encoding encoding;

    private String description;

    private byte[] bytes;

    public PersistentFile() {
    }

    public PersistentFile(String filePath, Filetype filetype, Encoding encoding, String description) throws IOException {
        this(getFile(filePath), filetype, encoding, description);
    }

    public PersistentFile(URL url, Filetype filetype, Encoding encoding, String description) throws IOException {
        this(new File(url.getFile()), filetype, encoding, description);
    }

    public PersistentFile(File file, Filetype filetype, Encoding encoding, String description) throws IOException {
        this(file.getName(), file.getName(), filetype, description, encoding, Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    }

    public PersistentFile(String originalFilename, String filename, Filetype filetype, String description, Encoding encoding, byte[] bytes) {
        this.originalFilename = originalFilename;
        this.filename = filename;
        this.filetype = filetype;
        this.encoding = encoding;
        this.description = description;
        this.bytes = bytes;
    }

    private static File getFile(String filePath) {
        File file = new File(filePath);
        if (! file.exists()) throw new IllegalStateException(format("File %s does not exist", filePath));
        return file;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getFilename() {
        return filename;
    }

    public Filetype getFiletype() {
        return filetype;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
