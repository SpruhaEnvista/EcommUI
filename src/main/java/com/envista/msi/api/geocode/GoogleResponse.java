package com.envista.msi.api.geocode;

/**
 * 
 * 
 * @author Srinivas
 * 
 */
public class GoogleResponse {


  private Result[] results;
  private String status;
  private String error_message;
  private String exclude_from_slo;
  
  public Result[] getResults() {
    return results;
  }

  public void setResults(Result[] results) {
    this.results = results;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getError_message() {
    return error_message;
  }

  public void setError_message(String error_message) {
    this.error_message = error_message;
  }

public String getExclude_from_slo() {
	return exclude_from_slo;
}

public void setExclude_from_slo(String exclude_from_slo) {
	this.exclude_from_slo = exclude_from_slo;
}



}
