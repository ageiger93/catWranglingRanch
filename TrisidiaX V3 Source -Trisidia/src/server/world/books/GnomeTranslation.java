package server.world.books;
import server.model.players.Client;

public class GnomeTranslation implements Book {

        private final String NAME = "Gnome-English Translation";

        private final String[][] PAGES = new String [][] {{
                                        "Gnome-English",
                                        "Translation",
                                        "",
                                        "written by Anita",
                                        "",
                                        "This text contains the",
                                        "ancient gnome words I",
                                        "have managed to translate",
                                        "thus far.",
                                        "",
                                        "",
                                        "-A-",
                                        "Arpos: rocks",
                                        "ando: gate",
                                        "andra: city",
                                        "ataris: cow",
                                        "",
                                        "-C-",
                                        "cef: threat",
                                        "cheray: lazy",
                                        "Cinqo: King",
                                        "cretor: bucket",
                                }, {
                                        "-E-",
                                        "eis: me",
                                        "es: a",
                                        "et: and",
                                        "eto: will",
                                        "",
                                        "-G-",
                                        "gandius: jungle",
                                        "gal: all",
                                        "gentis: leaf",
                                        "gutus: banana",
                                        "gomondo: branch",
                                        "",
                                        "-H-",
                                        "har: old",
                                        "harij: harpoom",
                                        "hewo: grass",
                                        "",
                                        "-I-",
                                        "ip: you",
                                        "imindus: quest",
                                        "irno: translate",
                                }, {
                                        "",
                                        "-K-",
                                        "kar: no",
                                        "kai: boat",
                                        "ko: sail",
                                        "",
                                        "-L-",
                                        "lauf: eye",
                                        "laquinay: common sense",
                                        "lemanto: man",
                                        "lemantolly: stupid man",
                                        "lovos: gave",
                                        "",
                                        "-M-",
                                        "meso: came",
                                        "meriz: kill",
                                        "mina: time(s)",
                                        "mos: coin",
                                        "mi: I",
                                        "mond: seal",
                                        "",
                                        "-O-",
                                }, {
                                        "o: for",
                                        "",
                                        "-P-",
                                        "por: long",
                                        "prit: with",
                                        "priw: tree",
                                        "pro: to",
                                        "",
                                        "-Q-",
                                        "qui: guard",
                                        "guir: guardian",
                                        "",
                                        "-R-",
                                        "rentos: agility",
                                        "",
                                        "-S-",
                                        "sarko: begone",
                                        "sind: big",
                                        "",
                                        "-T-",
                                        "ta: the",
                                        "tuzo: open",
                                }, {
                                        "",
                                        "-U-",
                                        "undri: lands",
                                        "umesco: Soul",
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
                                        ""
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
