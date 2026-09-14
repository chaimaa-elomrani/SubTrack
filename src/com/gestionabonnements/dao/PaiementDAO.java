package com.gestionabonnements.dao;

import com.gestionabonnements.entity.Paiement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


}