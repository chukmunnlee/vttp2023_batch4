package vttp2023.batch4.paf.day23emart.models;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class PurchaseOrder {
   private String orderId;
   private String name;
   private String address;
   private Date createdOn;
   private Date lastUpdate;
	private List<LineItem> lineItems = new LinkedList<>();

   public String getOrderId() { return orderId; }
   public void setOrderId(String orderId) { this.orderId = orderId; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getAddress() { return address; }

   public void setAddress(String address) { this.address = address; }

   public Date getCreatedOn() { return createdOn; }
   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

   public Date getLastUpdate() { return lastUpdate; }
   public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

	public List<LineItem> getLineItems() { return lineItems; }
	public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }

   @Override
   public String toString() {
      return "PurchaseOrder [orderId=" + orderId + ", name=" + name 
            + ", address=" + address + ", createdOn="
            + createdOn + ", lastUpdate=" + lastUpdate + "]";
   }
}
