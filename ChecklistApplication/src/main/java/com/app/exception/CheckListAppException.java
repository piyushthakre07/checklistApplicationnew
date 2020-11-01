package com.app.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Piyush.Thakre
 *
 */
@Getter
@Setter
public class CheckListAppException extends Exception {
	private static final long serialVersionUID = 1L;
	private int code = -1;
	private String developerMessage;
	private List<String> errors = new ArrayList<>();
	private int errorCodeValue = -1;
	public final static int SUCCESSFUL_REQUEST = 200;
	public final static int SUCCESS = 201;
	public final static int NO_CONTENT = 204;
	public final static int BAD_REQUEST = 400;
	public final static int UNAUTHORIZED = 401;
	public final static int FORBIDDEN = 403;
	public final static int NOT_FOUND = 404;
	public final static int NOT_ACCEPTABLE = 406;
	public final static int UNSUPPORTED_MEDIA_TYPE = 415;
	public final static int UNPROCESSABLE_ENTITY = 422;
	public final static int UNIQUE_KEY_CONSTRAINT_VIOLATION = 409;
	public final static int MULTIPLE_RECORDS_FOUND = 409;
	public final static int SERVER_ERROR = 500;
	public final static int SERVER_ERROR_DUPLICATE_KEY = 501;
	public final static int SERVER_ERROR_REFERENCE_KEY = 502;
	public final static int SERVER_ERROR_LockTimeout_KEY = 503;
	public final static int SERVER_ERROR_NonUniqueResult_KEY = 504;
	public final static int SERVER_ERROR_NoResult_KEY = 505;
	public final static int SERVER_ERROR_OptimisticLock_KEY = 506;
	public final static int SERVER_ERROR_Persistence_KEY = 507;
	public final static int SERVER_ERROR_QueryTimeout_KEY = 508;
	public final static int SERVER_ERROR_Rollback_KEY = 509;
	public final static int SERVER_ERROR_TransactionRequired_KEY = 510;
	public final static int SERVER_ERROR_Jdbc_KEY = 511;
	public final static int SERVER_ERROR_ConstraintViolation_KEY = 512;
	public final static int SERVER_ERROR_Data_KEY = 513;
	public final static int SERVER_ERROR_JDBCConnection_KEY = 514;
	public final static int SERVER_ERROR_LockAcquisition_KEY = 515;
	public final static int SERVER_ERROR_SQLGrammar_KEY = 517;
	public final static int SERVER_ERROR_Annotation_KEY = 518;
	public final static int SERVER_ERROR_AssertionFailure_KEY = 519;
	public final static int SERVER_ERROR_Callback_KEY = 520;
	public final static int SERVER_ERROR_DuplicateMapping_KEY = 521;
	public final static int SERVER_ERROR_Instantiation_KEY = 522;
	public final static int SERVER_ERROR_InvalidMapping_KEY = 523;
	public final static int SERVER_ERROR_LazyInitialization_KEY = 524;
	public final static int SERVER_ERROR_NonUniqueObject_KEY = 525;
	public final static int SERVER_ERROR_ObjectDeleted_KEY = 526;
	public final static int SERVER_ERROR_ObjectNotFound_KEY = 527;
	public final static int SERVER_ERROR_PersistentObject_KEY = 528;
	public final static int SERVER_ERROR_PessimisticLock_KEY = 529;
	public final static int SERVER_ERROR_PropertyAccess_KEY = 530;
	public final static int SERVER_ERROR_PropertyNotFound_KEY = 531;
	public final static int SERVER_ERROR_PropertyValue_KEY = 532;
	public final static int SERVER_ERROR_Query_KEY = 533;
	public final static int SERVER_ERROR_QueryParameter_KEY = 534;
	public final static int SERVER_ERROR_Session_KEY = 535;
	public final static int SERVER_ERROR_StaleObjectState_KEY = 536;
	public final static int SERVER_ERROR_Transaction_KEY = 537;
	public final static int SERVER_ERROR_TransientObject_KEY = 538;
	public final static int SERVER_ERROR_TypeMismatch_KEY = 539;
	public final static int SERVER_ERROR_UnresolvableObject_KEY = 540;
	public final static int SERVER_ERROR_WrongClass_KEY = 541;
	public final static int SERVER_ERROR_Sqltimeout_KEY = 542;
	public final static int REQUEST_TIMEOUT = 408;
	public final static int DUPLICATE_RECORD_ERROR = 490;

	public CheckListAppException(Exception e) {
		super(e.getMessage());
	}

	public CheckListAppException(String message) {
		super(message);
	}

	public CheckListAppException(int code, String message) {
		super(message);
		this.code = code;
	}

	public CheckListAppException(int code, String message, String developerMessage) {
		super(message);
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public CheckListAppException(int code, String message, String messageDescription, int errorCodeValue) {
		super(message);
		this.code = code;
		this.developerMessage = messageDescription;
		this.errorCodeValue = errorCodeValue;
	}
}
