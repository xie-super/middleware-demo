package iterator;

/**
 * Author:xie-super
 * Time:2023/12/30
 * Name:IntelliJ IDEA
 */


class MenuItem {
    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }
    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vegetarian=" + vegetarian +
                ", price=" + price +
                '}';
    }
}

interface Iterator {
    boolean hasNext();

    Object next();
}

class PizzaIterator implements Iterator {
    MenuItem[] menuItems;
    int position = 0;
    public PizzaIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return menuItems[position++];
    }
}
//聚合接口
interface Aggregator {
    public Iterator createIterator();
}
class PizzaHouseMenu implements Aggregator{
    MenuItem[] menuItems;
    int MAX_ITEMS = 2;
    int numberOfItems = 0;
    public PizzaHouseMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("披萨1号", "素食披萨", true, 4.99);
        addItem("披萨2号", "海鲜蛤蜊披萨", true, 5.99);
        numberOfItems = 2;
    }
    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menu = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS)
            System.out.println("对不起，菜单数量已满");
        else
            menuItems[numberOfItems++] = menu;
    }
    @Override
    public Iterator createIterator() {
        return new PizzaIterator(menuItems);
    }
}

public class IteratorPattern{
    public static void main(String[] args) {
        //具体聚合类
        PizzaHouseMenu picker = new PizzaHouseMenu();

        //迭代器接口
        Iterator it = picker.createIterator();
        while(it.hasNext()){
            MenuItem menuItem = (MenuItem) it.next();
            System.out.println(menuItem.toString());
        }
    }
}
