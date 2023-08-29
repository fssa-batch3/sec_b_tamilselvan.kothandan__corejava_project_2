package in.fssa.doc4you.interfaces;

import java.util.Set;


/**
 * The Base interface provides a generic template for basic CRUD (Create, Read, Update, Delete) operations
 * on entities. Classes implementing this interface are expected to provide concrete implementations for these methods.
 *
 * @param <T> The type of entity this interface operates on.
 */

public interface Base<T> {
	 /**
     * Creates a new entity.
     *
     * @param newT The new entity object to be created.
     * @return The identifier (such as ID) of the created entity.
     */
	public abstract int create(T newT);
	
	 /**
     * Retrieves a set of all entities.
     *
     * @return A set containing all entities available in the system.
     */
	public abstract Set<T> findAll();
	
	 /**
     * Updates an existing entity.
     *
     * @param id    The identifier of the entity to be updated.
     * @param newT  The updated entity object.
     */
	public abstract void update(int id , T newT);
	
	/**
     * Deletes an entity.
     *
     * @param id The identifier of the entity to be deleted.
     */
	public abstract void delete(int id);
	
	/**
     * Retrieves an entity by its identifier.
     *
     * @param id The identifier of the entity to be retrieved.
     * @return The entity object corresponding to the provided identifier.
     */
	public abstract  <T>T findById(int id);
}