import java.util.ArrayList;
import java.util.Locale;

public class Axie {

    Info kurlar = Main.kurlar;

    public double SLP_PRICE = -1;
    public double USD_PRICE = -1;
    public double TRY_PRICE = -1;
    public String NAME = "NaN";
    public int BREED_COUNT = 0;
    public Axie[] Parents = new Axie[2];
    public ArrayList<Axie> Childs = new ArrayList<Axie>();
    public boolean Pure;
    public Axie(){}
    public Axie (String name, String[] price, boolean pure){
        this.NAME = name;
        this.Pure = pure;
        //System.out.println("\n# Debug(axie.class)\n# Price[] :("+price[0]+"),("+price[1]+")\n#\n");
        calculatePrice(price);
        this.Parents = new Axie[]{new Axie(), new Axie()};
    }
    public void breed(Axie child){
        Childs.add(child);
        BREED_COUNT = Childs.size();
    }
    public void setParents(Axie[] parents){
        this.Parents = parents;
    }

    public void calculatePrice(String[] priceInfo){
        priceInfo[0] = ""+(Math.round( Double.parseDouble(priceInfo[0]) * 100.0) / 100.0);
        switch (priceInfo[1].toUpperCase(Locale.ROOT)){
            case "USD":
               // System.out.println("# DEBUG axie.class 39 \n" +
               //         "# priceinfo[0] : " + priceInfo[0]+"\n# parseInt(prInf[0]) : "+ Double.parseDouble(priceInfo[0])+"\n#");
                this.USD_PRICE = Double.parseDouble(priceInfo[0]);
               //System.out.println("# USD_PR : "+ this.USD_PRICE+"\n#");
                this.SLP_PRICE = Math.round( USD_PRICE/kurlar.SLP_USD * 100.0) / 100.0;
               //  System.out.println("# SLP_PR : "+ this.SLP_PRICE+"\n#");
               //  System.out.println("# KurSLP/USD : "+ kurlar.SLP_USD +"\n#");
                this.TRY_PRICE = Math.round( SLP_PRICE*kurlar.SLP_TRY * 100.0) / 100.0;
               // System.out.println("# TRY_PR : "+ this.TRY_PRICE+"\n#");
               // System.out.println("# KurUSD/TRY : "+ kurlar.USD_TRY +"\n#");
                break;
            case "SLP":
               // System.out.println("###DEBUG \n" +
               //         "this.SLP_PRICE = Integer.parseInt(priceInfo[0]);\n" +
               //        priceInfo[0]+"\n[1] :"+ priceInfo[1]);
                this.SLP_PRICE = Double.parseDouble(priceInfo[0]);
                this.TRY_PRICE = Math.round(SLP_PRICE*kurlar.SLP_TRY * 100.0) / 100.0;
                this.USD_PRICE = Math.round(SLP_PRICE*kurlar.SLP_USD * 100.0) / 100.0;
                break;
            case "TRY":
                this.TRY_PRICE = Double.parseDouble(priceInfo[0]);
                this.SLP_PRICE = Math.round(TRY_PRICE/kurlar.SLP_TRY * 100.0) / 100.0;
                this.USD_PRICE = Math.round(SLP_PRICE*kurlar.SLP_USD * 100.0) / 100.0;
                break;
        }
    }

}
