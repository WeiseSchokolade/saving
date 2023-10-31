package de.schoko.saving.streaming;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SavingManager {
	private String path;
	private List<Saveable> saveables;

	public SavingManager(String path) {
		this.path = path;
		this.saveables = new ArrayList<>();
	}

	public void addSaveable(Saveable s) {
		this.saveables.add(s);
	}

	/**
	 * @return Whether all saveables could be saved
	 */
	public boolean save() {
		try {
			SaveStream s = new SaveStream(path);
			for (int i = 0; i < saveables.size(); i++) {
				Saveable saveable = saveables.get(i);
				saveable.save(s);
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean load() {
		try {
			LoadStream s = new LoadStream(path);
			for (int i = 0; i < saveables.size(); i++) {
				Saveable saveable = saveables.get(i);
				saveable.load(s);
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean load(InputStream inputStream) {
		try {
			LoadStream s = new LoadStream(inputStream);
			for (int i = 0; i < saveables.size(); i++) {
				Saveable saveable = saveables.get(i);
				saveable.load(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
