package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Poof {
	
	private int x;
	private int y;
	private int xmap;
	private int ymap;
	
	private int width;
	private int height;
	
	private Animation animation;
	private BufferedImage[] sprits;
	
	private boolean remove;
	
	public Poof(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		width = 150;
		height = 150;
		
		try {
			
			BufferedImage spriteshet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/items 150x150.png"
				)
			);
			
			sprits = new BufferedImage[4];
			
			sprits[0] = spriteshet.getSubimage(
					0,
					900,
					width,
					height
				);
				sprits[1] = spriteshet.getSubimage(
						150,
						900,
						width,
						height
					);
				sprits[2] = spriteshet.getSubimage(
						300,
						900,
						width,
						height
					);
				sprits[3] = spriteshet.getSubimage(
						450,
						900,
						width,
						height
					);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("fasfsfsf");
		animation = new Animation();
		animation.setFrames(sprits);
		animation.setDelay(70);
		
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) {
			remove = true;
		}
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void setMapPosition(int d, int e) {
		xmap = d;
		ymap = e;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(
			animation.getImage(),
			x + xmap - width / 2,
			y + ymap - height / 2,
			null
		);
	}
	
}

















