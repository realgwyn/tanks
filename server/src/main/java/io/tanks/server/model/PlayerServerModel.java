package io.tanks.server.model;

import io.tanks.common.network.ConnectionInfo;
import io.tanks.common.network.model.command.PlayerInfo;
import io.tanks.common.network.model.game.PlayerModel;
import io.tanks.common.network.state.PlayerState;

public class PlayerServerModel {

  private ConnectionInfo connectionInfo;
  private PlayerModel model;
  private PlayerInfo playerInfo;

  public long sequenceNumber;
  public boolean sequenceFlipFlag;

  private boolean connectionEstablished;

  public PlayerServerModel(ConnectionInfo connectionInfo, PlayerInfo playerInfo) {
    this.connectionInfo = connectionInfo;
    this.playerInfo = playerInfo;
    this.model = new PlayerModel();
  }

  public PlayerInfo getPlayerInfo() {
    return playerInfo;
  }

  public int getConnectionId() {
    return connectionInfo.getConnectionId();
  }

  public String getPlayerName() {
    return playerInfo.getPlayerName();
  }

  public PlayerServerModel setPlayerName(String playerName) {
    playerInfo.setPlayerName(playerName);
    return this;
  }

  public int getKills() {
    return playerInfo.getKills();
  }

  public PlayerServerModel incrementKills() {
    playerInfo.incrementKills();
    return this;
  }

  public PlayerServerModel setKills(int kills) {
    playerInfo.setKills(kills);
    return this;
  }

  public PlayerServerModel incrementDeaths() {
    playerInfo.incrementDeaths();
    return this;
  }

  public int getDeaths() {
    return playerInfo.getDeaths();
  }

  public PlayerServerModel setDeaths(int deaths) {
    playerInfo.setDeaths(deaths);
    return this;
  }

  public PlayerState getState() {
    return playerInfo.getState();
  }

  public PlayerServerModel setState(PlayerState state) {
    playerInfo.setState(state);
    return this;
  }

  public int getLatency() {
    return playerInfo.getLatency();
  }

  public PlayerServerModel setLatency(int latency) {
    playerInfo.setLatency(latency);
    return this;
  }

  public float getHealth() {
    return playerInfo.getHealth();
  }

  public PlayerServerModel setHealth(float health) {
    playerInfo.setHealth(health);
    return this;
  }

  public PlayerServerModel appendHealth(float health) {
    playerInfo.appendHealth(health);
    return this;
  }

  public long getSequenceNumber() {
    return sequenceNumber;
  }

  public PlayerServerModel setSequenceNumber(long sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    return this;
  }

  public boolean isConnectionEstablished() {
    return connectionEstablished;
  }

  public PlayerServerModel setConnectionEstablished(boolean connectionEstablished) {
    this.connectionEstablished = connectionEstablished;
    return this;
  }

  public PlayerModel getModel() {
    return model;
  }

  public PlayerServerModel setModel(PlayerModel model) {
    this.model = model;
    return this;
  }

  public ConnectionInfo getConnectionInfo() {
    return connectionInfo;
  }

  public void setConnectionInfo(ConnectionInfo connectionInfo) {
    this.connectionInfo = connectionInfo;
  }

  public int getTeam() {
    return playerInfo.getTeam();
  }

  public PlayerServerModel setTeam(int team) {
    playerInfo.setTeam(team);
    return this;
  }

  @Override
  public String toString() {
    return "Player [id:" + connectionInfo.getConnectionId() + ", name:" + playerInfo.getPlayerName() + "]";
  }

}
