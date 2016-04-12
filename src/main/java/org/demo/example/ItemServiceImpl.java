package org.demo.example;

import org.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-04-09.
 */

/***
 * Implements the Service interface
 * and has the DB connection
 */
@Service
public class ItemServiceImpl implements ItemService{


	// TODO: 2016-04-09 :17:46 This is the DB conncection
	//in our first case it will be the file. loaded in and generate the data.
	@Autowired
	ItemRepository itemRepository;

	private ArrayList<Item> theList = new ArrayList<>();
	private Integer idCounter=0;

	public List<Item> findAll() {
		return this.theList;
	}

	public Item get(int id) {
		return theList.get(id);
	}

	/**
	 * Fake Id generator only to make id:es
	 * @return a increased id
	 */
	public Integer getAId() {
		return theList.size();
	}

	public Item save(Item item) {
		theList.add(item);
		return item;
	}

	public Item update(Item updatedItem) {
		theList.set(updatedItem.getId(),updatedItem);
		return theList.get(updatedItem.getId());
	}

	public void delete(int id) {
		theList.remove(id);
		theList.forEach(item -> System.out.println(item));
	}
}
