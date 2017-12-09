package cscie55.hw8;

import java.io.IOException;
import java.nio.charset.Charset;
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
 * cscie55.hw8.FindAnagram class:
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
     * args[0] represents input data files folder. If omitted, "anagram-data" will be assumed.
     * args[1] represents output file name. If omitted, "anagram_output.txt" will be assumed.
     */
    public static void main(String[] args)  {

        //Initialize the data file path with args[0] value.
        //If no argument is supplied, initialize data file path
        //with the default data folder "anagram-data".
        String anagramDataFilesPath = "";
        if (args.length !=0 && args[0] != null) {
            anagramDataFilesPath = args[0];
        } else {
            anagramDataFilesPath = "anagram-data";
        }


        //Extract the lines from the files saved in the "anagram-data" or supplied directory, process them
        //and save them in a List called anagramsList.
        try {
            List<String> anagramsList =
                    Files.list(Paths.get(anagramDataFilesPath))
                            .filter(Files::isRegularFile) //Not mandatory, just to check if the file is a regular one,
                                                        //i.e. not any kind of special unix file.
                            .flatMap(l -> { //Flatten to create a single stream from multiple files.
                                try {
                                    return Files.lines(l, Charset.defaultCharset());
                                } catch (IOException ioe) {
                                    System.out.println("Oops something went wrong:" + ioe.getMessage());
                                }
                                return null;
                            })
                            .map(line -> line.replaceAll("[^A-Za-z ]+", "")) //Remove all punctuations and special characters
                            .filter(line -> line.trim() != "") //Remove any empty line
                            //.distinct()
                            .filter(distinctByKey(line -> { //Remove any duplicate word or words
                                String word = line.replaceAll("[^A-Za-z]+","").toLowerCase();
                                String oneWord = Arrays
                                        .stream(word.split(""))
                                        .collect(Collectors.joining());
                                return oneWord;
                            }))
                            //.peek(System.out::println)
                            .collect(Collectors.groupingBy( //Group by key: all characters in a line - sorted and in small caps
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
                            .filter(line -> line.getValue().size() > 1) //Remove all single words keeping the anagrams only.
                            .map(line -> { //Format the lines with anagrams in the form: key->word1...word2...word3...word[n]
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
                            .collect(Collectors.toList()); //Collect the anagrams in a List

            anagramsList.stream().forEach(System.out:: println);

            //Print the anagram list found
            Files.write(
                    Paths.get((args.length == 2 && args[1] != null) ? args[1] : "anagram_output.txt"),
                    () -> anagramsList
                            //.entrySet()
                            .stream()
                            .<CharSequence>map(a -> a.toString())
                            .sorted()
                            .iterator()
            );

        } catch(IOException ioe) {
            System.out.println("Oops! Something went wrong: " + ioe.getMessage());
        } //End of Try Catch block

    } //End of main() method


    /**
     * distinctByKey() Predicate Function
     *
     * @param keyExtractor
     * @param <T>
     * @return
     *
     * It finds out if there is any existing key with same value.
     * The key is obtained from a function reference.
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    } //End of distinctKey() Function

} //End of FindAnagram class