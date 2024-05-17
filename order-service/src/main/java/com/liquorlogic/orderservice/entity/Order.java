package com.liquorlogic.orderservice.entity;

import com.liquorlogic.orderservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

/**
 * @author CypsoLabs
 */
@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @Column(name = "CUSTOMER_ID")
    private UUID customerId;
    @Column(name = "ITEM_ID")
    private UUID itemId;
    @Column(name = "EMPLOYEE_ID")
    private UUID employeeId;
    @Column(name = "QTY")
    private int qty;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;
    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;
    @Column(name = "STATUS")
    private Status status;
}
