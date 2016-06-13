package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 20.03.2016.
 */
public class BlockParseHandler extends AbstractHandler {

    private final String OPERATOR_REGEX = "((\\x20[^{]+?\\;))";

    public BlockParseHandler(CompositeType type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        element.add(new WordLeaf("{"));
        Pattern operatorPattern = Pattern.compile(OPERATOR_REGEX);
        Matcher matcher = operatorPattern.matcher(text);
        while (matcher.find()) {
            element.add(successor.chain(matcher.group()));
        }
        element.add(new WordLeaf("}"));
    }
}
