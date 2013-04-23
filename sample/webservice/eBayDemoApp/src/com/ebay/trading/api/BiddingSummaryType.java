// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.ebay.trading.api;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;

/**
 * 
 * Contains bidding summary information for the bidder of an item.
 * 
 */
public class BiddingSummaryType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element(name = "SummaryDays")
	@Order(value=0)
	public Integer summaryDays;	
	
	@Element(name = "TotalBids")
	@Order(value=1)
	public Integer totalBids;	
	
	@Element(name = "BidActivityWithSeller")
	@Order(value=2)
	public Integer bidActivityWithSeller;	
	
	@Element(name = "BidsToUniqueSellers")
	@Order(value=3)
	public Integer bidsToUniqueSellers;	
	
	@Element(name = "BidsToUniqueCategories")
	@Order(value=4)
	public Integer bidsToUniqueCategories;	
	
	@Element(name = "BidRetractions")
	@Order(value=5)
	public Integer bidRetractions;	
	
	@Element(name = "ItemBidDetails")
	@Order(value=6)
	public List<ItemBidDetailsType> itemBidDetails;	
	
	@AnyElement
	@Order(value=7)
	public List<Object> any;	
	
    
}