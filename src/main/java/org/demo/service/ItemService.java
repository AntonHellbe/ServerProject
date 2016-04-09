package org.demo.service;

import org.demo.model.Item;

import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-04-09.
 */

/**
 * A Item Service interface used to interact with the repository.
 * Here you can add diffrent type of search function like Between 2 dates.
 */
public interface ItemService {

	public List<Item> findAll();

	public Item get(int id);

	/**
	 * Fake Id generator
	 *
	 * @return a increased id
	 */
	public Integer getAId();

	public Item save(Item item);

	public Item update(Item updatedItem);

	public void delete(int id);
}
