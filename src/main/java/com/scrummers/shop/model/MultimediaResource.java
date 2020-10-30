package com.scrummers.shop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "multimedia_resources")
public class MultimediaResource implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;
	private String url;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PRODUCT")
	private Product product;
	private static final long serialVersionUID = 1L;

}
