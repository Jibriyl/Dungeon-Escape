package com.de.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.de.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Dungeon Escape");
		config.setForegroundFPS(120);
		config.setIdleFPS(30);
		config.setWindowedMode(1280, 720);
		//config.setMaximized(true);
		new Lwjgl3Application(new Main(), config);
	}
}
