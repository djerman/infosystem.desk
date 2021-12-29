package rs.atekom.infosystem.desk.app.pomocne;

import java.io.ByteArrayInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Slika extends ImageView{

	public Slika() {
		// TODO Auto-generated constructor stub
	}
	
	public void postavi() {
		/*prazno = generateImage(1, 1, 1, 1);//0, 177.0 / 255, 65.0 / 255
		logo = new Image("images/Atekom-Logo.png");
		try {
			file = new File("D:\\serverslike\\Atekom-Logo.png");
			System.out.println(file.getName());
			logo = new Image(new FileInputStream(file));
			}catch (Exception e) {
				e.printStackTrace();
				}
		
		try {
			logo = new Image(new FileInputStream("Atekom-Logo.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		slika = new ImageView(prazno);
		
		slika.setImage(null);
		slika.setFitWidth(100);
		slika.setFitHeight(100);
		
		slika.setPreserveRatio(false);
		*/
	}
	
	public Image generateImage(double red, double green, double blue, double opacity) {
	    WritableImage img = new WritableImage(1, 1);
	    PixelWriter pw = img.getPixelWriter();
	    Color color = Color.color(red, green, blue, opacity);
	    pw.setColor(0, 0, color);
	    return img;
	    }
	
	public byte[] slikaByte(Image slika) {
		/*
		int width = (int) slika.getWidth();
		int height = (int) slika.getHeight();
		byte[] pixelBytes = new byte[width * height * 4];
		slika.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), pixelBytes, 0, width * 4);
		return pixelBytes;*/
		
		// Cache Width and Height to 'int's (because getWidth/getHeight return Double) and getPixels needs 'int's //
		int w = (int)slika.getWidth();
		int h = (int)slika.getHeight();
		// Create a new Byte Buffer, but we'll use BGRA (1 byte for each channel) //
		byte[] buf = new byte[w * h * 4];
		/* Since you can get the output in whatever format with a WritablePixelFormat,
		 * we'll use an already created one for ease-of-use. */
		slika.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w * 4);
		/* Second last parameter is byte offset you want to start in your buffer,
		 * and the last parameter is stride (in bytes) per line for your buffer. */
		return buf;
		}
	
	public String imeSlike(Image slika) {
		try {
			String putanja = slika.getUrl();
			String[] niz = putanja.split("/");
			return niz[niz.length - 1];
			}catch (Exception e) {
				return "";
				}
		}
	
	public Image napraviSliku(byte[] niz) {
		try {
			Image slika = new Image(new ByteArrayInputStream(niz));
			return slika;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
				}
		
		}
	
	}
