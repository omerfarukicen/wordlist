package tr.com.words.dto;

import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordListRequest {

    private int index;

    private String value="REST";

}
