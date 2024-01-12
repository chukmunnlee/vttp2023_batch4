package vttp2023.batch4.paf.day23emart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.repositories.PurchaseOrderException;
import vttp2023.batch4.paf.day23emart.services.PurchaseOrderService;

@Controller
@RequestMapping
public class PurchaseOrderController {

	public static final String ATTR_PURCHASE_ORDER = "po";
	public static final String CTRL_ITEM = "item";
	public static final String CTRL_QUANTITY = "quantity";

	@Autowired
	private PurchaseOrderService poSvc;

	@GetMapping(path = { "/", "/index.html" })
	public ModelAndView getIndex(HttpSession sess) {
		ModelAndView mav = new ModelAndView("index.html");
		PurchaseOrder po = getPurchaseOrder(sess);

		mav.addObject(ATTR_PURCHASE_ORDER, po);

		return mav;
	}

	@PostMapping("/checkout")
	public ModelAndView posCheckout(HttpSession sess) {

		ModelAndView mav = new ModelAndView("index.html");

		PurchaseOrder po = getPurchaseOrder(sess);

		if (!poSvc.createPurchaseOrderManualTx(po)) {
			mav.setStatus(HttpStatusCode.valueOf(500));
			mav.addObject(ATTR_PURCHASE_ORDER, po);

		} else {
			mav.setStatus(HttpStatusCode.valueOf(200));
			sess.invalidate();
			mav.addObject(ATTR_PURCHASE_ORDER, new PurchaseOrder());
		}

		/*
		try {
			if (!poSvc.createPurchaseOrder(po)) {
				mav.setStatus(HttpStatusCode.valueOf(500));
				mav.addObject(ATTR_PURCHASE_ORDER, po);

			} else {
				mav.setStatus(HttpStatusCode.valueOf(200));
				sess.invalidate();
				mav.addObject(ATTR_PURCHASE_ORDER, new PurchaseOrder());
			}
		} catch (PurchaseOrderException ex) {
			mav.setStatus(HttpStatusCode.valueOf(500));
			mav.addObject(ATTR_PURCHASE_ORDER, po);
		}
		*/

		return mav;
	}

	@PostMapping("/order")
	public ModelAndView postOrder(HttpSession sess, @ModelAttribute PurchaseOrder inputPo,
			@RequestBody MultiValueMap<String, String> form) {

		PurchaseOrder po = getPurchaseOrder(sess);
		po.setName(inputPo.getName());
		po.setAddress(inputPo.getAddress());

		LineItem li = new LineItem();
		li.setItem(form.getFirst(CTRL_ITEM));
		li.setQuantity(Integer.parseInt(form.getFirst(CTRL_QUANTITY)));
		po.getLineItems().add(li);

		ModelAndView mav = new ModelAndView("index.html");

		System.out.printf(">>>> form %s\n", form);
		System.out.printf(">>>> inputPo %s\n", inputPo);

		mav.addObject(ATTR_PURCHASE_ORDER, po);

		return mav;
	}

	private PurchaseOrder getPurchaseOrder(HttpSession sess) {
		Object o = sess.getAttribute(ATTR_PURCHASE_ORDER);
		PurchaseOrder po;
		if (null == o) {
			po = new PurchaseOrder();
			sess.setAttribute(ATTR_PURCHASE_ORDER, po);
		} else
			po = (PurchaseOrder) o;
		return po;
	}
}
