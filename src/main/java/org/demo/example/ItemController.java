package org.demo.example;

import org.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Sebastian Börebäck on 2016-04-08.
 */

/**
 * A Rest Item Controller.
 * Handles rest queries from clients
 * Rest api url: localhost:8080/items
 */
@CrossOrigin(origins = "*") //so that anyone can connect to the rest API
@RestController
@RequestMapping("/items") // the url mapping. =localhost:8080/items
public class ItemController {

	//connect to the ItemsService. so that we are able to save to the DB
	//through the service.
	@Autowired
	private ItemService service;

	/**
	 * Get all the items in the DB
	 * @return a list of items
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Item>> findItems() {
		System.out.println("getting all");
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	/**
	 * Gets a Item. has error control as well.
	 * // TODO: 2016-04-09 :18:44 Error handling should maybe be in the service
	 * so that the service doesnt return Item but ResponseEntitity
	 * @param id the id of the item that you want to get.
	 * @return the found item.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Item> findOne(@PathVariable("id") Integer id) {
		System.out.println("getting one id:" + id);


		Item got;
		try {
			got = service.get(id);
			System.out.println("found " + got);
			return new ResponseEntity<>(got, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("ERROR!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Add a item
	 * @param item the item that should be added
	 * @return OK if succeded to add
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		System.out.println("Add new Item");
		item.setId(service.getAId());
		return new ResponseEntity<>(service.save(item), HttpStatus.OK);
	}

	/**
	 * Update a item
	 * @param updatedItem the new update data
	 * @param id the id of the item to be updated
	 * @return OK if succeded to update
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Item> updateItem(@RequestBody Item updatedItem, @PathVariable Integer id) {
		System.out.println("Update Item");
		updatedItem.setId(id);
		return new ResponseEntity<>(service.update(updatedItem), HttpStatus.OK);
	}

	/**
	 * Delete a item
	 * @param id the item that should be deleted
	 * @return OK if succeded to delete
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteItem(@PathVariable Integer id) {
		System.out.println("delete item");
		service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

}
