package src;

class InputManager{

    // Static Method to check use input

    public static boolean CheckUserInput(String startWord, String endWord){
        if(isStringEmpty(startWord) && isStringEmpty(endWord)){
            ErrorPopup.showError("Both Start Word and End Word are Empty");
            return false;
        }
        if(isStringEmpty(startWord)){
            ErrorPopup.showError("Start Word are Empty");
            return false;
        }
        if(isStringEmpty(endWord)){
            ErrorPopup.showError("End Word are Empty");
            return false;
        }

        if(!isWordExist(startWord) && !isWordExist(endWord)){
            ErrorPopup.showError("Both Start Word and End Word couldn't be found on the Dictionary");
            return false;
        }
        if(!isWordExist(startWord)){
            ErrorPopup.showError("Start Word couldn't be Found on the Dictionary");
            return false;
        }
        if(!isWordExist(endWord)){
            ErrorPopup.showError("End Word couldn't be Found on the Dictionary");
            return false;
        }
        if(startWord.length() != endWord.length()){
            ErrorPopup.showError("the Lenght of Start Word and End Word must be same");
            return false;
        }
        if(startWord.equals(endWord)){
            ErrorPopup.showError("Gaboleh Bang, harus beda startword ama endwordnya");
            return false; 
        }
        return true;
    }   
    
    private static boolean isStringEmpty(String word){
        return word.isEmpty();
    }

    private static boolean isWordExist(String word){
        return DictionaryMapper.isWordExist(word);
    }
}