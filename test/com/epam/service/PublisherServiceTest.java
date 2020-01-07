/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.service.impl.PublisherServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static com.epam.TestData.PUBLISHER_DAO;

public class PublisherServiceTest {
    PublisherService publisherService = new PublisherServiceImpl(PUBLISHER_DAO);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void insert() {

    }

    @Test
    public void select() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}