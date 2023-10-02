package com.design.j2ee.business;

public class Client {

    BusinessDelegate businessService;

    public Client(BusinessDelegate businessService){
        this.businessService  = businessService;
    }

    public void doTask(){
        businessService.doTask();
    }
}
