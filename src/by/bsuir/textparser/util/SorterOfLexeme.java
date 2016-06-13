package by.bsuir.textparser.util;

import by.bsuir.textparser.composite.WordLeaf;

import java.util.Comparator;

/**
 * Created by Иван on 29.03.2016.
 */
public class SorterOfLexeme implements Comparator<WordLeaf> {
    @Override
    public int compare(WordLeaf o1, WordLeaf o2) {
        return o1.getContent().compareToIgnoreCase(o2.getContent());
    }
}
