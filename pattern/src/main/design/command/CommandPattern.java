package command;

/**
 * Author:xie-super
 * Time:2023/12/30
 * Name:IntelliJ IDEA
 */
/*
* 模式名称：控制器模式，家用电器控制
* */

//首先定义一个命令接口
interface Command{
    public void execute();
}
//ConcreteCommand  TelevisionOnCommand
class TelevisionCommand implements Command{
    //一个 Reciver的引用
    Television television;
    public TelevisionCommand(Television television){
        this.television = television;
    }
    @Override
    public void execute() {
        television.turnOn();
    }
}
//Receiver
class Television{
    void turnOn(){
        System.out.println("电视机已打开");
    }
}
//Invoker  遥控器类
class RemoteControl {
    //一个命令接口的引用
    Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void pressButton() {
        command.execute();
    }
}
//Client
public class CommandPattern {
    public static void main(String[] args) {
        Television television = new Television();
        TelevisionCommand televisionCommand = new TelevisionCommand(television);
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(televisionCommand);
        remoteControl.pressButton();
    }
}
