package by.bsuir.textparser.start;

import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.WordLeaf;
import by.bsuir.textparser.parser.TextParser;
import by.bsuir.textparser.util.BuilderOfCode;
import by.bsuir.textparser.util.FinderPartsOfCode;
import by.bsuir.textparser.util.LexemeHandler;
import by.bsuir.textparser.util.OperatorHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.List;

public class Main {
    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    private final static String FILE_PATH_FOR_RESTORED_CODE = "results/RestoredCode.txt";
    private final static String FIlE_PATH_FOR_FIRST_TASK = "results/CodeWithSwappingLexemes.txt";
    private final static String FIlE_PATH_FOR_SECOND_TASK = "results/SortedOperatorsByCountLexemes.txt";
    private final static String FIlE_PATH_FOR_THIRD_TASK = "results/SortedLexemeByFirstLetter.txt";


    public static void main(String[] args) {

        TextParser parser = new TextParser();
        CompositeElement codeElement;

        codeElement = parser.parseText("resource/Example.java");

        BuilderOfCode builderOfCode = new BuilderOfCode();
        builderOfCode.buildCodeToFile(codeElement, FILE_PATH_FOR_RESTORED_CODE);

        CompositeElement element = (CompositeElement) CompositeElement.deepClone(codeElement);
        OperatorHandler.swapLexeme(element);


        builderOfCode.buildCodeToFile(element, FIlE_PATH_FOR_FIRST_TASK);

        FinderPartsOfCode finderPartsOfCode = new FinderPartsOfCode();
        List<CompositeElement> operators = finderPartsOfCode.findAllOperators(codeElement);

        OperatorHandler.sortOperators(operators);
        OperatorHandler.writeToFile(FIlE_PATH_FOR_SECOND_TASK, operators);

        List<WordLeaf> lexemes = finderPartsOfCode.findAllLexemes(codeElement);
        LexemeHandler.sortLexemeByFirstLetter(lexemes);
        LexemeHandler.writeResultToFile(FIlE_PATH_FOR_THIRD_TASK, lexemes);

    }
}
