package com.br.aircraft.api.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_aeronave")
public class Aeronave implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	
	private String marca;
	
	private Integer ano;
	
	@Column(columnDefinition = "text")  
	private String descricao; 
	 
	private Boolean vendido; 
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	private OffsetDateTime created;
	
	@UpdateTimestamp 
	@Column(nullable = false, columnDefinition = "timestamp")
	private OffsetDateTime updated;
	
}
