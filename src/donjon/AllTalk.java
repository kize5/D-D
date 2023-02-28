package donjon;

import java.util.Objects;
import java.util.Scanner;

import static donjon.WaitSecAndASCII.*;
import static donjon.WaitSecAndASCII.slowPrint;

public class AllTalk {
    public static void entrence(Scanner scanner) {
        justwaitASec();
        slowPrintForAscii(WaitSecAndASCII.drawDungon());
        justwaitASec();
        slowPrint("Te voilà face à un terrifiant donjon, de nuit, il pleut, tu es mouillé, il fait froid, tu es fatigué, tu entends les loups crier au loin, bref, bonne ambiance ... \n");
        justwaitASec();
        slowPrint("La porte du donjon s'ouvre d'elle même sous tes yeux, pour entrer, appuie sur A \n");
        slowPrint("Pour chercher une autre ouverture appuie sur 'B' \n");
        String input = scanner.nextLine();// Read user input
        if (Objects.equals(input, "a")) {
            justwaitASec();
            slowPrint("Tu avances et passe la massive porte de bois, rien, juste un grand hall, personne n'est là, c'est bon signe ça ? \n");
            justwaitASec();
            slowPrint("Mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce sombre devant toi. \n");
        } else {
            justwaitASec();
            slowPrint("Tu te faufiles sur le côté du donjon, et trouve une petite ouverture, tu t'y glisses, il fait noir, tu rampes dans une matière inconnue, mais ça sent mauvais, très mauvais, c'est sûrement les égouts du donjon ... \n");
            justwaitASec();
            slowPrint("Après un petit temps, tu sors enfin dans une petite pièce, ouvre la porte de cette dernière, qui donne sur ... le hall d'entré, dont la porte est toujours ouverte pour toi visiblement. \n");
            justwaitASec();
            slowPrint("Tu sens franchement mauvais, mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce face à toi. \n");
        }
    }
    }

