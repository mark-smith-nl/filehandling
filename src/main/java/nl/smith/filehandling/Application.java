package nl.smith.filehandling;

import nl.smith.filehandling.domain.PersistentFile;
import nl.smith.filehandling.persistence.PersistentFileMapper;
import nl.smith.filehandling.service.PersistentFileService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

@SpringBootApplication
@MapperScan("nl.smith.filehandling.persistence")
public class Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        PersistentFileService persistentFileService = context.getBean(PersistentFileService.class);
     //   URL resource1 = Application.class.getClassLoader().getResource("test-classes/file/myData1.txt");
        URL resource2 = Application.class.getClassLoader().getResource("file/399px-Colisa_lalia.jpg");

    //    persistentFileService.save(new PersistentFile(new File(resource1.getFile()), PersistentFile.Filetype.TEXT, "Osama Bin Laden", PersistentFile.Encoding.UTF_8));
    //    persistentFileService.save(new PersistentFile(new File(resource2.getFile()), PersistentFile.Filetype.IMAGE, "Bokassa", null));


        persistentFileService.downloadPersistenFile("399px-Colisa_lalia.jpg", "/Users/m.smithhva.nl/tmp/colisa.jpg");

       /* if (persistentFile.getFiletype().equals(PersistentFile.Filetype.TEXT)){
            String s = new String(persistentFile.getBytes(), persistentFile.getEncoding().getCharset());
            System.out.println(s);
        }*/

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }
}
