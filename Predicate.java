import java.util.ArrayList;
import java.util.Arrays;

public class Predicate {

    public static boolean TriTriIntersect(Vertex a, Vertex b, Vertex c, Vertex d, Vertex e, Vertex f)
    {
        // If it has one or more proper intersection with any of the edges then it will be a intersecting triangle
        int numIntersect = 0;
        
        boolean tri1ccw = false;
        boolean tri2ccw = false;
        
        // First determine the orientation of abc
        if(Left(a, b, c)){
            tri1ccw = true;
        }
        if(Left(d, e, f)){
            tri2ccw = true;
        }
        
        ArrayList<Vertex> tri1 = null;
        ArrayList<Vertex> tri2 = null;
        
        if(tri1ccw) {
            tri1 = new ArrayList<>(Arrays.asList(a, b, c));
        }
        else {
            tri1 = new ArrayList<>(Arrays.asList(a, c, b));
        }
        
        if(tri2ccw) {
            tri2 = new ArrayList<>(Arrays.asList(d, e, f));
        }
        else {
            tri2 = new ArrayList<>(Arrays.asList(d, f, e));
        }
        
        // We can just loop through tri1 to check if any of those intersect with any of the points in tri2
        for(int i=0;i<3;i++) {
            int next1 = (i + 1) % 3; // Handles wrap-arounds
            
            for(int j=0;j<3;j++) {
                int next2 = (j + 1) % 3;
                
                Vertex pa = tri1.get(i);
                Vertex pb = tri1.get(next1);
                Vertex pc = tri2.get(j);
                Vertex pd = tri2.get(next2);
                
                // Increment number of proper intersections
                if(IntersectProp(pa, pb, pc, pd)) 
                {
                    numIntersect++;
                }
                
            }
        }
        
        // If no collision then we have to check if the triangle is inside another
        if(numIntersect == 0) {
            // Need to check if the triangle is inside another
            // calculate absolute area
            int area1 = Math.abs(Area2(a, b, c));
            int area2 = Math.abs(Area2(d, e, f));
            
            if(area1 > area2) {
                // Check if all the edges of tri2 is inside tri1 by doing 3 left tests with any points
                // because it has no intersection, it is either outside or inside
                // we can tell by just doing 3 left test wiht 1 point
                for(int i=0;i<3;i++) {
                    int next1 = (i + 1) % 3;
                    System.out.println("here");
                    if(!Left(tri1.get(i), tri1.get(next1), d)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                // Check if all the edges of tri1 is inside tri2 by doing 3 left tests with any points of tri1
                for(int i=0;i<3;i++) {
                    int next1 = (i + 1) % 3;
                    if(!Left(tri2.get(i), tri2.get(next1), a)) {
                        return false;
                    }
                }
                return true;
            }
        }
        else {
            // Non-zero intersection means it is a proper intersection
            return true;
        }
    }
    
    //Here is the predicates from the book, adjusted to java
    public static boolean Left(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) > 0;
    }
    public static boolean LeftOn(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) >= 0;
    }
    public static boolean Collinear(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) == 0;
    }
    public static boolean IntersectProp(Vertex a, Vertex b, Vertex c, Vertex d)
    {
        if (Collinear(a,b,c) || Collinear(a,b,d) || Collinear (c, d, a) || Collinear(c,d,b))
        {
            System.out.println("Oops collinear");
            return false;
        }
        
        return Xor(Left(a,b,c), Left(a,b,d)) && Xor(Left(c,d,a), Left(c,d,b));
    }
    public static boolean Between(Vertex a, Vertex b, Vertex c)
    {
        if ( ! Collinear ( a, b, c ) )
            return false;
        if ( a.x != b.x )
            return ((a.x <= c.x) && (c.x <= b.x )) || ((a.x >= c.x) && (c.x >= b.x));
        return ((a.y <= c.y) && (c.y <= b.y)) || ((a.y >= c.y) && (c.y >= b.y));
    }
    public static boolean Intersect(Vertex a, Vertex b, Vertex c, Vertex d)
    {
        if (IntersectProp( a, b, c, d ) )
            return true;
        else if ( Between ( a, b, c) || Between (a, b, d) || Between (c, d, a) || Between (c, d, b))
            return true;
        return true;
    }
    public static int Area2(Vertex a, Vertex b, Vertex c)
    {
        return ((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
        // return (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y));
    }
    public static boolean Xor( boolean x, boolean y )
    {
        return x ^ y;
    }
}
