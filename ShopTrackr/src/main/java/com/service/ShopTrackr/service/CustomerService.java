package com.service.ShopTrackr.service;

import com.service.ShopTrackr.entity.*;
import com.service.ShopTrackr.repository.CustomerRepository;
import com.service.ShopTrackr.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    SaleService saleService;
    @Autowired
    CustomerTransactionRepository customerTransactionRepository;
    public Customer saveCustomer(Customer customer) throws NoSuchFieldException {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerName(customer.getCustomerName());
        if(optionalCustomer.isPresent())
        {
            throw new NoSuchFieldException("Already a customer is present with same name : "+customer.getCustomerName());
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(long customerId) {
        return customerRepository.findById(customerId);
    }
    public Optional<Customer> getCustomerByCustomerName(String customerName) {
        return customerRepository.findByCustomerName(customerName);
    }

    public Customer updateCustomer(Customer updatedCustomer) throws NoSuchFieldException {
        Optional<Customer> customer = customerRepository.findById(updatedCustomer.getCustomerId());
        if (customer.isPresent()) {
            return customerRepository.save(updatedCustomer);
        } else {
            throw new NoSuchFieldException("Customer does not exist for CustomerId: " + updatedCustomer.getCustomerId());
        }
    }

    public void deleteCustomer(long customerId) throws NoSuchFieldException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            throw new NoSuchFieldException("Customer does not exist for CustomerId: " + customerId);
        }
    }

    public ResponseEntity<Map<String, Object>> customerSaleHistory(String customerName, Date startDate, Date endDate, String customerContactNumber) throws NoSuchFieldException {
        if(customerName!=null)
        {
            Optional<Customer> customer = customerRepository.findByCustomerName(customerName);
            if(!customer.isPresent())
            {
                throw new NoSuchFieldException("Customer does not exist for CustomerName: " + customerName);
            }
        }


            List<Sale> saleList = saleService.getSaleHistory(customerName,startDate,endDate,customerContactNumber);
            Double totalSaleAmount = 0.0;
            for(Sale sale : saleList)
            {
                Double itemPrice = Double.valueOf(sale.getSalePrice());
                Double itemCount = Double.valueOf(sale.getSaleQuantity());
                totalSaleAmount = totalSaleAmount + itemCount*itemPrice;
            }
            Map<String, Object> response = new HashMap<>();
            response.put("TotalSaleAmount", totalSaleAmount);
            response.put("SaleDetails", saleList);
            return ResponseEntity.ok(response);


    }

    public CustomerTransaction saveCustomerTransaction(CustomerTransaction customerTransaction) throws NoSuchFieldException {
        Optional<Customer> customer = customerRepository.findByCustomerName(customerTransaction.getCustomerName());
        if(customer.isPresent())
        {
           return customerTransactionRepository.save(customerTransaction);
        }
        else
        {
            throw new NoSuchFieldException("No customer found for customerName: "+ customerTransaction.getCustomerName());
        }
    }


    public List<CustomerTransaction> getCustomerTransactions(String customerName, Date startDate, Date endDate) throws NoSuchFieldException {
        if(customerName!=null)
        {
            Optional<Customer> customer = customerRepository.findByCustomerName(customerName);
            if(!customer.isPresent()){
                throw new NoSuchFieldException("No such customer found for customerName: "+customerName);
            }
        }
        List<CustomerTransaction> customerTransactions = customerTransactionRepository.findTransactions(customerName, startDate, endDate);
        return customerTransactions;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
