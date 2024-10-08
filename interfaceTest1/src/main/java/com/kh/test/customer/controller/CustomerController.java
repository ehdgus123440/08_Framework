package com.kh.test.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.test.customer.dto.Customer;
import com.kh.test.customer.service.CustomerService;

@Controller 
public class CustomerController {

  @Autowired 
  private CustomerService service;

  @PostMapping("insertCustomer") 
  public String insertCustomer(Customer customer, Model model) {
    
    System.out.println(customer);

    int result = service.insertCustomer(customer);
    
    if (result > 0)
      model.addAttribute("message", "추가 성공!!!");
    else
      model.addAttribute("message", "추가 실패...");

    return "result"; 
  }
}
