package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 13.03.2016.
 */
public class ClassParseHandler extends AbstractHandler {

    private HashMap<CompositeType, AbstractHandler> tableOfHandlers;
    private static final String METHOD_REGEX = "(\\x20*)(((public|private|protected)\\s+)?)(((static|abstract)\\s+)?)(((final|native|synchronized|strctfp)\\s+)?)(\\w+(\\<\\w+\\>)?\\s)\\w+\\([^;]*?\\)((\\s*\\{[\\s\\S]*?\\})|\\;)";
    private static final String FIELD_REGEX = "((public|private|protected)\\s+)(final\\s)?(static\\s)?\\w+(\\<\\w+\\>)?(\\[\\])?\\s\\w+(\\[\\])?\\s?(\\=\\s.+?)?\\;\\r\\n";
    private static final String CONSTRUCTOR_REGEX = "\\s(public|private|protected)*\\s+\\w+\\s*\\([\\w\\,\\s]*\\)\\s*\\{[^>]+?\\}";
    private static final String BLOCK_REGEX = "(?<=[\\{\\;\\}])[\\n\\s]+(static\\s*)?\\{[^>]+?\\}";
    private static final String NAME_OF_CLASS = "\\w[^;\\(\\)]+?(?=\\{)";

    public ClassParseHandler(CompositeType type) {
        super(type);
        tableOfHandlers = new HashMap<>();
    }

    public void setTableOfHandlers(CompositeType type, AbstractHandler handler) {
        tableOfHandlers.put(type, handler);
    }


    @Override
    public void parse(String text) {
        parseToNameOfClass(text);
        element.add(new WordLeaf("{"));
        parseToField(text);
        parseToBlockElement(text);
        parseToConstructorElement(text);
        parseToMethodElement(text);
        element.add(new WordLeaf("}"));
    }


    private void parseToConstructorElement(String code) {
        Pattern pattern = Pattern.compile(CONSTRUCTOR_REGEX);
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            element.add(tableOfHandlers.get(CompositeType.CONSTRUCTOR).chain(matcher.group()));
        }
    }


    private void parseToMethodElement(String code) {
        Pattern pattern = Pattern.compile(METHOD_REGEX);
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            element.add(tableOfHandlers.get(CompositeType.METHOD).chain(matcher.group()));
        }
    }

    private void parseToBlockElement(String code) {
        Pattern pattern = Pattern.compile(BLOCK_REGEX);
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            element.add(tableOfHandlers.get(CompositeType.BLOCK).chain(matcher.group()));
        }
    }

    private void parseToField(String code) {
        Pattern pattern = Pattern.compile(FIELD_REGEX);
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            element.add(tableOfHandlers.get(CompositeType.FIELD).chain(matcher.group()));
        }
    }

    private void parseToNameOfClass(String code) {
        Pattern pattern = Pattern.compile(NAME_OF_CLASS);
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            element.add(tableOfHandlers.get(CompositeType.NAME).chain(matcher.group()));
        }
    }

}
