package src;
import java.util.Queue;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class UCS implements Algorithm{
    // Save All paths that is passed and also get the currentWord that will be processed
    private Queue<List<String>> path;

    // Hashmap for all word directories
    private Map<String,List<String>> hashMap;

    // Set for save all the visited words
    private Set<String> visited; 

    // EndOfWord
    private String EndWord;

    // Stash the amount of node, visited
    private int NodeCount;
    
    // Constructor
    public UCS(String startWord, String endWord) {
        this.hashMap = DictionaryMapper.dictMap;
        this.visited = new HashSet<>();
        this.EndWord = endWord;
        this.path = new LinkedList<>();

        // Save the startWord as a first element to check
        List<String> firstPath = new ArrayList<>();
        firstPath.add(startWord);
        path.add(firstPath);
    }

    // Process the Words`
    private void ProcessCurrentWord(String currentWord, List<String> currentPath){
        
        visited.add(currentWord);

        // Get all the Processed Words
        List<String> nextProcessedWords = hashMap.get(currentWord);

        // Save the Node Count
        NodeCount++;

        // Process Next Words
        for (String nextWord : nextProcessedWords) {
            // Save new Path to the Queue
            List<String> newPath = new ArrayList<>(currentPath); 
            newPath.add(nextWord);
            path.add(newPath);
        }
    }

    public List<String> GetResult() {
    
        while (!path.isEmpty()) {
            // Delete the checked path and save it as currentPath
            List<String> currentPath = path.poll();

            // Get the word to processed
            String currentWord = currentPath.get(currentPath.size() - 1);

            // Skip if word already been processed
            if (visited.contains(currentWord)) {
                continue;
            }

            // Path are found, Return the Path
            if(currentWord.equals(EndWord)){
                return currentPath;
            }

            ProcessCurrentWord(currentWord, currentPath);
        }

        return null;
    }

    // Return the node that is checked
    public int GetNodeCount(){
        return NodeCount;
    }
}