// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.ebay.trading.api;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;
import java.util.Date;

/**
 * 
 * Details about a Best Offer.
 * 
 */
public class BestOfferType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element(name = "BestOfferID")
	@Order(value=0)
	public String bestOfferID;	
	
	@Element(name = "ExpirationTime")
	@Order(value=1)
	public Date expirationTime;	
	
	@Element(name = "Buyer")
	@Order(value=2)
	public UserType buyer;	
	
	@Element(name = "Price")
	@Order(value=3)
	public AmountType price;	
	
	@Element(name = "Status")
	@Order(value=4)
	public BestOfferStatusCodeType status;	
	
	@Element(name = "Quantity")
	@Order(value=5)
	public Integer quantity;	
	
	@Element(name = "BuyerMessage")
	@Order(value=6)
	public String buyerMessage;	
	
	@Element(name = "SellerMessage")
	@Order(value=7)
	public String sellerMessage;	
	
	@Element(name = "BestOfferCodeType")
	@Order(value=8)
	public BestOfferTypeCodeType bestOfferCodeType;	
	
	@Element(name = "CallStatus")
	@Order(value=9)
	public String callStatus;	
	
	@AnyElement
	@Order(value=10)
	public List<Object> any;	
	
    
}