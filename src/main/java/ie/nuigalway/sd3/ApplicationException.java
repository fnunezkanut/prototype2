package ie.nuigalway.sd3;

//custom exception thrown by my controllers
public class ApplicationException extends Exception {

	public ApplicationException(){
		super();
	}

	public ApplicationException(String err){
		super(err);
	}
}
