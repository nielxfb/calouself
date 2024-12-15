package abstraction;

import javafx.scene.layout.Region;
import middleware.AuthMiddleware;
import util.StageManager;
import view.page.LoginPage;

public abstract class Page<T extends Region> {

    public T layout;

    public Page(T layout) {
        this.layout = layout;
    }

    public void middleware() {
        if (!AuthMiddleware.loggedIn()) {
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new LoginPage().layout);
        }
    }

    public abstract void initPage();

    public abstract void addEvent();

}
