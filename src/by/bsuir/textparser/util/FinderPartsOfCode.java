package by.bsuir.textparser.util;

import by.bsuir.textparser.composite.Component;
import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.composite.WordLeaf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 27.03.2016.
 */
public class FinderPartsOfCode {


    public List<CompositeElement> findAllOperators(CompositeElement compositeElement) {
        List<CompositeElement> operators = new ArrayList<>();

        class SearcherOfOperators {

            void searchAllOperators(Component component) {
                if ((component instanceof WordLeaf)) {
                    return;
                } else {
                    CompositeElement element = (CompositeElement) component;
                    if (element.getType() == CompositeType.OPERATORS) {
                        operators.add(element);
                    } else {
                        ((List<Component>) element.getContent()).forEach(this::searchAllOperators);
                    }
                }
            }
        }


        SearcherOfOperators searcher = new SearcherOfOperators();
        searcher.searchAllOperators(compositeElement);
        return operators;
    }


    public List<WordLeaf> findAllLexemes(CompositeElement compositeElement) {
        List<WordLeaf> lexemes = new ArrayList<>();

        class SearcherOfLexeme {
            void searchAllLexemes(Component component) {
                if (component instanceof WordLeaf) {
                    lexemes.add((WordLeaf) component);
                } else {
                    ((List<Component>) component.getContent()).forEach(this::searchAllLexemes);
                }
            }
        }

        SearcherOfLexeme searcher = new SearcherOfLexeme();
        searcher.searchAllLexemes(compositeElement);
        return lexemes;
    }


}
