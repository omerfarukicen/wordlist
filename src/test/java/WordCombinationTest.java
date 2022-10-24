import org.junit.jupiter.api.Test;
import org.paukov.combinatorics3.Generator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordCombinationTest {
    String word = "faruk";
    StringBuilder output = new StringBuilder();
    int start = 1;
    Set<String> wordList = new HashSet<>();


    private void permute(String str, int l, int r) {
        if (l == r) System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }


    @Test
    public void createPermutationDemo() {
        createPermutation("ABCDE", "\n");
    }


    public void createPermutation(String str, String ans) {
        if (str.length() == 0) {
            wordList.add(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            createPermutation(ros, ans + ch);
        }
    }

    @Test
    public void testCombinationLibrary() {
        String value = "ACU";
        List<Character> characterList = value.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Generator.combination(characterList).simple(2).stream().forEach(System.out::println);
    }


    private String getData(Set<Character> items) {
        StringBuilder sb = new StringBuilder();
        for (Character item : items) {
            sb.append(item);
        }
        return sb.toString();
    }

}
