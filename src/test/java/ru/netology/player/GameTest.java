package ru.netology.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.NotRegisteredException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    Game regPlayer = new Game();

    Player player1 = new Player(5, "John", 200);
    Player player2 = new Player(2, "May", 250);
    Player player3 = new Player(8, "Sam", 300);
    Player player4 = new Player(7, "Amanda", 350);
    Player player5 = new Player(3, "Marko", 200);
    Player player6 = new Player(4, "Joanna", 300);

    @Test
    public void gameBetweenUnRegisteredPlayers() {

        assertThrows(NotRegisteredException.class, () -> {
            regPlayer.round(player2.getName(), player3.getName());
        });
    }

    @Test
    public void winFSecond() {
        regPlayer.register(player1);
        regPlayer.register(player2);

        int expected = 2;
        int actual = regPlayer.round("John", "May");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void gameBetweenRegisteredAndUnregisteredPlayers() {
        regPlayer.register(player3);

       assertThrows(NotRegisteredException.class, () -> {
            regPlayer.round(player2.getName(), player3.getName());
        });
    }

    @Test
    public void gameBetweenRegisteredAndUnregisteredPlayersSecond() {
        regPlayer.register(player5);

        assertThrows(NotRegisteredException.class, () -> {
            regPlayer.round(player5.getName(), player1.getName());
        });
    }

    @Test
    public void gameDraw() {
        regPlayer.register(player3);
        regPlayer.register(player6);

        int expected = 0;
        int actual = regPlayer.round("Sam", "Joanna");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void winFirst() {
        regPlayer.register(player4);
        regPlayer.register(player5);

        int expected = 1;
        int actual = regPlayer.round("Amanda", "Marko");

        Assertions.assertEquals(expected, actual);
    }

}
