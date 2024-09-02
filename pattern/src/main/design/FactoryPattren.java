/**
 * Author:xie-super
 * Time:2023/12/28
 * Name:IntelliJ IDEA
 */
//商品
abstract class Pizza{
    //pizza制作过程
    public void prepare() {
        System.out.println("Normal pizza prepare");
    }
}
class ChineseCheesePizza extends Pizza {
    @Override
    public void prepare(){
        System.out.println("ChineseCheesePizza prepare");
    }
}
class JapanCheesePizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("JapanCheesePizza prepare");
    }
}
//工厂
abstract class PizzaStore{
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        return pizza;
    }
    protected abstract Pizza createPizza(String type);
}
class ChinesePizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {
        if(type == "Cheese"){
            return new ChineseCheesePizza();
        }
        return null;
    }
}
class JapanPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {
        if(type == "Cheese"){
            return new JapanCheesePizza();
        }
        return null;
    }
}
public class FactoryPattren {
    public static void main(String[] args) {
        ChinesePizzaStore chinesePizzaStore =  new ChinesePizzaStore();
        JapanPizzaStore japanPizzaStore = new JapanPizzaStore();
        Pizza pizza = chinesePizzaStore.orderPizza("Cheese");
        pizza = japanPizzaStore.orderPizza("Cheese");
    }
}
