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

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Override
    public void run(String... args) throws Exception {
        PersistentFileService persistentFileService = context.getBean(PersistentFileService.class);

        URL resource1 = Application.class.getClassLoader().getResource("file/myData1.txt");
        URL resource2 = Application.class.getClassLoader().getResource("file/399px-Colisa_lalia.jpg");

        System.out.println(new File(resource1.getFile()).getName());
     //   persistentFileService.save(new PersistentFile(resource1.getFile(), Filetype.TEXT, Encoding.UTF_8, "An UTF-8 encoded textfile"));
      //  persistentFileService.save(new PersistentFile(resource2.getFile(), Filetype.IMAGE, null, "An image of Colisa lalia"));


        //  persistentFileService.downloadPersistenFile("399px-Colisa_lalia.jpg", "/Users/m.smithhva.nl/tmp/colisa.jpg");

       /* if (persistentFile.getFiletype().equals(PersistentFile.Filetype.TEXT)){
            String s = new String(persistentFile.getBytes(), persistentFile.getEncoding().getCharset());
            System.out.println(s);
        }*/
    }
}
