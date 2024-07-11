package com.tcin.hackcelrate.hackathon.model;

import jakarta.persistence.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Violation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String vehicleNumber;

    private String chassisNumber;

    private String violatorName;

    private String dlRcNumber;

    private String challanNo;

    private String txnId;

    private String state;

    private LocalDateTime challanDate;

    private BigDecimal amount;

    private String status;

    private String paymentSource;

    private Boolean challanPrint;

    private Boolean receipt;

    private Boolean payment;

    private Boolean paymentVerify;

    @ElementCollection
    private List<String> violations;

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().replace("-", "");
        }
        if (this.challanNo == null) {
            this.challanNo = UUID.randomUUID().toString().replace("-", "");
        }
        if (this.txnId == null) {
            this.txnId = UUID.randomUUID().toString().replace("-", "");
        }
    }
}
