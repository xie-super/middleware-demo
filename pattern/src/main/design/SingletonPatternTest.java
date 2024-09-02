/**
 * Author:xie-super
 * Time:2023/12/26
 * Name:IntelliJ IDEA
 */
//懒汉
class Singleton{
    private static Singleton uniqueSingleton;
    private Singleton(){

    }
    public static Singleton getInstance() {
        if(uniqueSingleton == null){
            uniqueSingleton = new Singleton();
        }
        return uniqueSingleton;
    }
}
//饿汉
class Singleton2{
    private static Singleton2 uniqueSingleton;
    private Singleton2(){

    }
    public static Singleton2 getInstance() {
        return uniqueSingleton;
    }


}
//双重检查
class SingletonDoubleCheck{
    private volatile static SingletonDoubleCheck uniqueSingleton;
    private SingletonDoubleCheck(){

    }
    public static SingletonDoubleCheck getInstance() {
        if(uniqueSingleton == null){
            synchronized (SingletonDoubleCheck.class){
                if(uniqueSingleton == null){
                    uniqueSingleton = new SingletonDoubleCheck();
                }
            }
        }
        return uniqueSingleton;
    }

}
//静态内部类
class SingletonStatic{
    private SingletonStatic(){
    }
    public static SingletonStatic getInstance() {
        return SingletonStaticer.instance;
    }
    private static class SingletonStaticer{
        private static final SingletonStatic instance = new SingletonStatic();
    }
}

public class SingletonPatternTest {
    public static void main(String[] args) {
        //静态必须通过类名获取实例


    }

}
