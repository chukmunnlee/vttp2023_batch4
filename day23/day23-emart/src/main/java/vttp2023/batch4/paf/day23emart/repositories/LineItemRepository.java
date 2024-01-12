package vttp2023.batch4.paf.day23emart.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day23emart.models.LineItem;

@Repository
public class LineItemRepository {

   @Autowired
   private JdbcTemplate template;

   public boolean insertLineItems(String poId, List<LineItem> lineItems) 
         throws PurchaseOrderException {
      int count = 0;

      for (LineItem li: lineItems) {
         int inserted = template.update(Queries.SQL_INSERT_LINE_ITEM
               , li.getItem(), li.getQuantity(), poId);
         count += inserted;
         if (count > 1) {
            throw new PurchaseOrderException("fake error");
         }
      }
      System.out.printf("--- inserted: %d, actual: %d\n", count, lineItems.size());
      return count == lineItems.size();
   }
}
