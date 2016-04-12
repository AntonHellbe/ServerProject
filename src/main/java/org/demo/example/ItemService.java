package org.demo.example;

import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-04-09.
 */

/**
 * A Item Service interface used to interact with the repository.
 * Here you can add diffrent type of search function like Between 2 dates.
 */
public interface ItemService {

	/**
	 * Fetches all the items in the list
	 * @return all items in the list
	 **/
	public List<Item> findAll();

	/**
	 * Fetches the specified item from the list
	 * @param id the id of the item we want to fetch
	 * @return the sought after id
	 **/
	public Item get(int id);

	/**
	 * Fake Id generator only to make id:es
	 * @return a increased id
	 */
	public Integer getAId();

	/**
	 * Saves the item in the list
	 * @param item the item to be saved
	 * @return the saved item
	 **/
	public Item save(Item item);

	/**
	 * Updates an item in the list
	 * @param updatedItem The updated information
	 * @return the updated item
	 **/
	public Item update(Item updatedItem);

	/**
	 * Deletes an item from the list
	 * @param id the id of the item to be removed
	 **/
	public void delete(int id);
}
