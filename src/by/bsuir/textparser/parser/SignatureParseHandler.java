package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeType;


/**
 * Created by Иван on 16.03.2016.
 */
public class SignatureParseHandler extends AbstractHandler implements ILexemeParser {
    private final String SIGNATURE_REGEX = "(?<=\\s)?((\\w+(?=\\s))|([\\w\\.]+\\([\\s\\S]*?\\)))\\;?";

    public SignatureParseHandler(CompositeType type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        parseToLexeme(text, SIGNATURE_REGEX).forEach(wordLeaf -> element.add(wordLeaf));
    }

}
