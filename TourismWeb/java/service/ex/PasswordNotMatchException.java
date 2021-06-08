package cn.tedu.store.service.ex;

public class PasswordNotMatchException extends RuntimeException{
	private static final long serialVersionUID = -8903428607881147665L;
	public PasswordNotMatchException() {
	}
	public PasswordNotMatchException(String msg) {
		super(msg);
	}
}
