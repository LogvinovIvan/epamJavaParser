package by.bsuir.textparser.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Иван on 14.03.2016.
 */
public class CodeParserFileHandler {
    static Logger logger = Logger.getLogger(CodeParserFileHandler.class);

    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        Scanner fileInput;
        try {
            fileInput = new Scanner(new File(fileName));
            while (fileInput.hasNextLine()) {
                builder.append(fileInput.nextLine());
                builder.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            logger.fatal("Error with reading file",e);
            throw new RuntimeException();
        }

        return builder.toString();
    }
}
