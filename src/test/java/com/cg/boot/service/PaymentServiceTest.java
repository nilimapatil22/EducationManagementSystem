package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Payment;
import com.cg.boot.repository.PaymentRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

/**
 * 
 * @author Nilima
 *
 */
@SpringBootTest
class PaymentServiceTest {

	@Autowired
	IPaymentService service;

	@MockBean
	PaymentRepository repository;

	PaymentService listMock = mock(PaymentService.class, "my mock");

	/**
	 * This method test getAllPayment() method and also specify the condition if the
	 * size of payment list is exactly equal to given size in the function then test
	 * will pass.
	 * 
	 * @param list : {@link List}
	 */
	@Test
	public void getAllPaymentTest() {
		when(repository.findAll()).thenReturn(
				Stream.of(new Payment(26, 2500.0, "credit", 3456789067l, "successful", 9)).collect(Collectors.toList()));
		assertNull(service.getAllPayment());
	}

	/**
	 * This method test addPayment() method using Mockito and also specify the
	 * condition. Check that the data is added or not.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void addPaymentTest() {
		Payment payment = new Payment(27, 2500.0, "credit", 9876789876l, "successful", 37);
		when(repository.save(payment)).thenReturn(payment);
		assertEquals(payment, service.addPayment(payment));
	}
    /**
     * 
     */
	@Test
	public void updatePaymentTest() {
		Payment payment = new Payment(27, 3000.0, "credit", 8765432123l, "unsuccessful", 9);
		when(repository.save(payment)).thenReturn(payment);
		assertNotEquals(payment, service.updatePayment(payment, 1));
	}

	@Test
	public void deletePaymentTest() {
		Payment payment = new Payment(26, 2500.0, "credit", 3456789067l, "successful", 10);
		service.deletePayment(payment.getStudentId(), 1);
		verify(repository, times(1)).deleteById(payment.getPaymentId());
	}

	@Test
	public void isValidAccountNumberTest() {
		when(listMock.isvaliAccountNumber(234567890689l)).thenReturn(true);
		boolean accountFlag = listMock.isvaliAccountNumber(234567890689l);
		verify(listMock).isvaliAccountNumber(234567890689l);
		assertThat(accountFlag);
	}

	@Test
	public void isvalidStatusTest() {
		when(listMock.isvalidStatus("sucessful")).thenReturn(true);
		boolean statusFlag = listMock.isvalidStatus("successful");
		verify(listMock).isvalidStatus("unsuccessful");
		assertThat(statusFlag);
	}

	@Test
	public void isvalidTypeTest() {
		when(listMock.isvalidStatus("credit")).thenReturn(true);
		boolean typeFlag = listMock.isvalidType("credit");
		verify(listMock).isvalidType("debit");
		assertThat(typeFlag);
	}

}
