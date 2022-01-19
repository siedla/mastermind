package pl.edu.agh.to.mastermind.model.game;


public class Stopwatch {
    private boolean running=false;
    private long startTime, stopTime;

    public Stopwatch()
    {
        this.running = false;
        startTime = stopTime = 0;
    }

    public void start()
    {
        if(running) return;
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void stop()
    {
        if(!running) return;
        running = false;
        stopTime = System.currentTimeMillis();
    }

    public long getTimeElapsed()
    {
        if(running)
        {
            return System.currentTimeMillis() - startTime;
        }
        else
        {
            return stopTime - startTime;
        }
    }
}
