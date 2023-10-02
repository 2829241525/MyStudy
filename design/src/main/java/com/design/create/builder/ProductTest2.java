package com.design.create.builder;

import lombok.Data;

public class ProductTest2 {
    public static void main(String[] args) {
        Product.Builder builder = new Product.Builder().productName("xxxx").companyName("xxxxxx").part1("xxxxx").part2("xxxx");

        //
        builder.part3("part3");

        Product product = builder.build();
        System.out.println(product);
    }

}

@Data
class Product {

    private final String productName;
    private final String companyName;
    private final String part1;
    private final String part2;
    private final String part3;
    private final String part4;
    // ......


    static class Builder {

        private String productName;
        private String companyName;
        private String part1;
        private String part2;
        private String part3;
        private String part4;


        public Builder productName(String productName) {
            this.productName = productName;

            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder part1(String part1) {
            this.part1 = part1;
            return this;
        }

        public Builder part2(String part2) {
            this.part2 = part2;
            return this;
        }

        public Builder part3(String part3) {
            this.part3 = part3;
            return this;
        }

        public Builder part4(String part4) {
            this.part4 = part4;
            return this;
        }

        Product build() {
            //
            Product product = new Product(this.productName, this.companyName, this.part1, this.part2, this.part3, this.part4);
            return product;
        }

    }
    
}
