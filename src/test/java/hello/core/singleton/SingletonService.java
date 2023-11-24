package hello.core.singleton;

public class SingletonService {
    //static은 클래스레벨에 올라가기 때문에 하나만 존재함

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //생성자를 못만들게 막아버린다.
    private SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
