package by.bsuir.textparser.parser;

import by.bsuir.textparser.composite.CompositeElement;
import by.bsuir.textparser.composite.CompositeType;

/**
 * Created by Иван on 13.03.2016.
 */
public abstract class AbstractHandler {
    protected AbstractHandler successor;
    protected CompositeElement element;
    private CompositeType type;

    public AbstractHandler(CompositeType type) {
        this.type = type;
    }

    abstract public void parse(String text);



    public CompositeElement chain(String text) {
        element = new CompositeElement(type);
        parse(text);
        return element;
    }

    public void setSuccessor(AbstractHandler successor) {
        this.successor = successor;
    }
}

