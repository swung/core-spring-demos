package com.gordo.jmxitems.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gordo.jmxitems.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
@Configurable
@RooIntegrationTest(entity = Item.class)
public class ItemIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    private ItemDataOnDemand dod;

	@Test
    public void testCountItems() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        long count = com.gordo.jmxitems.entity.Item.countItems();
        org.junit.Assert.assertTrue("Counter for 'Item' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindItem() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        java.lang.Long id = dod.getRandomItem().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to provide an identifier", id);
        com.gordo.jmxitems.entity.Item obj = com.gordo.jmxitems.entity.Item.findItem(id);
        org.junit.Assert.assertNotNull("Find method for 'Item' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Item' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllItems() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        long count = com.gordo.jmxitems.entity.Item.countItems();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Item', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.gordo.jmxitems.entity.Item> result = com.gordo.jmxitems.entity.Item.findAllItems();
        org.junit.Assert.assertNotNull("Find all method for 'Item' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Item' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindItemEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        long count = com.gordo.jmxitems.entity.Item.countItems();
        if (count > 20) count = 20;
        java.util.List<com.gordo.jmxitems.entity.Item> result = com.gordo.jmxitems.entity.Item.findItemEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'Item' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Item' returned an incorrect number of entries", count, result.size());
    }

	@Test
    @Transactional
    public void testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        java.lang.Long id = dod.getRandomItem().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to provide an identifier", id);
        com.gordo.jmxitems.entity.Item obj = com.gordo.jmxitems.entity.Item.findItem(id);
        org.junit.Assert.assertNotNull("Find method for 'Item' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyItem(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Item' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }

	@Test
    @Transactional
    public void testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        java.lang.Long id = dod.getRandomItem().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to provide an identifier", id);
        com.gordo.jmxitems.entity.Item obj = com.gordo.jmxitems.entity.Item.findItem(id);
        org.junit.Assert.assertNotNull("Find method for 'Item' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyItem(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Item' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }

	@Test
    @Transactional
    public void testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        com.gordo.jmxitems.entity.Item obj = dod.getNewTransientItem(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Item' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Item' identifier to no longer be null", obj.getId());
    }

	@Test
    @Transactional
    public void testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to initialize correctly", dod.getRandomItem());
        java.lang.Long id = dod.getRandomItem().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Item' failed to provide an identifier", id);
        com.gordo.jmxitems.entity.Item obj = com.gordo.jmxitems.entity.Item.findItem(id);
        org.junit.Assert.assertNotNull("Find method for 'Item' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Item' with identifier '" + id + "'", com.gordo.jmxitems.entity.Item.findItem(id));
    }
}
