package com.example.zepto.global;

public class OrderManager {

    private static OrderManager instance; // Singleton instance
    private String status;

    private OrderManager() {
        status = "Pending"; // Initial order status
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }
}
