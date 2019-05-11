package com.example.paul.integration;

import com.example.paul.controllers.TransactionRestController;
import com.example.paul.utils.AccountInput;
import com.example.paul.utils.TransactionInput;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class MakeTransferIntegrationTest {

    @Autowired
    private TransactionRestController transactionRestController;

    @Test
    public void givenTransactionDetails_whenMakeTransaction_thenVerifyTransactionIsProcessed() {
        // given
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("73084635");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("65-93-37");
        targetAccount.setAccountNumber("21956204");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(27.5);
        input.setReference("My reference");
        input.setLatitude(45.0000000);
        input.setLongitude(90.0000000);

        // when
        var body = transactionRestController.makeTransfer(input).getBody();

        // then
        var isComplete = (Boolean) body;
        Assertions.assertThat(isComplete).isTrue();
    }
}