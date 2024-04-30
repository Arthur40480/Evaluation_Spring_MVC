package fr.ldnr.web;

import fr.ldnr.business.IBusiness;
import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Customer;
import fr.ldnr.entities.Order;
import fr.ldnr.entities.OrderItem;
import fr.ldnr.entities.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@Controller
public class CartController {

    @Autowired
    IBusinessImpl iBusiness;

    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/cart")
    public String cart(Model model) {
        double total = iBusiness.getTotalAmount();
        model.addAttribute("total", total);
        model.addAttribute("cart", iBusiness.getCart());
        model.addAttribute("nbcart", iBusiness.getCart().size());
        if(iBusiness.getCart().isEmpty()) {
            model.addAttribute("empty", "Votre panier est vide !");
        }
        return "cart";
    }

    @GetMapping("/addToCart")
    public String addToCart(Long idSession, int page, Date date, RedirectAttributes redirectAttributes) {
        try {
            iBusiness.addToCart(iBusiness.findSessionById(idSession));
        }
        catch (Exception e) {
            redirectAttributes.addAttribute("error",e.getMessage());
            logger.error("[CART CONTROLLER : ADD ARTICLE TO CART] : {} " , e.getMessage());
        }
        return "redirect:/session?page=" + page;
    }

    @GetMapping("/removeToCart")
    public String removeToCart(Long idSession) {
        iBusiness.removeToCart(idSession);
        return "redirect:/cart";
    }

    @GetMapping("/customerForm")
    public String customerForm(Model model) {
        if(iBusiness.getCart().isEmpty()) return "redirect:/index";
        model.addAttribute("customer", new Customer());
        model.addAttribute("nbcart", iBusiness.getCart().size());

        return "customerForm";
    }

    @PostMapping("/validCustomerForm")
    public String validCustomerForm(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) return "customerForm";

        model.addAttribute("nbcart", iBusiness.getCart().size());
        model.addAttribute("cart", iBusiness.getCart());
        double total = iBusiness.getTotalAmount();
        model.addAttribute("total", total);
        model.addAttribute("customer", customer);
        iBusiness.setCustomer(customer);

        return "recapOrder";
    }

    @GetMapping("/confirmOrder")
    @Transactional
    public String confirmOrder(RedirectAttributes redirectAttributes) {
        if(iBusiness.getCart().isEmpty()) return "redirect:/index";
        try {
            Customer customer = iBusiness.getCustomer();
            iBusiness.createCustomer(customer);

            Order order = new Order(null, new Date(), iBusiness.getTotalAmount(), customer, null);
            iBusiness.createOrder(order);

            for(Session session : iBusiness.getCart().values()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setSession(session);
                orderItem.setPrice(session.getPrice());
                orderItem.setQuantity(session.getQuantity());
                iBusiness.createOrderItem(orderItem);
            }
            iBusiness.clearCart();
        } catch (Exception e) {
            redirectAttributes.addAttribute("error",e.getMessage());
            logger.error("[CART CONTROLLER : CONFIRM ORDER] : {} " , e.getMessage());
        }
        return "confirmOrder";
    }
}
