package hello.core.singleton;

public class StatefulService {
//    private int price; // 상태를 유지하는필드

    public int order(String name, int price) {

        System.out.println("name = " + name + "price = " + price);
//        this.price = price;

        // 10000->20000 공유 해결위해 바꿔줌
        return price;


    }

//    public int getPrice() {
//        return price;
//    }

}
