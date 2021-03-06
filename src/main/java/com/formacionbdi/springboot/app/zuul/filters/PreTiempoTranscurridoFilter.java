package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * Se configura un filtro para el gateway del zuul. Hay tres tipos 'pre', 'post'
 * y 'resolve'
 * 
 * Se heread de zuulfilter para que automaticamente spring lo registre como un
 * filtro de zuul.
 *
 */
@Component
@Slf4j
public class PreTiempoTranscurridoFilter extends ZuulFilter {

	/**
	 * Si este metodo regresa true el metodo run se ejecuta, se puede hacer una validación cualquiera.
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * Aqui es donde se lleva a cabo la lógica de lo que hará este filtro.
	 */
	@Override
	public Object run() throws ZuulException {
		/**
		 * Asi se obtiene el request con zuul
		 */
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		log.info("{} request enrutado a {}", request.getMethod(), request.getRequestURI());
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		return null;
	}

	/**
	 * Aqui indicas el tipo del filtro en este caso 'pre'
	 * 
	 * existen tres tipos
	 * - pre: antes de que se resuleva la ruta (antes de mandar al microservicio)
	 * - post: despues de que se resuelva la ruta (cuando el miscroservicio termine)
	 * - route: ya cuando se manda al servicio
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
