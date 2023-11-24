package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    //상태를 유지하는 설계법
    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    //상태를 유지하지 않는 설계법
//    public int order(String name, int price){
//        return price;
//    }

    public int getPrice(){
        return price;
    }
}
