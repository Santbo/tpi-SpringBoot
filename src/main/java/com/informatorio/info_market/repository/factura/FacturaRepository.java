package com.informatorio.info_market.repository.factura;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatorio.info_market.domain.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
    
    List<Factura> findByFechaDeEmisionLessThanEqual (LocalDate fechaHasta);
    List<Factura> findByFechaDeEmisionGreaterThanEqual (LocalDate fechaDesde);
    List<Factura> findByFechaDeEmisionBetween(LocalDate fechaDesde, LocalDate fechaHasta);
}
