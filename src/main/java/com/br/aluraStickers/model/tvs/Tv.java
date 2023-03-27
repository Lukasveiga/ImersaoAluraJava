package com.br.aluraStickers.model.tvs;

import com.br.aluraStickers.assistant.ConsoleColors;

public class Tv {

    private String rank;
    private String title;
    private String year;
    private String image;
    private double imDbRating;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(double imDbRating) {
        this.imDbRating = imDbRating;
    }

    @Override
    public String toString() {
        return """
                Rank:  %s
                Year:  %s
                Title: %s
                IMDB Rating: %s
                """.formatted(customVar(this.rank), customVar(this.year), customVar(this.title),
                              (ConsoleColors.YELLOW + "* ".repeat(((int) this.imDbRating)) + ConsoleColors.RESET +
                                      " (" + this.imDbRating + ")"));
    }

    private static String customVar(String var) {
        return ConsoleColors.GREEN_UNDERLINED + var + ConsoleColors.RESET;
    }
}
