package by.bsuir.textparser.util;

import by.bsuir.textparser.composite.Component;
import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Иван on 20.03.2016.
 */
public class BuilderOfCode {

    static Logger logger = Logger.getLogger(BuilderOfCode.class);

    public void buildCodeToFile(CompositeElement codeElement, String fileName) {
        StringBuffer code = new StringBuffer();

        class RecursiveCodeBuilder {
            public void buildCode(Component element) {
                if (!(element instanceof WordLeaf)) {
                    ((List<Component>) element.getContent()).forEach(this::buildCode);
                    code.append("\n");
                } else {
                    code.append(element.getContent());
                    code.append(" ");
                }
            }
        }

        RecursiveCodeBuilder codeBuilder = new RecursiveCodeBuilder();

        codeBuilder.buildCode(codeElement);
        writeToFile(fileName, code);

    }


    public void writeToFile(String fileName, StringBuffer code) {
        try (FileOutputStream file = new FileOutputStream(fileName)) {
            file.write(code.toString().getBytes());
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
