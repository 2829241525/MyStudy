package com.design.create.builder;

/**
 * @author 腾讯课堂‐图灵学院 郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class BuilderTest {
    public static void main(String[] args) {

        ProductBuilder specialConcreteProductBuilder = new SpecialConcreteProductBuilder();
        Director director = new Director(specialConcreteProductBuilder);
        Product product = director.makeProduct("productNamexxx", "cn...", "part1", "part2", "part3", "part4");
        System.out.println(product);

    }
}

interface ProductBuilder {
    void builderProductName(String productName);

    void builderCompanyName(String companyName);

    void builderPart1(String part1);

    void builderPart2(String part2);

    void builderPart3(String part3);

    void builderPart4(String part4);

    Product build();
}

//class DefaultConcreteProductBuilder implements ProductBuilder {
//    private String productName;
//    private String companyName;
//    private String part1;
//
//    private String part2;
//    private String part3;
//    private String part4;
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    @Override
//    public void builderProductName(String productName) {
//        this.productName = productName;
//    }
//
//    @Override
//    public void builderCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    @Override
//    public void builderPart1(String part1) {
//        this.part1 = part1;
//    }
//
//    @Override
//    public void builderPart2(String part2) {
//        this.part2 = part2;
//    }
//
//    @Override
//    public void builderPart3(String part3) {
//        this.part3 = part3;
//    }
//
//    @Override
//    public void builderPart4(String part4) {
//        this.part4 = part4;
//    }
//
//    @Override
//    public Product build() {
//        return new Product(this.productName, this.companyName, this.part1, this.part2, this.part3, this.part4);
//    }
//}

class SpecialConcreteProductBuilder implements ProductBuilder {

    private String productName;
    private String companyName;
    private String part1;
    private String part2;
    private String part3;
    private String part4;

    @Override
    public void builderProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void builderCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void builderPart1(String part1) {
        this.part1 = part1;
    }

    @Override
    public void builderPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public void builderPart3(String part3) {
        this.part3 = part3;
    }

    @Override
    public void builderPart4(String part4) {
        this.part4 = part4;
    }

    @Override
    public Product build() {
        return new Product(this.productName, this.companyName, this.part1, this.part2, this.part3, this.part4);
    }

}


class Director {

    private ProductBuilder builder;

    public Director(ProductBuilder builder) {
        this.builder = builder;
    }

    public Product makeProduct(String productName, String companyName, String part1, String part2, String part3, String part4) {

        builder.builderProductName(productName);
        builder.builderCompanyName(companyName);
        builder.builderPart1(part1);
        builder.builderPart2(part2);
        builder.builderPart3(part3);
        builder.builderPart4(part4);
        Product product = builder.build();
        return product;
    }

}


