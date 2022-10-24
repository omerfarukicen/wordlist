package tr.com.words.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CacheService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String IS_UPLOADED_ENGLISH_KEY = "ENGLISH_WORD_UPLOADED";

    public void addWordInCache(String value) {
        redisTemplate.opsForValue().set(value.toUpperCase(Locale.ENGLISH), "WORLD");
    }

    public boolean isUploadedEnglishWords() {
        return isWordInCache(IS_UPLOADED_ENGLISH_KEY);
    }

    public void completed() {
        redisTemplate.opsForValue().set("IS_UPLOADED_ENGLISH_KEY", "1");
    }

    public boolean isWordInCache(String word) {
        return redisTemplate.hasKey(word);
    }
}
