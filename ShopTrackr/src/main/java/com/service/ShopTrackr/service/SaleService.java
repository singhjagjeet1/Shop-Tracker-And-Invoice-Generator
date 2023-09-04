package com.service.ShopTrackr.service;

import com.service.ShopTrackr.entity.Customer;
import com.service.ShopTrackr.entity.Product;
import com.service.ShopTrackr.entity.Sale;
import com.service.ShopTrackr.repository.CustomerRepository;
import com.service.ShopTrackr.repository.ProductRepository;
import com.service.ShopTrackr.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    public Sale saveSale(Sale sale) throws NoSuchFieldException {
        String customerName = sale.getCustomerName();
        Optional<Customer> customer = customerRepository.findByCustomerName(customerName);
        if(!customer.isPresent())
        {
            throw new NoSuchFieldException("No such Customer found for customerName: "+customerName);
        }
        String productName = sale.getProductName();
        Optional<Product> optionalProduct = productRepository.findByProductName(productName);
        if(optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            if((product.getProductQuantity()) >= (sale.getSaleQuantity()))
            {
                product.setProductQuantity(product.getProductQuantity() - sale.getSaleQuantity());
                productRepository.save(product);
            }
            else
            {
                throw new NoSuchFieldException("Product Stock is less the required saleQuantity which is: "+ productName+ product.getProductQuantity() );
            }
        }
        else
        {
            throw new NoSuchFieldException("No such Product found for productName: "+productName);
        }
        return saleRepository.save(sale);
    }
    public Optional<Sale> getSale(long saleId){
        return saleRepository.findById(saleId);
    }
    public Sale updateSale(Sale updatedSale) throws NoSuchFieldException {
        Optional<Sale> sale =saleRepository.findById(updatedSale.getSaleId());
         if(sale.isPresent())
         {
            return saleRepository.save(updatedSale);
         }
         else{
             throw new NoSuchFieldException("Sale doesn't exist for saleId"+updatedSale.getSaleId());
         }
    }
    public void deleteSale(long saleId) throws NoSuchFieldException {
        Optional<Sale> sale = saleRepository.findById(saleId);
        if (sale.isPresent()) {
            saleRepository.delete(sale.get());
        } else {
            throw new NoSuchFieldException("Sale does not exist for SaleId: " + saleId);
        }
    }

    public List<Sale> getSaleHistory(String customerName, Date startDate, Date endDate, String customerContactNumber) {
        return saleRepository.findSales(startDate,endDate,customerName,customerContactNumber);
    }
}
