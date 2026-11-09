package mx.unison.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Board extends JComponent {
    private final GameModel model;
    private final GameLogic logic;

    public Board(GameModel model, GameLogic logic) {
        this.model = model;
        this.logic = logic;
        
        setPreferredSize(new Dimension(GameModel.WIDTH, GameModel.HEIGHT));
        setBackground(Color.WHITE);
        setOpaque(true);
        setFocusable(true);
        
        setupKeyBindings();
        
        new Timer(16, e -> {
            logic.update();
            repaint();
        }).start();
    }

    private void setupKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("SPACE"), "pause");
        im.put(KeyStroke.getKeyStroke("R"), "resume");
        im.put(KeyStroke.getKeyStroke("ENTER"), "restart");

        am.put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { logic.movePaddleLeft(); }
        });
        am.put("right", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { logic.movePaddleRight(); }
        });
        am.put("pause", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (model.state == GameState.RUNNING) model.state = GameState.PAUSED;
            }
        });
        am.put("resume", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (model.state == GameState.PAUSED) model.state = GameState.RUNNING;
            }
        });
        am.put("restart", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (model.state == GameState.GAME_OVER) model.reset();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        g2.setColor(Color.RED);
        g2.fill(model.ball);
        
        g2.setColor(Color.BLACK);
        g2.fill(model.paddle);
        
        if (model.state == GameState.PAUSED) {
            g2.setColor(Color.BLUE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 24));
            g2.drawString("Paused", 20, 30);
        } else if (model.state == GameState.GAME_OVER) {
            g2.setColor(Color.RED);
            g2.setFont(new Font("SansSerif", Font.BOLD, 30));
            g2.drawString("GAME OVER - Press ENTER", 120, 240);
        }
    }
}
