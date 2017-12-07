import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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


        //Extract the lines from the files saved in the input directory args[0]
        //and save them in a list called "linesList". This is the common part for both parts
        //(i.e. two arguments vs four arguments) of the Problem2.
        try {
            //List<String> linesList =
            Map<String, List<String>> anagramsMap =
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
                            .distinct()
                            //.peek(System.out::println)
                            //
                            .collect(Collectors.groupingBy(
                                    line -> {
                                        String word = line.replaceAll("[^A-Za-z]+","").toLowerCase();
                                        String sortedWord = Arrays
                                                .stream(word.split(""))
                                                .sorted().collect(Collectors.joining());
                                        return sortedWord;
                                    }

                            ));

        System.out.println(anagramsMap);
        } catch(IOException ioe) {}
    }
}