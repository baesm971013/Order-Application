package hello.core.singleton;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 호출");
    }

    @Test
    void test1(){
        SingletonService instance1 = SingletonService.getInstance();

        SingletonService instance2 = SingletonService.getInstance();

        Assertions.assertThat(instance1).isSameAs(instance2);
        instance2.logic();
    }
}
