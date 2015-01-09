package es.pruebas.springmvc.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.springmvc.dao.RolDao;
import es.pruebas.springmvc.modelo.Rol;
import es.pruebas.springmvc.negocio.service.base.GenericServiceImpl;


@Transactional
@Service("rolService")
public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements RolService{

	@Autowired
	public RolServiceImpl(RolDao rolDao) {
		super(rolDao);
	}

}
