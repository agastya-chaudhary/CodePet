package model;

public class Pet {
    public int health = 100;
    public boolean running = true;

    public void startDecay(Runnable updateUI) {
        new Thread(() -> {
            try {
                while (running) {
                    health -= 2;

                    if (health < 0) health = 0;

                    updateUI.run();
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void increase(String diff) {
        if (diff.equals("easy")) health += 25;
        else if (diff.equals("medium")) health += 50;
        else health += 100;

        if (health > 100) health = 100;
    }

    public void pause() {
        running = false;
    }

    public void resume(Runnable updateUI) {
        if (!running) {
            running = true;
            startDecay(updateUI);
        }
    }
}