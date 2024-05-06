package src;
public class Main {
    public static void main(String args[]) {
        DictionaryMapper.CacheDictionary();
        GUI root = new GUI();
        root.ShowGUI();
    }
}