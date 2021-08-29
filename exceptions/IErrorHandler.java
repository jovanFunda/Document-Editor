package exceptions;

import model.observer.ISubject;

public interface IErrorHandler extends ISubject {
	
	void generateError(ErrorType errorType);

}
