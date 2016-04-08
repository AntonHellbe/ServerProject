package org.demo.repository;

import org.demo.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by Sebastian Börebäck on 2016-04-08.
 */
@Repository
public class ItemRepository {

	private ArrayList<Item> theList = new ArrayList<>();
	private Integer idCounter=0;

	public Iterable<Item> findAll() {
		return this.theList;
	}

	public Item get(Integer id) {
		return theList.get(id);
	}

	/**
	 * Fake Id generator
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

	public void delete(Integer id) {
		theList.remove(id);
	}


//	@Override
//	public <S extends T> List<S> save(Iterable<S> iterable) {
//		return null;
//	}
//
//	@Override
//	public Item findOne(Integer integer) {
//		return null;
//	}
//
//	@Override
//	public boolean exists(Integer integer) {
//		return false;
//	}
//
//	@Override
//	public List<Item> findAll() {
//		return null;
//	}
//
//	@Override
//	public long count() {
//		return 0;
//	}
//
//	@Override
//	public void delete(Integer integer) {
//
//	}
//
//	@Override
//	public void delete(Item item) {
//
//	}
//
//	@Override
//	public void delete(Iterable<? extends Item> iterable) {
//
//	}
//
//	@Override
//	public void deleteAll() {
//
//	}
//
//	@Override
//	public List<Item> findAll(Sort sort) {
//		return null;
//	}
//
//
//	public Item insert(Item item) {
//		return null;
//	}
}
