package windowComponents;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import data.CONST;

public class ReferencePanel extends JPanel{
	public BufferedImage bufferedImage, contentImage;
	
	
	public ReferencePanel() {
		setPreferredSize(new Dimension(CONST.FRAME_W, CONST.FRAME_H));
		reset();
	}
	
	public void drawNew(BufferedImage image) {
		reset();
		contentImage = image;
		displayImg();
	}
	
	public void reset() {
		bufferedImage = new BufferedImage( CONST.FRAME_W, CONST.FRAME_H, BufferedImage.TYPE_INT_ARGB );
		
		Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, CONST.FRAME_W, CONST.FRAME_H);
        g.dispose();
        
        repaint();
	}
	
	public void displayImg() {
		Graphics g = bufferedImage.getGraphics();
		g.drawImage( contentImage, 
				     (CONST.FRAME_W - contentImage.getWidth())/2, 
				     (CONST.FRAME_H - contentImage.getHeight())/2, 
				     null );
		g.dispose();
		
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, null);
	}


}
