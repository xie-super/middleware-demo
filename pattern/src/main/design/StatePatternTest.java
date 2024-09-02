/**
 * Author:xie-super
 * Time:2023/12/26
 * Name:IntelliJ IDEA
 */

//状态模式，糖果机问题，其代码太长，我们包装一下，大体代码逻辑不变，缩短代码量
//状态接口，封装行为

interface State {
    void handle(Context context);
}
class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前位于 ConcreteStateA");
        context.setState(new ConcreteStateB());//对应与糖果机中执行事件后状态转换，并且我们知道状态转换需要借助上下文完成
    }
}
class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前位于 ConcreteStateB");
        context.setState(new ConcreteStateA());
    }
}
//上下文，有着状态接口的实例以及状态转换的方法
class Context {
    State state; //实例
    public Context(){
        state = new ConcreteStateA();//在糖果机里这里具体实现应该分别为四个状态初始化
    }
    //具体的set get操作
    public void setState(State state) {
        this.state = state;
    }
    public State getState(){
        return this.state;
    }
    public void request(){
        state.handle(this);
    }
}
//客户端
public class StatePatternTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.request();
        context.request();
        context.request();
        context.request();
    }
}
