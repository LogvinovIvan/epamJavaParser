package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 13.03.2016.
 */
public class FieldParseHandler extends AbstractHandler implements ILexemeParser {
    private final String LEXEME_REGEX = "(?<=\\s)?[\\s\\S]+?[(?=\\x20);]";
    private final String OPERATOR_REGEX = "[\\s\\S]+?\\=[\\s\\S]+?\\;";

    public FieldParseHandler(CompositeType type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        Pattern operatorPattern = Pattern.compile(OPERATOR_REGEX);
        Matcher matcher = operatorPattern.matcher(text);
        if (matcher.find()) {
            element.add(successor.chain(text));
        } else {

            parseToLexeme(text, LEXEME_REGEX).forEach(wordLeaf -> element.add(wordLeaf));
        }
    }
}
