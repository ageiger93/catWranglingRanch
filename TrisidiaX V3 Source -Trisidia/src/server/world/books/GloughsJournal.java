package server.world.books;

import server.Config;
import server.model.players.Client;

public class GloughsJournal implements Book {

        private final String NAME = "Glough's Journal";

        private final String[][] PAGES = new String [][] {{
                                                "@red@The migration failed!",
                                                "",
                                                "After spending half a",
                                                "century hiding",
                                                "underground you would",
                                                "think that the great",
                                                "migration would have",
                                                "improved life on",
                                                Config.SERVER_NAME + " for tree",
                                                "gnomes. However, rather",
                                                "that the great liberation",
                                                "promised to us by King",
                                                "Healthorg at the end of",
                                                "the last age, we have been",
                                                "forced to live in hiding,",
                                                "up trees or in the gnome",
                                                "maze, laughed at and",
                                                "mocked by man. Living in",
                                                "constant fear of human",
                                                "aggression, we are in a",
                                                "no better situation now",
                                                "than when we lived in the"
                                        }, {
                                                "caves! Change must come",
                                                "soon!",
                                                "",
                                                "@red@They must be stopped!",
                                                "",
                                                "Today I heard of three",
                                                "more gnomes slain by",
                                                "Khazard's human troops",
                                                "for fun. I can't control",
                                                "my anger! Humanity",
                                                "seems to have acquired a",
                                                "level of arrogance",
                                                "comparable to that of",
                                                "Zamorak, killing and",
                                                "pillaging at will! We are",
                                                "small and at heart not",
                                                "warriors but something",
                                                "must be done! We will",
                                                "pick up arms and go",
                                                "forth into the human",
                                                "world! We will defend",
                                                "ourselves and we will"
                                        }, {
                                                "persue justice for all",
                                                "gnomes who fell at the",
                                                "hands of humans!",
                                                "",
                                                "@red@Gaining support.",
                                                "",
                                                "Some of the local gnomes",
                                                "seem strangely deluded",
                                                "about humans, many",
                                                "actually believe that",
                                                "humans are not all",
                                                "naturally evil but instead",
                                                "vary from person to",
                                                "person. This sort of talk",
                                                "could be the end for the",
                                                "tree gnomes and I must",
                                                "continue to convince my",
                                                "fellow gnome folk the cold",
                                                "truth about these human",
                                                "creatures! How they will",
                                                "not stop until all gnome",
                                                "life is destroyed! Unless"
                                        }, {
                                                "we can destroy them",
                                                "first!",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                                "",
                                        }};

        @Override
        public String getName() {
                return NAME;
        }
        public String[][] getPages() {
                return PAGES;
        }
        public void openBook(Client c) {
                c.bookPages = getPages();
                c.bookPage = 0;
                c.maxPages = getPages().length-1;
                c.bookName = getName();
                c.getPA().sendBook(c.bookName, c.bookPages[0]);
        }
}
