package org.game.tanks.core;

/**
 * Steady time precision loop
 * 
 * @author rafal.kojta
 */
public abstract class Loop implements Runnable{

  protected boolean runFlag = false;
  protected double UPDATE_RATE = 100;
  protected double TIME_QUANTUM = 0.01;

  /**
   * Begin the game loop
   * 
   * @param updateRate
   *          - updates per second - how often loop will run
   * 
   */
  public void run(double updateRate) {
    
    UPDATE_RATE = updateRate;
    TIME_QUANTUM = 1000 / UPDATE_RATE / 1000;
    System.out.println("Running loop with Update Rate: " + UPDATE_RATE + " ups, Time Quantum: " + TIME_QUANTUM);

    runFlag = true;

    startup();
    // convert the time to seconds
    double nextTime = System.nanoTime() / 1000000000.0;
    double maxTimeDiff = 0.5;
    int skippedFrames = 1;
    int maxSkippedFrames = 5;
    while (runFlag) {
      // convert the time to seconds
      double currTime = System.nanoTime() / 1000000000.0;
      if ((currTime - nextTime) > maxTimeDiff) {
        nextTime = currTime;
      }
      if (currTime >= nextTime) {
        // assign the time for the next update
        nextTime += TIME_QUANTUM;
        update();
        if ((currTime < nextTime) || (skippedFrames > maxSkippedFrames)) {
          render();
          skippedFrames = 1;
        } else {
          skippedFrames++;
        }
      } else {
        // calculate the time to sleep
        int sleepTime = (int) (1000.0 * (nextTime - currTime));
        // sanity check
        if (sleepTime > 0) {
          // sleep until the next update
          try {
            Thread.sleep(sleepTime);
          } catch (InterruptedException e) {
            // do nothing
          }
        }
      }
    }
    shutdown();
  }

  public void stop() {
    runFlag = false;
  }

  public abstract void startup();

  public abstract void shutdown();

  public abstract void update();

  public abstract void render();

}