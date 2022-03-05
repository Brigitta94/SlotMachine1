import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
    public class Main {

        static int buget;
        static int bugetInitial;
        static int miza;

        public static void main(String[] args) {

            System.out.println("Buna ziua si bine ati venit! Vreti sa jucati acest joc? Raspundeti cu da sau nu.");
            Scanner cititor = new Scanner(System.in);
            Scanner cititorInt = new Scanner(System.in);
            String raspuns = cititor.nextLine();

            if (raspuns.equalsIgnoreCase("nu")) {
                System.out.println("Te asteptam alta data pe la noi :)");
            } else if (raspuns.equalsIgnoreCase("da")) {
                System.out.println("Incepem jocul, te rog sa introduci bugetul disponibil. Bugetul maxim este 1000");
                buget = cititorInt.nextInt();
                while (buget <= 0 || buget >= 1000) {
                    if (Main.buget <= 0) {
                        System.out.println("Nu ai introdus un buget corect. Va rog sa introduceti un buget inte 1 si 1000");
                        buget = cititorInt.nextInt();
                    } else if (Main.buget > 1000) {
                        System.out.println("Ai introdus un buget mai mare decat 1000. Te rog sa introduci unul mai mic.");
                        buget = cititorInt.nextInt();
                    }
                }
                while (buget > 0 &&buget<1000) {
                    startJoc();
                }
            } else {
                System.out.println("Nu ati introdus un raspuns valid, jocul se va termina!");
            }
        }


        public static void startJoc() {
            Scanner cititorInt = new Scanner(System.in);
            while (Main.buget > 0 && Main.buget < 1000) {
                System.out.println("Te rog sa introduci miza");
                Main.miza = cititorInt.nextInt();
                if (miza <= 0) {
                    System.out.println("Ai introdus o miza mai mica sau egala decat 0 ceea ce nu se poate. Te rog sa introduci o miza valida ");
                } else if (miza > Main.buget) {
                    System.out.println("Miza este mai mare decat bugetul disponibil. Te rog sa introduci o miza valida.");
                } else {
                    apasaButonul();
                }
            }
        }

        public static void apasaButonul() {
            Random random = new Random();
            Scanner cititor = new Scanner(System.in);
            ArrayList<String> posibilitati = new ArrayList<>(Arrays.asList("CIREASA", "MAR", "PRUNA"));
            ArrayList<String> afisare = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                afisare.add(posibilitati.get(random.nextInt(4 - 1)));
            }
            System.out.print("Am apasat butonul, pe ecran este afisat: ");
            Main.buget -= miza;
            for (String afis : afisare) {
                System.out.print("[" + afis + "]" + " ");
            }
            System.out.println();
            if (afisare.get(0).equalsIgnoreCase(afisare.get(1)) && afisare.get(1).equalsIgnoreCase(afisare.get(2))) {
                System.out.println("JACKPOT, ati castigat miza dublata. Doriti sa dublati castigul? Raspundeti cu da sau nu.");
                String raspuns1 = cititor.nextLine();
                if (raspuns1.equalsIgnoreCase("da")) {
                    bugetInitial = buget;
                    dubleazaMiza();
                } else if ((raspuns1.equalsIgnoreCase("nu"))) {
                    buget += miza * 2;
                    System.out.println("Ai ales sa nu dublezi, bugetul tau este de " + buget+".");
                    castigFinal();
                } else {
                    buget += (miza * 2);
                    System.out.println("Nu ai introdus un raspuns corect, castigul nu va fi dublat. Bugetul tau este de: " + buget+".");
                    castigFinal();
                }

            } else if (afisare.get(0).equalsIgnoreCase(afisare.get(1)) || afisare.get(0).equalsIgnoreCase(afisare.get(2))) {
                System.out.println("SEMI-JACKPOT Ai castigat 50 la suta din miza. Doresti sa iti dublezi castigul? Raspunde cu da sau nu.");
                String raspuns = cititor.nextLine();
                miza /= 2;
                if (raspuns.equalsIgnoreCase("da")) {
                    bugetInitial = buget;
                    dubleazaMiza();
                } else if ((raspuns.equalsIgnoreCase("nu"))) {
                    buget =buget + miza;
                    System.out.println("Ai ales sa nu dublezi, bugetul tau este de " + buget);
                    castigFinal();
                } else {
                    buget += miza;
                    System.out.println("Nu ai introdus un raspuns corect, miza nu va fi dublata. Bugetul tau este de: " + buget);
                    castigFinal();
                }
            } else {
                jocPierdut();
            }
        }

        public static void dubleazaMiza() {
            Scanner cititor = new Scanner(System.in);
            Random random = new Random();
            String[] posibilitati = {"rosu", "negru"};
            ArrayList<String> castig = new ArrayList<>();
            castig.add(posibilitati[random.nextInt(3 - 1)]);
            System.out.println("Te rog sa alege: rosu sau negru. Daca alegi altceva vei pierde miza");
            String raspuns = cititor.nextLine();
            System.out.println("Ai ales " + raspuns + " iar jocul a ales " + castig.get(0));
            if (raspuns.equalsIgnoreCase(castig.get(0))) {
                miza *= 2;
                buget += miza;
                System.out.println("Ai reusit sa iti dublezi miza, bugetul actual este de: " + buget + " iar miza este " + miza + ". Doresti sa dublezi din nou?");
                String raspuns1 = cititor.nextLine();
                if (raspuns1.equalsIgnoreCase("da")) {
                    dubleazaMiza();
                } else if(raspuns1.equalsIgnoreCase("nu")){
                    System.out.println("Ai ales sa nu iti dublezi miza, bugetul actual este de: " + buget);
                    castigFinal();
                }else{
                    System.out.println("Nu ai introdus un raspuns corect, miza nu va fi dublata. Bugetul tau este de: " + buget);
                    castigFinal();
                }
            }else{
                buget = bugetInitial;
                jocPierdutDublareMiza();
            }
        }
        private static void jocPierdut() {
            if (buget <= 0) {
                System.out.println("Ai pierdut, bugetul tau este: " + buget);
                System.out.println("ATI PIERDUT JOCUL");
            } else {
                System.out.println("Ai pierdut, bugetul tau este: " + buget);
            }
        }

        private static void jocPierdutDublareMiza() {
            if (buget <= 0) {
                System.out.println("Ai pierdut, bugetul tau este: " + bugetInitial);
                System.out.println("ATI PIERDUT JOCUL");
            } else if (buget>1000) {
                System.out.println("Bugetul dumneavoastra a depasit pragul de 10000. FELICITARI! ATI CASTIGAT JOCUL");
            }else{
                System.out.println("Ai pierdut, bugetul tau este: " + bugetInitial);
            }
        }
        public static void castigFinal(){
            if (buget>=1000){
                System.out.println("AFGDFF");
            }
        }
    }


