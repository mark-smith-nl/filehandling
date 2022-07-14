package nl.smith.filehandling.enums;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public enum Encoding {
    UTF_8("UTF-8", StandardCharsets.UTF_8),
    UTF_16("UTF-16", StandardCharsets.UTF_16);

    public static final Encoding NO_ENCODING = null;

    private final String type;

    private final Charset charset;

    Encoding(String type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    public static Encoding getByType(String type) {
        for (Encoding v : values()) if (v.type.equals(type)) return v;
        return null;
    }

    public String getType() {
        return type;
    }

    public Charset getCharset() {
        return charset;
    }
}
