package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PostTiempoTranscurrioFilter extends ZuulFilter {

	/**
	 * Si este metodo regresa true el metodo run se ejecuta
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * Aqui es donde se lleva a cabo la lógica de lo que hará este filtro
	 */
	@Override
	public Object run() throws ZuulException {
		/**
		 * Asi se obtiene el request con zuul
		 */
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		log.info("entrando a post filter");
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		log.info("El tiempo transcurrido en milisegundos es {}", tiempoTranscurrido);
		log.info("El tiempo transcurrido en segundos es {}", tiempoTranscurrido.doubleValue() / 1000.00);
		return null;
	}

	/**
	 * Aqui indicas el tipo del filtro en este caso 'pre'
	 */
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
