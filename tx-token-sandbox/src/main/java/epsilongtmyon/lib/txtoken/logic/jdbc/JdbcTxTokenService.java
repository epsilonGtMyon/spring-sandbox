package epsilongtmyon.lib.txtoken.logic.jdbc;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;

import epsilongtmyon.lib.txtoken.TxTokenAttribute;
import epsilongtmyon.lib.txtoken.annotation.TxToken.TxTokenAction;
import epsilongtmyon.lib.txtoken.generator.TxTokenGenerator;
import epsilongtmyon.lib.txtoken.logic.TxTokenService;
import epsilongtmyon.lib.txtoken.logic.jdbc.repository.JdbcTxTokenRepository;
import epsilongtmyon.lib.txtoken.logic.jdbc.repository.TxTokenEntity;
import epsilongtmyon.lib.window.WindowIdResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class JdbcTxTokenService implements TxTokenService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final JdbcTxTokenRepository repository;

	private final TxTokenGenerator tokenGenerator;

	private final WindowIdResolver windowIdResolver;

	public JdbcTxTokenService(
			JdbcTxTokenRepository repository,
			TxTokenGenerator tokenGenerator,
			WindowIdResolver windowIdResolver) {
		super();
		this.repository = repository;
		this.tokenGenerator = tokenGenerator;
		this.windowIdResolver = windowIdResolver;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String findAndRemoveToken(HttpServletRequest request, HandlerMethod handlerMethod, TxTokenAction action) {
		final String clientUniqueId = resolveClientUniqueId(request);
		final String windowId = windowIdResolver.fromRequest(request);

		final TxTokenEntity tokenEntity = repository.findToken(clientUniqueId, windowId);
		if (tokenEntity == null) {
			logger.warn("token not found {}", request.getRequestURI());
			return null;
		}

		// 後続に同じトークンのリクエストが来た時に不正と判定したいので、この時点でトークンを消す
		repository.removeToken(clientUniqueId, windowId);

		return tokenEntity.getTokenValue();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String storeToken(HttpServletRequest request, HandlerMethod handlerMethod, TxTokenAction action) {

		final String clientUniqueId = resolveClientUniqueId(request);
		final String generateToken = tokenGenerator.generateToken(request);
		final String windowId = windowIdResolver.fromRequest(request);
		final String sessionId = request.getSession().getId();
		final LocalDateTime now = LocalDateTime.now();

		final TxTokenEntity tokenEntity = new TxTokenEntity();
		tokenEntity.setClientUniqueId(clientUniqueId);
		tokenEntity.setWindowId(windowId);
		tokenEntity.setSessionId(sessionId);
		tokenEntity.setTokenValue(generateToken);
		tokenEntity.setCreatedAt(now);

		if (action == TxTokenAction.PUBLISH) {
			// 既にあるかもしれないので消しておく
			repository.removeToken(clientUniqueId, windowId);
		}

		repository.storeToken(tokenEntity);

		return generateToken;
	}

	//-----

	/**
	 * クライアントユニークIDの解決
	 * 
	 * @param request
	 * @return クライアントユニークID
	 */
	protected String resolveClientUniqueId(HttpServletRequest request) {
		String clientUniqueId = null;
		HttpSession session = request.getSession(true);
		if (session != null) {
			clientUniqueId = (String) session.getAttribute(TxTokenAttribute.CLIENT_UNIQUE_ID_SESSION_ATTRIBUTE_KEY);
			if (clientUniqueId == null) {
				clientUniqueId = session.getId();
				session.setAttribute(TxTokenAttribute.CLIENT_UNIQUE_ID_SESSION_ATTRIBUTE_KEY, clientUniqueId);
			}
		}
		return clientUniqueId;
	}

}
