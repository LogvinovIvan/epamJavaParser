package by.bsuir.textparser.util;

import by.bsuir.textparser.composite.Component;
import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.CompositeType;
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
public class OperatorHandler {
    static Logger logger = Logger.getLogger(CodeParserFileHandler.class);



    public static void swapLexeme(Component element){
        if(element instanceof WordLeaf){
            return;
        }
        else {
            CompositeElement element1 = (CompositeElement) element;
            if(element1.getType() == CompositeType.OPERATORS){
                List<WordLeaf> lexemes = (List<WordLeaf>) element.getContent();
                Collections.swap(lexemes, 0, lexemes.size() - 1);
            }
            else {
                ((List<Component>) element.getContent()).forEach(OperatorHandler::swapLexeme);
            }
        }
    }


    public static void sortOperators(List<CompositeElement> operators) {
        Collections.sort(operators, new SorterOfOperators());
    }

    public static void writeToFile(String fileName, List<CompositeElement> operators) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            StringBuffer buffer = new StringBuffer();
            for (CompositeElement operator : operators) {
                for (Component lexeme : (List<WordLeaf>) operator.getContent()) {
                    buffer.append(lexeme.getContent());
                    buffer.append(" ");
                }
                buffer.append("\n");
                fileOutputStream.write(buffer.toString().getBytes());
                buffer.delete(0, buffer.length());
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
