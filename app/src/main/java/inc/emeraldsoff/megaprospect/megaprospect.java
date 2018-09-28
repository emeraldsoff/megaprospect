package inc.emeraldsoff.megaprospect;

import android.app.Application;

import io.realm.Realm;

public class megaprospect extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
