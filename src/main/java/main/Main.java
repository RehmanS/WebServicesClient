package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import request.ReqCustomer;
import response.RespCustomer;
import response.RespCustomerList;
import response.RespStatusList;
import util.Utility;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            String response = null;
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("GET or POST");
            switch (scan.nextLine()) {
                case "get":
                    //GET
                    response = Utility.sendGet("http://localhost:24073/WebServicesv2/resources/customerWebservicev2/getCustomerListv2");
                    System.out.println(response);
                    RespCustomerList customerList = objectMapper.readValue(response, RespCustomerList.class);
                    if (customerList.getStatus().getCode() == 1) {
                        List<RespCustomer> respCustomers = customerList.getRespCustomerList();
                        for (RespCustomer respCustomer : respCustomers) {
                            System.out.println(respCustomer.getId() + "--" + respCustomer.getFirstName());
                        }
                    }
                    break;
                case "post":
                    //POST
                    ReqCustomer reqCustomer = new ReqCustomer();
                    reqCustomer.setId(scan.nextInt());
                    scan.nextLine();
                    reqCustomer.setFirstName(scan.nextLine());
                    reqCustomer.setLastName(scan.nextLine());
                    reqCustomer.setPhoneNumber(scan.nextLine());
                    reqCustomer.setStreetNumber(scan.nextLine());
                    reqCustomer.setStreetName(scan.nextLine());
                    reqCustomer.setCountry(scan.nextLine());
                    reqCustomer.setPostalCode(scan.nextInt());
                    String reqJson = objectMapper.writeValueAsString(reqCustomer);
                    response = Utility.sendPost("http://localhost:24073/WebServicesv2/resources/customerWebservicev2/addCustomer", reqJson);
                    RespStatusList resp = objectMapper.readValue(response, RespStatusList.class);
                    if (resp.getStatus().getCode() == 1) {
                        System.out.println(resp.getStatus().getMessage());
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
