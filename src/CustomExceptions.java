
public enum CustomExceptions {
	//Exceptions with codes and messages
	FileNotFound(1, "Файл не найден."),
	FileAlreadyExists(2, "Данный файл уже существует."),
	FileTypeMismatch(3, "Несоответствие типа файла."),
	FileExtensionNotSupported(4, "Данное расширение файла не поддерживается."),
	IOFailed(5, "Ошибка чтения/записи."),
	DictPairNotRead(6, "Не удалось прочитать элемент словаря."),
	KeyNotFound(7, "Ключ отсутствует в словаре."),
	InvalidPair(8, "Ключ/значение не соответствует ограничениям."),
	InvalidContent(9, "Словарь содержит некоррекные элементы.")
	;
	
	private int exceptionCode;
	private String exceptionMessage;
	private CustomExceptions(int code, String message) {
		exceptionCode = code;
		exceptionMessage = message;
	}
	public int getCode() {
		return exceptionCode;
	}
	public String getMessage(){
		return exceptionMessage;
	}
	public void throwEx() throws CustomException {
		throw new CustomException(exceptionCode, exceptionMessage);
	}
}
