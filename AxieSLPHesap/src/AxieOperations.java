import java.util.Arrays;

public class AxieOperations {

    public Axie breed(Axie parent1, Axie parent2){
    /**/////////////////////////////////////////////**/
    /*                Initialize                     */
    /**/////////////////////////////////////////////**/
    /**/ int indexCount = 1;                       /**/
    /**/ String name = parent1.NAME+parent2.NAME;  /**/
    /**/                                           /**/
    /**/ double slpPrice = 0;                      /**/
    /**/                                           /**/
    /**/ boolean pure;                             /**/
    /**/ int chance = (int)(Math.random()*99) + 1; /**/

        if((parent1.Parents[0] == parent2.Parents[0] && parent1.Parents[1] == parent2.Parents[1]) || (parent1.Parents[0] == parent2.Parents[1] && parent1.Parents[1] == parent2.Parents[0])){
            return null;
        }else {

            for (int i = 0; i < parent1.Childs.size(); i++) {   // Preparing name+indexcount for child name
                if (Arrays.equals(parent1.Childs.get(i).Parents, new Axie[]{parent1, parent2}) || Arrays.equals(parent2.Childs.get(i).Parents, new Axie[]{parent2, parent1})) {
                    indexCount++;
                }
            }
            name += indexCount;

            if (parent1.Pure && parent2.Pure) {   // Calculating the Pureness chance
                if (chance > 3) {
                    pure = true;
                } else {
                    pure = false;
                }
            } else {
                if (chance % 2 == 0) {
                    pure = true;
                } else {
                    pure = false;
                }
            }

            if (pure) {                           // Calculating the Price
                slpPrice = parent1.SLP_PRICE;
               // System.out.println("\n# 44\n# First if : "+slpPrice+"\n# Parent1.slp : "+parent1.SLP_PRICE+"\n#");
            } else {
                if (parent1.Pure) {
                    slpPrice = parent1.SLP_PRICE - ((2 * parent1.SLP_PRICE) / 5);
                   // System.out.println("\n# 48\n# 1. elif : "+slpPrice+"\n# Parent1.slp : "+parent1.SLP_PRICE+"\n#");

                } else if (parent2.Pure) {
                    slpPrice = parent2.SLP_PRICE - ((2 * parent2.SLP_PRICE) / 5);
                   // System.out.println("\n# 52\n# 2. elif : "+slpPrice+"\n# Parent2.slp : "+parent2.SLP_PRICE+"\n#");
                } else {
                    slpPrice = parent1.SLP_PRICE;
                  //  System.out.println("\n# 55\n# else : "+slpPrice+"\n# Parent1.slp : "+parent1.SLP_PRICE+"\n#");
                }
            }
            //System.out.println("# Debug AxieOp.class\n# child's slpPrice : "+slpPrice+"\n#");
            Axie child = new Axie(name, new String[]{slpPrice + "", "SLP"}, pure);
            child.setParents(new Axie[]{parent1, parent2});

            parent1.breed(child);
            parent2.breed(child);

            return child;
        }
    }

}
