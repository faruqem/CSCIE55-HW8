import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * FindAnagram class:
 * This class finds anagrams, if any exist, from the word list saved in a file.
 * Anagrams are words or sequences of words that use exactly the same letters but in a different order.
 *
 * @version     1.0
 * @since       1.0
 */
public class FindAnagram {

    /**
     * main() method
     *
     * @params args A string array of arguments.
     */
    public static void main(String[] args)  {

        String anagramDataFilesPath = "";

        if (args.length !=0 && args[0] != null) {
            anagramDataFilesPath = args[0];
        } else {
            anagramDataFilesPath = "anagram-data";
        }


        //Extract the lines from the files saved in the "anagram-data" or another directory, process them
        //and save them in a Map called anagramsMap.
        try {
            List<String> anagramsList =
                    Files.list(Paths.get(anagramDataFilesPath))
                            .filter(Files::isRegularFile) //Not mandatory, just to check if the file is a regular one,
                            //i.e. not any kind of special unix file.
                            .flatMap(l -> { //Flatten to create a single stream
                                try {
                                    return Files.lines(l);
                                } catch (IOException ioe) { }
                                return null;
                            })
                            .map(line -> line.replaceAll("[^A-Za-z ]+", ""))
                            .filter(line -> line.trim() != "")
                            //.distinct()
                            .filter(distinctByKey(line -> {
                                String word = line.replaceAll("[^A-Za-z]+","").toLowerCase();
                                String oneWord = Arrays
                                        .stream(word.split(""))
                                        .collect(Collectors.joining());
                                return oneWord;
                            }))
                            //.peek(System.out::println)
                            .collect(Collectors.groupingBy(
                                    line -> {
                                        String word = line.replaceAll("[^A-Za-z]+","").toLowerCase();
                                        String sortedWord = Arrays
                                                .stream(word.split(""))
                                                .sorted().collect(Collectors.joining());
                                        return sortedWord;
                                    }

                            ))
                            .entrySet()
                            .stream()
                            .filter(line -> line.getValue().size() > 1)
                            .map(line -> {
                                String s = line.getKey() + "->";
                                Integer count = 1;
                                for(String l: line.getValue()) {
                                    if(count < line.getValue().size())
                                        s += l + "...";
                                    else
                                        s += l;

                                    count++;
                                }
                                return s;
                            })
                            .collect(Collectors.toList());

            anagramsList.stream().forEach(System.out:: println);
        } catch(IOException ioe) {}
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}