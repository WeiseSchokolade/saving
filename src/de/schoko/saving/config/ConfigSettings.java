package de.schoko.saving.config;

public class ConfigSettings {
	private static ConfigSettings globalSettings = new ConfigSettings("", "");
	
	private String baseFilePath;
	private String baseResourcePath;
	
	private ConfigSettings(String baseFilePath, String baseResourcePath) {
		this.baseFilePath = baseFilePath;
	}
	
	public static ConfigSettings getGlobal() {
		return globalSettings;
	}
	
	public static ConfigSettings getNew() {
		return new ConfigSettings("", "");
	}
	
	public String getBaseFilePath() {
		return baseFilePath;
	}
	
	public void setBaseFilePath(String baseFilePath) {
		this.baseFilePath = baseFilePath;
	}
	
	public String getBaseResourcePath() {
		return baseResourcePath;
	}
	
	public void setBaseResourcePath(String baseResourcePath) {
		this.baseResourcePath = baseResourcePath;
	}
}
