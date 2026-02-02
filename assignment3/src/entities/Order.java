package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private String status;
    private boolean completed;
    private List<OrderItem> items;
    private String deliveryAddress;

    public Order(int customerId) {
        this.customerId = customerId;
        this.completed = false;
        this.status = "NEW";
        this.items = new ArrayList<>();
    }

    public Order(int id, int customerId, String status, boolean completed) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.completed = completed;
        this.items = new ArrayList<>();
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.status = builder.status;
        this.completed = builder.completed;
        this.items = builder.items;
        this.deliveryAddress = builder.deliveryAddress;
    }

    public String getDeliveryAddress() {return deliveryAddress;}
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public String deliveryAddress;
        private int id;
        private int customerId;
        private String status = "NEW";
        private boolean completed = false;
        private List<OrderItem> items = new ArrayList<>();

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder addItem(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public Builder setItems(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder setDeliveryAddress(String deliveryAddress){
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }


    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) {
        this.completed = completed;
        this.status = completed ? "COMPLETED" : "ACTIVE";
    }
}