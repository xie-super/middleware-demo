//策略接口
interface FlyBehavior{//接口表示行为
    public void fly();
}
interface QuackBehavior{
    public void quack();
}
//具体策略
class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}
class FlyNoWay implements FlyBehavior {
    @Override
    public void fly(){
        System.out.println("不能飞");
    }
}
//Quack行为类似
class Quack implements QuackBehavior{
    @Override
    public void quack(){
        System.out.println("Quack!!!");
    }
}
//...
//上下文,包含对策略接口的引用，可以切换不同策略
abstract class Duck{//基类
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior = quackBehavior;
    }
    public void performFly(){
       flyBehavior.fly();
    }
    public void performQuack() {
        quackBehavior.quack();
    }
    public void swim(){
        System.out.println("All duck can swim");
    }
    public abstract void display();//每个鸭子独有
    //...其他行为
}
//一个具体的鸭子类，继承基类
class MallardDuck extends Duck {
    public MallardDuck(){//初始化鸣叫以及飞行方式
        quackBehavior = new Quack();
        flyBehavior = new FlyNoWay();
    }
    @Override
    public void display() {
        System.out.println("I am a real Mallard Duck");
    }
}
//客户端
public class StrategyPattrenTest {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        //动态改变其飞行方式
        mallardDuck.setFlyBehavior(new FlyWithWings());
        mallardDuck.performFly();
    }
}
