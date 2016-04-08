package org.demo.controller;

import org.demo.model.Item;
import org.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Sebastian Börebäck on 2016-04-08.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Item> findItems() {
		System.out.println("getting all");
		return repo.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Item findOne(Integer id) {
		System.out.println("getting one id:" + id);

		Item got = repo.get(id);
		System.out.println("found " + got);
		return got;


	}

	@RequestMapping(method = RequestMethod.POST)
	public Item addItem(@RequestBody Item item) {
		System.out.println("Add new Item");
		item.setId(repo.getAId());
		return repo.save(item);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Item updateItem(@RequestBody Item updatedItem, @PathVariable Integer id) {
		System.out.println("Update Item");
		updatedItem.setId(id);
		return repo.update(updatedItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		System.out.println("delete item");
		repo.delete(id);
	}

}
