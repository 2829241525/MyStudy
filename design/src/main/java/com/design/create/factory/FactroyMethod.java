package com.design.create.factory;

public class FactroyMethod {

    public static void main(String[] args) {
        //Application application = new Application();
        //Product getobj = application.getobj(1);
        //
        createA createA = new createA();
        Product getobj = createA.getobj(1);
        getobj.method1();
        createB createB = new createB();


        Product getobj2 = createB.getobj(1);
        getobj2.method1();
    }

}

interface Product{

    public void method1();
}


class ProductA implements Product{

    public void method1(){
        System.out.println("A");
    }
}

class ProductB implements Product{

    public void method1(){
        System.out.println("B");
    }
}

class createA extends Application{

    @Override
    Product create(int n) {

        return new ProductA();
    }
}

class createB extends Application{

    @Override
    Product create(int n) {

        return new ProductB();
    }
}



abstract class Application{

    abstract Product create(int n);
    //{
    //    if (n ==0){
    //        return new ProductA();
    //    }else if (n ==1){
    //        return new ProductB();
    //    }
    //
    //    return null;
    //}

    Product getobj(int n){
        Product product = create(n);
        return product;
    }

}