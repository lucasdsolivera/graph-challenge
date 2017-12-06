## Bionexo Challenge

This a Java application focused on Graph Problem, to solve this problem was used the depth first search algorithm and some search variations methods.

# How to install
1. Download the project using git clone:
```sh
        $  git clone git@github.com:lucasdsolivera/graph-challenge.git
```        
2. Go to project directory:
```sh		 
	$ cd graph-challenge
```		
3. Excecute:       
```sh
	$ mvn install
```
4. Excecute:       
```sh
	$ mvn exec:java -Dexec.mainClass="bionexo.graphchallenge.app.GraphChallengeApplication"
``` 
  You can also import and save files from GitHub and add the Maven Project to your IDE to execute main class

5. Input the Graph as String:
```sh
        $ AD4, DE1, EC8, CB2, BA6, AC9, DF7, FC5, FE9, BD3, FA3
```
The expected output is:

```sh
Output #1: 5
Output #2: NO SUCH ROUTE
Output #3: 10
Output #4: 19
Output #5: 5
Output #6: 3
Output #7: 6
Output #8: 2
Output #9: 5
Output #10: 6
Output #11: 27
Output #12: 137
```
### Tech
This application uses only:

* [Java] - Java 8 
* [Maven] - Version 4.0.0
* [Junit] - Version 4.12


### Todos

 - Refactor GraphService class
 - Write more tests
 
#  Author
 ### Lucas Oliveira
