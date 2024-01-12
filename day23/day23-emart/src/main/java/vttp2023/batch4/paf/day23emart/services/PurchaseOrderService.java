package vttp2023.batch4.paf.day23emart.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day23emart.repositories.PurchaseOrderException;
import vttp2023.batch4.paf.day23emart.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

   @Autowired
   private PurchaseOrderRepository poRepo;

   @Autowired
   private LineItemRepository liRepo;

	@Autowired
	private PlatformTransactionManager txMgr;

	public boolean createPurchaseOrderManualTx(PurchaseOrder po) {
		System.out.printf(">>>> using manual transaction \n");
		TransactionStatus tx = txMgr.getTransaction(null);
      String poId = UUID.randomUUID().toString().substring(0, 8);
      po.setOrderId(poId);
		try {
			boolean result = poRepo.insertPurchaseOrder(po) 
               && liRepo.insertLineItems(poId, po.getLineItems());
			txMgr.commit(tx);
			return result;
		} catch (Exception ex) {
			System.out.printf(">>>> rolling back transaction\n");
			ex.printStackTrace();
			txMgr.rollback(tx);
		}
		return false;
	}

   @Transactional(rollbackFor = PurchaseOrderException.class)
   public boolean createPurchaseOrder(PurchaseOrder po) throws PurchaseOrderException {
      String poId = UUID.randomUUID().toString().substring(0, 8);
      System.out.printf(">>>>> poId: %s\n", poId);
      po.setOrderId(poId);
      try {
         return poRepo.insertPurchaseOrder(po) 
               && liRepo.insertLineItems(poId, po.getLineItems());
      } catch (PurchaseOrderException ex) {
         System.out.println("----- exception occured");
         throw ex;
      }
   }
}
