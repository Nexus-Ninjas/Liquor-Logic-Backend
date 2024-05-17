package com.liquorlogic.brandservice.entity;

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
@Table(name = "t_brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BRANDID")
    private UUID brandId;
    @Column(name = "BRAND_NAME")
    private String brandName;
    @Column(name = "DILIVERY_COMPANYID")
    private UUID diliveryCompanyId;
    @Column(name = "DISCRIPTION")
    private String discription;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
