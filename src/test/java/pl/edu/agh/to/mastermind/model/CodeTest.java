package pl.edu.agh.to.mastermind.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CodeTest {
    @Test
    public void testNoCommonColors()
    {
        var targetCode = new Code(List.of(new Colors[]{Colors.RED, Colors.BLACK, Colors.BLACK, Colors.RED}));

        var guessedCode = new Code(List.of(new Colors[]{Colors.BLUE, Colors.GREEN, Colors.WHITE, Colors.YELLOW}));

        GuessResult result = targetCode.check(guessedCode);
        Assert.assertEquals(0, result.getGuessedCorrectly());
        Assert.assertEquals(0, result.getGuessedInDifferentPlace());
    }

    @Test
    public void testSomeColorsInWrongPlaces()
    {
        var targetCode = new Code(List.of(new Colors[]{Colors.RED, Colors.BLACK, Colors.BLACK, Colors.RED}));

        var guessedCode = new Code(List.of(new Colors[]{Colors.BLUE, Colors.RED, Colors.RED, Colors.BLACK}));

        GuessResult result = targetCode.check(guessedCode);
        Assert.assertEquals(0, result.getGuessedCorrectly());
        Assert.assertEquals(3, result.getGuessedInDifferentPlace());
    }

    @Test
    public void testOnlyColorsInCorrectPlaces()
    {
        var targetCode = new Code(List.of(new Colors[]{Colors.RED, Colors.BLACK, Colors.ORANGE, Colors.RED}));

        var guessedCode = new Code(List.of(new Colors[]{Colors.BLUE, Colors.BLACK, Colors.GREEN, Colors.WHITE}));

        GuessResult result = targetCode.check(guessedCode);
        Assert.assertEquals(1, result.getGuessedCorrectly());
        Assert.assertEquals(0, result.getGuessedInDifferentPlace());
    }
    @Test
    public void testColorsInRightAndWrongPlaces()
    {
        var targetCode = new Code(List.of(new Colors[]{Colors.RED, Colors.BLACK, Colors.ORANGE, Colors.RED}));

        var guessedCode = new Code(List.of(new Colors[]{Colors.BLUE, Colors.BLACK, Colors.RED, Colors.WHITE}));

        GuessResult result = targetCode.check(guessedCode);
        Assert.assertEquals(1, result.getGuessedCorrectly());
        Assert.assertEquals(1, result.getGuessedInDifferentPlace());
    }

}
