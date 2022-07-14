package nl.smith.filehandling;

import nl.smith.filehandling.domain.PersistentFile;
import nl.smith.filehandling.enums.Encoding;
import nl.smith.filehandling.enums.Filetype;
import nl.smith.filehandling.service.PersistentFileService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@SpringBootApplication
@MapperScan("nl.smith.filehandling.persistence")
public class Application implements CommandLineRunner {

    private final ApplicationContext context;

    public Application(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PersistentFileService persistentFileService = context.getBean(PersistentFileService.class);

        URL url = Application.class.getClassLoader().getResource("file/myData1.txt");

        if (url != null) {
            PersistentFile persistentFile = PersistentFile.valueOf(url);
            persistentFile = persistentFileService.getPersistentFileByFilename(persistentFile);
            if (persistentFile== null)
                persistentFileService.save(PersistentFile.valueOf(url, Filetype.TEXT, Encoding.UTF_8, "An UTF-8 encoded textfile"));
            else
                persistentFileService.downloadPersistentFile(persistentFile, "olama1");
        }

        url = Application.class.getClassLoader().getResource("file/399px-Colisa_lalia.jpg");
        if (url != null) {
            PersistentFile persistentFile = PersistentFile.valueOf(url);
            persistentFile = persistentFileService.getPersistentFileByFilename(persistentFile);
            if (persistentFile== null)
                persistentFileService.save(PersistentFile.valueOf(url, Filetype.TEXT, Encoding.UTF_8, "An image of Colisa lalia"));
            else
                persistentFileService.downloadPersistentFile(persistentFile, "colisa");
        }
    }
}
