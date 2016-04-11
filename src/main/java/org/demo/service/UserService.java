package org.demo.service;

import org.demo.model.Item;
import org.demo.repository.ListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anton on 2016-04-11.
 */

@Service
public class UserService implements ItemService {

    private ListRepository listRepository;

    @Override
    public List<Item> findAll() {
        return
    }

    @Override
    public Item get(int id) {
        return null;
    }

    @Override
    public Integer getAId() {
        return null;
    }

    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public Item update(Item updatedItem) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
