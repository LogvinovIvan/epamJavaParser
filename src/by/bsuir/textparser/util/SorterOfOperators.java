package by.bsuir.textparser.util;

import by.bsuir.textparser.composite.Component;
import by.bsuir.textparser.composite.CompositeElement;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Иван on 20.03.2016.
 */
public class SorterOfOperators implements Comparator<CompositeElement> {

    @Override
    public int compare(CompositeElement operator1, CompositeElement operator2) {
        List<Component> listOfLexeme1 = (List<Component>) operator1.getContent();
        List<Component> listOfLexeme2 = (List<Component>) operator2.getContent();
        if(listOfLexeme1.size()>listOfLexeme2.size()){
            return 1;
        }
        else if(listOfLexeme1.size()<listOfLexeme2.size()){
            return -1;
        }
        return 0;

    }
}
