package abstraction;

import javafx.scene.layout.Region;

public abstract class Page<T extends Region>{

    public T layout;

    public abstract void middleware();

    public abstract void initPage();

    public abstract void addEvent();

}
