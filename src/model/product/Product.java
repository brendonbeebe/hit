package model.product;

import model.common.IModel;
import common.Result;

/**
 * The Product class encapsulates all the funtions and data associated with a "Product".
 * It extends the {@link model.product.ProductData ProductData} 
 * 	class which contains getters and setters for the various datas.
 */

public class Product implements IModel{
	/**
	 * A unique ID is associated with every Product once it is presisted to the vault.
	 * _id is not set by the user, but by the vault when it is saved.
	 * _id can be -1 if it is new and has not been saved
	 */
	private int _id;

	/**
	 * When a change is made to the data it becomes invalid and 
	 * must be validated before it can be saved.
	 * _valid maintains this state
	 */
	private boolean _valid;

	/**
	 * _saved maintaines the state of if the instance of the model is the same as the 
	 * persisted model in the vault.
	 */
	private boolean _saved;	
	private String name;
	
	/**
	 * Constructor
	 */
	public Product(){
		//super();
		_id = -1;
		_valid = false;
		_saved = false;
	}

	/**
	 * Is the Product saved?
	 */
	public boolean isSaved(){
		return this._saved;
	}

	/**
	 * Is the Product valid?
	 */
	public boolean isValid(){
		return this._valid;
	}

	/**
	 * If the Product is valid it is saved into the vault.
	 */
	public Result save(){
		return new Result(false, "Saving is not yet implemented");
	}

	/**
	 * Validate that the product is able to be saved into the vault.
	 */
	public Result validate(){
		return new Result(false, "Validating is not yet implemented");
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
}