import java.util.Scanner;

/**
 * Author:xie-super
 * Time:2023/12/26
 * Name:IntelliJ IDEA
 */
//定义骨架
abstract class CaffineBeverage {
    public void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiment())
            addCondiments();
    }
    public abstract void brew();
    public void boilWater() {
        System.out.println("Boil warter");
    }
    public void pourInCup() {
        System.out.println("Pour in cup");
    }
    public abstract void addCondiments();
    //"钩子"方法
    public Boolean customerWantsCondiment(){
        return true;
    }
}
class Coffee extends CaffineBeverage {
    @Override
    public void brew() {
        System.out.println("加咖啡");
    }
    @Override
    public void addCondiments() {
        System.out.println("加 Sugar 和Milk");
    }
    //重写钩子
    @Override
    public Boolean customerWantsCondiment(){
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equals("yes")) return true;
        return false;
    }
}
class Tea extends CaffineBeverage {
    @Override
    public void brew() {
        System.out.println("加茶叶");
    }
    @Override
    public void addCondiments() {
        System.out.println("加柠檬");
    }

}
//客户端
public class TemplateMethodPattren {
    public static void main(String[] args) {
        CaffineBeverage tea = new Tea();
        tea.prepareRecipe();
    }
}
