package edu.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiyu
 *
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id, boolean lock);
	
	List<T> findAll();
	
	List<T> findByExample(T example, String... excludeProperty);
	
	void makePersistent(T entity);
	
	void makeTransient(T entity);
	
	void flush();
	
	void clear();
}
