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
import java.util.List;

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

    public PersistentFileService(PersistentFileMapper persistentFileMapper) {
        this.persistentFileMapper = persistentFileMapper;
    }

    public void save(@NonNull PersistentFile persistentFile) {
        persistentFileMapper.save(persistentFile);
    }

    public PersistentFile getPersistentFileById(long id) {
        return persistentFileMapper.getPersistenFileById(id);
    }

    public PersistentFile getPersistentFileByFilename(@NonNull String filename) {
        return persistentFileMapper.getPersistenFileByFilename(filename);
    }

    public void downloadPersistenFile(@NonNull String filename, @NonNull String newName) {
        PersistentFile persistenFile = persistentFileMapper.getPersistenFileByFilename(filename);
        if (persistenFile == null) return;

        File file = new File(newName);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(persistenFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PersistentFile> getAllPersistentFiles() {
        return persistentFileMapper.findAll();
    }
}
