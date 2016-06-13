package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 14.03.2016.
 */
public class CodeParseHandler extends AbstractHandler {
    private static final String CLASS_REGEX = "(\\x20*)((public)\\s+)?((final|abstract)\\s+)?(class|interface)\\x20+\\w+(\\<\\w\\>)?\\x20+((extends)\\s\\w+\\6\\s?)?((implements)(\\s\\w+\\6(,)?)*)?\\{[\\s\\S]+?\\n\\1\\}";
    private HashMap<CompositeType, AbstractHandler> tableOfHandlers;

    public CodeParseHandler(CompositeType type) {
        super(type);
        tableOfHandlers = new HashMap<>();
    }

    public void setTableOfHandlers(CompositeType type, AbstractHandler handler) {
        tableOfHandlers.put(type, handler);
    }

    @Override
    public void parse(String text) {
        parseClass(text);
    }


    private void parseClass(String code) {
        Pattern pattern = Pattern.compile(CLASS_REGEX);
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            element.add(tableOfHandlers.get(CompositeType.CLASS).chain(matcher.group()));

        }
    }

}
