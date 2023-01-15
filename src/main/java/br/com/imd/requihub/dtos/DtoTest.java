package br.com.imd.requihub.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class DtoTest {
    public DtoTest(String orderCode, String principalCode,
                   String marketplaceOrderId, String sellerOrderId,
                   String orderGroup, String customerDocumentType,
                   String customerPhone, String customerEmail,
                   String customerExternalEmail, String customerDocument,
                   String customerFirstName, String customerLastName,
                   String orderOrigin, ZonedDateTime creationDate,
                   String affiliatedId, String sellerId,
                   String approvedBy, String giftRegistryData,
                   String hostname, String marketplaceServicesEndpoint,
                   String origin, String merchantName, BigDecimal value,
                   ZonedDateTime lastChange, String callCenterOperatorId,
                   String callCenterOperatorEmail, String callCenterOperatorUserName, String followUpEmail,
                   String lastMessage, String openTextField, BigDecimal roundingError,
                   String orderFormId, String commercialConditionData,
                   Boolean isCompleted, Boolean allowCancellation,
                   Boolean allowEdition, Boolean isCheckedIn,
                   String marketplaceBaseURL, String marketplaceIsCertified,
                   String marketplaceName, ZonedDateTime authorizedDate,
                   ZonedDateTime invoicedDate, String sellersName,
                   String acceptPartialOrder, String partnerMarketplaceOrderId,
                   BigDecimal totalValue, BigDecimal totalItemValue,
                   BigDecimal totalDiscounts, BigDecimal totalFreight,
                   String partnerName, String stateInscription) {
        this.orderCode = orderCode;
        this.principalCode = principalCode;
        this.marketplaceOrderId = marketplaceOrderId;
        this.sellerOrderId = sellerOrderId;
        this.orderGroup = orderGroup;
        this.customerDocumentType = customerDocumentType;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerExternalEmail = customerExternalEmail;
        this.customerDocument = customerDocument;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.orderOrigin = orderOrigin;
        this.creationDate = creationDate;
        this.affiliatedId = affiliatedId;
        this.sellerId = sellerId;
        this.approvedBy = approvedBy;
        this.giftRegistryData = giftRegistryData;
        this.hostname = hostname;
        this.marketplaceServicesEndpoint = marketplaceServicesEndpoint;
        this.origin = origin;
        this.merchantName = merchantName;
        this.value = value;
        this.lastChange = lastChange;
        this.callCenterOperatorId = callCenterOperatorId;
        this.callCenterOperatorEmail = callCenterOperatorEmail;
        this.callCenterOperatorUserName = callCenterOperatorUserName;
        this.followUpEmail = followUpEmail;
        this.lastMessage = lastMessage;
        this.openTextField = openTextField;
        this.roundingError = roundingError;
        this.orderFormId = orderFormId;
        this.commercialConditionData = commercialConditionData;
        this.isCompleted = isCompleted;
        this.allowCancellation = allowCancellation;
        this.allowEdition = allowEdition;
        this.isCheckedIn = isCheckedIn;
        this.marketplaceBaseURL = marketplaceBaseURL;
        this.marketplaceIsCertified = marketplaceIsCertified;
        this.marketplaceName = marketplaceName;
        this.authorizedDate = authorizedDate;
        this.invoicedDate = invoicedDate;
        this.sellersName = sellersName;
        this.acceptPartialOrder = acceptPartialOrder;
        this.partnerMarketplaceOrderId = partnerMarketplaceOrderId;
        this.totalValue = totalValue;
        this.totalItemValue = totalItemValue;
        this.totalDiscounts = totalDiscounts;
        this.totalFreight = totalFreight;
        this.partnerName = partnerName;
        this.stateInscription = stateInscription;
    }

    private String orderCode;
    private String principalCode;
    private String marketplaceOrderId;
    private String sellerOrderId;
    private String orderGroup;
    private String customerDocumentType;
    private String customerPhone;
    private String customerEmail;
    private String customerExternalEmail;
    private String customerDocument;
    private String customerFirstName;
    private String customerLastName;
    private String orderOrigin;
    private ZonedDateTime creationDate;
    private String affiliatedId;
    private String sellerId;
    private String approvedBy;
    private String giftRegistryData;
    private String hostname;
    private String marketplaceServicesEndpoint;
    private String origin;
    private String merchantName;
    private BigDecimal value;
    private ZonedDateTime lastChange;
    private String callCenterOperatorId;
    private String callCenterOperatorEmail;
    private String callCenterOperatorUserName;
    private String followUpEmail;
    private String lastMessage;
    private String openTextField;
    private BigDecimal roundingError;
    private String orderFormId;
    private String commercialConditionData;
    private Boolean isCompleted;
    private Boolean allowCancellation;
    private Boolean allowEdition;
    private Boolean isCheckedIn;
    private String marketplaceBaseURL;
    private String marketplaceIsCertified;
    private String marketplaceName;
    private ZonedDateTime authorizedDate;
    private ZonedDateTime invoicedDate;
    private String sellersName;
    private String acceptPartialOrder;
    private String partnerMarketplaceOrderId;
    private BigDecimal totalValue;
    private BigDecimal totalItemValue;
    private BigDecimal totalDiscounts;
    private BigDecimal totalFreight;
    private String partnerName;
    private String stateInscription;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPrincipalCode() {
        return principalCode;
    }

    public void setPrincipalCode(String principalCode) {
        this.principalCode = principalCode;
    }

    public String getMarketplaceOrderId() {
        return marketplaceOrderId;
    }

    public void setMarketplaceOrderId(String marketplaceOrderId) {
        this.marketplaceOrderId = marketplaceOrderId;
    }

    public String getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(String sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public String getOrderGroup() {
        return orderGroup;
    }

    public void setOrderGroup(String orderGroup) {
        this.orderGroup = orderGroup;
    }

    public String getCustomerDocumentType() {
        return customerDocumentType;
    }

    public void setCustomerDocumentType(String customerDocumentType) {
        this.customerDocumentType = customerDocumentType;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerExternalEmail() {
        return customerExternalEmail;
    }

    public void setCustomerExternalEmail(String customerExternalEmail) {
        this.customerExternalEmail = customerExternalEmail;
    }

    public String getCustomerDocument() {
        return customerDocument;
    }

    public void setCustomerDocument(String customerDocument) {
        this.customerDocument = customerDocument;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getOrderOrigin() {
        return orderOrigin;
    }

    public void setOrderOrigin(String orderOrigin) {
        this.orderOrigin = orderOrigin;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getAffiliatedId() {
        return affiliatedId;
    }

    public void setAffiliatedId(String affiliatedId) {
        this.affiliatedId = affiliatedId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getGiftRegistryData() {
        return giftRegistryData;
    }

    public void setGiftRegistryData(String giftRegistryData) {
        this.giftRegistryData = giftRegistryData;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getMarketplaceServicesEndpoint() {
        return marketplaceServicesEndpoint;
    }

    public void setMarketplaceServicesEndpoint(String marketplaceServicesEndpoint) {
        this.marketplaceServicesEndpoint = marketplaceServicesEndpoint;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ZonedDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(ZonedDateTime lastChange) {
        this.lastChange = lastChange;
    }

    public String getCallCenterOperatorId() {
        return callCenterOperatorId;
    }

    public void setCallCenterOperatorId(String callCenterOperatorId) {
        this.callCenterOperatorId = callCenterOperatorId;
    }

    public String getCallCenterOperatorEmail() {
        return callCenterOperatorEmail;
    }

    public void setCallCenterOperatorEmail(String callCenterOperatorEmail) {
        this.callCenterOperatorEmail = callCenterOperatorEmail;
    }

    public String getCallCenterOperatorUserName() {
        return callCenterOperatorUserName;
    }

    public void setCallCenterOperatorUserName(String callCenterOperatorUserName) {
        this.callCenterOperatorUserName = callCenterOperatorUserName;
    }

    public String getFollowUpEmail() {
        return followUpEmail;
    }

    public void setFollowUpEmail(String followUpEmail) {
        this.followUpEmail = followUpEmail;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getOpenTextField() {
        return openTextField;
    }

    public void setOpenTextField(String openTextField) {
        this.openTextField = openTextField;
    }

    public BigDecimal getRoundingError() {
        return roundingError;
    }

    public void setRoundingError(BigDecimal roundingError) {
        this.roundingError = roundingError;
    }

    public String getOrderFormId() {
        return orderFormId;
    }

    public void setOrderFormId(String orderFormId) {
        this.orderFormId = orderFormId;
    }

    public String getCommercialConditionData() {
        return commercialConditionData;
    }

    public void setCommercialConditionData(String commercialConditionData) {
        this.commercialConditionData = commercialConditionData;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Boolean getAllowCancellation() {
        return allowCancellation;
    }

    public void setAllowCancellation(Boolean allowCancellation) {
        this.allowCancellation = allowCancellation;
    }

    public Boolean getAllowEdition() {
        return allowEdition;
    }

    public void setAllowEdition(Boolean allowEdition) {
        this.allowEdition = allowEdition;
    }

    public Boolean getCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public String getMarketplaceBaseURL() {
        return marketplaceBaseURL;
    }

    public void setMarketplaceBaseURL(String marketplaceBaseURL) {
        this.marketplaceBaseURL = marketplaceBaseURL;
    }

    public String getMarketplaceIsCertified() {
        return marketplaceIsCertified;
    }

    public void setMarketplaceIsCertified(String marketplaceIsCertified) {
        this.marketplaceIsCertified = marketplaceIsCertified;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }

    public ZonedDateTime getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(ZonedDateTime authorizedDate) {
        this.authorizedDate = authorizedDate;
    }

    public ZonedDateTime getInvoicedDate() {
        return invoicedDate;
    }

    public void setInvoicedDate(ZonedDateTime invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    public String getSellersName() {
        return sellersName;
    }

    public void setSellersName(String sellersName) {
        this.sellersName = sellersName;
    }

    public String getAcceptPartialOrder() {
        return acceptPartialOrder;
    }

    public void setAcceptPartialOrder(String acceptPartialOrder) {
        this.acceptPartialOrder = acceptPartialOrder;
    }

    public String getPartnerMarketplaceOrderId() {
        return partnerMarketplaceOrderId;
    }

    public void setPartnerMarketplaceOrderId(String partnerMarketplaceOrderId) {
        this.partnerMarketplaceOrderId = partnerMarketplaceOrderId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getTotalItemValue() {
        return totalItemValue;
    }

    public void setTotalItemValue(BigDecimal totalItemValue) {
        this.totalItemValue = totalItemValue;
    }

    public BigDecimal getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(BigDecimal totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public BigDecimal getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(BigDecimal totalFreight) {
        this.totalFreight = totalFreight;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getStateInscription() {
        return stateInscription;
    }

    public void setStateInscription(String stateInscription) {
        this.stateInscription = stateInscription;
    }




}
