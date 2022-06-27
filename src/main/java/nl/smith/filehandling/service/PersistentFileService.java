package nl.smith.filehandling.service;

import nl.smith.filehandling.domain.PersistentFile;
import nl.smith.filehandling.persistence.PersistentFileMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */

@Service
@Transactional
@Validated
public class PersistentFileService {

    private final PersistentFileMapper persistentFileMapper;

    public PersistentFileService(@NonNull PersistentFileMapper persistentFileMapper) {
        this.persistentFileMapper = persistentFileMapper;
    }

    public void save(PersistentFile persistentFile) {
        persistentFileMapper.save(persistentFile);
    }

    public PersistentFile getPersistentFileById(Long id) {
        return persistentFileMapper.getPersistenFileById(id);
    }

    public PersistentFile getPersistentFileByFilename(String filename) {
        return persistentFileMapper.getPersistenFileByFilename(filename);
    }

    public void downloadPersistenFile(String filename, String newName) {
        PersistentFile persistenFile = persistentFileMapper.getPersistenFileByFilename(filename);
        File file = new File(newName);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(persistenFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
