package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 16.03.2016.
 */
public class ConstructorParseHandler extends AbstractHandler {
    private final String SIGNATURE_REGEX = "(public|prvate|protected\\s)?\\w+\\s*\\([\\S\\s]*?\\)(?=(\\s+?\\{))";
    private static final String BLOCK_REGEX = "\\{[\\s\\S]+?\\}";

    public  ConstructorParseHandler(CompositeType type) {
        super(type);
    }

    private Map<CompositeType, AbstractHandler> tableHandler = new HashMap<>();

    public void setHandler(CompositeType type, AbstractHandler handler) {
        tableHandler.put(type, handler);
    }

    @Override
    public void parse(String text) {
        parseConstructSignature(text);
        parseBlocks(text);
    }

    private void parseConstructSignature(String code) {
        Pattern operatorPattern = Pattern.compile(SIGNATURE_REGEX);
        Matcher matcher = operatorPattern.matcher(code);
        while (matcher.find()) {
            element.add(tableHandler.get(CompositeType.SIGNATURE).chain(matcher.group()));
        }
    }

    private void parseBlocks(String code) {

        Pattern operatorPattern = Pattern.compile(BLOCK_REGEX);
        Matcher matcher = operatorPattern.matcher(code);
        while (matcher.find()) {
            element.add(tableHandler.get(CompositeType.BLOCK).chain(matcher.group()));
        }

    }

}
