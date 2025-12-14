package re.forestier.edu.rpg.Progression;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class LevelSystem {

    private final Map<Integer, Integer> xpTable;

    public LevelSystem(int maxLevel) {
        this.xpTable = generateLevelsXP(maxLevel);
    }

    public Map<Integer, Integer> getXpTable() {
        return xpTable;
    }

    private Map<Integer, Integer> generateLevelsXP(int maxLevel) {
        Map<Integer, Integer> levels = new HashMap<>();

        int previousXp = 0;

        for (int lvl = 2; lvl <= maxLevel; lvl++) {
            // Pure integer math – this matches the teacher’s examples:
            int xp = (lvl - 1) * 10 + (lvl * previousXp) / 4;
            levels.put(lvl, xp);
            previousXp = xp;
        }

        return levels;
    }

    public int computeLevel(int xp) {
        int level = 1;

        List<Integer> sorted = new ArrayList<>(xpTable.keySet());
        Collections.sort(sorted);

        for (int lvl : sorted) {
            int threshold = xpTable.get(lvl);

            if (xp >= threshold) {
                level = lvl;
            } else {
                break;
            }
        }

        return level;
    }
}
