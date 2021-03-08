package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XERES_RECSYS")
@Data
public class ResourcesOfSystem   implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_rec_sis")
	private Long id;
	
	@Column(  name = "fec_asignacion_sis_rec",  nullable = false)
	private LocalDateTime assignmentDate;

	@Column( name = "fec_modificacion_perfil")
	private LocalDateTime modifiedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_sistema",nullable = false)
	@JsonIgnore
	private SystemApp system;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cod_recurso",nullable = false)
	@JsonIgnore
	private Resource resource;
}
