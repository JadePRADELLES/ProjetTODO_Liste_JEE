package com.pradelles.todoliste.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tache")
public class Tache {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;	
	private String description;
	private Boolean etat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_derniere_modif;
	private String url_img;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_prevu_fin;	
	@ManyToOne 
    @JoinColumn( name="id_utilisateur", nullable=false )
	private Utilisateur utilisateur ;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public Date getDate_derniere_modif() {
		return date_derniere_modif;
	}
	public void setDate_derniere_modif(Date date_derniere_modif) {
		this.date_derniere_modif = date_derniere_modif;
	}
	public String getUrl_img() {
		return url_img;
	}
	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}
	public Date getDate_prevu_fin() {
		return date_prevu_fin;
	}
	public void setDate_prevu_fin(Date date_prevu_fin) {
		this.date_prevu_fin = date_prevu_fin;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
