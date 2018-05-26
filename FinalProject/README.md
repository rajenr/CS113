
# Query
## Final Group Project for CS113 - Graph

Complete the `Graph` class, either as an adjacency list or adjacency matrix, per the ADT proposal in the lecture slides along with an advanced algorithm (Dijkstra's, Prim's, DFS, BFS, or some other one we haven't covered [clear with instructor first]).  Use the data structure in a core/useful way for your groups idea. Ideally (but not required), you should include a GUI frontend to showcase your project.

**Use Scrum (Agile) development to build your group project over 3 weeks:**
- Sprint 0 = planning sprint
	- End of sprint should have: project proposal/description, decide graph algorithm, initial UML + sequence diagram
- Sprint 1 = first half of development
- Sprint 2 = second half of development
	- End of sprint should have: project completed+documented, screenshots, updated UML+sequence diagrams
- *See past group projects for general sprint requirements (i.e., unit tests for each sprint, updated documentation, etc.)*

**You will be graded on the following:**
- Complete/updated UML Class and relationship diagram
- Complete/updated Sequence diagram for driver (main part of program that creates objects, does user input, etc.)
- JUnit tests for all model classes + data structure (Graph)
	- Note: GUI classes need not have JUnit tests
- All code documented (author boxes, algorithm for driver, methods have description/precondition/postcondition, class invariants)
- No crashes/compile issues
**- Every group member writes significant amount of code**, ideally proportional to other members.


> ***NOTE:***
> - You'll be tempted to push the project off for the end, or give certain group members monolithic parts (someone build the Graph, someone build the GUI, etc.).  **DON'T!**  Embrace the agile development process! Plan in Sprint 0, build a working product by the end of Sprint 1 and Sprint 2.
> - Trouble splitting up the UML/sequence diagram? Build them together! That way everyone's on the same page! 
> - Plan plan plan plan! When you're done planning, PLAN SOME MORE!
> - Create and use Trello boards, Slack, etc. to help you all communicate and organize yourselves
## General program outline

**1.** Our program will be known to the user as **Query**. Upon initialization of the program, a graph of words is built from a text file, either automatically or user-initialized. The text file itself should be massive in order to replicate realistic search results. This will be easy with training the graph, however, we decided it would be easiest to implement our program using an automatic text file. This text file contains phrases that a human can easily interpret and make sense to the human brain.
- The graph has the potential to be constructued from a multitude of text files, as well. This is because the more inputs we feed, the better the graph will be. However, having one text file containing the mass amount of data will be easiest to parse and handle.
> *NOTE:* 
> If text files are scraped from the web, we can utilize a method to get rid of HTML/JS tags, however, we may need to keep 	         in mind that some may still show up. It is worth mentioning that we did not go through with scraping web files. However, this would suffice in a version update of Query.
	
**2.** The program will need to go through the text twice.
- On the **first** scan, a sorted ArrayList of words is built.
	- Perhaps during this first scan, there is a string cleaning process. Every time a sentence is scanned, it is cleaned of punctuation and capitalization and added to an arraylist of strings. It is worth noting that our implementation was true to this, and sentences would be parsed of certain types of punctuation.
- On the **second** scan, either a List graph or a Matrix graph is constructed using the number of unique words found in the   		  process.
	
**3.** The program will need to go through each word pair (source -> dest).
- As each word is scanned, a binary search of a private, sorted ArrayList finds the index for the word that corresponds to the index in the Edge List array in the ListGraph
	
**4.** As the graph is built: edges are either added with a weight of one or their weight is updated if the edge exists. The weight will always be incremented by one.

**5.** The GUI I have in my mind is pretty simple, What do you guys think?  
- The GUI will have a text field for the user to type their sentence in. The field will have a key listener for the space key. This will trigger three or four buttons to appear above the field with suggested words. BFS will be used to find the top weighted edges and the destination words will populate these buttons The user can either keep typing or select a word button. If the user selects a word button, the suggestions are updated again.
-  Perhaps a button for "Add sentence to database" that saves the sentence to the initial text file the graph is built from?
-  Perhaps a way for this to be integrated with the Discord interface in order to be utilized with a bot. In this case, the bot instantaneously responds with suggestions in the same format as the GUI. **Note:** The GUI should remain as the main attraction in case integration isn't able to be implemented. It is worth noting that the GUI is the official main attraction, as we did not have ample time to expand our implementation reach.
	
*Note: If ya'll have any ideas for additions, alterations, or things to scrap, please feel free to do so! -Erik*

## Project proposal/description:
Our goal is to build a very simple Markov predictive text generator, much like Google's search algorithm itself. This search algorith is known as **Query**, due to utilizing a Priority Queue in tandem with a List Graph, but also serving as a connectivity query similar to Google's search algorithm. This project uses a **Markov chain**, or a stochastic model describing a sequence of possible events in which the probability of each event depends only on the state attained in the previous event. The program quickly builds a network of text using a weighted and directeed graph. The more inputs fed into the graph, the better the program will be.

In terms of interface, the three most popular phrases based on the last word typed by the user are displayed at the bottom of the interface. The user recieves a real time update with each word typed. Words themselves are delineated by the spacebar and parsed without the space. The user is able to continue typing as necessary within the main text field of the GUI, and is able to copy and paste this text into a multitude of other programs.

## Graph algorithm used:
As the graph is built, **BFS** (breadth first search) will be used to add edges to the graph or update their weights accordingly. BFS will also be used to find the top guessed words for the user (destination words with the highest Edge weight). 
> **NOTE:** The graph will need not perform any DFS (depth first search) of any kind because only adjacent vertices are relevant in this case. We originally suspected Dijkstra's as appropriate, however, decided that this would not be the case.

## Screenshot(s): Display progress from initial interface to final.
![Screenshot](resources/first.JPG)
![Screenshot](resources/first1.JPG)
![Screenshot](resources/second.JPG)
![Screenshot](resources/second1.JPG)
![Screenshot](resources/second2.JPG)
![Screenshot](resources/usage1.png)
![Screenshot](resources/usage2.png)
![Screenshot](resources/final.JPG)
![Screenshot](resources/final1.JPG)
![Screenshot](resources/query.JPG)
![Screenshot](resources/query1.JPG)

## Final Interface
![Screenshot](resources/queryFinal.JPG)
![Screenshot](resources/queryFinal2.JPG)
![Screenshot](resources/queryFinal1.JPG)

## UML Class+Relationship Diagram:
![UML class diagram](resources/UML.png)


![Relationship Diagram](resources/ClassDiagram.png)

## Sequence Diagram (for driver):
![Sequence Diagram](resources/sequenceDiagram.png)
