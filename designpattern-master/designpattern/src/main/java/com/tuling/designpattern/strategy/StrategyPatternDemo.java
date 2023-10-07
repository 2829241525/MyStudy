package com.tuling.designpattern.strategy;

// 定义策略接口
interface PaymentStrategy {
    public void pay(double amount);
}

// 实现具体策略类：支付方式1
class PaymentMethod1 implements PaymentStrategy {
    private String accountName;
    private String password;

    public PaymentMethod1(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        // 实现支付方式1的支付逻辑
        System.out.printf("Pay %f RMB using payment method 1. Account name: %s, password: %s.\n", amount, accountName, password);
    }
}

// 实现具体策略类：支付方式2
class PaymentMethod2 implements PaymentStrategy {
    private String creditCardNumber;
    private int cvv;

    public PaymentMethod2(String creditCardNumber, int cvv) {
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        // 实现支付方式2的支付逻辑
        System.out.printf("Pay %f RMB using payment method 2. Credit card number: %s, CVV: %d.\n", amount, creditCardNumber, cvv);
    }
}

// 实现具体策略类：支付方式3
class PaymentMethod3 implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // 实现支付方式3的支付逻辑
        System.out.printf("Pay %f RMB using payment method 3. Cash.\n", amount);
    }
}

// 定义上下文类，用于选择不同的策略
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}

// 客户端代码
public class StrategyPatternDemo {
    public static void main(String[] args) {
        // 初始化一个上下文对象，并设置支付方式1为默认策略
        PaymentContext context = new PaymentContext(new PaymentMethod1("Alice", "123456"));

        // 使用默认策略进行支付
        System.out.println("Use default payment strategy:");
        context.pay(100.0);

        // 切换到支付方式2
        System.out.println("\nSwitch to payment method 2:");
        context.setPaymentStrategy(new PaymentMethod2("1234 5678 9012 3456", 123));
        context.pay(200.0);

        // 切换到支付方式3
        System.out.println("\nSwitch to payment method 3:");
        context.setPaymentStrategy(new PaymentMethod3());
        context.pay(300.0);
    }
}
