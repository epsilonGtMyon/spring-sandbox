package epsilongtmyon.lib.txtoken.logic;

import org.springframework.web.method.HandlerMethod;

import epsilongtmyon.lib.txtoken.annotation.TxToken.TxTokenAction;
import jakarta.servlet.http.HttpServletRequest;

public interface TxTokenService {

	String findAndRemoveToken(HttpServletRequest request, HandlerMethod handlerMethod, TxTokenAction action);

	String storeToken(HttpServletRequest request, HandlerMethod handlerMethod, TxTokenAction action);

}
