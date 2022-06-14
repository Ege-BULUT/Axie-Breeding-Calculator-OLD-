import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    static Tools tool = new Tools();
    static Info kurlar = new Info();
    static AxieOperations ao = new AxieOperations();

    static LinkedList<Axie> AxieList = new LinkedList<Axie>();
    static LinkedList<Axie> tempAxieList = new LinkedList<Axie>();

    public static void main(String[] args) {
        System.out.println("\nBugün itibari ile");
        System.out.println("1 USD = ? TRY : ");
        kurlar.setUSD_TRY(scan.nextDouble());
         System.out.println("1 AXS = ? USD : ");
        kurlar.setAXS_USD(scan.nextDouble());
        System.out.println("1 SLP = ? TRY : ");
        kurlar.setSLP_TRY(scan.nextDouble());
        System.out.println("1 SLP = ? USD : ");
        kurlar.setSLP_USD(scan.nextDouble());
        System.out.println("\nBilgilendirme için teşekkürler\n(USD/TRY:"+kurlar.USD_TRY+", SLP/TRY:"+kurlar.SLP_TRY+", SLP/USD:"+kurlar.SLP_USD+", AXS/USD:"+kurlar.AXS_USD+", AXS/TRY:"+kurlar.AXS_TRY+")\n");
        menu();
    }

    public static void menu() {

        System.out.println("\nMENU\n");
        System.out.println( "1. SLP işlemleri\n" +
                            "2. Breeding işlemleri\n" +
                            "3. Çıkış\n");
        switch (scan.nextInt()) {
            case 1:
                System.out.println("\nBurası henüz yapım aşamasındadır!\n");
                menu();
                //Eklenecek
                break;
            case 2:
                breedingMenu();
                break;
            case 3:
                System.exit(4);
                break;
            default:
                System.out.println("\n### Yanlış bir tuşlama yaptınız! \n\n");
                menu();
                break;
        }

    }

    public static void breedingMenu() {
        if (AxieList.size() < 1) {
            breeding();
        } else {

            System.out.println("\nBreeding İşlemleri Menüsü\n(Hali hazırda [" + AxieList.size() + "] adet axie kaydı mevcut.)\n");
            System.out.println( "1. Axie Listesini getir\n" +
                                "2. Yeni Breeding işlemi\n" +
                                "3. Ana menüye dön\n");
            switch (scan.nextInt()) {
                case 1:
                    scan.nextLine(); //Dummy
                    int maxNameLength = 0;
                    System.out.println("\nn. Axie : (Name, Price(usd), Pure)");
                    for (int index = 0; index < AxieList.size(); index++) {
                        if (AxieList.get(index).NAME.length() > maxNameLength) {
                            maxNameLength = AxieList.get(index).NAME.length();
                        }
                    }
                    for (int index = 0; index < AxieList.size(); index++) {
                        Axie tempAxie = AxieList.get(index);
                        System.out.print("\n" + (index + 1) + ". Axie :");
                        tool.writeAndAddFixedSpace(tempAxie.NAME, maxNameLength + 3, "");
                        tool.writeAndAddFixedSpace(tempAxie.USD_PRICE + "usd ", 12, "");
                        tool.writeAndAddFixedSpace(tempAxie.Pure + "", 6, "");
                    }
                    System.out.println("\nDüzenlemek istediğin axie'nin numarasını gir.\n(-1. geri)\n");
                    int tempIndex = scan.nextInt() - 1;
                    if (tempIndex < 0) {
                        breedingMenu();
                    } else {
                        System.out.println("\n");
                        tool.writeAndAddFixedSpace(AxieList.get(tempIndex).NAME, maxNameLength + 3, "");
                        tool.writeAndAddFixedSpace(AxieList.get(tempIndex).USD_PRICE + "slp ", 12, "");
                        tool.writeAndAddFixedSpace(AxieList.get(tempIndex).Pure + "", 6, "");
                        System.out.println("\n1-> Adını düzenle\n2-> Fiyatını değiştir\n3-> Pureluk değiştir\n4-> başka axie seç\n-1-> geri\n");
                        switch (scan.nextInt()) {
                            case -1:
                                breedingMenu();
                                break;
                            case 1:
                                scan.nextLine();//dummy
                                System.out.println("\n[" + AxieList.get(tempIndex).NAME + "] için yeni isim gir : ");
                                AxieList.get(tempIndex).NAME = scan.nextLine();
                                System.out.println("İsim başarıyla değiştirildi.");
                                breedingMenu();
                                break;
                            case 2:
                                scan.nextLine();//dummy
                                System.out.println("\n[" + AxieList.get(tempIndex).USD_PRICE + "USD] yerine yeni fiyat gir :\n" +
                                        "(FIYAT [boşluk] USD/SLP/TRY ");
                                AxieList.get(tempIndex).calculatePrice(scan.nextLine().split(" "));
                                System.out.println("Fiyat başarıyla değiştirildi.");
                                breedingMenu();
                                break;
                            case 3:
                                scan.nextLine();//dummy
                                System.out.println("\n[" + AxieList.get(tempIndex).NAME + "] için yeni pureluk gir(D/Y) : ");
                                switch (scan.nextLine()) {
                                    case "D":
                                    case "d":
                                        AxieList.get(tempIndex).Pure = true;
                                    case "Y":
                                    case "y":
                                        AxieList.get(tempIndex).Pure = false;
                                }
                                System.out.println("Pureluk başarıyla değiştirildi.");
                                breedingMenu();
                                break;
                            default:
                        }
                    }
                    break;
                case 2:
                    scan.nextLine(); //Dummy
                    breeding();
                    break;
                case 3:
                    scan.nextLine(); //Dummy
                    menu();
                    break;
            }

        }

    }

    static int dayCount = 0;
    public static void breeding() {
        boolean pure = false;
        String currency = "";
        String[] priceInfoArr = new String[2];
        int initAxie = 0, Price = 0;


        tool.writeAndAddFixedSpace("Başlangıçta kaç adet axie alacaksın", 60, ": ");
        initAxie = scan.nextInt(); scan.nextLine(); //Dummy
        tool.writeAndAddFixedSpace("Kaç günlük rapor istiyorsun", 60, ": ");
        dayCount = scan.nextInt(); scan.nextLine(); //Dummy
        System.out.println("");

        for (int i = 0; i < initAxie; i++) {
            System.out.println("\n"+(i + 1) + ". Axie için bilgileri doldur");
            tool.writeAndAddFixedSpace("Axie ismi (A-B-C gibi kısa harfler önerilir)", 60, ": ");
            String AxieName = scan.nextLine();
            System.out.println();
            tool.writeAndAddFixedSpace("[Fiyat SLP/USD/TRY],Pure olup olmadığı('D' ya da 'Y' yaz)", 60, ": ");
            System.out.print("\n(Fiyat ve kur arasına boşluk koymayı unutmayın!\n Kur ve D/Y arasına virgül ',' koymayı unutmayın!\nÖrn: '500 USD,D'\n");
            String[] info = scan.nextLine().split(",");
            switch (info[1]) {
                case "d": case "D":
                    pure = true;
                    break;
                case "y": case "Y":
                    pure = false;
                    break;
            }
            priceInfoArr = info[0].split(" ");
           // System.out.println("# DEBUG \n" +
           //                    "# PriceInfoArr(173) : [("+priceInfoArr[0]+"),("+priceInfoArr[1]+")]\n#");
            Axie axie = new Axie(AxieName, priceInfoArr, pure);
           //System.out.println("# DEBUG (177) \n" +
            //        "# slp_price/usd_price/try_price : "+axie.SLP_PRICE+"/"+axie.USD_PRICE+"/"+axie.TRY_PRICE+"\n#");
            tempAxieList.add(axie);
        }

        breedingConfirm();

    }

    public static void breedingConfirm() {
        System.out.println("\n\n# Şu anda Axie Listesinde [" + tempAxieList.size() + "] adet axie kaydı mevcut.");
        System.out.println("\n1-> Listeyi aç/düzenle\n2-> Listeyi onayla/kaydet\n3-> Listeyi sil");
        switch (scan.nextInt()) {
            case 1:
                scan.nextLine();//dummy
                int maxNameLength = 0;
                System.out.println("\nn. Axie : (Name, Price(usd), Pure)");
                for(int index = 0; index < tempAxieList.size(); index++){
                    if(tempAxieList.get(index).NAME.length()>maxNameLength){
                        maxNameLength = tempAxieList.get(index).NAME.length();
                    }
                }
                for (int index = 0; index < tempAxieList.size(); index++) {
                    Axie tempAxie = tempAxieList.get(index);
                    System.out.print("\n" + (index + 1) + ". Axie :");
                    tool.writeAndAddFixedSpace(tempAxie.NAME, maxNameLength+3, "");
                    tool.writeAndAddFixedSpace(tempAxie.USD_PRICE + "usd ", 12, "");
                    tool.writeAndAddFixedSpace(tempAxie.Pure + "", 6, "");
                }

                    System.out.println("\nDüzenlemek istediğin axie'nin numarasını gir.\n(-1. geri)\n");
                    int tempIndex = scan.nextInt()-1;
                    if(tempIndex < 0){
                        breedingConfirm();
                    }
                    else {
                        System.out.println("\n");
                        tool.writeAndAddFixedSpace(tempAxieList.get(tempIndex).NAME, maxNameLength + 3, "");
                        tool.writeAndAddFixedSpace(tempAxieList.get(tempIndex).USD_PRICE + "slp ", 12, "");
                        tool.writeAndAddFixedSpace(tempAxieList.get(tempIndex).Pure + "", 6, "");
                        System.out.println("\n1-> Adını düzenle\n2-> Fiyatını değiştir\n3-> Pureluk değiştir\n4-> başka axie seç\n-1-> geri\n");
                        switch (scan.nextInt()) {
                            case -1:
                                breedingConfirm();
                                break;
                            case 1:
                                scan.nextLine();//dummy
                                System.out.println("\n[" + tempAxieList.get(tempIndex).NAME + "] için yeni isim gir : ");
                                tempAxieList.get(tempIndex).NAME = scan.nextLine();
                                System.out.println("İsim başarıyla değiştirildi.");
                                breedingConfirm();
                                break;
                            case 2:
                                scan.nextLine();//dummy
                                System.out.println("\n[" + tempAxieList.get(tempIndex).USD_PRICE + "USD] yerine yeni fiyat gir :\n" +
                                        "(FIYAT [boşluk] USD/SLP/TRY ");
                                tempAxieList.get(tempIndex).calculatePrice(scan.nextLine().split(" "));
                                System.out.println("Fiyat başarıyla değiştirildi.");
                                breedingConfirm();
                                break;
                            case 3:
                                scan.nextLine();//dummy
                                System.out.println("\n[" + tempAxieList.get(tempIndex).NAME + "] için yeni pureluk gir(D/Y) : ");
                                switch (scan.nextLine()) {
                                    case "D":
                                    case "d":
                                        tempAxieList.get(tempIndex).Pure = true;
                                    case "Y":
                                    case "y":
                                        tempAxieList.get(tempIndex).Pure = false;
                                }
                                System.out.println("Pureluk başarıyla değiştirildi.");
                                breedingConfirm();
                                break;
                            default:
                                breedingConfirm();
                                break;
                        }
                    }
                break;
            case 2:
                scan.nextLine();//dummy
                for (Axie axie : tempAxieList) {
                    AxieList.add(axie);
                }
                System.out.println("\nListe başarıyla kaydedildi.");
                System.out.println("1-> Rapor oluştur\n2-> Ana menüye dön\n");
                switch (Integer.parseInt(scan.nextLine())){
                    case 1:
                        runSimulation();

                        break;
                    default:
                        menu();
                        break;
                }
                breedingMenu();
                break;
            case 3:
                scan.nextLine();//dummy
                tempAxieList.clear();
                menu();
                break;
        }
        scan.nextLine(); //Dummy
    }

    static void runSimulation(){
        ArrayList<Axie> childList = new ArrayList<Axie>();
        ArrayList<Axie> babyList = new ArrayList<Axie>();
        Axie baby = null;
        Axie Child = null;
        double totalLoss = 0;
        for(int day = 0; day<=dayCount; day++){
            tool.delay(250);
            System.out.println();
            for(int i = 0; i<25; i++){
                System.out.print("_");
            }
            System.out.println("\nDay "+day+":\n");
            if(day%5==0 || day==0) {

                double fiveDayUSDCost = 0;
                double fiveDayTRYCost = 0;
                double fiveDaySLPCost = 0;

                // Bebeklerin yetişkinliğe dönme kısmı
                if(!babyList.isEmpty()) {
                    for(int b=0; b<babyList.size(); b++){
                        Child = babyList.get(b);
                        System.out.println("\n- [" + babyList.get(b).NAME + "] Bebek artık bir yetişkin!");
                        childList.add(Child);
                    }
                }
                babyList.clear();

                for (int i = 0; i < tempAxieList.size()/2; i++) {

                    Axie parent1 = tempAxieList.get(i), parent2 = tempAxieList.get(tempAxieList.size() - (i + 1));
                    baby = ao.breed(parent1, parent2);
                    if(baby != null) {
                        System.out.print("\n- [" + parent1.NAME + "] ve [" + parent2.NAME + "] arasında breeding :\n" +
                                "[" + baby.NAME + "] bebek yumurtanın içine yerleştirildi.");

                        int parent1Cost = parent1.BREED_COUNT*150;
                        int parent2Cost = parent2.BREED_COUNT*150;
                        int totalSLPCost = parent1Cost + parent2Cost;
                        double totalUSDCost = (totalSLPCost*kurlar.SLP_USD)+(2* kurlar.AXS_USD);
                        double totalTRYCost =  Math.round( totalUSDCost*kurlar.USD_TRY * 100.0 / 100.0);
                        fiveDayUSDCost += totalUSDCost;
                        fiveDayTRYCost += totalTRYCost;
                        fiveDaySLPCost += Math.round(totalUSDCost/kurlar.SLP_USD * 100.0 / 100.0);
                        totalLoss += Math.round(fiveDayUSDCost * 100.0 / 100.0);
                        System.out.println("\nBreeding ücreti : ["+parent1Cost+"] + ["+parent2Cost+"] = "+totalSLPCost+" SLP + 2 AXS");
                        System.out.println("Breeding ücreti : "+totalUSDCost+" USD | "+totalTRYCost+" TRY\n");

                        babyList.add(baby);
                    }
                }
                int pure = 0;
                double pureFiyat = 0, npureFiyat = 0;
                for(int c = 0; c<childList.size(); c++){
                    if(childList.get(c).Pure){
                        pure++;
                        pureFiyat = childList.get(c).USD_PRICE;
                    }else{
                        npureFiyat = childList.get(c).USD_PRICE;
                    }
                }
                double totalGain = Math.round(((pure*pureFiyat)+(npureFiyat*(childList.size()-pure))) * 100.0) / 100.0;
                System.out.println("# Bugün toplamda : "+fiveDayUSDCost+" USD / "+fiveDaySLPCost+" SLP / "+fiveDayTRYCost+" TRY harcandı");
                System.out.println("# Şu ana kadar toplamda '"+pure+"' adet pure ve"+(childList.size()-pure)+" melez çocuk dünyaya geldi\n" +
                                   "Bu da "+totalGain+" USD ediyor.");

                System.out.println("\n# BUGÜNE KADARKİ [TOPLAM KAZANÇ("+totalGain+") - TOPLAM HARCAMA("+totalLoss+") = "+(totalGain-totalLoss)+" USD.]\n");
            }
            for(int i = 0; i<25; i++){
                System.out.print("_");
            }
            System.out.println();
        }
    }
}
