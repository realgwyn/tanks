package io.tanks.server.cfg;

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
	boolean enableProcessScheduler;
	boolean enablePlayerPositionCorrection;
	boolean enableAntiCheat;
	boolean enablePacketValidation;
	int pingSendingFrequency;
	int statsSendingFrequency;
	long maxChatHistorySize;
	int roundDurationSeconds;
	int matchDurationSeconds;
	int roundStartFreezeTimeDurationSeconds;
	boolean enableFriendlyFire;
	boolean enableOfflineDebugMode;
	boolean enableNetworkDebugMode;

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

	public boolean isEnableProcessScheduler() {
		return enableProcessScheduler;
	}

	public boolean isEnablePlayerPositionCorrection() {
		return enablePlayerPositionCorrection;
	}

	public boolean isEnableAntiCheat() {
		return enableAntiCheat;
	}

	public boolean isEnablePacketValidation() {
		return enablePacketValidation;
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

	public boolean isEnableFriendlyFire() {
		return enableFriendlyFire;
	}

	public boolean isEnableOfflineDebugMode() {
		return enableOfflineDebugMode;
	}

	public boolean isEnableNetworkDebugMode() {
		return enableNetworkDebugMode;
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

	public void setEnableProcessScheduler(boolean enableProcessScheduler) {
		this.enableProcessScheduler = enableProcessScheduler;
	}

	public void setEnablePlayerPositionCorrection(boolean enablePlayerPositionCorrection) {
		this.enablePlayerPositionCorrection = enablePlayerPositionCorrection;
	}

	public void setEnableAntiCheat(boolean enableAntiCheat) {
		this.enableAntiCheat = enableAntiCheat;
	}

	public void setEnablePacketValidation(boolean enablePacketValidation) {
		this.enablePacketValidation = enablePacketValidation;
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

	public void setEnableFriendlyFire(boolean enableFriendlyFire) {
		this.enableFriendlyFire = enableFriendlyFire;
	}

	public void setEnableOfflineDebugMode(boolean enableOfflineDebugMode) {
		this.enableOfflineDebugMode = enableOfflineDebugMode;
	}

	public void setEnableNetworkDebugMode(boolean enableNetworkDebugMode) {
		this.enableNetworkDebugMode = enableNetworkDebugMode;
	}
}
