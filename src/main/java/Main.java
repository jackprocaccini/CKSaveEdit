import java.io.File;
import java.io.IOException;

public class Main {

    public static String pathToDemoFile = "C:\\Users\\User\\Documents\\Paradox Interactive\\Crusader Kings III\\save games\\gamestate";
    public static String pathToTestFile = "src\\main\\resources\\stuff.txt";

    public static void main(String[] args){
        // "living" is the beginning of live characters, starts on line 436247 and ends on 1270007. Thorgil is on line 549810
//        new CKSaveEdit(new File(pathToDemoFile));
        try {
            SaveReader.getCharacters(new File(pathToTestFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// TODO: Implement CK3Character.java