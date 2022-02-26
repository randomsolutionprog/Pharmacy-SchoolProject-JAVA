public class PharmacyApp {
    public static void main(String[] args) throws Exception {
        Pharmacy p = new Pharmacy();
        int option=0;
        int array=0;
        int end=0;
        do{
            do{
                option=p.userInput();
                p.quantityOrder(array);
                p.orderMed(option, array);
                
            }while(option<=0);
            array++;
            end=p.endOrder();
            
        }while(end==0);
        p.displayOrder(array);
    }
}
