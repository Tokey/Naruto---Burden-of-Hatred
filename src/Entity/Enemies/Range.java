package Entity.Enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Kunai;
import tileMap.TileMap;

public class Range extends Kunai{

	public Range(TileMap tm, boolean right) {
		super(tm, right);
		facingRight = right;
		
		moveSpeed = 23.8;
		if(right) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 100;
		height = 100;
		cwidth = 20;
		cheight = 50;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/Ranged 100x100.png"
				)
			);
			BufferedImage spr2 = ImageIO.read(
					getClass().getResourceAsStream(
						"/Sprites/Player/items 100x100.png"
					)
				);
			
			
			
			//loading sprites
			try {
				BufferedImage spritesheet1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Sasuke 100x100.png"));
				
				sprites = new BufferedImage[5];
				
				for(int i = 0; i < sprites.length;i++){
					sprites[i] = spritesheet1.getSubimage(i*width,4*height,width,height);
				}
				animation = new Animation();
				animation.setFrames(sprites);
				animation.setDelay(100);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
