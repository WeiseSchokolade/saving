package de.schoko.saving.streaming;

public interface Saveable {
	public void save(SaveStream s);
	public void load(LoadStream s);
}
