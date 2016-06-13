package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 20.03.2016.
 */
public class NameParseHandler extends AbstractHandler implements ILexemeParser {
    private final String OPERATOR_REGEX = "\\s*[^>]+?\\s+";

    public NameParseHandler(CompositeType type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        parseToLexeme(text, OPERATOR_REGEX).forEach(wordLeaf -> element.add(wordLeaf));
    }
}
