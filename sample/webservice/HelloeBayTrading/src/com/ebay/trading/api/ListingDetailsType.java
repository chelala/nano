// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.ebay.trading.api;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;
import java.util.Date;

/**
 * 
 * Various details about a listing. Some of the details are calculated or derived after
 * an item is listed. The details in this type include the start and end time and
 * the converted (localized) prices. The details in this type also include
 * input values applicable to the Best Offer feature.
 * Additional details in this type include flags indicating if a seller
 * specified fields whose values are not visible to the requesting user.
 * 
 */
public class ListingDetailsType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element(name = "Adult")
	@Order(value=0)
	public Boolean adult;	
	
	@Element(name = "BindingAuction")
	@Order(value=1)
	public Boolean bindingAuction;	
	
	@Element(name = "CheckoutEnabled")
	@Order(value=2)
	public Boolean checkoutEnabled;	
	
	@Element(name = "ConvertedBuyItNowPrice")
	@Order(value=3)
	public AmountType convertedBuyItNowPrice;	
	
	@Element(name = "ConvertedStartPrice")
	@Order(value=4)
	public AmountType convertedStartPrice;	
	
	@Element(name = "ConvertedReservePrice")
	@Order(value=5)
	public AmountType convertedReservePrice;	
	
	@Element(name = "HasReservePrice")
	@Order(value=6)
	public Boolean hasReservePrice;	
	
	@Element(name = "RelistedItemID")
	@Order(value=7)
	public String relistedItemID;	
	
	@Element(name = "SecondChanceOriginalItemID")
	@Order(value=8)
	public String secondChanceOriginalItemID;	
	
	@Element(name = "StartTime")
	@Order(value=9)
	public Date startTime;	
	
	@Element(name = "EndTime")
	@Order(value=10)
	public Date endTime;	
	
	@Element(name = "ViewItemURL")
	@Order(value=11)
	public String viewItemURL;	
	
	@Element(name = "HasUnansweredQuestions")
	@Order(value=12)
	public Boolean hasUnansweredQuestions;	
	
	@Element(name = "HasPublicMessages")
	@Order(value=13)
	public Boolean hasPublicMessages;	
	
	@Element(name = "BuyItNowAvailable")
	@Order(value=14)
	public Boolean buyItNowAvailable;	
	
	@Element(name = "SellerBusinessType")
	@Order(value=15)
	public SellerBusinessCodeType sellerBusinessType;	
	
	@Element(name = "MinimumBestOfferPrice")
	@Order(value=16)
	public AmountType minimumBestOfferPrice;	
	
	@Element(name = "MinimumBestOfferMessage")
	@Order(value=17)
	public String minimumBestOfferMessage;	
	
	@Element(name = "LocalListingDistance")
	@Order(value=18)
	public String localListingDistance;	
	
	@Element(name = "TCROriginalItemID")
	@Order(value=19)
	public String tcrOriginalItemID;	
	
	@Element(name = "ViewItemURLForNaturalSearch")
	@Order(value=20)
	public String viewItemURLForNaturalSearch;	
	
	@Element(name = "PayPerLeadEnabled")
	@Order(value=21)
	public Boolean payPerLeadEnabled;	
	
	@Element(name = "BestOfferAutoAcceptPrice")
	@Order(value=22)
	public AmountType bestOfferAutoAcceptPrice;	
	
	@AnyElement
	@Order(value=23)
	public List<Object> any;	
	
    
}