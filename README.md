# Triangle Intersect User Interface

In this program, we test triangle-triangle intersection predicates. Rather than have a downloadable executable, this program is simply a collection of .java files that when compiled and run generate an interface for users to test their code.

## Step 1: Writing your Code

The program is in Java, and thus your predicate will have to be written in Java. The predicate takes as inputs 6 Vertex objects a,b,c,d,e,f. These objects have an .x property for x coordinate and .y property for y coordinate. These values are public but can also be accessed via getX() and getY().
So write your code, with the return value being a boolean.

## Step 2: Insert your Code

Download all of the .java files and place them into the src folder of a new project in the IDE of your choice. Then go to the file Predicate.java, line 3:
```java
public static boolean TriTriIntersect(Vertex a, Vertex b, Vertex c, Vertex d, Vertex e, Vertex f)
    {
        //Insert your code here
        return false; //default return value
    }
```
Insert your code where instructed and delete the ```return false;``` . Then your code should be ready to test.

## Step 3: Run the Program

Run the main method of Main.java, and a UI will pop up. Left-click to add in points, Right-click to remove. Left click will add a point in at the earliest slot available, so if you remove a point from the second triangle and then one from the first triangle, your next point will be added to the first triangle.
After drawing in your two triangles, click CONTINUE to run your predicate. The default value is false, so be sure to check the console for exceptions.

Enjoy!
