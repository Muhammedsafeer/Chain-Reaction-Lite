package main;

public class GameLoop implements Runnable{

    GamePanel gp;

    Thread gameThread;

    public final int maxFPS = 60;
    public final int maxUPS = 60;

    public GameLoop(GamePanel gp) {
        this.gp = gp;
    }
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / maxFPS;
        double timePerUpdate = 1000000000.0 / maxUPS;


        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                gp.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gp.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
