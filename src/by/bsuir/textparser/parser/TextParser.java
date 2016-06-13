package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.CompositeType;
import by.bsuir.textparser.util.CodeParserFileHandler;

/**
 * Created by Иван on 14.03.2016.
 */
public class TextParser {


    public CompositeElement parseText(String fileName)  {
        CodeParseHandler codeParseHandler = new CodeParseHandler(CompositeType.LISTING);
        ClassParseHandler classParseHandler = new ClassParseHandler(CompositeType.CLASS);
        ConstructorParseHandler constructorParseHandler = new ConstructorParseHandler(CompositeType.CONSTRUCTOR);
        MethodParseHandler methodParseHandler = new MethodParseHandler(CompositeType.METHOD);
        FieldParseHandler fieldParseHandler = new FieldParseHandler(CompositeType.FIELD);
        OperatorParseHandler operatorParseHandler = new OperatorParseHandler(CompositeType.OPERATORS);
        SignatureParseHandler signatureParseHandler = new SignatureParseHandler(CompositeType.SIGNATURE);
        NameParseHandler nameParseHandler = new NameParseHandler(CompositeType.NAME);
        BlockParseHandler blockParseHandler = new BlockParseHandler(CompositeType.BLOCK);

        blockParseHandler.setSuccessor(operatorParseHandler);

        classParseHandler.setTableOfHandlers(CompositeType.METHOD, methodParseHandler);
        classParseHandler.setTableOfHandlers(CompositeType.FIELD, fieldParseHandler);
        classParseHandler.setTableOfHandlers(CompositeType.CONSTRUCTOR, constructorParseHandler);
        classParseHandler.setTableOfHandlers(CompositeType.NAME, nameParseHandler);
        classParseHandler.setTableOfHandlers(CompositeType.BLOCK, blockParseHandler);


        methodParseHandler.setHandler(CompositeType.SIGNATURE, signatureParseHandler);
        methodParseHandler.setHandler(CompositeType.BLOCK, blockParseHandler);

        constructorParseHandler.setHandler(CompositeType.SIGNATURE, signatureParseHandler);
        constructorParseHandler.setHandler(CompositeType.BLOCK, blockParseHandler);

        fieldParseHandler.setSuccessor(operatorParseHandler);

        codeParseHandler.setTableOfHandlers(CompositeType.CLASS, classParseHandler);


        CompositeElement element = null;

        element = codeParseHandler.chain(CodeParserFileHandler.readFile(fileName));


        return element;

    }


}
