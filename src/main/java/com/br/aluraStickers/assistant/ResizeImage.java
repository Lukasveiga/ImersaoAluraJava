package com.br.aluraStickers.assistant;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

public class ResizeImage {

    public static BufferedImage simpleResizeImage(BufferedImage orginalImage, int targetWidth) throws Exception {
        return Scalr.resize(orginalImage, Scalr.Method.ULTRA_QUALITY ,targetWidth);
    }
}
