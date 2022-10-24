package tr.com.words.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Component

public class DictionaryService {

    @Autowired
    private CacheService cacheService;

    @Value("${dictionary.rest.service}")
    private String dictionaryRestServiceUrl;

    @PostConstruct
    public void initialize() {
        if (!cacheService.isUploadedEnglishWords()) {
            loadRedisDatabaseAllWords();
        }
    }


    public void loadRedisDatabaseAllWords() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(dictionaryRestServiceUrl, HttpMethod.GET, entity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String wordStringList = responseEntity.getBody();
            String[] lines = wordStringList.split("\\r?\\n");
            for (String line : lines) {
                cacheService.addWordInCache(line.toString());
            }
            cacheService.completed();
            ;
        }
    }


    public File wordListControlAndWriteText(Set<String> wordList) throws IOException {
        Path tempFile = Files.createTempFile(null, null);
        StringBuilder sb = new StringBuilder();
        for (String word : wordList) {
            if (cacheService.isWordInCache(word)) {
                sb.append(word + "\n");
            }
        }

        Files.writeString(tempFile, sb.toString());
        return tempFile.toFile();

    }

}
