package com.backend.core.controller;

import com.backend.core.models.entity.Factura;
import com.backend.core.models.entity.Producto;
import com.backend.core.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	private IClienteService clienteService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return clienteService.findFacturaById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.deleteFacturaById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/facturas/filtrar-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> filtrarProductos(@PathVariable String term){
		return clienteService.findProductoByNombre(term);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		return clienteService.saveFactura(factura);
	}

}
