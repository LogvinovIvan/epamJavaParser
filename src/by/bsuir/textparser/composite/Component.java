package by.bsuir.textparser.composite;



/**
 * Created by Иван on 13.03.2016.
 */
public interface Component {
    void add(Component component);
    Object getContent();

}
