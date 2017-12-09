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



Solution Overview:
—————————————————
1. Development environment: I developed in IntelliJ Idea as a Maven project on a Mac machine, created the jar file from the command line and run the solutions both from within IntelliJ Idea and command line to match the results.

3. Problem1 solution:
	a. I resolved this problem by writing one class - LinkParser and calling the methods of the supplied Link class to get the URL and related Tags from each line of the supplied JSON data files.

	b. First I gather the URL as the Key and Set of tags as Values in a Map “linkTags” in the reduce() method of  Reducer without writing the results to context yet and then processed & wrote the results by concatenating the tags in the cleanup() method of the Reducer. 


4. Problem 2 Solution:
	a. I resolved the Problem2 by writing one class - LinkStreamer and calling the methods of the supplied Link class to get the URL, Timestamps and related Tags from each line of the supplied JSON data files.

	b. First I gathered all the lines from all 15 JSON data files in a List based on the passed arguments - in case of two arguments, the List contains all the lines and in case of four arguments, the List contains only the lines fell within the supplied start and end date range.

	c. Then within a IF clause first I resolved the Problem 2 Part 2 when run with four arguments in the format <URL Count>. This part was straight forward. I created a Map<String Long> by grouping on the URL and counting the total number of the same URL. Finally wrote the output in a file. Please, check the inline code for more details.

	d. Then in the ELSE clause I resolved the Part 1 of the Problem2 i.e. when supplied with two arguments to create an output in the format <URL tags> which was much more complicated because of duplicate tags scattered around the files for a URL. That’s why I did it in a multiple steps by cleaning up data in each step to gather the final result set in a Map<String String> to represent the URL as the Key and a comma delimited string of unique tags as the Value. Finally wrote the output in a file. Please, check the inline comment for more details.

	e. To calculate the seconds past since January 1, 1970 for a supplied date, I created a separate method secondsPast() in the LinkStreamer class by using Charles’ supplied code. This is a simple utility method developed for convenience - so I wrote it straight forward without using any Stream APIs.

5. All solutions are well commented and java doc documented. So more details can be found in the source files.


———————————————————

Thank you.

Best,

Mo
(Mohiuddin Faruqe)
	
 








