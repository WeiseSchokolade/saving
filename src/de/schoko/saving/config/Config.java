package de.schoko.saving.config;

import java.io.IOException;
import java.util.HashMap;

public class Config {
	private ConfigSettings settings;
	private HashMap<String, String> values;
	private boolean changed;

	public Config(String data) {
		this.settings = ConfigSettings.getGlobal();
		values = new HashMap<>();
		load(data);
	}
	
	public Config(ConfigSettings settings, String data) {
		this.settings = settings;
		values = new HashMap<>();
		load(data);
	}
	
	public static Config getConfig(String path, ResourceLocation location) {
		try {
			String data = location.getTextResource(getPath(ConfigSettings.getGlobal(), path, location));
			return new Config(data);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Config getConfig(String path, ResourceLocation location, String defaultPath, ResourceLocation defaultLocation) {
		try {
			String data = location.getTextResource(getPath(ConfigSettings.getGlobal(), path, location));
			return new Config(data);
		} catch (IOException e) {
			return getConfig(defaultPath, defaultLocation);
		}
	}
	
	private void load(String data) {
		values.clear();
		String[] lines = data.split("\n");
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.isBlank()) continue;
			String[] pair = line.split(":", 2);
			if (pair.length == 2) {
				 values.put(pair[0], pair[1].trim());
			} else {
				continue;
			}
		}
	}
	
	public boolean write(String path, ResourceLocation location) throws IOException {
		if (!location.supportsWriting()) {
			throw new IllegalArgumentException("Location " + location + " doesn't support writing!");
		}
		location.write(getPath(settings, path, location), serialize());
		changed = false;
		return true;
	}
	
	private String serialize() {
		StringBuilder builder = new StringBuilder();
		values.forEach((key, value) -> {
			builder.append(key + ": " + value + "\n");
		});
		return builder.toString();
	}

	private static String getPath(ConfigSettings settings, String path, ResourceLocation location) {
		if (location == ResourceLocation.FILE) {
			path = settings.getBaseFilePath() + path;
		}
		if (location == ResourceLocation.JAR) {
			path = settings.getBaseResourcePath() + path;
		}
		return path;
	}
	
	public void set(String key, String value) {
		if (values.get(key) != value) changed = true;
		values.put(key, value);
	}
	
	public String get(String key) {
		return values.get(key);
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
}
