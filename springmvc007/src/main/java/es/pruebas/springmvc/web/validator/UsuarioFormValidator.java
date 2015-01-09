package es.pruebas.springmvc.web.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.web.form.usuario.UsuarioForm;

@Component("usuarioFormValidator")
public class UsuarioFormValidator  implements Validator {
	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	 @Autowired
	 private javax.validation.Validator validator;
	 
	 
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("validando usuarioForm");
		
		UsuarioForm usuarioForm = (UsuarioForm) target;		
		Usuario usuario = usuarioForm.getUsuario();
		
		realizarValidacionesJavax(usuarioForm, errors);		
		realizarValidacionesComplejasNegocio(usuario, errors);		
		
		logger.debug("UsuarioForm valdiado con {} errores detectados", errors.getErrorCount());
	}
	
	
	

	/**
	 * Validaciones generales de javax validation
	 * @param usuarioForm
	 * @param errors
	 */
	private void realizarValidacionesJavax(UsuarioForm usuarioForm, Errors errors) {
		/*
		De esta forma se crearía un validador por código, pero en vez de eso es preferible usar el configurado por spring.
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		*/		
	    Set<ConstraintViolation<UsuarioForm>> constraintViolations = validator.validate(usuarioForm);	 
	    for (ConstraintViolation<UsuarioForm> violation : constraintViolations) {
			errors.rejectValue(violation.getPropertyPath().toString(),
					violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(),
					violation.getConstraintDescriptor().getAttributes().values().toArray(),
					violation.getMessage());
		}
	    
	}
	
	/**
	 * Validaciones más especéficas relacionadas con el negocio o con otros objetos
	 * @param usuario
	 * @param errors
	 */
	private void realizarValidacionesComplejasNegocio(Usuario usuario, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "usuario.tipo.id", "errors.required");
		
		
		ValidationUtils.rejectIfEmpty(errors, "usuario.roles", "errors.required");		
		if (usuario.getRoles() != null && usuario.getRoles().size() > 2) {
			errors.rejectValue("usuario.roles", "usuario.error.maxRoles", "Sólo se permiten dos roles por usuario");
		}						
	}

}
