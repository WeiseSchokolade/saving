package de.schoko.saving.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public enum ResourceLocation {
	/**
	 * Seperator: /
	 */
	JAR((String path) -> {
		InputStream inputStream = ResourceLocation.class.getClassLoader().getResourceAsStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		StringBuilder stringBuilder = new StringBuilder("");
		
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append('\n');
		}
		bufferedReader.close();
		
		return stringBuilder.toString();
	}),
	/**
	 * Seperator: /
	 */
	FILE((String path) -> {
		return Files.readString(Path.of(path));
	}, (String path, String data) -> {
		File file = new File(path);
		file.getParentFile().mkdirs();
		Files.writeString(file.toPath(), data);
	}),
	/**
	 * Path needs to be a url, including the protocol.
	 * I.E. http://example.com
	 */
	WEB((String path) -> {
		InputStream inputStream = new URL(path).openStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		StringBuilder stringBuilder = new StringBuilder("");
		
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append('\n');
		}
		bufferedReader.close();
		
		return stringBuilder.toString();
	});
	
	private TextResourceGetter textResourceGetter;
	private TextResourceWriter textResourceWriter;
	
	ResourceLocation(TextResourceGetter textResourceGetter) {
		this.textResourceGetter = textResourceGetter;
		this.textResourceWriter = null;
	}

	ResourceLocation(TextResourceGetter textResourceGetter, TextResourceWriter textResourceWriter) {
		this.textResourceGetter = textResourceGetter;
		this.textResourceWriter = textResourceWriter;
	}
	
	public String getTextResource(String path) throws IOException {
		return textResourceGetter.getResource(path);
	}

	public void write(String path, String data) throws IOException {
		textResourceWriter.write(path, data);
	}
	
	public boolean supportsWriting() {
		return this.textResourceWriter != null;
	}

}
