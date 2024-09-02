abstract class Beverage{
    String description = "父类";
    public abstract double cost();
    public String getDescription(){
        return description;
    }
}

abstract class CondimentDecorator extends Beverage{
    public abstract String getDescription();
}

class DarkRoast extends Beverage{
    public DarkRoast(){
        description = "深培咖啡";
    }
    public double cost(){
        return 0.99;
    }
}
class Mocha extends CondimentDecorator{
    Beverage beverage;
    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }
    public double cost(){
        return 0.20+beverage.cost();
    }
    public String getDescription(){
        return beverage.getDescription()+"Mocha";
    }
}
class Whip extends CondimentDecorator{
    Beverage beverage;
    public Whip(Beverage beverage){
        this.beverage = beverage;
    }
    public double cost(){
        return 0.10+beverage.cost();
    }
    public String getDescription(){
        return beverage.getDescription()+"Whip";
    }
}


class DectoratorTest {
    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        //添加配料
        Beverage  mocha = new Mocha(beverage);
        Beverage   whip = new Whip(mocha);
        System.out.println(whip.getDescription()+" "+whip.cost());

    }
}
