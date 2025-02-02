package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * /kr/or/ddit/auth/SecuredResource.properties 데이터 자체를 사용할경우는 Properties 사용 메시지는
 * MessageBundle 보호 자원에 대한 요청을 확인하고, 해당 요청의 인증 여부를 결정하기 위한 필터 인증 여 : 인증된 요청을 해당
 * 자원으로 제어권을 이동 시킴. 인증 부 : 인증이 안된 요청인 경우, loginForm 으로 제어권을 이동 시킴.
 * 
 */
@Slf4j
public class AuthenticationCheckFilter implements Filter {
	private Map<String, String[]> securedResources;
	private MemberService service = new MemberServiceImpl();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String securedResourcesPath = "/kr/or/ddit/auth/SecuredResource.properties";
		Properties resourceProps = new Properties();
		securedResources = new LinkedHashMap<>();
		try (InputStream is = this.getClass().getResourceAsStream(securedResourcesPath);) {
			resourceProps.load(is);
			resourceProps.forEach((k, v) -> {
				String url = k.toString().trim();
				String[] tmpArray = v.toString().trim().split("\\s*,\\s*");
				Arrays.sort(tmpArray);
				securedResources.put(url, tmpArray);
				log.info("보호자원정보로딩 - {} : {}", url, tmpArray);
			});
			filterConfig.getServletContext().setAttribute("securedResources", securedResources);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI().substring(req.getContextPath().length());
		boolean pass = false;
		boolean secured = securedResources.containsKey(uri);
		// 1. 요청의 보호자원 여, 부 (아닌경우 그냥 통과) /secured
		if (secured) {
			// 1-1. 여 (보호 자원에 대한 요청)
			// 2. 보호자원 여 인 경우 : 인증 여, 부 인지 ( 여 일 경우 통과 ) /pass
//			boolean authenticated = req.getSession().getAttribute("authMember") != null;
			boolean authenticated = req.getUserPrincipal() != null;

			if (authenticated) {
				// 2-1. 여 : 통과 ( pass if 문에서 chain.doFilter로 이동 )
				pass = true;
			} else {
				// 2-2. 부 : 접근 통제 ( pass if 문에서 else로 이동 )
				pass = false;
			}
		} else {
			// 1-2. 부 (비 보호 자원에 대한 요청) : 통과
			pass = true;
		}
		if (pass) {
			// 통과시켜도 되는 요청인 경우
			chain.doFilter(request, response);
		} else {
			// 2-2. 부 : 접근 통제
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
