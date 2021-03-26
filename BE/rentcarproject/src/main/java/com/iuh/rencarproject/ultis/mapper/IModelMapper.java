/**
 * @author DuyTT10
 * @date Mar 23, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.ultis.mapper;

import java.util.List;

public interface IModelMapper<O, M> {

	O convertModelToObject(M m, O o);

	List<O> convertListModelToListObject(List<M> ms);
}
