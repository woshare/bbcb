package com.example.bbcb.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Cookie工具
 *cookie知识需要补充：1，需要域名才可以用cookie
 */
public class CookieUtil {

    /**
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies=request.getCookies();
        if(cookies==null || StringUtils.isEmpty(cookieName)){
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookieName.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     *
     * @param response
     * @param name
     * @param path
     * @param domain
     */
    public static void removeCookie(HttpServletResponse response,String name,String path,String domain){
        Cookie cookie=new Cookie(name,null);
        if(path!=null){
            cookie.setPath(path);
        }
        if(domain!=null){
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(-1000);
        response.addCookie(cookie);
    }

    /**
     *
     * @param response
     * @param cookieName
     * @param cookieVal
     * @param httpType
     */
    public static void setCookie(HttpServletResponse response,String cookieName,String cookieVal,String httpType){
        Cookie cookie=new Cookie(cookieName,cookieVal);
        cookie.setPath("/");
        if(httpType.equals("https")){
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

}
