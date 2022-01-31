package ept.dic2.crud_person_app.models;

import java.io.Serializable;

public class Person implements Serializable {

    public String email;
    public String clef;
    public String prenom;
    public String nom;
    public String dateNaissance;
    public String dateEnregistrement;
    public String dateModification;

    public Person(String email, String clef, String prenom, String nom, String dateNaissance, String dateEnregistrement, String dateModification) {
        this.email = email;
        this.clef = clef;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.dateEnregistrement = dateEnregistrement;
        this.dateModification = dateModification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public String getDateModification() {
        return dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }
}
