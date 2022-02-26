import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Pharmacy{
    private String medicineName;
    private float medicinePrice;
    private int numberMedicine;
    String[] medOrder = new String[100];
    float[] medPrice = new float[100];
    int[] numOfOrder = new int[100];
    private String updateNameMed;

    //User Input
    int userInput() throws IOException{
        int choose;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose type of medicine below:");
        File f=new File("pharmacy.txt");
        BufferedReader freader = new BufferedReader(new FileReader(f));
        int i=1;
        String read="";
        System.out.println("_________________________________________________________________________________");
        while(( read = freader.readLine()) != null) {
            String[] st = read.split(" ");// Seperate a line of string in pharmacy file into a string by checking spaces
			String id = st[0];
            String numberOfMedicine = st[1];
			String readMedicine= st[2];
            String readPrice=st[3];
            System.out.println(i+")"+readMedicine+"\n||RM"+readPrice+"\n||"+ numberOfMedicine+" piece is available\n_________________________________________________________________________________");
            i++;
        }
        System.out.print(">>Choose from the list:");
        choose=sc.nextInt();
        freader.close();
        while(choose>i-1){
            System.out.println(">>Invalid Option");
            System.out.print(">>Choose from the list:");
            choose=sc.nextInt();
        }
        
        return choose;
    }
    //save quantity
    int quantityOrder(int loop){
        Scanner sc = new Scanner(System.in);
        System.out.print(">>Quantitiy:");
        int quantity=sc.nextInt();
        numOfOrder[loop]=quantity;
        return quantity;

    }
    //Analyze type of medicinePrice
    void orderMed(int order, int loop)throws Exception{
        File f=new File("pharmacy.txt");
        BufferedReader freader = new BufferedReader(new FileReader(f));
        
        //Intialise variable for scanning phase
        String read;
        
        //Scanning phase line by line
        while(( read = freader.readLine()) != null) 
		{
			
            String[] st = read.split(" ");// Seperate a line of string in pharmacy file into a string by checking spaces
			String num= st[0];
            int  id =Integer.parseInt(num);
            String numberOfMedicine = st[1];//to select line like an id
			String readMedicine= st[2];
            String readPrice=st[3];

			if(order==id)
			{
                medicineName=readMedicine;
                medicinePrice=Float.parseFloat(readPrice);
                numberMedicine=Integer.parseInt(numberOfMedicine);
                updateNameMed=medicineName;
			}
		}
       
        freader.close();
        saveOrder(loop);
    }

    //update quantity
    void updateNumMed(String medName,int quantity) throws IOException{
        
        //read and intialize list
        File f=new File("pharmacy.txt");
        BufferedReader freader1 = new BufferedReader(new FileReader(f));
        int i=0;//array
        String[] st = new String[100];
        String read="";
        while(( read = freader1.readLine()) != null) {
            String[] verse = read.split(" ");
            for(int x=1; x<4;x++){
                String word=verse[x];
                st[i]=word;
                i++;
            }
            
        }
        
        //Update medicine quantity
        File file = new File("temp.txt");
		FileWriter fr1 = new FileWriter(file, true);
		BufferedWriter br1 = new BufferedWriter(fr1);
       for(int x=0; x<i;x++){
            if(st[x]==medName){
                br1.write(st[x]+" ");
                st[x+1] =String.valueOf(quantity);
                br1.write(st[x+1]+" ");
                br1.write(st[x+2]+" ");
                br1.write(st[x+3]+" ");
                x=x+4;
            }
            else{
                for(int y=0;y<4;y++){
                    br1.write(st[x]);
                    x++;
                }
            }
       }
        freader1.close();
        br1.close();
    }
    //save order of medication
    void saveOrder(int save){
        medOrder[save]=medicineName;
        medPrice[save]=medicinePrice;
    }

    //display order
    void displayOrder(int loop){
        float total2=0;
        System.out.println("********************************Medicine Ordered*********************************");
        System.out.println("_________________________________________________________________________________");
        int i=1;
        for(int x=0; x<loop;x++){
            System.out.println(i+")"+medOrder[x]+"\n||RM"+medPrice[x]+"\n||Quantity:"+numOfOrder[x]);
            System.out.println("_________________________________________________________________________________");
            i++;
        }
        for(int x=0; x<loop;x++){
            float total1=medPrice[x]*numOfOrder[x];
            total2=total2+total1;
        }
        System.out.printf("||Total: %.2f",total2);//to print 2 decimal point
    }
    
    //end option
    int endOrder(){
        Scanner scanner = new Scanner(System.in);
        int endNum=0;
        
        System.out.print("||Do you want to add more order?\n||0-yes\n||1=no\n>>");
        endNum=scanner.nextInt();
        while(endNum!=0 && endNum!=1){
            System.out.println("||Invalid option");
            System.out.print("||Do you want to add more order?\n||0-yes\n||1=no\n>>");
            endNum=scanner.nextInt();
        }
        return endNum;
    }
}