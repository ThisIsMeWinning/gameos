package thisismewinning.github.io.gameos;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	boolean fse = false;
	int fullscreen = 0;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1];
	public GameWindow(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void setfullscreen() {
		switch(fullscreen){
		case 0:
			System.out.println("No fullscreen");
			setUndecorated(false);
			break;
		case 1:
			setUndecorated(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			break;
		case 2:
			setUndecorated(true);
			device.setFullScreenWindow(this);
			break;
		}
	}
	
	public void setFullscreen(int fsnm) {
		fse = true;
		if(fullscreen <= 2) {
			this.fullscreen = fsnm;
			setfullscreen();
		} else {
			System.err.println("Error " + fsnm +" is not supported!");
		}
	}

}
