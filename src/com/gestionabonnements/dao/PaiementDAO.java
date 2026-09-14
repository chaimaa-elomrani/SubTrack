package com.gestionabonnements.dao;

import com.gestionabonnements.entity.Paiement;
import com.gestionabonnements.entity.StatutPaiement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

public class PaiementDAO {

    private List<Paiement> paiements = new ArrayList<>();


    public void create(Paiement paiement) {
        paiements.add(paiement);
    }

    public Optional<Paiement> findById(String idPaiement) {
        return paiements.stream()
                .filter(p -> p.getIdPaiement().equals(idPaiement))
                .findFirst();
    }

    public List<Paiement> findAll() {
        return new ArrayList<>(paiements);
    }

    public void update(Paiement paiement) {
        for (int i = 0; i < paiements.size(); i++) {
            if (paiements.get(i).getIdPaiement().equals(paiement.getIdPaiement())) {
                paiements.set(i, paiement);
                return;
            }
        }
    }

    public void delete(String idPaiement) {
        paiements.removeIf(p -> p.getIdPaiement().equals(idPaiement));
    }

    public List<Paiement> findByAbonnement(String idAbonnement){
        return paiements.stream().filter(p->p.getIdAbonnement().equals(idAbonnement))
                .collect(java.util.stream.Collectors.toList());

    }

    public List<Paiement> getUnpaidByAbonnement(String idAbonnement){
        return paiements.stream().filter(p -> p.getIdAbonnement().equals(idAbonnement))
                .filter(p -> p.getStatut() == StatutPaiement.EN_RETARD)
                .collect(java.util.stream.Collectors.toList());

    }


    public List<Paiement> findLastPayments() {
        return paiements.stream().sorted((p1, p2) -> p2.getDateEcheance().compareTo(p1.getDateEcheance()))
                .limit(5)
                .collect(java.util.stream.Collectors.toList());
    }
}