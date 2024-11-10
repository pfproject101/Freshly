package com.assignment.freshly.asyncTask.vendorTask;

import com.assignment.freshly.entity.Vendor;

import java.util.List;

public interface GetVendors {
    void onVendorsReceived(List<Vendor> vendors);
}
