package com.example.demo.api;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    private static final String TOPIC = "KafkaAPI";
    private static final String USAGE_TOPIC = "KafkaUsageAPI";

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        kafkaTemplate.send(TOPIC, customer);
        kafkaTemplateString.send(USAGE_TOPIC,"POST Customer API has been Accessed");
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        kafkaTemplateString.send(USAGE_TOPIC,"GET Customer API has been Accessed");
        return customerService.getCustomers();
    }

    @GetMapping(value = "/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId) {
        kafkaTemplateString.send(USAGE_TOPIC,"GET BY ID Customer API has been Accessed");
        return customerService.getCustomer(customerId);
    }

    @PutMapping(value = "/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId,@RequestBody Customer customer) {
        kafkaTemplateString.send(USAGE_TOPIC,"PUT Customer API has been Accessed");
        return customerService.updateCustomer(customerId,customer);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
        kafkaTemplateString.send(USAGE_TOPIC,"DELETE Customer API has been Accessed");
        customerService.deleteCustomer(customerId);
    }
}
