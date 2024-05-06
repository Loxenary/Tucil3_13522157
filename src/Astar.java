package src;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Astar implements Algorithm{
    // Menyimpan target kata
    private String endWord = "";

    // Menyimpan jumlah kata yang di process
    private int NodeCount;

    // Menyimpan set of visited atau lebih dikenal sebagai Closed list
    private Set<String> visited;
    
    // Menyimpan dictionary yang sudah parser menjadi map
    private Map<String, List<String>> hashMap;
    private PriorityQueue<node> prioqueue;
    
    private int HammingStringMatching(String target, String currentChecked){
            
        int distance = 0;
        // Iterate over each character in the strings
        for (int i = 0; i < target.length(); i++) {
            // If characters at the same index are different, increment the distance
            if (target.charAt(i) != currentChecked.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    private int AstarCalculation(node a, String endWord){
        return HammingStringMatching(a.word, endWord) + a.depth;
    }

    public Astar(String startWord, String endWord){
        this.endWord = endWord;
        NodeCount = 0;
        this.visited = new HashSet<>();    
        this.hashMap = DictionaryMapper.dictMap;
        this.prioqueue = new PriorityQueue<node>(Comparator.comparingInt(a -> AstarCalculation(a, endWord)));
    
        // Create the start node with depth 0 and no parent
        node startNode = new node(startWord, 0, null);
        
        // Add the start node to the priority queue
        prioqueue.add(startNode);
        
        // Initialize the path map with the start word
        List<String> firstPath = new ArrayList<>();
        firstPath.add(startWord);
    }

    public List<String> GetResult() {
        while (!prioqueue.isEmpty()) {
            node currentWord = prioqueue.poll();
            int depth = currentWord.depth;
    
            // Skip any visited word
            if(visited.contains(currentWord.word)){
                continue;
            }
            visited.add(currentWord.word);
            
            // Path Found
            if (currentWord.word.equals(endWord)) {
                return node.getPathFromEndToStart(currentWord);
            }
    
            List<String> nextProcessedWords = hashMap.get(currentWord.word);
            NodeCount++;
    
            for (String nextWord : nextProcessedWords) {
                // Save new Path to the Queue
                node nextnode = new node(nextWord, depth + 1, currentWord); // Set parent node
                prioqueue.add(nextnode);
            }
        }
        return null; // No path found
    }

    public int GetNodeCount(){
        return NodeCount;
    }
}