package RestFullApplication.controller;

import RestFullApplication.dto.Soldado;
import RestFullApplication.request.SoldadoEditRequest;
import RestFullApplication.service.SoldadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {

    private SoldadoService soldadoService;

    public SoldadoController(SoldadoService soldadoService) {
        this.soldadoService = soldadoService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Soldado> responseEntity(@PathVariable() String cpf) {
        Soldado soldado = soldadoService.buscarSoldado(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(soldado);
    }

    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody Soldado soldado) {
        soldadoService.criarSoldado(soldado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{cpf}")
    public ResponseEntity editarSoldado(@PathVariable() String cpf, @RequestBody SoldadoEditRequest soldadoEditRequest){
        soldadoEditRequest.alterarSoldado(cpf, soldadoEditRequest);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{cpf}")
    public ResponseEntity deleteSoldado(@PathVariable String cpf){
        soldadoService.deletarSoldado(cpf);
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    public ResponseEntity<List<Soldado>> responseEntity() {
        List<Soldado> soldados = soldadoService.todosSoldados();
        return ResponseEntity.status(HttpStatus.OK).body(soldados);
    }
}
