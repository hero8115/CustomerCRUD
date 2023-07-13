package com.example.customercrud.controller;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDto;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> customerList(){
        return customerService.customerList();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{page}")
    public Page<Customer> customerListByPage(@PathVariable Integer page){
        return customerService.customerPage(page);
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public ApiResponse updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto){
        return customerService.updateCustomer(id,customerDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }


}
