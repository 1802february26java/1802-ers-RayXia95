package com.revature.request;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.ErrorControllerAlpha;
import com.revature.controller.HomeControllerAlpha;
import com.revature.controller.LoginControllerAlpha;
import com.revature.controller.ReimbursementControllerAlpha;

/**
 * The RequestHelper class is consulted by the MasterServlet and provides
 * him with a view URL or actual data that needs to be transferred to the
 * client.
 * 
 * It will execute a controller method depending on the requested URI.
 * 
 * Recommended to change this logic to consume a ControllerFactory.
 * 
 * @author Revature LLC
 */
public class RequestHelper
{
    private static RequestHelper requestHelper;

    private RequestHelper()
    {
    }

    public static RequestHelper getRequestHelper()
    {
	if ( requestHelper == null )
	{
	    return new RequestHelper();
	}
	else
	{
	    return requestHelper;
	}
    }

    /**
     * Checks the URI within the request object passed by the MasterServlet
     * and executes the right controller with a switch statement.
     * 
     * @param request
     * 		  The request object which contains the solicited URI.
     * @return A String containing the URI where the user should be
     * forwarded, or data (any object) for AJAX requests.
     */
    public Object process(HttpServletRequest request)
    {
	switch (request.getRequestURI())
	{
	    case "/ERS/login.do":
		return LoginControllerAlpha.getLoginController().login(request);
	    case "/ERS/home.do":
		return HomeControllerAlpha.getHomeController().showEmployeeHome(request);
	    case "/ERS/request.do":
		return ReimbursementControllerAlpha.getReimbursementController().submitRequest(request);
	    case "/ERS/logout.do":
		return LoginControllerAlpha.getLoginController().logout(request);
	    default:
		return new ErrorControllerAlpha().showError(request);
	}
    }
}
