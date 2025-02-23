public class InvoiceItem {
    private String id;
    private String desc;
    private int qty;
    private double unitPrice;

    public InvoiceItem(String id, String desc, int qty, double uni){
        this.id=id;
        this.desc=desc;
        this.qty=qty;
        unitPrice=uni;
    }

    public String getId(){
        return id;
    }
    public String getDesc(){
        return desc;
    }
    public int getQty(){
        return qty;
    }
    public double getUnitPrice(){
        return unitPrice;
    }

    public void setQty(int qty){
        this.qty=qty;
    }
    public void setUnitPrice(double uni){
        unitPrice=uni;
    }

    public double getTotal(){
        return unitPrice*qty;
    }
    @Override
    public String toString(){
        return "InvoiceItem[id="+id+",desc="+desc+",qty="+qty+" ,unirPrice="+unitPrice+"]";
    }
}