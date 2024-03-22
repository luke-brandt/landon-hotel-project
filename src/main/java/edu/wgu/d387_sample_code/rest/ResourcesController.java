package edu.wgu.d387_sample_code.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;

@RestController
@RequestMapping("/resources")
@CrossOrigin
public class ResourcesController {

    static ExecutorService messageExecutor=newFixedThreadPool(2);
    Properties properties = new Properties();

    @GetMapping("welcome")
    public ResponseEntity<List<String>>getWelcomeMessage(){

        List<String> welcomeMessages = new ArrayList<String>();

        //Get English welcome message
        messageExecutor.execute(()->{
            try{
                InputStream stream = new ClassPathResource("translation_en_US.properties").getInputStream();
                properties.load(stream);
                String eng = properties.getProperty("welcome");
                System.out.println(eng + "thread 1");
                welcomeMessages.add(eng);

            } catch (Exception e){
                e.printStackTrace();
            }
        });

        //get French welcome message
        messageExecutor.execute(()->{
            try{
                InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream();
                properties.load(stream);
                String fr = properties.getProperty("welcome");
                System.out.println(fr + "thread 2");
                welcomeMessages.add(fr);

            } catch (Exception e){
                e.printStackTrace();
            }
        });


        return ResponseEntity.ok(welcomeMessages);
    }

}
