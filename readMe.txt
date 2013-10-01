Project: TrainsVinayG
Author: Vinay Gangoli

Instructions to run:
Download the TrainsVinayG.jar at a location of your choice. Create a text file with the comma separated 
list of edges and place it at the same location as the jar file. City names aren't case-sensitive. A is same as a.
A sample input file "Kiwiland" has been placed under src/resources for the users convenience.

e.g: 
        /Users/VinayG/HomeProjects/TrainsVinayG/TrainsVinayG.jar
        /Users/VinayG/HomeProjects/TrainsVinayG/Kiwiland 

Execute the client as follows:
    -> java -jar TrainsVinayG.jar 
    
Follow the instructions on the screen to test drive the graph library. 
Please note that this UI client has been primarily created for testing purposes.
On selecting "find number of trips between nodes", you will be provided the option of selecting
one of the 6 comparison operations. (<, <=, = for hops or cost). 
The program outputs the path itself in addition to the distance (hops/cost depending on the query).

There are extensive test cases under src/test which test the library with multiple test graphs.
These can also be tweaked for further testing.


Basic layout: 
The project has 2 main folders. Src and Test.
Test is self explanatory.
Src has 2 sets of packages: 
    com.tworks.collections.*
    com.tworks.trains.*

The collections is the core of the project. It has the Graph interface implemented by DirectedGraph.
The DirectedGraph uses a AdjacencyMatrix to maintain data. 

Cons: This results in a space complexity of O(V*V) (Vertices squared).
It it understood that Adjacency List implementation would reduce this to O(V+E). 
The space complexity improves as E approaches V*V.

Pros: The matrix implementation was chosen as the implementation deals with small graphs and it provides constant time access
for finding if an edge exists between 2 nodes and its cost. Getting cost of given paths like a-b-c will be constant time(per hop).

The Graphs class has the graph traversals (Dijkstras and DFS). It uses the GraphTraversalComparator interface to determine
which logic to use (e.g <= or < or exact) for comparing costs. This design allows the client to decide this and 
the Trains package does this by using different comparator implementations.


Assumptions: Weights are non-zero and if path exists their value is greater than 0. City/Vertex names are always exactly one letter.

Final note: All the queries from the sample graph have been included as unit test in the test suite.
In addition Three more sample graphs have been added (testGraph, homeGrown and homeGrown2) and tested against.




