package cn.tedu.store.service.ex;

public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -2111882581918102362L;
	public UserNotFoundException() {
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
