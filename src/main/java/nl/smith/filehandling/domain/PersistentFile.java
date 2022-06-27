package nl.smith.filehandling.domain;

import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Immutable class to store file or byte  data to be stored in a database table.
 *
 * @author m.smithhva.nl
 */
public class PersistentFile {

    public enum Filetype {
        TEXT,
        IMAGE
    }

    public enum Encoding {
        UTF_8("Olama", StandardCharsets.UTF_8),
        UTF_16("Bokassa", StandardCharsets.UTF_16);

        private final String description;

        private final Charset charset;

        Encoding(String description, Charset charset) {
            this.description = description;
            this.charset = charset;
        }

        public String getDescription() {
            return description;
        }

        public Charset getCharset() {
            return charset;
        }
    }

    private long id;

    private String originalFilename;

    private String filename;

    private Filetype filetype;

    private String description;

    private Encoding encoding;

    private byte[] bytes;

    public PersistentFile() {
    }

    public PersistentFile(File file, Filetype filetype, String description, Encoding encoding) throws IOException {
        this(file.getName(), file.getName(), filetype, description, encoding, Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    }

    public PersistentFile(String originalFilename, String filename, Filetype filetype, String description, Encoding encoding, byte[] bytes) {
        this.originalFilename = originalFilename;
        this.filename = filename;
        this.filetype = filetype;
        this.description = description;
        this.encoding = encoding;
        this.bytes = bytes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Filetype getFiletype() {
        return filetype;
    }

    public void setFiletype(Filetype filetype) {
        this.filetype = filetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
