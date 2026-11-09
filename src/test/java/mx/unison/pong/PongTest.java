package mx.unison.pong;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PongTest {

    @Test
    public void testWallCollisionLeft() {
        GameModel model = new GameModel();
        GameLogic logic = new GameLogic(model);
        model.ball.x = -5; 
        model.dx = -5;
        logic.update();
        assertTrue(model.dx > 0); 
        assertEquals(0, model.ball.x); 
    }

    @Test
    public void testWallCollisionTop() {
        GameModel model = new GameModel();
        GameLogic logic = new GameLogic(model);
        model.ball.y = -5;
        model.dy = -5;
        logic.update();
        assertTrue(model.dy > 0);
        assertEquals(0, model.ball.y);
    }

    @Test
    public void testPaddleCollision() {
        GameModel model = new GameModel();
        GameLogic logic = new GameLogic(model);
        model.ball.x = 300;
        model.ball.y = 425; 
        model.dy = 5; 
        model.paddle.x = 290; 
        logic.update();
        assertTrue(model.dy < 0); 
    }

    @Test
    public void testGameOver() {
        GameModel model = new GameModel();
        GameLogic logic = new GameLogic(model);
        model.ball.y = GameModel.HEIGHT + 10; 
        logic.update();
        assertEquals(GameState.GAME_OVER, model.state);
    }
}
