package com.br.aluraStickers.stickers;

import com.br.aluraStickers.model.Model;
import com.br.aluraStickers.model.imdb.ModelIMDB;
import com.br.aluraStickers.model.nasa.ModelNASA;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    private static <T extends Model> void createSticker(@NotNull T model, String text, String folder) throws IOException {

        InputStream inputStream = new URL(model.image()).openStream();

        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = (int) (image.getHeight() * 1.1);

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, (width / 20)); // --> Best proportion between figure width and font size of text;
        graphics.setColor(Color.red);
        graphics.setFont(font);

        FontMetrics fm = graphics.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        graphics.drawString(text, x, (int) (height * 0.985));

        // Drawing outline
        TextLayout t1 = new TextLayout(text, font, graphics.getFontRenderContext());
        Shape shape = t1.getOutline(null);

        graphics.translate(x, (int) (height * 0.985));
        graphics.setColor(new Color(255, 255, 255));
        graphics.setStroke(new BasicStroke((int) (width / 216))); // --> Best proportion between figure width and thickness of outline;
        graphics.draw(shape);


        File directory = new File("saida/" + folder);
        if (!directory.exists()) {
            directory.mkdir();
        }

        ImageIO.write(newImage, "png", new File("saida/" + folder + "/"
                                                        + model.title().replace(" ", "_")
                .replace(":", "") + ".png"));

    }

    public static void createStickerIMDB(ModelIMDB model) throws IOException {

        double filmeRating = model.imDbRating();
        String classificacao;

        if (filmeRating <= 8) {
            classificacao = "Mó furada pô!";
        }
        else if (filmeRating <= 8.5) {
            classificacao = "Dá pra ir...";
        }
        else {
            classificacao = "Mete bronca!!";
        }

        createSticker(model, classificacao, "imdb");
    }

    public static void createStickerNASA(ModelNASA model) throws IOException {

        createSticker(model, model.title(), "nasa");

    }
}
