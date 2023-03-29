package com.br.aluraStickers.model.imdb;

import com.br.aluraStickers.assistant.ConsoleColors;
import com.br.aluraStickers.model.Model;

public record ModelIMDB(String rank, String title, String year, String image, double imDbRating) implements Model {

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
