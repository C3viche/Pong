import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_WIDTH) / UNIT_SIZE; //num of units on screen (14,400)
    static final int DELAY = 5;
    static final int RECTANGLE_WIDTH = 25;
    static final int RECTANGLE_HEIGHT = 75;
    static final int BALL_SPEED = 4;

    int rect1x = SCREEN_WIDTH / 12;
    int rect1y = (SCREEN_HEIGHT - RECTANGLE_HEIGHT) / 2;
    int rect2x = SCREEN_WIDTH - rect1x - RECTANGLE_WIDTH;
    int rect2y = (SCREEN_HEIGHT - RECTANGLE_HEIGHT) / 2;
    int ballSize = 25;
    double ballDir = 0;
    int ballX = (SCREEN_WIDTH - ballSize) / 2;
    int ballY = (SCREEN_HEIGHT - ballSize) / 2;
    int points;
    boolean running = false;
    Timer timer;

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        points = 0;
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void move(char d) {
        switch(d) {
            case 'W' -> rect1y -= 5;
            case 'S' -> rect1y += 5;
            case 'U' -> rect2y -= 5;
            case 'D' -> rect2y += 5;
        }
    }

    public void checkCollisions() {

    }

    public void gameOver() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            //Draws rectangle
            g.setColor(Color.WHITE);
            g.fillRect(rect1x, rect1y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
            g.fillRect(rect2x, rect2y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);

            //Draws circle
            g.setColor(Color.GREEN);
            g.fillOval(ballX, ballY, ballSize, ballSize);
        }
    }

    private void moveBall() {
        ballX += BALL_SPEED * Math.cos(ballDir);
        ballY += BALL_SPEED * Math.sin(ballDir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            moveBall();
            checkCollisions();
        }
        repaint();
    }



    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if(code == KeyEvent.VK_W)
                move('W');
            if(code == KeyEvent.VK_S)
                move('S');
            if(code == KeyEvent.VK_UP)
                move('U');
            if(code == KeyEvent.VK_DOWN)
                move('D');
            System.out.println(code);
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_W -> move('W');
//                case KeyEvent.VK_S -> move('S');
//                case KeyEvent.VK_UP -> move('U');
//                case KeyEvent.VK_DOWN -> move('D');
//            }
        }
    }
}
