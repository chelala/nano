// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.ebay.trading.api;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;

/**
 * 
 * Container consisting of shipping costs and other details related to a domestic
 * shipping service. An exception to the domestic shipping service rule is seen in the
 * ShippingServiceSelected container returned under the Order and Transaction
 * containers in order and order line item retrieval calls such as GetOrders or
 * GetItemTransactions. In this scenario, the SelectedShippingService container
 * consists of either domestic or international shipping service data, based on the
 * selected service according to the buyer's shipping address.
 * <br/><br/>
 * If one or more international shipping services are provided, the
 * seller must specify at least one domestic shipping service as well.
 * <br/><br/>
 *             <span class="tablenote">
 *             <strong>Note:</strong> For GetItemTransactions and GetSellerTransactions, this container does not return accurate shipping service and cost information for multiple line item orders. Use GetOrders instead, and provide the order's combined <strong>OrderID</strong> to retrieve this information.
 *             </span>
 * 
 */
public class ShippingServiceOptionsType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element(name = "ShippingInsuranceCost")
	@Order(value=0)
	public AmountType shippingInsuranceCost;	
	
	@Element(name = "ShippingService")
	@Order(value=1)
	public String shippingService;	
	
	@Element(name = "ShippingServiceCost")
	@Order(value=2)
	public AmountType shippingServiceCost;	
	
	@Element(name = "ShippingServiceAdditionalCost")
	@Order(value=3)
	public AmountType shippingServiceAdditionalCost;	
	
	@Element(name = "ShippingServicePriority")
	@Order(value=4)
	public Integer shippingServicePriority;	
	
	@Element(name = "ExpeditedService")
	@Order(value=5)
	public Boolean expeditedService;	
	
	@Element(name = "ShippingTimeMin")
	@Order(value=6)
	public Integer shippingTimeMin;	
	
	@Element(name = "ShippingTimeMax")
	@Order(value=7)
	public Integer shippingTimeMax;	
	
	@Element(name = "ShippingSurcharge")
	@Order(value=8)
	public AmountType shippingSurcharge;	
	
	@Element(name = "FreeShipping")
	@Order(value=9)
	public Boolean freeShipping;	
	
	@Element(name = "LocalPickup")
	@Order(value=10)
	public Boolean localPickup;	
	
	@Element(name = "ImportCharge")
	@Order(value=11)
	public AmountType importCharge;	
	
	@AnyElement
	@Order(value=12)
	public List<Object> any;	
	
    
}