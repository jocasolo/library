package com.at.library.service;

import java.util.List;

public interface CommonService {

	/**
	 * Transforma un objeto de un tipo, en el tipo indicado por par�metro.
	 * 
	 * @param source
	 * @param destinationClass
	 * @return
	 */
	<T, S> T transform(S source, Class<T> destinationClass);

	/**
	 * Transforma una lista de objetos de un tipo, en otra lista de objetos del
	 * tipo indicado por par�metro.
	 * 
	 * @param sources
	 * @param destinationClass
	 * @return
	 */
	<T, S> List<T> transform(List<S> sources, Class<T> destinationClass);

}
