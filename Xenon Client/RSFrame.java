// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import sign.signlink;

final class RSFrame extends Frame
{

	public RSFrame(RSApplet RSApplet_, int i, int j) {
		rsApplet = RSApplet_;
		setTitle("XENON");
		setResizable(false);
		setVisible(true);
		toFront();
		setSize(i + 8, j + 28);
		setResizable(false);
		setLocationRelativeTo(null);
		Image icon = Toolkit.getDefaultToolkit().getImage(signlink.findcachedir() + "Sprites/icon.png");
		setIconImage(icon);
	}
	
	public static String currentDate() {
		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
		return simpleDate.format(currentDate);
	}

	public Graphics getGraphics()
	{
		Graphics g = super.getGraphics();
		g.translate(4, 24);
		return g;
	}

	public void update(Graphics g)
	{
		rsApplet.update(g);
	}

	public void paint(Graphics g)
	{
		rsApplet.paint(g);
	}

	private final RSApplet rsApplet;
}

