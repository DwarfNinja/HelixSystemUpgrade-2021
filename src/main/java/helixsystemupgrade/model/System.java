package helixsystemupgrade.model;

import java.util.ArrayList;
import java.util.List;

public class System {
    private List<HelixSystem> helixSystemList = new ArrayList<>();

    private static System theSystem = new System();

    public System() {
        helixSystemList.add(new HelixSystem("LUMC"));
        helixSystemList.add(new HelixSystem("ErasmusMC"));
        helixSystemList.add(new HelixSystem("UMCUtrecht"));
    }

    public static System getTheSystem() {
        return theSystem;
    }

    public HelixSystem getHelixSystem(String hospitalname) {
        for (HelixSystem helixSystem : helixSystemList) {
            if (helixSystem.getName().equals(hospitalname)) {
                return helixSystem;
            }
        }
        return null;
    }
}
