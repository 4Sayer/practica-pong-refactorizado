package mx.unison.pong;

public class GameLogic {
    private final GameModel model;

    public GameLogic(GameModel model) {
        this.model = model;
    }

    public void update() {
        if (model.state != GameState.RUNNING) {
            return;
        }

        model.ball.x += model.dx;
        model.ball.y += model.dy;

        if (model.ball.x <= 0) {
            model.ball.x = 0;
            model.dx = Math.abs(model.dx);
        } else if (model.ball.x >= GameModel.WIDTH - model.ball.width) {
            model.ball.x = GameModel.WIDTH - model.ball.width;
            model.dx = -Math.abs(model.dx);
        }

        if (model.ball.y <= 0) {
            model.ball.y = 0;
            model.dy = Math.abs(model.dy);
        }

        if (model.ball.y >= GameModel.HEIGHT) {
            model.state = GameState.GAME_OVER;
        }

        if (model.ball.intersects(model.paddle) && model.dy > 0) {
            model.dy = -Math.abs(model.dy);
            model.ball.y = model.paddle.y - model.ball.height;
        }
    }

    public void movePaddleLeft() {
        if (model.state == GameState.RUNNING && model.paddle.x > 0) {
            model.paddle.x = Math.max(0, model.paddle.x - 25);
        }
    }

    public void movePaddleRight() {
        if (model.state == GameState.RUNNING && model.paddle.x < GameModel.WIDTH - model.paddle.width) {
            model.paddle.x = Math.min(GameModel.WIDTH - model.paddle.width, model.paddle.x + 25);
        }
    }
}
