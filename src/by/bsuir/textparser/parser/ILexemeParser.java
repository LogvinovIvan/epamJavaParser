package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.WordLeaf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Иван on 23.03.2016.
 */
public interface ILexemeParser {
    default List<WordLeaf> parseToLexeme(String code, String regex) {
        List<WordLeaf> leafs = new ArrayList<>();
        Pattern operatorPattern = Pattern.compile(regex);
        Matcher matcher = operatorPattern.matcher(code);
        while (matcher.find()) {
            String group = matcher.group();
            String buf = group.trim();
            WordLeaf wordLeaf = new WordLeaf(buf);
            leafs.add(wordLeaf);
        }
        return leafs;
    }
}
