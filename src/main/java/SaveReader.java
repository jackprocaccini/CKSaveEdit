import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class SaveReader {

    public static void getCharacters(File gamestate) throws IOException {
        Scanner sc = new Scanner(gamestate, StandardCharsets.UTF_8);
        HashMap<String, ICK3Character> allChars = new HashMap<>();
        Stack<String> curlyBraceStack = new Stack<>();

        String currentLine = sc.nextLine();

        while(!currentLine.equals("living={")){ // bring us to the "living" section
            currentLine = sc.nextLine();
        }
        curlyBraceStack.push("{"); // starts the stack, so we know where to stop reading data (hopefully)

        while(!curlyBraceStack.empty()){ // when the stack is empty, we've reached the end of the "living" section
            currentLine = sc.nextLine();

            if(currentLine.contains("{")){
                curlyBraceStack.push("{");
            }

            if(currentLine.contains("}")){
                curlyBraceStack.pop();
            }

            if(curlyBraceStack.size() == 2){ // when the stack has exactly size 2, we reached a character block
                StringBuilder currentCharacterData = new StringBuilder();
                currentCharacterData.append(currentLine + "\n"); // beginning of the current character block (something like "2={")

                while(curlyBraceStack.size() >= 2){ // when the code exits this loop, we've left this character's section
                    currentLine = sc.nextLine();
                    currentCharacterData.append(currentLine + "\n");

                    if(currentLine.contains("{")){
                        curlyBraceStack.push("{");
                    }

                    if(currentLine.contains("}")){
                        curlyBraceStack.pop();
                    }

                }

                ICK3Character temp = new CK3Character(currentCharacterData.toString());
                allChars.put(temp.getID(), temp);
            }
        }
    }
}