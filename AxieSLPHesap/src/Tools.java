public class Tools {

    public void writeAndAddFixedSpace(String text, int length, String ending) {
        if (length >= text.length()) {
            System.out.print(text);
            for (int spaces = 0; spaces < (length - text.length()); spaces++) {
                System.out.print(" ");
            }
            System.out.print(ending);
        }
        else
        {
            System.out.println("\n##\nError occured : Text length is longer than space amount.\nThe Text : '"+text+"'("+text.length()+" character)\nDesired space amount : "+length);
        }
    }

    public void delay(int milis){
        try {Thread.sleep(milis);} catch (InterruptedException ignored) {}
    }
    public void wait(int milis){
        delay(milis);
    }
}
