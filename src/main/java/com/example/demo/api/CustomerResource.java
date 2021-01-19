package com.example.demo.api;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    private static final String TOPIC = "KafkaAPI";
    private static final String USAGE_TOPIC = "KafkaUsageAPI";
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplate.send(TOPIC, customer);
        //kafkaTemplateString.send(USAGE_TOPIC,"POST Customer API has been Accessed at : "+formatDateTime);
        log.info("POST Customer API has been Accessed at : "+formatDateTime);
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        //kafkaTemplateString.send(USAGE_TOPIC,"GET Customer API has been Accessed at : "+formatDateTime);
        log.info("GET Customer API has been Accessed at : "+formatDateTime);
        return customerService.getCustomers();
    }

    @GetMapping(value = "/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        //kafkaTemplateString.send(USAGE_TOPIC,"GET BY ID Customer API has been Accessed at : "+formatDateTime);
        log.info("GET BY ID Customer API has been Accessed at : "+formatDateTime);
        return customerService.getCustomer(customerId);
    }

    @PutMapping(value = "/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId,@RequestBody Customer customer) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        //kafkaTemplateString.send(USAGE_TOPIC,"PUT Customer API has been Accessed at : "+formatDateTime);
        log.info("PUT Customer API has been Accessed at : "+formatDateTime);
        return customerService.updateCustomer(customerId,customer);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        //kafkaTemplateString.send(USAGE_TOPIC,"DELETE Customer API has been Accessed at : "+formatDateTime);
        log.warn("DELETE Customer API has been Accessed at : "+formatDateTime);
        customerService.deleteCustomer(customerId);
    }
}
