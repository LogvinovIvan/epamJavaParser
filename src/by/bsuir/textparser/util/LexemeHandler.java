package by.bsuir.textparser.util;

import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.WordLeaf;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * Created by Иван on 27.03.2016.
 */
public class LexemeHandler {
    static Logger logger = Logger.getLogger(CodeParserFileHandler.class);


    public static void sortLexemeByFirstLetter(List<WordLeaf> lexemes) {
        Collections.sort(lexemes, new SorterOfLexeme());
    }

    public static void writeResultToFile(String fileName, List<WordLeaf> leafs) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(formRightOutputOfLexemes(leafs).toString().getBytes());

        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }


    private static StringBuffer formRightOutputOfLexemes(List<WordLeaf> lexemes) {
        StringBuffer buffer = new StringBuffer();
        char basicChar = 0;
        for (WordLeaf leaf : lexemes) {

            if (Character.toLowerCase(leaf.getContent().charAt(0)) == basicChar) {
                buffer.append(" ");
            } else {
                basicChar = Character.toLowerCase(leaf.getContent().charAt(0));
                buffer.append("\n");
            }
            buffer.append(leaf.getContent());
        }
        return buffer;
    }
}
