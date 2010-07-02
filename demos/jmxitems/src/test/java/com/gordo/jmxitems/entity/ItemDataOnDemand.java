package com.gordo.jmxitems.entity;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.gordo.jmxitems.entity.Item;

@Component
@Configurable
@RooDataOnDemand(entity = Item.class)
public class ItemDataOnDemand {

	private Random rnd = new java.security.SecureRandom();

	private List<Item> data;

	public Item getNewTransientItem(int index) {
        com.gordo.jmxitems.entity.Item obj = new com.gordo.jmxitems.entity.Item();
        return obj;
    }

	public Item getSpecificItem(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Item obj = data.get(index);
        return Item.findItem(obj.getId());
    }

	public Item getRandomItem() {
        init();
        Item obj = data.get(rnd.nextInt(data.size()));
        return Item.findItem(obj.getId());
    }

	public boolean modifyItem(Item obj) {
        return false;
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void init() {
        if (data != null) {
            return;
        }
        
        data = com.gordo.jmxitems.entity.Item.findItemEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Item' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<com.gordo.jmxitems.entity.Item>();
        for (int i = 0; i < 10; i++) {
            com.gordo.jmxitems.entity.Item obj = getNewTransientItem(i);
            obj.persist();
            data.add(obj);
        }
    }
}
