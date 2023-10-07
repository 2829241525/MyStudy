//package com.tuling.designpattern.chainofresponsibility;
//
//// 定义请求类
//class Request {
//    private String content;
//
//    public Request(String content) {
//        this.content = content;
//    }
//
//    public String getContent() {
//        return content;
//    }
//}
//
//// 定义抽象处理者类
//abstract class Handler {
//    protected Handler successor;
//
//    public void setSuccessor(Handler successor) {
//        this.successor = successor;
//    }
//
//    public abstract void handleRequest(Request request);
//}
//
//// 实现具体处理者类：处理器1
//class ConcreteHandler1 extends Handler {
//    @Override
//    public void handleRequest(Request request) {
//        if (request.getContent().contains("keyword1")) {
//            System.out.println("ConcreteHandler1 handles the request: " + request.getContent());
//        } else if (successor != null) {
//            successor.handleRequest(request);
//        }
//    }
//}
//
//// 实现具体处理者类：处理器2
//class ConcreteHandler2 extends Handler {
//    @Override
//    public void handleRequest(Request request) {
//        if (request.getContent().contains("keyword2")) {
//            System.out.println("ConcreteHandler2 handles the request: " + request.getContent());
//        } else if (successor != null) {
//            successor.handleRequest(request);
//        }
//    }
//}
//
//// 实现具体处理者类：处理器3
//class ConcreteHandler3 extends Handler {
//    @Override
//    public void handleRequest(Request request) {
//        // 其他处理逻辑...
//        System.out.println("ConcreteHandler3 handles the request: " + request.getContent());
//    }
//}
//
//// 客户端代码
//public class ChainOfResponsibilityPatternDemo {
//    public static void main(String[] args) {
//        Handler handler1 = new ConcreteHandler1();
//        Handler handler2 = new ConcreteHandler2();
//        Handler handler3 = new ConcreteHandler3();
//
//        // 构建责任链
//        handler1.setSuccessor(handler2);
//        handler2.setSuccessor(handler3);
//
//        // 创建请求
//        Request request1 = new Request("This is a request contains keyword1");
//        Request request2 = new Request("This is a request contains keyword2");
//        Request request3 = new Request("This is a request");
//
//        // 处理请求
//        handler1.handleRequest(request1);
//        handler1.handleRequest(request2);
//        handler1.handleRequest(request3);
//    }
//}
