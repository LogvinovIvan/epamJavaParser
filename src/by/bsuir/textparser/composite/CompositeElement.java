package by.bsuir.textparser.composite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Иван on 13.03.2016.
 */
public class CompositeElement implements Component {

    private ArrayList<Component> content;
    private CompositeType type;

    public CompositeElement(CompositeType type) {
        content = new ArrayList<>();
        this.type = type;
    }


    public CompositeType getType() {
        return type;
    }

    @Override
    public void add(Component component) {
        content.add(component);
    }

    @Override
    public Object getContent() {
        return content;
    }

    public static Component deepClone(Component component) {
        if (component instanceof WordLeaf) {
            return new WordLeaf((String) component.getContent());
        } else {
            CompositeElement element = (CompositeElement) component;
            CompositeElement element1 = new CompositeElement(element.type);
            for (Component component1 : (List<Component>) element.getContent()) {
                element1.add(deepClone(component1));
            }
            return element1;
        }
    }

}
