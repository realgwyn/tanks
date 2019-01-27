package io.tanks.server.core.process;

public abstract class ScheduledProcess {

  protected long maxProcessTime = Long.MAX_VALUE;

  public abstract void runProcess();

  public void setMaxProcessTime(long maxProcessTime) {
    this.maxProcessTime = maxProcessTime;
  }

  public long getMaxProcessTime() {
    return maxProcessTime;
  }

}
