package es.pruebas.springmvc.web.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller general del que heredan el resto de controllers.
 * @author evazquezma
 *
 */
public abstract class AbstractBaseController {
	private final Logger logger = LoggerFactory.getLogger(getClass());




	/**
	 * Método para devolver una excepción
	 * @param model
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleGenericRuntimeException(HttpServletRequest request, RuntimeException ex){
		logger.error("Lanza una excepción runtime", ex);
		generaLogRequest(request);

		return new ModelAndView("excepciones/genericExceptionPage");
	}


	/**
	 * Método para devolver una excepción
	 * @param model
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(HttpServletRequest request, Exception ex){
		logger.error("Lanza una excepción", ex);
		generaLogRequest(request);

		return new ModelAndView("excepciones/genericExceptionPage");
	}





	/**
	 * Pinta la informaciónd e la request en el log
	 * @param request
	 */
	private void generaLogRequest(HttpServletRequest request){
		String s;

		logger.error("\n-------------------------------\nInformación de los atributos de la request");
		@SuppressWarnings("rawtypes")
		Enumeration e=	 request.getAttributeNames();
		while (e.hasMoreElements()) {
			s =(String)e.nextElement();
			logger.error("Atributo: '{}' \tValor: '{}'", s, request.getAttribute(s));
		}


		logger.error("\n-------------------------------\nInformación de los parámetros de la request");
		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			s =(String)e.nextElement();
			logger.error("Parámetro: '{}' \tValor: '{}'", s, request.getParameter(s));
		}


		logger.error("\n-------------------------------\nInformación de los parámetros de sesión");
		if (request.getSession() != null){
			e = request.getSession().getAttributeNames();
			while (e.hasMoreElements()) {
				s =(String)e.nextElement();
				logger.error("Atributo sesión: '{}' \tValor: '{}' \tTipo: '{}'",
						s, request.getSession().getAttribute(s), request.getSession().getAttribute(s).getClass());
			}
		}
		else {
			logger.error("No se dispone de sessión en la request");
		}

		logger.error("Fin de la información de la request");
	}

}
