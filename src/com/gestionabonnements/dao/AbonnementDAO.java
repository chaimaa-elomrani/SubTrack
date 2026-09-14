package com.gestionabonnements.dao;

import com.gestionabonnements.entity.Abonnement;
import com.gestionabonnements.entity.AbonnementAvecEngagement;
import com.gestionabonnements.entity.AbonnementSansEngagement;
import com.gestionabonnements.entity.StatutAbonnement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbonnementDAO {

    private List<Abonnement> abonnements = new ArrayList<>();

    public void create(Abonnement abonnement) {
        abonnements.add(abonnement);
    }

    public Optional<Abonnement> findById(String id) {
        return abonnements.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public List<Abonnement> findAll() {
        return new ArrayList<>(abonnements);
    }

    public void update(Abonnement abonnement) {
       for(int i =0 ; i<abonnements.size(); i++){
           if(abonnements.get(i).getId().equals(abonnement.getId())){
               abonnements.set(i, abonnement);
               return;
           }

       }
    }

    public void delete(String id){
        abonnements.removeIf(a-> a.getId().equals(id));
    }

    public List<Abonnement> findActiveSubscriptions(){
        return abonnements.stream().filter(a -> a.getStatut() == StatutAbonnement.ACTIVE)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Abonnement> findByType(String type){
        return abonnements.stream().filter(a -> {
            if(type.equalsIgnoreCase("avec")){
                return a instanceof AbonnementAvecEngagement;
            } else if (type.equalsIgnoreCase("sans")) {
                return a instanceof AbonnementSansEngagement;
            }
            return false ;
        }).collect(java.util.stream.Collectors.toList());
    }






}