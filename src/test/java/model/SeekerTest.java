package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeekerTest extends Assert {
    private static final String EXPEDITION_NAME = "DREAM";
    private static final double START_X = 0;
    private static final double START_Y = 0;
    private static final double START_RADIUS = 5;
    private static final String FIND_DESCRIPTION = "Bond";
    private static final double FIND_X = 10;
    private static final double FIND_Y = 10;
    private static final double DELTA = 0.01;
    private static final double SEARCH_RADIUS = 1;
    private Seeker seeker;

    @Before
    public void setUp() {
        seeker = new Seeker("Yulia", "Alenkova");
    }

    @Test
    public void createNewExpedition() {
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        assertEquals(1, seeker.getExpeditions().size());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotCreateNewExpeditionUntilCompletingPrevious() throws IllegalStateException {
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);

    }

    @Test
    public void failExpedition() {
        String expectedString = "The Expedition \"" + EXPEDITION_NAME + "\" has failed";
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        assertEquals(expectedString, seeker.finishExpedition());

    }

    @Test
    public void findSomething() {
        String expectedString = "Congratulations! It's your 2 find in this expedition";
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.findSomething(FIND_DESCRIPTION, FIND_X, FIND_Y);
        assertEquals(expectedString, seeker.findSomething(FIND_DESCRIPTION, FIND_X, FIND_Y));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotFindSomethingAfterFinish() throws IllegalStateException {
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.finishExpedition();
        seeker.findSomething(FIND_DESCRIPTION, FIND_X, FIND_Y);
    }

    @Test
    public void finishTwice() {
        String expectedString = "You've already finished last expedition";
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.finishExpedition();
        assertEquals(expectedString, seeker.finishExpedition());


    }

    @Test
    public void getSquareWithoutFinds() {
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        assertEquals(Math.PI * Math.pow(START_RADIUS, 2), seeker.getSquareOfLastExpedition(), DELTA);

    }

    @Test
    public void getSquareWithFindOutside() {
        double expectedSquare = Math.PI * Math.pow(START_RADIUS, 2) + Math.PI * Math.pow(SEARCH_RADIUS, 2);
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.findSomething(FIND_DESCRIPTION, FIND_X, FIND_Y);
        assertEquals(expectedSquare, seeker.getSquareOfLastExpedition(), DELTA);

    }

    @Test
    public void getSquareWithFindInside() {
        double expectedSquare = Math.PI * Math.pow(START_RADIUS, 2);
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.findSomething(FIND_DESCRIPTION, 2, 3);
        assertEquals(expectedSquare, seeker.getSquareOfLastExpedition(), DELTA);

    }

    @Test
    public void getSquareWithFindWithСrossroad() {
        double expectedSquare = Math.PI * Math.pow(START_RADIUS, 2) + 0.237972;
        seeker.createExpedition(EXPEDITION_NAME, START_X, START_Y, START_RADIUS);
        seeker.findSomething(FIND_DESCRIPTION, 3, 3);
        assertEquals(expectedSquare, seeker.getSquareOfLastExpedition(), DELTA);

    }


}
