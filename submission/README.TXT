CSCI E-55 Homework 8
Student: Mohiuddin Faruqe
—————————————————————————

How to test:
———————————

1. Please, download the homework8.jar file to a local folder. 

2. Extract the contents by running the following command from the local folder: $ jar -xf homework8.jar. This will give you all the necessary files and folder to test the solutions including the executable HW8.jar for both the solutions and the necessary data files for the Anagrams.

3. Sample runs for the BeerFest solution from command line - three ways to run; run from the directory where you extracted the file:
	a. Without any optional parameter values (default values: countryName = “USA”; Price Range - lowestPrice: 8.50f, highestPrice: 10.00f): $ java -cp HW8.jar cscie55.hw8.BeerFest
	b. With the optional parameter value for “Country” parameter (default for Price Range - lowestPrice: 8.50f, highestPrice: 10.00f): $ java -cp HW8.jar cscie55.hw8.BeerFest Belgium
	c. With the optional parameter values for both “Country” and “Price Range”: $java -cp HW8.jar cscie55.hw8.BeerFest Belgium 7.0f 10.0f

4. Sample runs for the Anagram solution from command line - three ways to run, run from the directory where you extracted the file:
	a. Without the optional parameter value (defaults: input directory = “anagram_data” and output file = “anagram_output.txt” ): $ java -cp HW8.jar cscie55.hw8.FindAnagram
	b. With the first optional parameter value (default value for output file: anagram_output.txt): $ java -cp HW8.jar cscie55.hw8.FindAnagram anagram_data
	c. With both the optional parameter values: $ java -cp HW8.jar cscie55.hw8.FindAnagram anagram_data anagram_output.txt
	

Submission folder structure and contents:
————————————————————————————————————————

1. “README.TXT” file : this is the file you are reading now.

2. “src” folder : contains all source codes (in src/main/java/cscie55/hw8/ folder): BeerFest.java and FindAnagram.java

3. “cscie55” folder : contains all class files (in cscie55/hw8/ folder): BeerFest$Beer.class , BeerFest.class and FindAnagram.class
				
4. “HW8.jar” : the executable jar file.

5. “BeerFest_sample_output.txt” : sample output for BeerFest solution.

6. “anagram_output_last_20_lines.txt” : the last 20 lines of the anagram output.

7. “anagram_output.txt” : full output file of the anagram solution.
		
8. “Anagram-data” folder : data folder for FindAnangram - contains anagram-data.txt data file. 

9. “Homework8.docx” : Homework 8 instruction file for convenience.



Solutions Overview:
—————————————————
1. Development environment: I developed in IntelliJ Idea as a Maven project on a Mac (OS Sierra) machine, created the jar file from the command line and run the solutions both from within IntelliJ Idea and command line to check.

2. BeerFest solution:
	a. In case of BeerFest, I just implemented the unimplemented code as required by the homework without changing the core structure of the class or adding any extra features or comments/JavaDoc. 
	b. In my solution I gave the option to run the solution from command-line (1) without any argument in which case it will run with default hard-coded values or (2) one argument which represent the parameter value for “Country” or (3) three arguments - first one is for “Country” the rest two are for “Price Range” - lowest and highest price. 
	c. While passing the default values, I created the option to pass hardcoded values or values passed via command line argument- for country: “((argv.length != 0  && argv[0] != null) ? argv[0] : "USA")” and for price range: “(((argv.length >= 3 && argv[1] != null) ? Float.parseFloat(argv[1]) : 8.50f), ((argv.length >= 3 && argv[2] != null) ? Float.parseFloat(argv[2]) : 10.00f))”. So if argv[0] is not passed assume countryName=“USA”, and if argv[1] & argv[2] are not passed assume the price range = 8.50f to 10.00f.
	d. Since I just filled-up the missing code, the solution was straight forward and without much option to change any already implemented code.

3. Anagram solution:
	a. I solved it with a single class FindAnagram with the main() method and a predicate function “distinctByKey”.
	b. The solution can be run from the command line (1) without any argument value in which case default value for data directory is “anagram-data” and output file is “anagram_output.txt”, (2) one argument value which represents the data directory, in which case default value for output file will be “anagram_output.txt” or (3) two argument values of which first one represents the anagram data directory & the second one represents the anagram output file.
	c. I created the solution in such a way so that it can work with one or more data files saved in the input data directory. 
	d. Solution flow: read and extract all the lines from all data files; remove any punctuation or special characters; remove any empty line; remove any duplicate words; now group by key and remove any key-value pair with a single item for values so that the list is only left with the anagrams; and finally collect the anagrams into a list. Then I saved the file to an output file "anagram_output.txt" in the same folder as well as printed the results in the command line. The output file name can also be supplied from the command line as the second argument value.
	e. The most tricky part of the solution was removing any duplicate words in the data file because some duplicate words were in different format (like a single word vs sequence of words) so calling straight forward distinct() method was not working and I had to declare & call a custom predicate called “distinctByKey”.
	f. For “character key string” generation, I followed the logic supplied in the homework instruction: “To look for anagrams you need to extract a core representation of the constituent characters of each record from the data file, call it the "character key string.". For example, for the line that reads "Wolfgang Amadeus Mozart" you should generate a pair of strings, the first being the character key string, "aaaadefgglmmnoorstuwz", and the second be the word or phrase on the line. Likewise, when your program eats the line "A famous German waltz god", it should generate the same key string, allowing it to identify the two strings as anagrams of one another. Notice that the letters are in alphabetic order and that each letter appears as many times in the key string as it occurs in the line. Thus, your stream processing should generate a pair of strings”.
	g. The homework instructions said “collect the results into a map where the key is the character key string and the value is the string concatenation of the anagrams. Output should have the form: eeehnoorttw->one two three...three two one”. So I first collect the result into a map and then converted to a List<String> right away to generate the final output in the same chain of command. So before saving to file my final collection was List<String> instead of Map<String, String>.
	f. The FindAnagram class is thoroughly commented out. So more details can be found in the source files.


Sample output (also attached as text files):
———————————————————————————————————————————
BeerFest:
————————-
Command-line: $ java -cp HW8.jar cscie55.hw8.BeerFest
Output:
Country: USA Name: Sam Adams, price: 7.0
Country: USA Name: Bud Light, price: 5.0
Country: USA Name: Yuengling, price: 5.5
Country: USA Name: Brooklyn Lager, price: 8.25
Country: Belgium Name: Leffe Blonde, price: 8.75
Country: Belgium Name: Chimay Blue, price: 10.0

Command-line: $ java -cp HW8.jar cscie55.hw8.BeerFest Belgium
Output:
Country: Belgium Name: Stella, price: 7.75
Country: Belgium Name: Leffe Blonde, price: 8.75
Country: Belgium Name: Chimay Blue, price: 10.0
Country: Belgium Name: Leffe Blonde, price: 8.75
Country: Belgium Name: Chimay Blue, price: 10.0

Command-line: $ java -cp HW8.jar cscie55.hw8.BeerFest Belgium 7.0f 10.0f
Output:
Country: Belgium Name: Stella, price: 7.75
Country: Belgium Name: Leffe Blonde, price: 8.75
Country: Belgium Name: Chimay Blue, price: 10.0
Country: Belgium Name: Stella, price: 7.75
Country: USA Name: Sam Adams, price: 7.0
Country: Belgium Name: Leffe Blonde, price: 8.75
Country: Belgium Name: Chimay Blue, price: 10.0
Country: USA Name: Brooklyn Lager, price: 8.25


FindAnagram:
———————————-
Command-line: $ java -cp HW8.jar cscie55.hw8.FindAnagram
Output: Please check “anagram_output.txt” for output.

Command-line: $ java -cp HW8.jar cscie55.hw8.FindAnagram anagram_data
Output: Please check “anagram_output.txt” for output.

Command-line: $ java -cp HW8.jar cscie55.hw8.FindAnagram anagram_data anagram_output2.txt
Output: Please check “anagram_output2.txt” for output.


———————————————————

Thank you.

Best,

Mo
(Mohiuddin Faruqe)
	
 








