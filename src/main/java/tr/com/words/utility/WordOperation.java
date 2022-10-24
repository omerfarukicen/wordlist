package tr.com.words.utility;

import lombok.experimental.UtilityClass;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class WordOperation {

    private Set<String> wordList = new HashSet<>();

    public void clearWordList() {
        wordList.clear();
    }

    public Set<String> getWords(String word, int index) {
        clearWordList();
        List<String> combinationsValues = getCombination(word, index);
        for (String combinationValue : combinationsValues) {
            getPermutation(combinationValue, "");
        }
        return wordList;
    }

    // TODO PERMÜTASYON
    public void getPermutation(String str, String ans) {
        if (str.length() == 0) {
            wordList.add(ans.toUpperCase(Locale.ENGLISH));
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            getPermutation(ros, ans + ch);
        }
    }


    //TODO Combination
    public List<String> getCombination(String value, int length) {
        List<String> combinationsValue = new ArrayList<>();
        var characterList = value.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Generator.combination(characterList).simple(length).stream().forEach(x -> addCombination(combinationsValue, (ArrayList) x));
        return combinationsValue;
    }

    private void addCombination(List<String> combinationsValue, ArrayList<Character> x) {
        StringBuilder builder = new StringBuilder(x.size());
        for (Character ch : x) {
            builder.append(ch);
        }
        combinationsValue.add(builder.toString());
    }
}
