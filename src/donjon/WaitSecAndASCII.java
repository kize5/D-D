package donjon;

import java.util.concurrent.TimeUnit;


public class WaitSecAndASCII {
    /**
     * Permet d'ajouter du délai entre l'écriture des lignes dans la console
     */
//*********Remettre nbMillisecond à la place de 1 dans le Thead.sleep
    public static void justwaitASec(int nbMillisecond) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ignored) {}
    }

    /**
     * Permet d'ajouter du délai dans l'écriture du text dans la console
     * @param output le text à afficher dans la console
     * @param slowPrintTime le temps d'écriture en millisecond entre chaque caractère
     */
    //*********Remettre slowPrintTime à la place de 0 dans le TimeUnit
    public static void slowPrint(String output, int slowPrintTime) {
        for (int i = 0; i<output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(0);
            }
            catch (Exception ignored) {}
        }
    }


    public static String drawDungon() {
        return """
                                          .
                                          |~~
                                          |~~
                                         /L\\
                                  ,.---./LLL\\. _.--.
                                .'(|~~`/LLLLL\\` )  )`.,
                              .(`  |~~/LLLLLLL\\   )  )-. .
                             (  ( /L\\/LLLLLLLLL\\ )  )   `|~~
                            ((`_./LLL\\LLLLLLLLLL\\`.)_),.)|~~
                                /LLLLL\\.=.=.=.=|        /L\\
                                 |.=.| .-._.-. |       /LLL\\   ~'~
                                 |  [| | | | | |      /LLLLL\\
                                 |   | | | | | | _   _|] _=.|
                        ~'~      |  [| |_|_|_| || |_| |_| | |
                                 |  |~~        |=.=.=.=.=.| |       .
                                 |  |~~        |    |~~   | |       |~~
                                 | /L\\ .-._.-. |    |~~   | |       |~~
                                 |/LLL\\| | | | |   /L\\    |/       /L\\
                                 |].=.|_ | _ | _  /LLL\\   |       /LLL\\
                           ,- _--|]] [| |_| |_| |/LLLLL\\  |      /LLLLL\\
                          (|_| |_|]---|.=.=.=.=./LLLLLLL\\ _   _ /LLLLLLL\\
                           \\.=.=.=|\\_/           |.=.=.|_| |_| |_|.=.=.|
                           /|[]   |              | []  |.=.=.=.=.|  [] |
                           ||     |    .-._.-.   |     | .-----. |     |
                           \\|     |    | | | |   |     |/|||||||\\|     |
                            |  [] |    | | | |   |     ]|||||||||[     |
                            |  __ |    |_|_|_|   |  [] ]|||| ||||[ []  |
                            | /<_\\_    ____      |     ]|||| ||||[     |
                            |/ |  "\\__/  ) \\.-.  |     ]|?=||||||[     |_
                           /"  )\\_ >  ) >\\__ ")`\\_     ]|||||||||[ ,_./`.\\
                        __/ _/ _ ,| \\  __  "|_  ) |_   ]|||||||||[/("_ -">\\_
                       /> )"__/ \\___  "  \\__  _) \\_ -\\_.==___===/.<  \\__(\\_ \\
                      /  __/ )___   > \\_ ) \\  \\_ "  ).==_____==( <."/ (_<  \\)|
                     lc_/>.=__.._\\"__\\_  >_)___\\-_/.=________=/___/.__>__"(__/     \n
                """;
    }

    public static String drawDadDonjon() {
        return """

                  _               _       _             _            \s
                 | |             | |     | |           (_)           \s
                 | |__   __ _  __| |   __| | ___  _ __  _  ___  _ __ \s
                 | '_ \\ / _` |/ _` |  / _` |/ _ \\| '_ \\| |/ _ \\| '_ \\\s
                 | |_) | (_| | (_| | | (_| | (_) | | | | | (_) | | | |
                 |_.__/ \\__,_|\\__,_|  \\__,_|\\___/|_| |_| |\\___/|_| |_|
                                                      _/ |           \s
                                                     |__/            \s
                """;
    }

    public static String drawWarrior() {
        return """
                  ,^.
                  |||
                  |||       _T_
                  |||   .-.[:|:].-.
                  ===_ /\\|  "'"  |/
                   E]_|\\/ \\--|-|''''|
                   O  `'  '=[:]| A  |
                          /""\""|  P |
                         /""\"""`.__.'
                        []"/""\"\\"[]
                        | \\     / |
                        | |      | |
                   <\\\\\\)      (///> \n
                """;
    }

    public static String drawMage() {
        return """                             
                                                 _,'/_   |
                                                 `(")' ,'/
                                              _ _,---./ /
                                              \\_\\_\\.   /
                                                )" |  (
                                             __/   H   \\__
                                             \\    /|\\    /
                                              `--'|||`--'
                                                 ==^== \n
                """;
    }

    public static String drawMurloc() {
        return """
                               _   _
                              (.)_(.)
                           _ (   _   ) _
                          / \\/`-----'\\/ \\
                        __\\ ( (     ) ) /__
                        )   /\\ \\._./ /\\   (
                         )_/ /|\\   /|\\ \\_( \n
                """;
    }
    public static String drawSword() {
        return """
                                           ___
                                          ( ((
                                           ) ))
                  .::.                    / /(
                 'M .-;-.-.-.-.-.-.-.-.-/| ((:::::::::::::::::::::::::::::::::::::::::::::::::::::.._
                (V ( ( ( ( ( ( ( ( ( ( ( |  ))   -=============================================-    _.>
                 `P `-;-`-`-`-`-`-`-`-`-\\| ((:::::::::::::::::::::::::::::::::::::::::::::::::::::''
                  `::'                    \\ \\(
                                           ) ))
                                          (_(( \n
                """;
    }
    public static String drawLightningBlot() {
        return """
                                __________________
                                   '\\      \\
                                     \\      \\'
                                      \\       \\
                                     \\Z\\    ___\\
                                      \\   \\'
                                      __\\   \\
                                      \\      \\
                                       \\    __\\'
                                        \\  \\
                                          \\ \\
                                           \\\\'
                                     '       \\
                                              \\ \n
                """;
    }
    public static String drawFireBall() {
        return """
⠀⠀⠀⠀⠀⠀⢠⣤⡄⠀⠀⠀⠀⠀⠀⠀⠠⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢀⣀⣀⣈⠉⠁⠀⣀⣀⡀⠀⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢸⣿⠇⣿⣧⠀⢸⣿⣿⣧⠀⠀⠀⠀⠀⣿⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⡀⠀⣠⣿⣿⣦⣈⣻⣿⠏⢰⣶⠀⠀⣰⡟⠉⠙⠻⢶⣄⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠈⠛⠛⠉⣾⠏⠉⠉⠁⠀⢀⣁⣤⡾⠋⠀⠀⠀⠀⠀⠻⣧⠀⠀⠀⠀⠀
⠀⡀⠀⠀⠀⣀⡼⢿⣄⠀⠀⠀⠀⠀⢹⣧⠀⣠⣶⣶⣦⣄⠀⠀⣿⠀⠀⠀⠀⠀
⠀⠛⠛⠛⠛⠋⠀⠀⢹⣆⠀⠈⠻⠾⠿⠋⠀⠻⣿⡿⠀⢻⡇⠀⣿⡀⠀⠀⠀⠀
⠀⣤⣄⠀⢰⣿⣿⣷⣸⡟⠀⣾⣦⣤⡀⠀⢠⣤⡀⠀⠀⣼⣧⡀⢻⡇⠀⠀⠀⠀
⠀⠀⠙⣷⡈⠻⠿⠿⠛⠁⢀⣿⠿⢿⣿⠀⠈⠉⠀⠐⣾⠋⠛⠃⠈⣿⡀⠀⠀⠀
⠀⠀⠀⠘⣷⡀⠀⠀⠀⠀⢸⡇⠀⠘⠋⠀⢠⣤⣤⣤⣿⣿⣷⣦⢀⣿⣿⣄⠀⠀
⠀⠀⠀⠀⠘⢷⣤⣀⠀⠀⠸⣇⠀⠀⠀⠀⠈⠉⠉⠻⣿⣿⣿⣧⣾⣿⣿⣿⡆⠀
⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠛⠻⣆⠀⠀⢀⣾⣿⣿⡆⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⠶⣦⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⠛⠉⠁ \n
                """;
    }
    public static String drawSmallPotion() {
        return """
                   _____
                  `.___,'
                   (___)
                   <   >
                    ) (
                   /`-.\\
                  /     \\
                 / _    _\\
                :,' `-.' `:
                |         |
                :  Small  ;
                 \\      /
                  `.___.' \n
                """;
    }
    public static String drawMediumPotion() {
        return """
                   _____
                  `.___,'
                   (___)
                   <   >
                    ) (
                   /`-.\\
                  /     \\
                 / _    _\\
                :,' `-.' `:
                |         |
                :  Medium ;
                 \\      /
                  `.___.' \n
                """;
    }
    public static String drawLargePotion() {
        return """
                   _____
                  `.___,'
                   (___)
                   <   >
                    ) (
                   /`-.\\
                  /     \\
                 / _    _\\
                :,' `-.' `:
                |         |
                :  Large  ;
                 \\      /
                  `.___.' \n
                """;
    }
}
