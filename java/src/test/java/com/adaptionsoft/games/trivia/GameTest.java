package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;

import org.approvaltests.Approvals;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;

public class GameTest {

	@Test
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}

	@Test
    public void failIfLessThanTwoPlayers() {
	    boolean notAWinner;
        Game aGame = new Game();
        Random rand = new Random();

        aGame.add("Chet");

        boolean status = aGame.roll(rand.nextInt(5) + 1);
        Assert.assertEquals(status, false);
    }
}
