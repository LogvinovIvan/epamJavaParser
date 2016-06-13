package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 14.03.2016.
 */
public class OperatorParseHandler extends AbstractHandler implements ILexemeParser {


    private final String OPERATOR_REGEX = "(([\\\"\\(]+[\\s\\S]+?[\\\"\\)]+((?=\\s)|\\;))|(?<=\\s)?[^\\s]+((?=\\s)|\\;))";

    public OperatorParseHandler(CompositeType type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        parseToLexeme(text, OPERATOR_REGEX).forEach(wordLeaf -> element.add(wordLeaf));
    }

}
