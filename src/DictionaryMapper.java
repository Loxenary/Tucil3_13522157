package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryMapper {
    // Saves in map to cache the word with the list of words that has one diffrent character
    public static Map<String, List<String>> dictMap = new HashMap<String, List<String>>();

    // Check if a word is exist within the map
    public static boolean isWordExist(String word) {
        return dictMap.containsKey(word);
    }

    // Save the parsed data into a Map
    public static void CacheDictionary() {
        dictMap = new HashMap<String, List<String>>();
        try {
            File file = new File("src/data/parsed_data.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // Save the data that has format of string int
                String[] temp = scanner.nextLine().split(" ");
                if(temp.length > 1){
                    String data = temp[0]; // Word
                    Integer len = Integer.parseInt(temp[1]); // Length of List word under it
                    List<String> list = new ArrayList<String>();
                    for (int j = 0; j < len; j++) {
                        list.add(scanner.nextLine());
                    }
                    if (!dictMap.containsKey(data)) {
                        dictMap.put(data, list);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}