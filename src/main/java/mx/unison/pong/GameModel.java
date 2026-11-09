package mx.unison.pong;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class GameModel {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    
    public Ellipse2D.Double ball;
    public Rectangle2D.Double paddle;
    
    public int dx;
    public int dy;
    
    public GameState state;

    public GameModel() {
        reset();
    }

    public void reset() {
        ball = new Ellipse2D.Double(310, 230, 20, 20);
        paddle = new Rectangle2D.Double(280, 430, 80, 20);
        dx = 5;
        dy = 5;
        state = GameState.RUNNING;
    }
}
