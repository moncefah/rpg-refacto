package re.forestier.edu.rpg.Progression;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LevelSystemTest {

    @Test
    void constructor_generatesXpTableFromLevel2ToMaxLevel() {
        LevelSystem ls = new LevelSystem(5);
        Map<Integer, Integer> table = ls.getXpTable();

        assertEquals(4, table.size()); // levels 2,3,4,5
        assertTrue(table.containsKey(2));
        assertTrue(table.containsKey(3));
        assertTrue(table.containsKey(4));
        assertTrue(table.containsKey(5));
        assertFalse(table.containsKey(1));
    }

    @Test
    void xpTable_matchesExpectedValuesForSmallMaxLevel() {
        LevelSystem ls = new LevelSystem(5);
        Map<Integer, Integer> t = ls.getXpTable();

        // Using your formula with integer math:
        // lvl2: (1)*10 + (2*0)/4 = 10
        // lvl3: (2)*10 + (3*10)/4 = 20 + 7 = 27
        // lvl4: (3)*10 + (4*27)/4 = 30 + 27 = 57
        // lvl5: (4)*10 + (5*57)/4 = 40 + 71 = 111
        assertEquals(10, t.get(2));
        assertEquals(27, t.get(3));
        assertEquals(57, t.get(4));
        assertEquals(111, t.get(5));
    }

    @Test
    void computeLevel_returns1_whenXpBelowFirstThreshold() {
        LevelSystem ls = new LevelSystem(5);
        assertEquals(1, ls.computeLevel(0));
        assertEquals(1, ls.computeLevel(9)); // below level 2 threshold (10)
    }

    @Test
    void computeLevel_returnsExactLevel_whenXpEqualsThreshold() {
        LevelSystem ls = new LevelSystem(5);

        assertEquals(2, ls.computeLevel(10));
        assertEquals(3, ls.computeLevel(27));
        assertEquals(4, ls.computeLevel(57));
        assertEquals(5, ls.computeLevel(111));
    }

    @Test
    void computeLevel_returnsHighestReachedLevel_whenXpBetweenThresholds() {
        LevelSystem ls = new LevelSystem(5);

        assertEquals(2, ls.computeLevel(26));  // between 10 and 27
        assertEquals(3, ls.computeLevel(56));  // between 27 and 57
        assertEquals(4, ls.computeLevel(110)); // between 57 and 111
    }

    @Test
    void computeLevel_capsAtMaxLevel_whenXpAboveMaxThreshold() {
        LevelSystem ls = new LevelSystem(5);

        assertEquals(5, ls.computeLevel(1000));
        assertEquals(5, ls.computeLevel(Integer.MAX_VALUE));
    }
}
