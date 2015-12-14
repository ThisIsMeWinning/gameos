package thisismewinning.github.io.gameos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MainLoop extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running;
	private BufferedImage img;
	
	private int width;
	private int height;
	
	public Graphics2D graphics2D;
	
	private int fps;
	private int ups;
	
	public static double currentFPS = 60D;

	public MainLoop(int width, int height) {
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		requestFocus();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		
	}
	
	@Override
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		double nsPerUpdate = 1000000000D / currentFPS;
		int frames = 0;
		int updates = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerUpdate;
			lastTime = now;
			boolean shouldRender = false;
			
			while(delta >= 1) {
				updates++;
				update(delta);
				delta -= 1;
				shouldRender = true;
			}
			
			if(shouldRender == true) {
				frames++;
				render();
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				ups = frames;
				fps = updates;
				//System.out.println("Updates: " + ups + " FPS: " + fps);
				frames = 0;
				updates = 0;
			}
		}
	}

	public void update(double delta) {
	}
	
	public void render() {
		graphics2D.clearRect(0, 0, width, height);
	}

	public void init() {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		graphics2D = (Graphics2D) img.getGraphics();
		running = true;
	}
	
	public void clear() {
		Graphics g2 = getGraphics();
		if(img != null) {
			g2.drawImage(img, 0, 0, null);
		}
		g2.dispose();
	}

}
