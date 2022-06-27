package nl.smith.filehandling.persistence;

import nl.smith.filehandling.domain.PersistentFile;

public interface PersistentFileMapper {

    void save(PersistentFile persistentFile);

    PersistentFile getPersistenFileById(long id);

    PersistentFile getPersistenFileByFilename(String filename);
}
