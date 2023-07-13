package com.example.customercrud.service;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDto;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    public Page<Customer> customerPage(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Customer> all = customerRepository.findAll(pageable);
        return all;
    }

    public Customer getCustomerById(Integer id){
        Optional<Customer> byId = customerRepository.findById(id);
            return byId.get();
    }

    public ApiResponse addCustomer(CustomerDto customerDto){
        boolean b = customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (b){
            return new ApiResponse("Bunday raqamli mijoz mavjud bo'lishi mumkin", false);
        }
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAdress(customerDto.getAdress());
        customerRepository.save(customer);
        return new ApiResponse("Mijoz qo'shildi", true);
    }


    public ApiResponse updateCustomer(Integer id, CustomerDto customerDto) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()){
            Customer customer = byId.get();
            customer.setName(customerDto.getName());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customer.setAdress(customerDto.getAdress());
            customerRepository.save(customer);
            return new ApiResponse("Mijoz malumotlari yangilandi", true);
        }
        return new ApiResponse("Bunday Id lik mijoz topilmadi", false);
    }

    public ApiResponse deleteCustomer(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()){
            customerRepository.deleteById(id);
            return new ApiResponse("Mijoz o'chirildi", true);
        }
        return new ApiResponse("Mijoz topilmadi", false);
    }
}
