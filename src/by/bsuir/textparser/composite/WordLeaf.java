package by.bsuir.textparser.composite;

/**
 * Created by Иван on 13.03.2016.
 */
public class WordLeaf implements Component {

    private String content;

    public WordLeaf(String content) {

        this.content = content;
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public String getContent() {
        return content;
    }


}
