package com.gestionabonnements.service;

import com.gestionabonnements.dao.AbonnementDAO;
import com.gestionabonnements.entity.Abonnement;
import com.gestionabonnements.entity.Paiement;
import com.gestionabonnements.entity.StatutAbonnement;
import com.gestionabonnements.dao.PaiementDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AbonnementService {

    private AbonnementDAO abonnementDAO = new AbonnementDAO();
    private PaiementDAO paiementDAO = new PaiementDAO();

    public void create(Abonnement abonnement) {
        abonnementDAO.create(abonnement);
    }

    public Optional<Abonnement> findById(String id) {
        return abonnementDAO.findById(id);
    }
    public List<Abonnement> findAll() {
        return abonnementDAO.findAll();
    }
    public void update(Abonnement abonnement) {
        abonnementDAO.update(abonnement);
    }

    public void delete(String id) {
        abonnementDAO.delete(id);
    }

    public List<Abonnement> findActiveSubscriptions() {
        return abonnementDAO.findActiveSubscriptions();
    }

    public List<Abonnement> findByType(String type) {
        return abonnementDAO.findByType(type);
    }
    //cherche l’abonnement, change le statut en RESILIE, puis sauvegarde
    public void resilier(String id) {
        Optional<Abonnement> opt = abonnementDAO.findById(id);
        if (opt.isPresent()) {
             Abonnement abonnement = opt.get();
            abonnement.setStatut(StatutAbonnement.RESILIE);
            abonnementDAO.update(abonnement);
        }
    }

    public void genererEcheances(Abonnement abonnement) {
        LocalDate date = abonnement.getDateDebut();
        LocalDate fin = abonnement.getDateFin();

        while (!date.isAfter(fin)) {
            Paiement paiement = new Paiement(abonnement.getId(), date, "Carte");
            paiementDAO.create(paiement);
            date = date.plusMonths(1);
        }
    }


}