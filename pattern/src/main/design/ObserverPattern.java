import java.util.ArrayList;

/**
 * Author:xie-super
 * Time:2023/12/26
 * Name:IntelliJ IDEA
 */
interface Subject {
    public void registerObserver(Observer o);//加入

    public void removeObserver(Observer o);//移出

    public void notifyObservers();//通知状态变更,相当于来新报纸了
}

class WeatherData implements Subject {
    //一个观察者队列
    private ArrayList<Observer> observers;
    //三个属性，相当于订阅的报纸的内容
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.indexOf(o) >= 0) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    //当数据更新时，通知观察者, 其实就相当于一个主观发布新报纸
    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float presure) {
        //测试用方法
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = presure;
        measurementsChanged();
    }
}

interface Observer {
    void update(float temperature, float humidity, float pressure);
}

//这里具体观察者只实现CurrentConditionsDisplay，其余类似
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;
    //观察者可以主观选择订阅以及不订阅，因此需要一个 Subject实例
    private Subject weatherSubject;

    public CurrentConditionsDisplay(Subject weatherSubject) {
        this.weatherSubject = weatherSubject;
        weatherSubject.registerObserver(this);
    }
    public void display() {
        //显示当前温湿度状况
        System.out.println("Current conditions:" + temperature + "F degrees and"+ humidity + "% humidity");
    }
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

}

public class ObserverPattern {
    public static void main(String[] args) {
        WeatherData weatherSubject = new WeatherData();
        //订阅
        CurrentConditionsDisplay cd = new CurrentConditionsDisplay(weatherSubject);
        weatherSubject.measurementsChanged();
        weatherSubject.setMeasurements(80, 65, 30.4f);
    }
}
