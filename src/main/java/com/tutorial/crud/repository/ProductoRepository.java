package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Producto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    @Query(
        value = "SELECT * FROM auth.producto WHERE producto.nombre LIKE %:slug% OR producto.precio LIKE %:slug% OR producto.coordenadas LIKE %:slug%",
        countQuery = "SELECT count(*) FROM auth.producto",
        nativeQuery = true
    )
	Page<Producto> findPostBySlugNative( @Param("slug") String slug, Pageable pageable);
}
