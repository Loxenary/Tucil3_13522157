package src;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;



public class GreedyBFS implements Algorithm{
    
    // Menyimpan data kata yang ingin dicapai
    private String endWord = "";

    // Menyimpan set of visited nodes, atau biasa dikenal sebagai closed list
    private Set<String> visited;

    // Menyimpan jumlah Node yang di cek
    private int NodeCount; 

    // Prioqueue yang menggunakan string matching sebagai cost-nya
    private PriorityQueue<node> prioQueue;

    // hashmap yang menyimpan data dictionary parser
    private Map<String, List<String>> hashMap;

    // Algoritma String Matching yang digunakan sebagai cost
    private int DaveStringMatching(String target, String currentChecked){
        int diffWord = 0;
        int length = target.length();
        for (int i = 0; i < length; i++) {
            char char1 = target.charAt(i);
            char char2 = currentChecked.charAt(i);
            // Check jika ada kata yang berbeda
            if (char1 != char2) {
            
                if (isVowel(char1) && isVowel(char2)) {
                    diffWord += 2; // Penambahan cost jika kedua kata vocal
                } else if (!isVowel(char1) && !isVowel(char2)) {
                    diffWord += 1; // Penambahan cost jika kedua kata consonant
                } else {
                    if(isVowel(char1) && !isVowel(char2)){
                        diffWord += 3; // Penambahan cost jika kata target vocal tapi currentchecked consonant
                    }else{
                        diffWord += 1; // Penambahan cost jika kata target consonant tapi currentchecked vocal
                    }
                }
            }
        }   
        return diffWord;    
    }

    // Apakah char yang di cek adalah jenis char vocal
    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
    
    // Constructor
    public GreedyBFS(String startword, String endword){
        this.endWord = endword;
        this.visited = new HashSet<String>();
        this.prioQueue = new PriorityQueue<node>(Comparator.comparing(a -> DaveStringMatching(endWord, a.word)));
        node startNode = new node(startword, null);

        // Set startWord sebagai initiator
        this.prioQueue.add(startNode);
        List<String> firstWords = new ArrayList<>();
        firstWords.add(startword);
        this.hashMap = DictionaryMapper.dictMap;
        this.NodeCount = 0;
    }

    // Get path atau null
    public List<String> GetResult(){
        while(!prioQueue.isEmpty()){
            node currentNode = prioQueue.poll();       
            prioQueue.clear();
            // Skip data pada visited set     

            if(currentNode.word.equals(this.endWord)){
                return node.getPathFromEndToStart(currentNode);
            }

            visited.add(currentNode.word);

            List<String> nextProcessedWords = hashMap.get(currentNode.word);
            NodeCount++;

            for (String nextWord : nextProcessedWords) {
                // Save new Path to the Queue
                if(!visited.contains(nextWord)){
                    node NewNode = new node(nextWord, currentNode);
                    prioQueue.add(NewNode);
                }
            }
        }
        return null;
    }

    public int GetNodeCount(){
        return NodeCount;
    }
}