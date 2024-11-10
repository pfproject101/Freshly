package com.assignment.freshly.asyncTask.customerTask;

import com.assignment.freshly.entity.Customer;

public interface GetCustomer {
    void onCustomerReceived(Customer customer);
}
