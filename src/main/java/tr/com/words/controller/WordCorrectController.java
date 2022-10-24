package tr.com.words.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tr.com.words.service.DictionaryService;
import tr.com.words.utility.WordOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Validated
public class WordCorrectController {
    private final DictionaryService dictionaryService;

//    @GetMapping("/download")
//    public ResponseEntity<?> getWorldCombination(@ModelAttribute("wordListRequest") WordListRequest wordListRequest, Model model) throws IOException {

    @GetMapping("/download/{word}/{index}")
    public ResponseEntity<?> getWorldCombination(@PathVariable("word") String word, @PathVariable("index") Integer index) throws IOException {
        Set<String> wordList = WordOperation.getWords(word, index);
        File file = dictionaryService.wordListControlAndWriteText(wordList);
        String fileName = "Dictionary.txt";
        String fileContent = "Simple Solution \nDownload Example 1";
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.TEXT_PLAIN).contentLength(file.length()).body(inputStreamResource);
    }

}


