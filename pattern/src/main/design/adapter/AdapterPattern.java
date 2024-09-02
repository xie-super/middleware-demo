package adapter;

/**
 * Author:xie-super
 * Time:2023/12/30
 * Name:IntelliJ IDEA
 */
/*
* 适应器模式：鸭子与火鸡问题
* */

interface Duck{
    void fly();
    void quack();
}
class MallardDuck implements Duck{
    @Override
    public void fly(){
        System.out.println(" MallardDuck fly");
    }
    @Override
    public void quack() {
        System.out.println("MallardDuck quack");
    }
}
//火鸡接口
interface Turkey{
    void gobble();
    void fly();
}
class WildTurkey implements Turkey{
    @Override
    public void gobble() {
        System.out.println("WildTurkey gobble");
    }

    @Override
    public void fly() {
        System.out.println("WildTurkey fly");
    }
}
class TurkeyAdapter implements Duck{
    Turkey turkey;
    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }
    @Override
    public void quack(){
        turkey.gobble();
    }
    @Override
    public void fly(){
        turkey.fly();
    }
}
//客户端
public class AdapterPattern {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        Turkey wildTurkey = new WildTurkey();
        //通过适配器将火鸡转成鸭子
        Duck turkeyAdapter = (Duck) new TurkeyAdapter(wildTurkey);
        System.out.println("mallardDuck say:");
        mallardDuck.fly(); mallardDuck.quack();
        System.out.println("\nwildTurkey say:");
        wildTurkey.fly(); wildTurkey.gobble();
        System.out.println("\nturkeyAdapter say:");
        turkeyAdapter.fly();  turkeyAdapter.quack();

    }
}
