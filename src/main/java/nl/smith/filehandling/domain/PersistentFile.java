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
public class PersistentFile extends AbstractEntity {

    private String originalFilename;

    private String filename;

    private Filetype filetype;

    private Encoding encoding;

    private String description;

    private byte[] bytes;

    /**
     * Constructor used by MyBatis
     */
    private PersistentFile() {

    }

    private PersistentFile(String filename) {
        this.filename = filename;
    }

    private PersistentFile(String originalFilename, String filename, Filetype filetype, Encoding encoding, String description, byte[] bytes) {
        this.originalFilename = originalFilename;
        this.filename = filename;
        this.filetype = filetype;
        this.encoding = encoding;
        this.description = description;
        this.bytes = bytes;
    }

    /**
     * Create an instance of {@link PersistentFile with no content (bytes)}
     * @param url
     * @return
     */
    public static PersistentFile valueOf(URL url) {
        if (url == null) throw new IllegalArgumentException();
        return new PersistentFile(new File(url.getFile()).getName());
    }

    public static PersistentFile valueOf(URL url, Filetype filetype) {
        return valueOf(url, filetype, null);
    }

    public static PersistentFile valueOf(URL url, String filename, Filetype filetype) {
        return valueOf(url, filetype, null);
    }

    public static PersistentFile valueOf(URL url, Filetype filetype, String description) {
        return valueOf(url, filetype, null, description);
    }

    public static PersistentFile valueOf(URL url, Filetype filetype, Encoding encoding, String description) {
        if (url == null) throw new IllegalArgumentException();

        return valueOf(new File(url.getFile()), filetype, encoding, description);
    }

    public static PersistentFile valueOf(File file, Filetype filetype) {
        return valueOf(file, filetype, null);
    }

    public static PersistentFile valueOf(File file, Filetype filetype, String description) {
        return valueOf(file, filetype, null, description);
    }

    public static PersistentFile valueOf(File file, Filetype filetype, Encoding encoding, String description) {
        if (file == null || !file.exists() || file.isDirectory()) throw new IllegalArgumentException();

        try {
            return new PersistentFile(file.getName(), file.getName(), filetype, encoding, description,
                    Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
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
