/**
 * 
 */
package com.envista.msi.api.web.rest.dto;

/**
 * @author SANKER
 *
 */
public class ApiException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;

	public ApiException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");
		sb.append("\"code\":");
		sb.append("\" " + code + " \",");

		sb.append("\"message:\"");
		sb.append("\" " + this.getMessage() + " \"");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	public String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
