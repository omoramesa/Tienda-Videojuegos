package util;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CustomErrorType {
	private String errorMessage;
	private boolean validationOk;
	private MessageType type;

	public CustomErrorType() {

	}

	public CustomErrorType(String errorMessage, MessageType type) {
		this.errorMessage = errorMessage;
		this.type = type;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isValidationOk() {
		return validationOk;
	}

	public void setValidationOk(boolean validationOk) {
		this.validationOk = validationOk;
	}

	public List<String> processValidationError(BindingResult bindingResult) {
		List<FieldError> errors = bindingResult.getFieldErrors();
		List<String> message = new ArrayList<>();
		for (FieldError e : errors) {
			if (e.getDefaultMessage() != null) {
				message.add(e.getField() + " " + e.getDefaultMessage());
			}
		}

		return message;
	}
}
