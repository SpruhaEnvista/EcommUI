package com.envista.msi.api.web.rest;

import com.envista.msi.api.dao.NoAppliedFilterFoundException;
import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.errors.InvalidUserException;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.response.ErrorResponse;
import com.envista.msi.api.web.rest.util.WebConstants;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * Created by Sujit kumar on 31/01/2017.
 */
public class DashboardBaseController {

    @Inject
    private UserService userService;

    @Autowired
    HttpSession session;

    protected UserProfileDto getUserProfile(){
        UserProfileDto user = null;
        try {
            user = userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin());
            if(null == user){
                throw new InvalidUserException(WebConstants.ResponseMessage.INVALID_USER);
            }
        } catch (Exception e) {
            throw new InvalidUserException(WebConstants.ResponseMessage.INVALID_USER);
        }
        return user;
    }

    protected DashboardsFilterCriteria populateDashboardFilterCriteria(DashboardAppliedFilterDto dashboardAppliedFilterDto){
        return DashboardUtil.prepareDashboardFilterCriteria(dashboardAppliedFilterDto);
    }

    public ResponseEntity<CommonResponse> prepareCommonResponseEntity(Object data){
        CommonResponse response = new CommonResponse();
        response.setData(data != null ? data : "{}");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler({JSONException.class, InvalidUserException.class, NoAppliedFilterFoundException.class, Exception.class})
    public ResponseEntity<ErrorResponse> commonExceptionHandler(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(null == e.getMessage() ? "Internal Server Error" : e.getMessage());
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(errorResponse);

        if(e instanceof NoAppliedFilterFoundException){
            errorResponse.setStatusCode(HttpStatus.OK.value());
            errorResponse.setMessage(null == e.getMessage() ? "Applied filter not found" : e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }else if(e instanceof InvalidUserException){
            errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            errorResponse.setMessage(null == e.getMessage() ? "Invalid User" : e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }else if(e instanceof JSONException){
            errorResponse.setStatusCode(HttpStatus.OK.value());
            errorResponse.setMessage(null == e.getMessage() ? "Error while parsing json" : e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
        return responseEntity;
    }
}
