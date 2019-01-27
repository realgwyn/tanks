package io.game.tanks.server.core.process;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.model.Command;
import io.game.tanks.network.model.command.ChangeName;
import io.game.tanks.network.model.command.Disconnect;
import io.game.tanks.network.model.command.Latency;
import io.game.tanks.network.model.command.Ping;
import io.game.tanks.network.model.command.PlayersLatency;
import io.game.tanks.server.cfg.ServerConfig;
import io.game.tanks.server.core.ServerContext;
import io.game.tanks.server.core.ServerEventBus;
import io.game.tanks.server.model.PlayerServerModel;

/**
 * Processes GameCommands
 */
@Component
public class GameCommandHandler extends ScheduledProcess {

  private final static Logger logger = Logger.getLogger(GameCommandHandler.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerEventBus bus;
  @Autowired
  SchedulerContext schedulerCtx;
  @Autowired
  ServerConfig config;

  private long currentTime;

  private int pingSendingFrequency = 100;// After how many loops Ping command should be sent again
  private int pingFrequencyCount = 0;

  private int latencyStatsSendingFrequency = 100;
  private int sendingFrequencyCount = 0;

  @PostConstruct
  public void init() {
    pingSendingFrequency = config.getPropertyInt(ServerConfig.SERVER_NETWORK_PING_SENDING_FREQUENCY);
    latencyStatsSendingFrequency = config.getPropertyInt(ServerConfig.SERVER_NETWORK_STATS_SENDING_FREQUENCY);
  }

  @Override
  public void runProcess() {
    currentTime = System.currentTimeMillis();
    while (!bus.getIncomingCommands().isEmpty()) {
      Command cmd = bus.getIncomingCommands().poll();
      if (cmd instanceof ChangeName) {
        processChangeNameCommand((ChangeName) cmd);
      } else if (cmd instanceof Disconnect) {
        processDisconnectCommand((Disconnect) cmd);
      } else if (cmd instanceof Ping) {
        processPingCommand((Ping) cmd);
      } else {
        throw new UnsupportedOperationException("Message not supported " + cmd.getClass().getSimpleName());
      }
    }

    if (schedulerCtx.getPlayers().size() > 0) {
      sendPingToPlayers();
      sendLatencyStatsToPlayers();
    }
  }

  private void sendPingToPlayers() {
    pingFrequencyCount++;
    if (pingFrequencyCount > pingSendingFrequency) {
      pingFrequencyCount = 0;

      Ping ping = new Ping()
          .setSentTime(currentTime);

      bus.getOutgoingCommands().add(ping);
    }
  }

  private void sendLatencyStatsToPlayers() {
    sendingFrequencyCount++;
    if (sendingFrequencyCount > latencyStatsSendingFrequency) {
      sendingFrequencyCount = 0;

      List<PlayerServerModel> players = schedulerCtx.getPlayers();
      Latency[] latencies = new Latency[players.size()];
      for (int i = 0; i < players.size(); i++) {
        PlayerServerModel player = players.get(i);
        latencies[i] = new Latency().setPlayerId(player.getConnectionId()).setLatency(player.getLatency());
      }
      PlayersLatency playersLatency = new PlayersLatency().setPlayersLatency(latencies);

      bus.getOutgoingCommands().add(playersLatency);
    }
  }

  private void processChangeNameCommand(ChangeName cmd) {
    if (!cmd.getNewPlayerName().isEmpty()) {
      PlayerServerModel player = schedulerCtx.getPlayerById(cmd.getPlayerId());
      if (player != null) {
        logger.debug(player + " changed name to: " + cmd.getNewPlayerName());
        player.setPlayerName(cmd.getNewPlayerName());
        bus.getOutgoingCommands().add(cmd);
      }
    }
  }

  private void processDisconnectCommand(Disconnect cmd) {
    PlayerServerModel player = schedulerCtx.getPlayerById(cmd.getPlayerId());
    if (player != null) {
      logger.debug(player + " disconnecting");
      bus.getLeavingPlayerIds().add(player.getConnectionId());
    }
  }

  // XXX: performance: vulnerable to DDoS
  private void processPingCommand(Ping ping) {
    PlayerServerModel player = schedulerCtx.getPlayerById(ping.getPlayerId());
    if (player != null) {
      int latency = (int) (currentTime - ping.getSentTime());
      player.setLatency(latency);
    }
  }

}
