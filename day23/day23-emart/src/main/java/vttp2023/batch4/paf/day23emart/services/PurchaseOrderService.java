package vttp2023.batch4.paf.day23emart.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day23emart.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

   @Autowired
   private PurchaseOrderRepository poRepo;

   @Autowired
   private LineItemRepository liRepo;

   public boolean createPurchaseOrder(PurchaseOrder po) {
      String poId = UUID.randomUUID().toString().substring(0, 8);
      po.setOrderId(poId);
      return poRepo.insertPurchaseOrder(po) 
            && liRepo.insertLineItems(poId, po.getLineItems());
   }
}
