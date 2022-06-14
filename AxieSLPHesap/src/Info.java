public class Info {

   public double SLP_TRY;
   public double SLP_USD;
   public double USD_TRY;
   public double AXS_USD;
   public double AXS_TRY;


    public void setSLP_TRY(double SLP_TRY) {
        this.SLP_TRY = SLP_TRY;
    }

    public void setSLP_USD(double SLP_USD) {
        this.SLP_USD = SLP_USD;
    }

    public void setUSD_TRY(double USD_TRY) {
        this.USD_TRY = USD_TRY;
    }

    public void setAXS_USD(double AXS_USD) {
        this.AXS_USD = AXS_USD;
        this.AXS_TRY = AXS_USD*USD_TRY;
    }
}
