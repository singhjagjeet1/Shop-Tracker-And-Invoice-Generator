package com.service.ShopTrackr.controller;

import com.service.ShopTrackr.entity.Customer;
import com.service.ShopTrackr.entity.CustomerTransaction;
import com.service.ShopTrackr.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer) throws NoSuchFieldException {
         return customerService.saveCustomer(customer);
    }
    @GetMapping("/getCustomer")
    public Optional<Customer> getCustomer(@RequestParam long customerId){
        return customerService.getCustomer(customerId);
    }
    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer) throws NoSuchFieldException {
        return customerService.updateCustomer(updatedCustomer);
    }
    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@RequestParam long customerId) throws NoSuchFieldException {
        customerService.deleteCustomer(customerId);
    }
    @GetMapping("/customerSaleHistory")
    public ResponseEntity<Map<String, Object>> customerSaleHistory(@RequestParam(required = false) String customerName, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@RequestParam(required = false) Date startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date endDate, @RequestParam(required = false) String customerContactNumber) throws NoSuchFieldException {
       return customerService.customerSaleHistory(customerName,startDate,endDate,customerContactNumber);
    }
    @PostMapping("/saveCustomerTransaction")
    public CustomerTransaction saveCustomerTransaction(@RequestBody CustomerTransaction customerTransaction) throws NoSuchFieldException {
        return customerService.saveCustomerTransaction(customerTransaction);
    }

    @GetMapping("/getCustomerTransactions")
    public List<CustomerTransaction> getCustomerTransactions(@RequestParam(required = false) String customerName, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date endDate) throws NoSuchFieldException {
        return customerService.getCustomerTransactions(customerName, startDate, endDate);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
