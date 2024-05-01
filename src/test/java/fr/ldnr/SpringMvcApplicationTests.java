package fr.ldnr;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringMvcApplicationTests {

	@Autowired
	IBusinessImpl iBusiness;

	private static Instant startedAt;

	@Test
	void contextLoads() {
		assertFalse(1==2);
	}

	@BeforeEach
	public void clearCart() {
		System.out.println("Je vide le panier");
		iBusiness.clearCart();
	}

	@Test
	public void addSessionToCart() {
		//GIVEN
		Session session = new Session(new Date(), "11:00", 7, null);

		//WHEN
		iBusiness.addToCart(session);

		//THEN
		assertFalse(iBusiness.getCart().isEmpty());
	}

	@Test
	public void removeSessionToCart() {
		//Given
		Session session = new Session(1L,new Date(), "11:00", 7, null);

		//WHEN
		iBusiness.addToCart(session);
		iBusiness.removeToCart(session.getId());

		//THEN
		assertTrue(iBusiness.getCart().isEmpty());
	}

	@Test
	public void sessionQuantityIncreases() {

		//Given
		Session session = new Session(1L,new Date(), "11:00", 7, null);

		//WHEN
		iBusiness.addToCart(session);
		iBusiness.addToCart(session);
		iBusiness.removeToCart(session.getId());
		iBusiness.addToCart(session);

		//THEN
		assertEquals(session.getQuantity(), 2);
	}

	@Test
	public void amountCart() {

		//Given
		Session session = new Session(1L,new Date(), "11:00", 30, null);
		Session session2 = new Session(2L,new Date(), "11:00", 40, null);
		Session session3 = new Session(3L,new Date(), "11:00", 130, null);

		//WHEN
		iBusiness.addToCart(session);
		iBusiness.addToCart(session2);
		iBusiness.addToCart(session3);

		//THEN
		assertEquals(iBusiness.getTotalAmount(), 200);
	}

}
