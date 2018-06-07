package cn.mh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/pages/front/member/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session=((HttpServletRequest)servletRequest).getSession();
        if(session.getAttribute("user")!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            servletRequest.setAttribute("msg", "您还未登录，请先登录！");
            servletRequest.setAttribute("url", "/login.jsp");
            servletRequest.getRequestDispatcher("/WEB-INF/pages/forward.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
