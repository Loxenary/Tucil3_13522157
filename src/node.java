package src;

import java.util.ArrayList;
import java.util.List;

public class node {

    public String word;
    public int depth;
    public node parent; 

    public node(String word, int depth, node parent) {
        this.word = word;
        this.depth = depth;
        this.parent = parent;
    }

    public node(String word, node parent){
        this.word = word;
        this.depth = 0;
        this.parent = parent;
    }
    public static List<String> getPathFromEndToStart(node endNode) {
        List<String> path = new ArrayList<>();
        while (endNode != null) {
            path.add(0, endNode.word); // Add word to the beginning of the path
            endNode = endNode.parent; // Move to the parent node
        }
        return path;
    }
    
}
