import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class SaveReader {

    public static HashMap<String, ICK3Character> getCharacters(File gamestate) throws IOException {
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

            if(currentLine.contains("{") || currentLine.contains("}")){
                readBraces(curlyBraceStack, currentLine);
            }

            if(curlyBraceStack.size() == 2){ // when the stack has exactly size 2, we reached a character block
                StringBuilder currentCharacterData = new StringBuilder();
                currentCharacterData.append(currentLine + "\n"); // beginning of the current character block (something like "2={")

                while(curlyBraceStack.size() >= 2){ // when the code exits this loop, we've left this character's section
                    currentLine = sc.nextLine();
                    currentCharacterData.append(currentLine + "\n");

                    if(currentLine.contains("{") || currentLine.contains("}")){
                        readBraces(curlyBraceStack, currentLine);
                    }
                }
                System.out.println(currentCharacterData);
                ICK3Character temp = new CK3Character(currentCharacterData.toString());
                allChars.put(temp.getID(), temp);
            }
        }

        return allChars;
    }

    private static void readBraces(Stack<String> curlyBraceStack, String currentLine) {
        if(currentLine.indexOf("{") != currentLine.lastIndexOf("{") || currentLine.indexOf("}") != currentLine.lastIndexOf("}")) {
            char[] tempLine = currentLine.toCharArray();
            for(int i = 0; i < tempLine.length; i++){
                if(tempLine[i] == '{'){
                    curlyBraceStack.push("{");

                } else if(tempLine[i] == '}'){
                    curlyBraceStack.pop();
                }

            }

        } else {
            if(currentLine.contains("{")){
                curlyBraceStack.push("{");
            }

            if(currentLine.contains("}")){
                curlyBraceStack.pop();

            }
        }
    }
}
