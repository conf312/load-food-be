package com.load.food.domain.food;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity(name = "food")
public class Food {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String codeOpenLocalGovernment;
    private String managementNumber;
    private String dateAuthorization;
    private String businessStatusCode;
    private String businessStatusName;
    private String detailedBusinessStatusCode;
    private String detailedBusinessStatusName;
    private String closedBusinessDate;
    private String phoneNumber;
    private String locationArea;
    private String locationPostalCode;
    private String address;
    private String roadNameAddress;
    private String roadNamePostalCode;
    private String businessPlaceName;
    private String businessStatusClassificationName;
    private String x;
    private String y;

    @Builder
    public Food(long id, String codeOpenLocalGovernment, String managementNumber, String dateAuthorization, String businessStatusCode, String businessStatusName, String detailedBusinessStatusCode, String detailedBusinessStatusName, String closedBusinessDate, String phoneNumber, String locationArea, String locationPostalCode, String address, String roadNameAddress, String roadNamePostalCode, String businessPlaceName, String businessStatusClassificationName, String x, String y) {
        this.id = id;
        this.codeOpenLocalGovernment = codeOpenLocalGovernment;
        this.managementNumber = managementNumber;
        this.dateAuthorization = dateAuthorization;
        this.businessStatusCode = businessStatusCode;
        this.businessStatusName = businessStatusName;
        this.detailedBusinessStatusCode = detailedBusinessStatusCode;
        this.detailedBusinessStatusName = detailedBusinessStatusName;
        this.closedBusinessDate = closedBusinessDate;
        this.phoneNumber = phoneNumber;
        this.locationArea = locationArea;
        this.locationPostalCode = locationPostalCode;
        this.address = address;
        this.roadNameAddress = roadNameAddress;
        this.roadNamePostalCode = roadNamePostalCode;
        this.businessPlaceName = businessPlaceName;
        this.businessStatusClassificationName = businessStatusClassificationName;
        this.x = x;
        this.y = y;
    }

    public Food(Object o) {
        Food food = (Food) o;
        this.id = food.id;
        this.codeOpenLocalGovernment = food.codeOpenLocalGovernment;
        this.managementNumber = food.managementNumber;
        this.dateAuthorization = food.dateAuthorization;
        this.businessStatusCode = food.businessStatusCode;
        this.businessStatusName = food.businessStatusName;
        this.detailedBusinessStatusCode = food.detailedBusinessStatusCode;
        this.detailedBusinessStatusName = food.detailedBusinessStatusName;
        this.closedBusinessDate = food.closedBusinessDate;
        this.phoneNumber = food.phoneNumber;
        this.locationArea = food.locationArea;
        this.locationPostalCode = food.locationPostalCode;
        this.address = food.address;
        this.roadNameAddress = food.roadNameAddress;
        this.roadNamePostalCode = food.roadNamePostalCode;
        this.businessPlaceName = food.businessPlaceName;
        this.businessStatusClassificationName = food.businessStatusClassificationName;
        this.x = food.x;
        this.y = food.y;
    }

    @Getter
    @Setter
    public static class Request {
        private long id;
        private String codeOpenLocalGovernment;
        private String managementNumber;
        private String dateAuthorization;
        private String businessStatusCode;
        private String businessStatusName;
        private String detailedBusinessStatusCode;
        private String detailedBusinessStatusName;
        private String closedBusinessDate;
        private String phoneNumber;
        private String locationArea;
        private String locationPostalCode;
        private String address;
        private String roadNameAddress;
        private String roadNamePostalCode;
        private String businessPlaceName;
        private String businessStatusClassificationName;
    }
}
