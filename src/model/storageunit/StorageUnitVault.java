package model.storageunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map.Entry;

import model.common.IModel;
import model.common.Vault;
import model.item.Item;
import model.product.Product;
import model.productgroup.ProductGroup;
import common.Result;
import common.util.QueryParser;


/**
 * The Product class provides a way to query for ProductModels within the select data backend
 * (A Model superclass or interface could be created for this)
 * <PRE>
 * Product.find(2) // returns ProductModel with id of 2
 * </PRE>
 * Other findBy* methods may be implemented.
 */
public class StorageUnitVault extends Vault {
	
	/**
	 * Returns just one StorageUnit based on the query sent in. 
	 * If you need more than one StorageUnit returned use FindAll
	 * 
	 * @param attribute Which attribute should we search on for each Product
	 * @param value What value does the column have
	 * 
	 */
	public StorageUnit find(String query)  {
		QueryParser MyQuery = new QueryParser(query);

		
		//Do a linear Search first
		//TODO: Add ability to search by index
		try {
			return linearSearch(MyQuery,1).get(0);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	/**
	 * Returns a list of StorageUnits which match the criteria
	 * 
	 * @param attribute 
	 * @param value
	 * 
	 */
	public ArrayList<StorageUnit> findAll(String query) {
		QueryParser MyQuery = new QueryParser(query);

		
		//Do a linear Search first
		//TODO: Add ability to search by index
		try {
			ArrayList<StorageUnit> results = linearSearch(MyQuery,0);
			return results;
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private ArrayList<StorageUnit> linearSearch(QueryParser MyQuery,int count) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		ArrayList<StorageUnit> results = new ArrayList<StorageUnit>();
		String objName = MyQuery.getObjName();
		String attrName = MyQuery.getAttrName();
		String value = MyQuery.getValue();
		

		StorageUnit mySU = new StorageUnit();
		
		//Class associated with the product model
		Class suCls = mySU.getClass();
		//Method we will call to get the value
		Method method;
		method = suCls.getMethod("get"+attrName);

		
		//Loop through entire hashmap and check values one at a time
		for (Entry<Integer, IModel> entry : this.dataVault.entrySet()) {
			mySU = (StorageUnit) entry.getValue();
			String mySUValue; 
			mySUValue = (String) method.invoke(mySU, null);

		    if(mySUValue.equals(value)){
		    	results.add(mySU);
		    }
		    if(count != 0 && results.size() == count )
		    	return results;
		}
		return results;
	}
	
	
	/**
	 * Checks if the model passed in already exists in the current map
	 * - Must have a unique name
	 * 
	 * @param model
	 * @return Result of the check
	 */
	protected static Result validateNew(StorageUnit model){
		return null;
	}

	/**
	 * Checks if the model already exists in the map
	 * 
	 * @param model
	 * @return Result of the check
	 */
	protected static Result validateModified(StorageUnit model){
		return null;
	}

	/**
	 * Adds the StorageUnit to the map if it's new.  Should check before doing so.
	 * 
	 * @param model StorageUnit to add
	 * @return Result of request
	 */
	protected static Result saveNew(StorageUnit model){
		return null;
	}

	/**
	 * Adds the StorageUnit to the map if it already exists.  Should check before doing so.
	 * 
	 * @param model StorageUnit to add
	 * @return Result of request
	 */
	protected static Result saveModified(StorageUnit model){
		return null;
	}
}

