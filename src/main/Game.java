package main;

import javax.swing.JFrame;

import Audio.AudioPlayer;

public class Game {
	
	public static void main(String[] args) {
		AudioPlayer bgmusic = new AudioPlayer("/Music/Emergence Of Talents.mp3");
		bgmusic.play();
		JFrame window = new JFrame("Naruto - Burden of Hatrated");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
	}
	
}
