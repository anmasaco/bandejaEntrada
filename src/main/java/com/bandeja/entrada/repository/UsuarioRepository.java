package com.bandeja.entrada.repository;
import com.bandeja.entrada.entities.GdoTraTareaDocView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<GdoTraTareaDocView, Long> {

}