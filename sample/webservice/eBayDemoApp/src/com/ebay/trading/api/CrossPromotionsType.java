// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.ebay.trading.api;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;

/**
 * 
 * eBay Store Cross Promotions are no longer supported in the Trading API, so the
 * <b>CrossPromotion</b> container will either not be returned, or, if it is
 * returned, the data in the container may not be accurate. Contains one or
 * more items cross-promoted with the display or purchase of a referring item.
 * 
 */
public class CrossPromotionsType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element(name = "ItemID")
	@Order(value=0)
	public String itemID;	
	
	@Element(name = "PrimaryScheme")
	@Order(value=1)
	public PromotionSchemeCodeType primaryScheme;	
	
	@Element(name = "PromotionMethod")
	@Order(value=2)
	public PromotionMethodCodeType promotionMethod;	
	
	@Element(name = "SellerID")
	@Order(value=3)
	public String sellerID;	
	
	@Element(name = "ShippingDiscount")
	@Order(value=4)
	public boolean shippingDiscount;	
	
	@Element(name = "StoreName")
	@Order(value=5)
	public String storeName;	
	
	@Element(name = "PromotedItem")
	@Order(value=6)
	public List<PromotedItemType> promotedItem;	
	
	@AnyElement
	@Order(value=7)
	public List<Object> any;	
	
    
}