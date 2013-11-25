package in.v8delta.web.filter;

import in.v8delta.template.myWebAppTmpl.core.log.LoggerAgent;
<<<<<<< HEAD
import in.v8delta.template.myWebAppTmpl.core.utils.AppConstants;
import in.v8delta.template.myWebAppTmpl.core.utils.LogUtil;
import in.v8delta.web.controller.async.AppConfigControllerAsync;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Base Filter for All the Application Specific Filters
 * 
 * @author v8-suresh
 * 
 */
public abstract class BaseFilter implements Filter {

	private final static LoggerAgent LOGGER = LogUtil.getAppLogger(BaseFilter.class);

	private FilterConfig filterConfig;

	private boolean enabled;
	private boolean preventException;

	private boolean processRequest;
	private boolean processResponse;
	
	private Set<Pattern> validUrlPatterns;
	

	
	protected BaseFilter() {
		this.validUrlPatterns = new HashSet<Pattern>();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("Filter Initialized");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean isValidUrl = false;
		boolean errorInReqProcess = false;
		Throwable ex = null;
		
		if((this.validUrlPatterns == null) || (this.validUrlPatterns.size() == 0)){
			isValidUrl = true;
		} else {
			for(Pattern pattern : this.validUrlPatterns){
				if(pattern.matcher(((HttpServletRequest) request).getServletPath()).matches()){
					isValidUrl = true;
					break;
				}
			}
		}
		
		
		try {
			if((this.enabled) && (isValidUrl) && (this.processRequest)){
				errorInReqProcess = processRequest((HttpServletRequest) request, (HttpServletResponse) response);
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			ex = e;
		} finally {
			if((this.enabled) && (isValidUrl) && (! errorInReqProcess)){
				if(ex != null){
					handleException((HttpServletRequest) request, (HttpServletResponse) response, ex);
				} else if(this.processResponse){
					processResponse((HttpServletRequest)request, (HttpServletResponse)response);
				}
			}
		}
		
		
	}

	@Override
	public void destroy() {
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("Filter Destroyed");
		}

	}

	/**
	 * Protected process request method which will be handled in their
	 * subclasses
	 * 
	 * @param req
	 * @return - request processing is successful or not
	 */
	protected abstract boolean processRequest(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * Protected process response method which will be handled in their
	 * subclasses
	 * 
	 * @param resp
	 * @return - response processing is successful or not
	 */
	protected abstract boolean processResponse(HttpServletRequest req, HttpServletResponse resp);


	/**
	 * Method to Handle Exception in Filters
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException 
	 */
	protected void handleException(HttpServletRequest req, HttpServletResponse resp, Throwable ex) throws ServletException{
		if(! this.preventException){
			throw new ServletException(ex);
		} else {
			if (getLogger().isDebugEnabled()) {
				getLogger().debug("Exception Suppressed", ex);
			}
		}
	}
	
	
	/**
	 * @return the filterConfig
	 */
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**
	 * @param filterConfig
	 *            the filterConfig to set
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * @return the ignorePatterns
	 */
	public Set<Pattern> getValidUrlPatterns() {
		return validUrlPatterns;
	}

	/**
	 * @param ignorePatterns
	 *            the ignorePatterns to set
	 */
	public void setValidUrlPatterns(String patternStr) {
		if (patternStr != null) {
			String[] patterns = patternStr.split(AppConstants.STR_COMMA);
			for (String pattern : patterns) {
				try {
					this.validUrlPatterns.add(java.util.regex.Pattern.compile(pattern));
				} catch (Exception e) {
					if(getLogger().isWarnEnabled()){
						getLogger().warn("Unable to Compile Pattern : " + pattern);
					}
				}
			}
			if(getLogger().isDebugEnabled()){
				getLogger().debug("Patterns Added for ignore" + this.validUrlPatterns);
			}
		}
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the preventException
	 */
	public boolean isPreventException() {
		return preventException;
	}

	/**
	 * @param preventException
	 *            the preventException to set
	 */
	public void setPreventException(boolean preventException) {
		this.preventException = preventException;
	}

	/**
	 * @return the processRequest
	 */
	public boolean isProcessRequest() {
		return processRequest;
	}

	/**
	 * @param processRequest the processRequest to set
	 */
	public void setProcessRequest(boolean processRequest) {
		this.processRequest = processRequest;
	}

	/**
	 * @return the processResponse
	 */
	public boolean isProcessResponse() {
		return processResponse;
	}

	/**
	 * @param processResponse the processResponse to set
	 */
	public void setProcessResponse(boolean processResponse) {
		this.processResponse = processResponse;
	}

	protected LoggerAgent getLogger() {
		return LOGGER;
	}

=======
import in.v8delta.template.myWebAppTmpl.core.utils.LogUtil;
import in.v8delta.web.controller.async.AppConfigControllerAsync;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * Base Filter for All the Application Specific Filters
 * 
 * @author v8-suresh
 *
 */
public class BaseFilter implements Filter{

	private final static LoggerAgent LOGGER   = LogUtil.getAppLogger(BaseFilter.class);
	
	private boolean isEnabled;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(getLogger().isDebugEnabled()){
			getLogger().debug("");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
	}

	@Override
	public void destroy() {
		if(getLogger().isDebugEnabled()){
			getLogger().debug("");
		}
		
	}
	
	protected LoggerAgent getLogger(){
		return LOGGER;
	}
	
>>>>>>> refs/remotes/origin/master
}
