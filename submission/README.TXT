CSCI E-55 Homework 8
Student: Mohiuddin Faruqe
—————————————————————————

How to test:
———————————

1. Please, download the homework8.jar file to a local folder. 

2. Extract the contents by running the following command from the local folder: $jar -xf homework8.jar. This will give you all the necessary files and folder to test the solutions including the executable HW8.jar for both the solutions and the necessary data files for the Anagrams.

3. Sample runs for the BeerFest solution, run from the directory where you extracted the file:
	a. Without any optional parameter (hardcoded in the code): $ java -cp HW8.jar cscie55.hw8.BeerFest
	b. With the optional parameter value for “Country” parameter (“Price Range” is hardcoded): $java -cp HW8.jar cscie55.hw8.BeerFest Belgium
	c. With the optional parameter values for both “Country” and “Price Range”: $java -cp HW8.jar cscie55.hw8.BeerFest Belgium 7.0f 10.0f

4. Sample runs for the Anagram solution, run from the directory where you extracted the file:
	a. Without the optional parameter value (hardcoded in the code - default): $java -cp HW8.jar cscie55.hw8.FindAnagram
	b. With the optional parameter value: java -cp HW8.jar cscie55.hw8.FindAnagram anagram_data
	



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

9. “Homework8.docx” : homework8 instruction file for convenience.



Solutions Overview:
—————————————————
1. Development environment: I developed in IntelliJ Idea as a Maven project on a Mac machine, created the jar file from the command line and run the solutions both from within IntelliJ Idea and command line to match the results.

2. BeerFest solution:
	a. In case of BeerFest, I just implemented the unimplemented code as required by the homework without changing the core structure of the class or adding any extra comments. 
	b. In my solution I gave the option to run the solution from command-line without any argument in which case it will run with default hard-code values or one argument which represent the parameter value for “Country” or three arguments - first one for “Country” the rest two for “Price Range” - lowest and highest price.

3. Anagram solution:
	a. I solved it with one class FindAnagram with the main() method and a predicate “distinctByKey”.
	b. The solution can be run from the command line without any argument in which case default value “anagram-data” folder is hardcoded or with an argument in which case it will be taken as the input folder for anagram data file(s).
	c. I created the solution in such a way so that it can work with one or more data files. 
	d. The FindAnagram class is thoroughly commented. So more details can be found in the source files.
	e. Solution flow: read and extract all the lines from all data files, remove any punctuation, numeric or special characters, remove any empty line. Remove any duplicate words (tricky part), group by key, remove any key-value for with single value just to find the anagrams, and finally save the anagrams into a list. Then I saved the file to an output file "anagram_output.txt" in the same folder. The output file name can also be supplied from the command line as the second argument value.


Sample output (also attached as text files):
———————————————————————————————————————————


———————————————————

Thank you.

Best,

Mo
(Mohiuddin Faruqe)
	
 








