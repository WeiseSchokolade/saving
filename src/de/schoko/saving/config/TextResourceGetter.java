package de.schoko.saving.config;

import java.io.IOException;

interface TextResourceGetter {
	String getResource(String path) throws IOException;
}