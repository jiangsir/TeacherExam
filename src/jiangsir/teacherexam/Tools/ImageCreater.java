package jiangsir.teacherexam.Tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCreater {
	public enum FORMAT {
		jpg, //
		png, //
		bmp, //
		gif;//
	}

	public static void generateImage(String str, File file, Font font,
			int height, int width) throws Exception {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		// bi = g.getDeviceConfiguration().createCompatibleImage(width, height,
		// Transparency.TRANSLUCENT);
		// g = bi.createGraphics();
		// bi = g.getDeviceConfiguration().createCompatibleImage(width, height,
		// Transparency.TRANSLUCENT);
		// g = bi.createGraphics();
		// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
		// (float) 0.5));
		bi = g.getDeviceConfiguration().createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
		g.dispose();
		g = bi.createGraphics();
		// float alpha = 0; // 透明度
		// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
		// alpha));
		// g.setColor(Color.WHITE);
		// g.fillRect(0, 0, width, height);
		g.setFont(font);
		g.setColor(Color.BLACK);

		FontMetrics fm = g.getFontMetrics(font);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(str, g);
		int textWidth = (int) (rect.getWidth());
		int textHeight = (int) (rect.getHeight());

		int x = (width - textWidth) / 2;
		int y = (height - textHeight) / 2 + fm.getAscent();

		g.drawString(str, x, y);
		g.dispose();
		ImageIO.write(bi, FORMAT.png.toString(), file);
	}

	public static void main(String[] args) {
		// /Users/jiangsir/DynamicWebProjects/TeacherExam
		System.out.println(System.getProperty("user.dir"));
		String base = System.getProperty("user.dir");
		String fontpath = base + "/WebContent/Fonts";
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontpath,
					"HanaMinPlus.ttf"));
			font = font.deriveFont(72f);
			font = font.deriveFont(Font.BOLD);

			// Font font = new Font("Courier New", Font.PLAIN, 72);
			String s = "中文堃喆許功蓋酶";
			ImageCreater.generateImage(s, new File(fontpath, "abc.png"), font,
					72, 72 * s.getBytes().length / 2);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
