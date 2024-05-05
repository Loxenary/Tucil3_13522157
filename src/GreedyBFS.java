package src;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;



public class GreedyBFS implements Algorithm{
    
    private String endWord = "";

    private Set<String> visited;
    private int NodeCount; 

    private PriorityQueue<node> prioQueue;
    private Map<String, List<String>> hashMap;

    

    private int DaveStringMatching(String target, String currentChecked){
        int diffWord = 0;
        int length = target.length();
        for (int i = 0; i < length; i++) {
            char char1 = target.charAt(i);
            char char2 = currentChecked.charAt(i);
            // Check if characters are different
            if (char1 != char2) {
                // Prioritize the difference based on character type
                if (isVowel(char1) && isVowel(char2)) {
                    diffWord += 2; // Difference between two vowels
                } else if (!isVowel(char1) && !isVowel(char2)) {
                    diffWord += 1; // Difference between two consonants
                } else {
                    if(isVowel(char1) && !isVowel(char2)){
                        diffWord += 3;
                    }else{
                        diffWord += 1; // Difference between vowel and consonant
                    }
                }
            }
        }   
        return diffWord;    
    }
    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
    
    public GreedyBFS(String startword, String endword){
        this.endWord = endword;
        this.visited = new HashSet<String>();
        this.prioQueue = new PriorityQueue<node>(Comparator.comparing(a -> DaveStringMatching(endWord, a.word)));
        node startNode = new node(startword, null);
        this.prioQueue.add(startNode);
        List<String> firstWords = new ArrayList<>();
        firstWords.add(startword);
        this.hashMap = WordChecker.wordList;
        this.NodeCount = 0;
    }


    public List<String> GetResult(){
        while(!prioQueue.isEmpty()){
            node currentNode = prioQueue.poll();            
            if(visited.contains(currentNode.word)){
                continue;
            }

            if(currentNode.word.equals(this.endWord)){
                return node.getPathFromEndToStart(currentNode);
            }

            visited.add(currentNode.word);

            List<String> nextProcessedWords = hashMap.get(currentNode.word);
            NodeCount++;

            for (String nextWord : nextProcessedWords) {
                // Save new Path to the Queue
                node NewNode = new node(nextWord, currentNode);
                prioQueue.add(NewNode);
            }
        }
        return null;
    }

    public int GetNodeCount(){
        return NodeCount;
    }
}