package com.capgemini.test;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.mockito.Mockito.when;
public class AccountTest {

	@Mock
	AccountRepository accountRepository;
	//@Mock
	//Account account;
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1. when the amount is less than 500 system should throw exception
	 * 2. when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException
	{
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account, accountService.createAccount(101, 5000));
		
	}
	
	
	/*
	 * Show Balance
	 * 1. when the valid account number is passed show Balance
	 * 2. when the invalid account number is passed throw an exception
	 */
	
	@Test
	public void whenValidAccountNumberIsPassedShowBalance() throws InvalidAccountNumberException
	{
		//when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(accountService.showBalance(101),6000);
	}
	@Test//(expected=com.capgemini.exceptions.InsufficientInitialBalanceException.class)
	public void whenInvalidAccountNumberIsPassed() throws InsufficientInitialBalanceException
	{
Account account = new Account();
		
		account.setAccountNumber(102);
		account.setAmount(6000);
		when(accountRepository.searchAccount(565)).thenReturn(null);
		System.out.println(account.getAccountNumber());
		assertNotEquals(565,account.getAccountNumber());
	}
	
	
}
