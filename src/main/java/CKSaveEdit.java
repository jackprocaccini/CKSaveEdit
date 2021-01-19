import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class CKSaveEdit {
    private File gamestate;
    private ICK3Character player;
    private HashMap<String, ICK3Character> allCharacters;

    public CKSaveEdit(File gamestate){
        this.gamestate = gamestate;
    }

}
