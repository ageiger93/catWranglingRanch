package server.world.books;

import server.model.players.Client;

public interface Book {
        public String[][] getPages();
        public String getName();
        public void openBook(Client c);
}
