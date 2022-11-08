package com.registro.consulta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.registro.consulta.model.ChaveRegistroConsulta;
import com.registro.consulta.model.RegistroConsulta;
import com.registro.consulta.repository.RegistroConsultaRepository;

@Controller
public class RegistroConsultaController {

	private static final int RETORNADA = 1;
	private static final int CONSULTA = 0;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private RegistroConsultaRepository rcp;

	@RequestMapping(value = "/consulta", method = RequestMethod.GET)
	public String form() {
		return "consulta/formConsulta";
	}

	@RequestMapping(value = "/consulta", method = RequestMethod.POST)
	public String form(@Valid RegistroConsulta registroConsulta, ServletRequest request, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos validos!");
			return "redirect:/consulta";
		} 
		String chaves = request.getParameter("chave");

		List<String> chavesRetornadas = new ArrayList<String>();
		chavesRetornadas.add("123");
		chavesRetornadas.add("456");
		chavesRetornadas.add("789");

		List<ChaveRegistroConsulta> chavesConsulta = chavesConsulta(registroConsulta, chaves, chavesRetornadas);

		registroConsulta.setChavesConsulta(chavesConsulta);
		rcp.save(registroConsulta);

		return "redirect:/consulta";
	}

	public List<ChaveRegistroConsulta> chavesConsulta(RegistroConsulta registroConsulta, String chavesConsultadas,
			List<String> chavesRetornadas) {

		List<ChaveRegistroConsulta> chavesConsulta = new ArrayList<ChaveRegistroConsulta>();

		// Registros de chaves consultadas
		if (chavesConsultadas != null) {

			for (String chk : chavesConsultadas.split("\\r\\n")) {
				if (chk != null) {
					ChaveRegistroConsulta chaveRegistroConsulta = new ChaveRegistroConsulta();
					chaveRegistroConsulta.setIdentificadorChave(CONSULTA);
					chaveRegistroConsulta.setChave(chk);
					chaveRegistroConsulta.setRegistroConsulta(registroConsulta);
					chavesConsulta.add(chaveRegistroConsulta);

				}
			}
		}

		// Registros de chaves retornadas
		for (String chk : chavesRetornadas) {
			if (chk != null) {
				ChaveRegistroConsulta chaveRegistroConsulta = new ChaveRegistroConsulta();
				chaveRegistroConsulta.setIdentificadorChave(RETORNADA);
				chaveRegistroConsulta.setChave(chk);
				chaveRegistroConsulta.setRegistroConsulta(registroConsulta);
				chavesConsulta.add(chaveRegistroConsulta);
			}
		}
		return chavesConsulta;

	}

	// Metodo para listar as consultas realizadas
	@RequestMapping(value = "/consultas") // Quando o usuário digitar localhos:8080/consultas irá chamar esse metodo
	public ModelAndView listarConsultas() {
		ModelAndView mv = new ModelAndView("index"); // a pagina index será a página que será reindexado as consultas
														// cadastradas
		Iterable<RegistroConsulta> consultas = rcp.findAll();

		// Mostrar a lista de consultas na view, o "consultas é a mesma declarada na div
		// th:each" na página index
		mv.addObject("consultas", consultas);
		return mv;

	}

	@RequestMapping(value = "{sequencial}")
	public ModelAndView detalhesConsulta(@PathVariable("sequencial") long sequencial) {
		RegistroConsulta consulta = rcp.findBySequencial(sequencial);

		ModelAndView mv = new ModelAndView("consulta/detalheConsulta"); // a pagina detalheConsulta será a página que será
																// mostrado as consultas
		// cadastradas detalhadas

		mv.addObject("consulta", consulta);
		return mv;

	}

}
