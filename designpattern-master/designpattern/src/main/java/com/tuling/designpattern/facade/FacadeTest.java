package com.tuling.designpattern.facade;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */


public class FacadeTest {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}
// 子系统A
class SubsystemA {
    public void operationA() {
        System.out.println("Subsystem A operation");
    }
}

// 子系统B
class SubsystemB {
    public void operationB() {
        System.out.println("Subsystem B operation");
    }
}

// 子系统C
class SubsystemC {
    public void operationC() {
        System.out.println("Subsystem C operation");
    }
}

// 门面类
class Facade {
    private SubsystemA subsystemA;
    private SubsystemB subsystemB;
    private SubsystemC subsystemC;

    public Facade() {
        subsystemA = new SubsystemA();
        subsystemB = new SubsystemB();
        subsystemC = new SubsystemC();
    }

    public void operation() {
        subsystemA.operationA();
        subsystemB.operationB();
        subsystemC.operationC();
    }
}
