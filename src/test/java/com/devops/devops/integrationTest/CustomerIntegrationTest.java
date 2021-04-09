package com.devops.devops.integrationTest;
import com.devops.devops.dto.Customer;
import org.junit.Rule;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CustomerIntegrationTest {
    // test comment
    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.withUnit("integration-test");

    @Test
    public void testSavingNewCustomer() {
        this.provider.begin();
        this.provider.em().persist(new Customer("John", "Duke", LocalDate.of(2000, 12, 12)));
        this.provider.em().persist(new Customer("Foo", "Bar", LocalDate.of(2000, 12, 12)));
        this.provider.em().persist(new Customer("Paul", "One", LocalDate.of(2000, 12, 12)));

        List<Customer> resultList = this.provider.em()
                .createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();

        assertEquals(3, resultList.size());

        for (Customer resultCustomer : resultList) {
            assertNotNull(resultCustomer.getId());
        }

        this.provider.commit();
    }

}