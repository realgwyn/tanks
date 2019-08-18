package io.tanks.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;



// TODO: saving config values back to config.properties file when exiting the game
// TODO: restoring config.properties values from default_config.properties - just copy file content and load
@Configuration
@ConfigurationProperties
@PropertySource("classpath:gameserver-config.yml")
public class ServerConfig {

	int maxPlayers;
	int maxPlayersPerTeam;
	int defaultTcpPort;
	int defaultUdpPort;
	String defaultServerName;
	int updaterate;
	boolean processScheduler;
	boolean playerPositionCorrection;
	boolean enableAntiCheat;
	boolean packetValidation;
	int pingSendingFrequency;
	int statsSendingFrequency;
	long maxChatHistorySize;
	int roundDurationSeconds;
	int matchDurationSeconds;
	int roundStartFreezeTimeDurationSeconds;
	boolean friendlyFire;
	boolean offlineDebugMode;
	boolean networkDebugMode;

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getMaxPlayersPerTeam() {
		return maxPlayersPerTeam;
	}

	public int getDefaultTcpPort() {
		return defaultTcpPort;
	}

	public int getDefaultUdpPort() {
		return defaultUdpPort;
	}

	public String getDefaultServerName() {
		return defaultServerName;
	}

	public int getUpdaterate() {
		return updaterate;
	}

	public boolean isProcessScheduler() {
		return processScheduler;
	}

	public boolean isPlayerPositionCorrection() {
		return playerPositionCorrection;
	}

	public boolean isEnableAntiCheat() {
		return enableAntiCheat;
	}

	public boolean isPacketValidation() {
		return packetValidation;
	}

	public int getPingSendingFrequency() {
		return pingSendingFrequency;
	}

	public int getStatsSendingFrequency() {
		return statsSendingFrequency;
	}

	public long getMaxChatHistorySize() {
		return maxChatHistorySize;
	}

	public int getRoundDurationSeconds() {
		return roundDurationSeconds;
	}

	public int getMatchDurationSeconds() {
		return matchDurationSeconds;
	}

	public int getRoundStartFreezeTimeDurationSeconds() {
		return roundStartFreezeTimeDurationSeconds;
	}

	public boolean isFriendlyFire() {
		return friendlyFire;
	}

	public boolean isOfflineDebugMode() {
		return offlineDebugMode;
	}

	public boolean isNetworkDebugMode() {
		return networkDebugMode;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public void setMaxPlayersPerTeam(int maxPlayersPerTeam) {
		this.maxPlayersPerTeam = maxPlayersPerTeam;
	}

	public void setDefaultTcpPort(int defaultTcpPort) {
		this.defaultTcpPort = defaultTcpPort;
	}

	public void setDefaultUdpPort(int defaultUdpPort) {
		this.defaultUdpPort = defaultUdpPort;
	}

	public void setDefaultServerName(String defaultServerName) {
		this.defaultServerName = defaultServerName;
	}

	public void setUpdaterate(int updaterate) {
		this.updaterate = updaterate;
	}

	public void setProcessScheduler(boolean processScheduler) {
		this.processScheduler = processScheduler;
	}

	public void setPlayerPositionCorrection(boolean playerPositionCorrection) {
		this.playerPositionCorrection = playerPositionCorrection;
	}

	public void setEnableAntiCheat(boolean enableAntiCheat) {
		this.enableAntiCheat = enableAntiCheat;
	}

	public void setPacketValidation(boolean packetValidation) {
		this.packetValidation = packetValidation;
	}

	public void setPingSendingFrequency(int pingSendingFrequency) {
		this.pingSendingFrequency = pingSendingFrequency;
	}

	public void setStatsSendingFrequency(int statsSendingFrequency) {
		this.statsSendingFrequency = statsSendingFrequency;
	}

	public void setMaxChatHistorySize(long maxChatHistorySize) {
		this.maxChatHistorySize = maxChatHistorySize;
	}

	public void setRoundDurationSeconds(int roundDurationSeconds) {
		this.roundDurationSeconds = roundDurationSeconds;
	}

	public void setMatchDurationSeconds(int matchDurationSeconds) {
		this.matchDurationSeconds = matchDurationSeconds;
	}

	public void setRoundStartFreezeTimeDurationSeconds(int roundStartFreezeTimeDurationSeconds) {
		this.roundStartFreezeTimeDurationSeconds = roundStartFreezeTimeDurationSeconds;
	}

	public void setFriendlyFire(boolean friendlyFire) {
		this.friendlyFire = friendlyFire;
	}

	public void setOfflineDebugMode(boolean offlineDebugMode) {
		this.offlineDebugMode = offlineDebugMode;
	}

	public void setNetworkDebugMode(boolean networkDebugMode) {
		this.networkDebugMode = networkDebugMode;
	}
}
