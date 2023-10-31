package de.schoko.saving.config;

import java.io.IOException;

public interface TextResourceWriter {
	public void write(String path, String data) throws IOException;
}
